package uk.ac.tees.mad.b1970805.model


import android.app.Activity
import android.content.Context
import android.os.Build
import android.util.Log
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import java.util.concurrent.TimeUnit


data class RegistrationData(
    val  fname: String,
    val lname : String,
    val email: String,
    val phoneNumber: String,
    val password: String,
    val confirmPassword: String

)

class Registration {
    private val auth = FirebaseAuth.getInstance();
    private val firestore = FirebaseFirestore.getInstance()


    suspend fun registerUser(registrationData: RegistrationData): Boolean {
        return try {
            // Create a user in Firebase Authentication
            val result = auth.createUserWithEmailAndPassword(registrationData.email, registrationData.password).await()

            // Update the user's display name
            val profileUpdates = UserProfileChangeRequest.Builder()
                .setDisplayName(registrationData.fname)
                .build()
            result.user?.updateProfile(profileUpdates)?.await()

            // Use Firebase Authentication UID as Firestore document ID
            val userId = result.user?.uid

            // Add user data to Firestore
            userId?.let { uid ->
                val user = hashMapOf(
                    "userId" to uid,
                    "email" to registrationData.email,
                    "fname" to registrationData.fname,
                    "phoneNumber" to registrationData.phoneNumber,
                    "password" to registrationData.password,
                    "balance" to 0.00.toString(),
                    "timestamp" to FieldValue.serverTimestamp()
                )

                val documentReference = firestore.collection("users").document(uid)

                // Check if the document already exists
                if (documentReference.get().await().exists()) {
                    Log.d("Registration", "User document already exists")
                    // Handle the case where the user document already exists
                } else {
                    // Add a new document with the user's UID as ID
                    documentReference.set(user).await()
                    Log.d("Registration", "DocumentSnapshot added with ID: $uid")
                }
            }

            true
        } catch (e: FirebaseAuthException) {
            // Log the Firebase Authentication exception
            Log.e("Registration", "Firebase Authentication Error", e)
            false
        } catch (e: Exception) {
            // Log other exceptions
            Log.e("Registration", "Error registering user", e)
            false
        }
    }

    suspend fun setPin(pin: String): Boolean {
        return try {
            val user = auth.currentUser
            val userData = hashMapOf("pin" to pin)
            firestore.collection("users").document(user?.uid ?: "").update(userData as Map<String, Any>).await()
            true
        } catch (e: FirebaseAuthException) {
            // Log the Firebase Authentication exception
            Log.e("Registration", "Firebase Authentication Error", e)
            false
        } catch (e: Exception) {
            // Log other exceptions
            Log.e("Registration", "Error setting PIN", e)
            false
        }
    }


    // Step 3: Send OTP to the user's phone number
    suspend fun sendOtp(phoneNumber: String, activity: Context) {
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)      // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout duration
            .setActivity(activity as Activity)                // Activity (for callback binding; can be null)
            .setCallbacks(getPhoneAuthCallbacks())
            .build()

        return PhoneAuthProvider.verifyPhoneNumber(options)
    }

    // Step 4: Sign in with phone auth credential
    private suspend fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential).await()
    }

    // Function to get PhoneAuthCallbacks
    private fun getPhoneAuthCallbacks(): PhoneAuthProvider.OnVerificationStateChangedCallbacks {
        return object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                // This callback will be invoked in two situations:
                // 1 - Instant verification. In some cases, the phone number can be instantly verified without needing to send or enter an OTP.
                // 2 - Auto-retrieval. On some devices, Google Play services can automatically detect the incoming verification SMS and perform verification without user action.
                // You can handle these cases in this method.
                Log.d("PhoneAuthCallbacks", "onVerificationCompleted:$credential")
            }

            override fun onVerificationFailed(e: FirebaseException) {
                // This callback is invoked if an invalid request for verification is made, for example, if the phone number format is invalid.
                Log.w("PhoneAuthCallbacks", "onVerificationFailed", e)
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                // The SMS verification code has been sent to the provided phone number, we now need to ask the user to enter the code and then construct a credential by combining the code with a verification ID.
                Log.d("PhoneAuthCallbacks", "onCodeSent:$verificationId")
            }
        }
    }

    private suspend fun getNextUserId(): String {
        return firestore.runTransaction { transaction ->
            val counterDocRef = firestore.collection("users").document("userId")
            val currentCounter = transaction.get(counterDocRef).getLong("value") ?: 0

            // Increment the counter
            transaction.update(counterDocRef, "value", currentCounter + 1)

            // Format the counter as a three-digit string with leading zeros
            "%03d".format(currentCounter)
        }.await()
    }

    private suspend fun incrementUserId() {
        // Increment the user ID counter in Firestore atomically
        val counterDocRef = firestore.collection("counters").document("userId")
        counterDocRef.update("value", FieldValue.increment(1)).await()
    }


    fun getDeviceName(): String {
        return Build.MODEL
    }



}


