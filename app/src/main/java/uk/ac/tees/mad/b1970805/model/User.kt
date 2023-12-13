package uk.ac.tees.mad.b1970805.model


import android.util.Log
import androidx.compose.runtime.MutableState
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

class UserRepo {

    private val firestore = Firebase.firestore


    suspend fun fetchUserData(uid: String): UserFirestore? {
        val userDocRef = Firebase.firestore.collection("users").document(uid)

        return try {
            val userData = userDocRef.get().await().toObject(UserFirestore::class.java)
            Log.d("UserRepository", "Fetched user data: $userData")
            return userData

        } catch (e: Exception) {
            // Handle error fetching user data
            Log.e("UserRepository", "Error fetching user data", e)
            null
        }
    }


    fun logoutUser() {
        val auth = FirebaseAuth.getInstance()

        auth.signOut()

        // Additional cleanup or navigation logic can be added here
    }

    suspend fun initiateTransfer(senderId: String, recipientId: String, amount: Double): TransferResult {
        try {
            // Fetch sender and recipient data from Firestore (replace 'users' with your Firestore collection)
            val senderDoc = firestore.collection("users").document(senderId).get().await()
            val recipientDoc = firestore.collection("users").document(recipientId).get().await()

            // Check if sender and recipient exist
            if (!senderDoc.exists() || !recipientDoc.exists()) {
                return TransferResult.Error("Sender or recipient not found.")
            }

            // Get sender and recipient balances
            val senderBalance = senderDoc.getDouble("balance") ?: 0.0
            val recipientBalance = recipientDoc.getDouble("balance") ?: 0.0

            // Check if sender has enough balance
            if (senderBalance < amount) {
                return TransferResult.Error("Insufficient funds.")
            }

            // Perform the transfer (update sender and recipient balances)
            firestore.runBatch { batch ->
                batch.update(senderDoc.reference, "balance", senderBalance - amount)
                batch.update(recipientDoc.reference, "balance", recipientBalance + amount)
            }

            return TransferResult.Success("Transfer successful.")
        } catch (e: Exception) {
            // Handle any exceptions that might occur during the transfer
            return TransferResult.Error("Error during transfer.")
        }
    }

    suspend fun verifyPin(userId:String, enteredPin: String): Boolean {
        Log.d("UserId Model", "User ID: $userId, Pin: $enteredPin")

        val userDocument = FirebaseFirestore.getInstance().collection("users").document(userId.toString()).get().await()

        // Assuming you have a field named "pin" in your user document
        val storedPin = userDocument.getString("pin")

        Log.d("Stored Pin", "Pin: $storedPin")

        // Compare the entered PIN with the stored PIN
        return storedPin == enteredPin
    }
    suspend fun doesRecipientNumberExist(currentUserId: String, recipientNumber: String): Boolean {
        val firestore = FirebaseFirestore.getInstance()

        try {
            Log.d("FirestoreQuery", "Start querying Firestore for recipientNumber: $recipientNumber")

            val querySnapshot = firestore.collection("users")
                .whereEqualTo("userId", currentUserId)
                .whereNotEqualTo("phoneNumber", recipientNumber)
                .get()
                .await()

            // Check if any document matches the query
            val exists = !querySnapshot.isEmpty
            Log.d("FirestoreQuery", "Recipient number $recipientNumber exists: $exists")

            return exists
        } catch (e: Exception) {
            // Handle exceptions (e.g., Firestore query failure)
            Log.e("FirestoreQuery", "Error querying Firestore", e)
            return false
        }
    }


    private suspend fun updateBalanceInFirestore(userId: String, newBalance: Double) {
        try {
            // Update the user's balance in Firestore
            val userDocRef = FirebaseFirestore.getInstance().collection("users").document(userId)
            userDocRef.update("balance", newBalance)
            Log.d("FirestoreUpdate", "User balance updated successfully: $newBalance")
        } catch (e: Exception) {
            // Handle Firestore update failure
            Log.e("FirestoreUpdate", "Error updating user balance", e)
        }
    }

//     suspend fun updateUserBalance(userId: String, newBalance: Double) {
//        try {
//            val userDocumentRef = FirebaseFirestore.getInstance().collection("users").document(userId)
//            userDocumentRef.update("balance", newBalance)
//            Log.d("UpdateUserBalance", "User balance updated successfully:$userId:  $newBalance")
//        } catch (e: Exception) {
//            // Handle update failure
//            Log.e("UpdateUserBalance", "Error updating user balance", e)
//            throw e
//        }
//    }

