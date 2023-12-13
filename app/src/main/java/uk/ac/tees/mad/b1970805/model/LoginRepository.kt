package uk.ac.tees.mad.b1970805.model


import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import kotlinx.coroutines.tasks.await

class LoginRepository {

    private val auth = FirebaseAuth.getInstance()

    suspend fun loginUser(email:String,  password: String): Boolean{
        return try {

            auth.signInWithEmailAndPassword(email, password).await()

            val user = auth.currentUser
            if (user != null) {
                // User is signed in
                Log.d("LoginRepository", "User signed in: ${user.email}")
            } else {
                // No user is signed in
                Log.d("LoginRepository", "No user signed in")
            }
            true

        }catch (e: FirebaseAuthException) {
            // Handle reCAPTCHA challenge if required
            if (e.errorCode == "auth/too-many-requests") {

                Log.d("Login", "ReCAPTCHA challenge required")
            } else {

                Log.e("Login", "Firebase Authentication Error", e)
            }
            // Log the Firebase Authentication exception
            Log.e("Registration", "Firebase Authentication Error", e)
            false
        } catch (e: Exception){
            false
        }

    }
}