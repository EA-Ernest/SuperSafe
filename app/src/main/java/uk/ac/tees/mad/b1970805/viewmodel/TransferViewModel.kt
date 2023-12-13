package uk.ac.tees.mad.b1970805.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import uk.ac.tees.mad.b1970805.model.Transaction
import uk.ac.tees.mad.b1970805.model.UserRepo

class TransferViewModel(private val userRepo: UserRepo) : ViewModel() {





    private val _recipientNumber = mutableStateOf("")
    val recipientNUmber: State<String> get() = _recipientNumber

    private  val _amount = mutableStateOf("")
    val amount: State<String> get() = _amount

    private val _validationError = MutableStateFlow<String?>(null)
    val validationError: StateFlow<String?> get() = _validationError

    private val _recipientExists = mutableStateOf(true)
    val recipientExists: State<Boolean> get() = _recipientExists

    private val _narration = mutableStateOf("")
    val narration: State<String> get() = _narration

    private val _isPinRequired = MutableStateFlow(false)
    val isPinRequired: StateFlow<Boolean> = _isPinRequired


    private val _transferSuccessful = MutableStateFlow(false)
    val transferSuccessful: StateFlow<Boolean> get() = _transferSuccessful

    // Separate variables for each digit of the PIN
    private val _confirmpinDigits = List(4) { mutableStateOf("") }
    val confirmpinDigits: List<MutableState<String>> = _confirmpinDigits

    private val _transferCompleted = MutableStateFlow(false)
    val transferCompleted: StateFlow<Boolean> = _transferCompleted


    // New state for tracking registration in progress
    private val _transferInProgress = MutableStateFlow(false)
    val transferInProgress: StateFlow<Boolean> = _transferInProgress

    private val _userTransactions = MutableStateFlow<List<Transaction>>(emptyList())
    val userTransactions: StateFlow<List<Transaction>> = _userTransactions


    fun updateConfirmPin(index: Int, digit: String) {
        _confirmpinDigits[index].value = digit
    }

    fun setRecipientNumber(number: String) {
        _recipientNumber.value = number

//        if (isValidPhoneNumber(number)) {
//            _recipientNumber.value = number
//            // Reset the recipientExists state when the recipient number changes
//            _recipientExists.value = true
//        } else {
//            _recipientExists.value = false
//            // You can also set a validation error message here
//            _validationError.value = "Invalid phone number"
//        }
    }

    private fun isValidPhoneNumber(number: String): Boolean {
        // Implement your phone number validation logic here
        // For simplicity, we'll check if the number has at least 10 digits
        return number.length >= 10
    }

    // Set method for amount
    fun setAmount(amount: String) {
        _amount.value = amount
    }

    // Set method for narration
    fun setNarration(narration: String) {
        _narration.value = narration
    }

    // Set method for isPinRequired
    fun setIsPinRequired(isRequired: Boolean) {
        _isPinRequired.value = isRequired
    }

    // Set method for transferSuccessful
    fun setTransferSuccessful(isSuccessful: Boolean) {
        _transferSuccessful.value = isSuccessful
    }









    private fun isInputValid(userBalance: String): Boolean{

        _validationError.value = null
        val formattedRecipientNumber = "+44" + _recipientNumber.value.trim()

        Log.d("TransferViewModel", "Formatted Recipient Number: $formattedRecipientNumber")


        if(_recipientNumber.value.isBlank()){
            _validationError.value = "Recipient Name cannot be empty."
            return false
        }

        if (_amount.value.isBlank()) {
            _validationError.value = "Amount is Required ."
            return false
        }

        if( _amount.value.toDoubleOrNull() == null || _amount.value.toDouble() <= 0){
            _validationError.value = "Invalid Amount"
            return false
        }
        if (!isValidNumberFormat(formattedRecipientNumber)) {
            _validationError.value = "Invalid recipient number format."
            return false
        }

        if(_amount.value.toDoubleOrNull() ?: 0.0 > userBalance.toDoubleOrNull() ?: 0.0){
            _validationError.value = "Insufficient Balance"
            return false
        }

        if(!isValidNarrationFormat(_narration.value )){
            _validationError.value = "Invalid Input"
            return false
        }


        return true

    }


    fun fetchTransactionsForUser(userId: String) {
        viewModelScope.launch {
            try {
                val transactions = userRepo.getTransactionsForUser(userId)
                _userTransactions.value = transactions
            } catch (e: Exception) {
                // Handle the exception or set an error state
                Log.e("TransactionViewModel", "Error fetching user transactions", e)
            }
        }
    }