    suspend fun updateUserBalance(userId: String, newBalance: Double) {
        try {
            val firestore = FirebaseFirestore.getInstance()
            val userDocumentRef = firestore.collection("users").whereEqualTo("userId", userId)

            // Execute the query to get the matching documents
            val querySnapshot = userDocumentRef.get().await()

            // Check if there is any matching document
            if (!querySnapshot.isEmpty) {
                // Assuming userId is unique, there should be only one document in the result
                val document = querySnapshot.documents[0]
                val balanceToString = newBalance.toString()

                // Update the "balance" field
                document.reference.update("balance", balanceToString)
                Log.d("UpdateUserBalance", "User balance updated successfully: $userId: $newBalance")
            } else {
                Log.e("UpdateUserBalance", "No document found for userId: $userId")
            }
        } catch (e: Exception) {
            // Handle update failure
            Log.e("UpdateUserBalance", "Error updating user balance", e)
            throw e
        }
    }

    suspend fun updateUserBalancePhone(phoneNumber: String, newBalance: Double) {
        try {
            val firestore = FirebaseFirestore.getInstance()
            val userDocumentRef = firestore.collection("users").whereEqualTo("phoneNumber", phoneNumber)

            // Execute the query to get the matching documents
            val querySnapshot = userDocumentRef.get().await()

            // Check if there is any matching document
            if (!querySnapshot.isEmpty) {
                // Assuming userId is unique, there should be only one document in the result
                val document = querySnapshot.documents[0]
                val balanceToString = newBalance.toString()

                // Update the "balance" field
                document.reference.update("balance", balanceToString)
                Log.d("UpdateUserBalance", "Sender balance updated successfully: $phoneNumber: $newBalance")
            } else {
                Log.e("UpdateUserBalance", "No document found for Sender: $phoneNumber")
            }
        } catch (e: Exception) {
            // Handle update failure
            Log.e("UpdateUserBalance", "Error updating user balance", e)
            throw e
        }
    }

    // In your UserRepo or relevant data repository
// Inside your UserRepo or relevant data repository
    suspend fun getUserBalanceByPhoneNumber(phoneNumber: String): String? {
        val firestore = FirebaseFirestore.getInstance()
        try {
            val querySnapshot = firestore.collection("users")
                .whereEqualTo("phoneNumber", phoneNumber)
                .get()
                .await()

            if (!querySnapshot.isEmpty) {
                val document = querySnapshot.documents[0]
//                val balance = document.getDouble("balance")
                val balance = document.getString("balance")

                if (balance != null) {
                    Log.d("UserRepo", "User balance for $phoneNumber: $balance")
                    return balance
                } else {
                    Log.e("UserRepo", "Error: 'balance' field is not a valid number.")
                    return null
                }
            } else {
                Log.d("UserRepo", "User with phone number $phoneNumber not found.")
                return null
            }
        } catch (e: Exception) {
            // Handle exceptions (e.g., Firestore query failure)
            Log.e("UserRepo", "Error getting user balance", e)
            return null
        }
    }


    suspend fun getTransactionsForUser(userId: String): List<Transaction> {
        try {
            Log.d("FirestoreQuery", "Start querying Firestore for transactions of user: $userId")

            val querySnapshot = firestore.collection("transactions")
                .whereEqualTo("userId", userId)
                .get()
                .await()

            // Convert the query result to a list of transactions
            val transactions = querySnapshot.documents.mapNotNull { document ->
                document.toObject(Transaction::class.java)
            }

            Log.d("FirestoreQuery", "Transactions for user $userId: $transactions")

            return transactions
        } catch (e: Exception) {
            // Handle exceptions (e.g., Firestore query failure)
            Log.e("FirestoreQuery", "Error querying Firestore", e)
            return emptyList()
        }
    }


    suspend fun recordTransaction(
        userId: String,
        recipientNumber: String,
        narration: String,
        amount: Double
    ) {
        try {
            val transactionsCollectionRef = FirebaseFirestore.getInstance().collection("transactions")

            // Create a new transaction document
            val transactionData = hashMapOf(
                "userId" to userId,
                "receiver" to recipientNumber,
                "amount" to amount,
                "status" to "success",
                "timestamp" to FieldValue.serverTimestamp(),
                "narration" to narration
            )

            transactionsCollectionRef.add(transactionData)
            Log.d("RecordTransaction", "Transaction recorded successfully")
        } catch (e: Exception) {
            // Handle record failure
            Log.e("RecordTransaction", "Error recording transaction", e)
            throw e
        }
    }




}