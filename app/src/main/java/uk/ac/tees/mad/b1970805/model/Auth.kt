package uk.ac.tees.mad.b1970805.model


import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import java.util.concurrent.TimeUnit

class Auth {
    private val auth : FirebaseAuth = FirebaseAuth.getInstance()
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()


//    suspend fun verifyPhoneNumber(phoneNumber: String): String {
//        // Use Firebase Authentication to send a verification code to the provided phone number
//        val options = PhoneAuthOptions.newBuilder(auth)
//            .setPhoneNumber(phoneNumber)
//            .setTimeout(60L, java.util.concurrent.TimeUnit.SECONDS)
//            .setActivity(activity)
//            .setCallbacks(/* Your PhoneAuthCallbacks here */ null)
//            .build()
//
//        auth.verifyPhoneNumber(options).await()
//
//        // Retrieve and return the verification ID
//        // This ID will be used later during code verification
//        return ""
//    }

    suspend fun verifyPhoneNumberWithCode(verificationId: String, code: String): String {
        // Use Firebase Authentication to verify the code
        val credential = PhoneAuthProvider.getCredential(verificationId, code)
        val result = auth.signInWithCredential(credential).await()

        // Retrieve and return the user's UID
        return result.user?.uid ?: ""
    }


    suspend fun getCurrentUserBalance(): Double {
        // Check if the user is authenticated
        val currentUser = auth.currentUser
        if (currentUser != null) {
            // Fetch the user document from Firestore using the UID
            val userDocument = firestore.collection("users").document(currentUser.uid).get().await()

            // Retrieve the balance field from the user document
            return userDocument.getDouble("balance") ?: 0.0
        } else {
            throw IllegalStateException("User not authenticated.")
        }
    }

}