    private fun verifyRecipientPhoneNumber() {
        viewModelScope.launch {
            try {
                // Perform recipient phone number verification
                val recipientPhoneNumber = _recipientNumber.value
//                val recipientUid = authRepository.verifyPhoneNumber(recipientPhoneNumber)
                // Assuming recipientUid is the unique identifier for the recipient
//                if (recipientUid.isNotEmpty()) {
//                    // Perform the transfer only if the recipient phone number is verified
//                    performTransfer()
//                } else {
//                    _validationError.value = "Recipient phone number verification failed."
//                }
            } catch (e: Exception) {
                // Handle verification failure
                _validationError.value = "Recipient phone number verification failed: ${e.message}"
            }
        }
    }


    // Function to initiate a transfer
    fun initiateTransfer(recipientNumber: String, userId: String, userBalance: String) {
        viewModelScope.launch {
            Log.d("ValidationError", "Number:${validationError.value}")

            if(isInputValid(userBalance = userBalance)){




                if(userRepo.doesRecipientNumberExist(recipientNumber, userId))
                {

//                    if(_amount.value >= userBalance){
//                        _validationError.value = "Insufficient Balance"
//                        Log.d("BalanceValidation", "Insufficient Balance")
//

//                }
                    _isPinRequired.value = true

                    Log.d("ValidationError", "user Exist")


                }else{
                    _validationError.value = "User Does not Exit"
                    Log.d("ValidationError", "User Does not Exit")


                }


            }




        }


    }

    private fun validateUserBalance(userBalance: String, enterBalance:String): Boolean{
        return (enterBalance <= userBalance)
    }



    private fun verifyUserPin(enteredPin: String, userPin: String): Boolean {
        return try {
            // Call the repository to verify the entered PIN
            Log.d("UserID_PIN", "EnteredPin:$enteredPin and  userPin: $userPin")
            enteredPin == userPin
//            userRepo.verifyPin(enteredPin = pin, userId = userId)
        } catch (e: Exception) {
            // Handle exceptions (e.g., network issues, repository error)
            Log.e("TransferViewModel", "Error verifying PIN", e)
            false
        }
    }

    fun performTransfer(
        narration: String,
        userId: String,
        enteredPin: String,
        userBalance: String,
        userPin: String
    ) {
        viewModelScope.launch {
            try {
                // Verify the PIN
                if (verifyUserPin(enteredPin = enteredPin, userPin = userPin)) {
                    Log.d("PinVerification", "Pin verification Successful ")

                    // Calculate the new balance after the transfer
                    val transferAmount = _amount.value.toDoubleOrNull() ?: 0.0
                    val userBalance = userBalance.toDoubleOrNull()?: 0.0
                    val newSenderBalance = userBalance - transferAmount
                    val recipientNumber = "+44"+_recipientNumber.value
                    // Get recipient's balance
                    val recipientBalance = userRepo.getUserBalanceByPhoneNumber(phoneNumber = recipientNumber)

                    Log.d("RecipientBalance", "Recipient Balance: $recipientBalance")
                    Log.d("RecipientBalance", "Transfer Amount: $transferAmount")



                    if (recipientBalance != null) {
                        // Calculate the new balance for the recipient
                        val recipientBalance = recipientBalance.toDoubleOrNull()?:0.0
                        val newRecipientBalance = recipientBalance + transferAmount

                        Log.d("RecipientBalance", "New Recipient Balance: $newRecipientBalance")

                        Log.d("SenderBalance", "Recipient Balance: $newSenderBalance")

//                        // Update both sender's and recipient's balances
//                        userRepo.updateUserBalance(userId, newSenderBalance)
//                        userRepo.updateUserBalancePhone(recipientNumber, newRecipientBalance)
//
//                        // Record the transaction with a unique transaction ID
//                        userRepo.recordTransaction(userId, recipientNumber, narration, transferAmount)

                        // Set transferSuccessful flag to true
                        _transferSuccessful.value = true
                    } else {
                        Log.d("RecipientBalance", "Recipient balance not found")
                        _validationError.value = "Recipient balance not found"
                    }
                } else {
                    Log.d("PinVerification", "Pin verification Failed ")
                    // Handle PIN verification failure
                    _validationError.value = "Invalid PIN"
                }
            } catch (e: Exception) {
                // Handle transfer failure
                _validationError.value = "Transfer failed: ${e.message}"
            }
        }
    }





}

