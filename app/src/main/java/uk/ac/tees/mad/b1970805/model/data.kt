package uk.ac.tees.mad.b1970805.model


data class UserFirestore(
    val userId: String? = null,
    val fname: String ? = null,
    val lname: String? = null,
    val email: String? = null,
    val pin: String? = null,
    val balance:String? = null
    // Add more fields as needed
)

sealed class TransferResult {
    data class Success(val message: String) : TransferResult()
    data class Error(val errorMessage: String) : TransferResult()
    // You can add more states as needed
}


sealed class PinEntryState {
    object Initial : PinEntryState()
    object Prompt : PinEntryState()
    object Success : PinEntryState()
    object Failure : PinEntryState()
}

data class SnackbarModel(
    val message: String = "",
    val isVisible: Boolean = false
)

data class Transaction(
    val amount: Int,
    val narration: String,
    val receiver: String,
    val status: String,
    val timestamp: String,
    val userId: String
)

