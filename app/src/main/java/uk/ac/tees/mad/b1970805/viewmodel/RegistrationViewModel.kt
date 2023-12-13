package uk.ac.tees.mad.b1970805.viewmodel

import uk.ac.tees.mad.b1970805.model.RegistrationData



import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import uk.ac.tees.mad.b1970805.model.Registration

class RegisterViewModel(private val registrationRepository: Registration) : ViewModel() {

    private val _fname = mutableStateOf("")
    val fname: State<String> get() = _fname


    private val _lname = mutableStateOf("")
    val lname: State<String> get() = _lname

    private val _email = mutableStateOf("")
    val email: State<String> get() = _email

    private val _phoneNumber = mutableStateOf("")
    val phoneNumber: State<String> get() = _phoneNumber

    private val _countryCode = mutableStateOf("+44")
    val countryCode: State<String> get() = _countryCode


    private val _password = mutableStateOf("")
    val password: State<String> get() = _password

    private val _confirmPassword = mutableStateOf("")
    val confirmPassword: State<String> get() = _confirmPassword

    private val _validationError = mutableStateOf<String?>(null)
    val validationError: State<String?> get() = _validationError

    private val _registrationSuccessful = MutableStateFlow(false)
    val registrationSuccessful: StateFlow<Boolean> = _registrationSuccessful

    private val _pin = mutableStateOf("")
    val pin: State<String> = _pin

    // Separate variables for each digit of the PIN
    private val _pinDigits = List(4) { mutableStateOf("") }
    val pinDigits: List<MutableState<String>> = _pinDigits

    private val _verificationId = MutableStateFlow("")
    val verificationId: StateFlow<String> = _verificationId

    private val _otp = mutableStateOf("")
    val otp: State<String> get() = _otp

    private val _registrationError = mutableStateOf<String?>(null)
    val registrationError: State<String?> = _registrationError

    private val _registrationCompleted = MutableStateFlow(false)
    val registrationCompleted: StateFlow<Boolean> = _registrationCompleted


    // New state for tracking registration in progress
    private val _registrationInProgress = MutableStateFlow(false)
    val registrationInProgress: StateFlow<Boolean> = _registrationInProgress





    // Function to update a specific digit of the PIN
    fun updatePin(index: Int, digit: String) {
        _pinDigits[index].value = digit
    }
    // Function to retrieve the complete PIN
    fun getPin(): String {
        return _pinDigits.joinToString(separator = "") { it.value }
    }

    // Function to perform PIN validation
    fun validatePin(): Boolean {
        // Add your PIN validation logic here
        return _pin.value.length == 4 // For example, check if the PIN length is 4
    }



    fun isInputValid(): Boolean {
        if (_fname.value.isBlank() || _lname.value.isBlank() || _email.value.isBlank() || _password.value.isBlank() || _confirmPassword.value.isBlank()) {
            _validationError.value = "All Fields are required"
            return false
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(_email.value).matches()) {
            _validationError.value = "Invalid Email Address"
            return false
        }

        val specialCharactersRegex = "[!@#\$%^&*(),.?\":{}|<>]".toRegex()
        if (specialCharactersRegex.matches(_fname.value)) {
            _validationError.value = "First Name cannot contain special characters"
            return false
        }

        if (specialCharactersRegex.matches(_lname.value)) {
            _validationError.value = "Last Name cannot contain special characters"
            return false
        }

        if (_password.value.length < 8 || specialCharactersRegex.matches(_password.value)) {
            _validationError.value = "Password must be at least 8 characters long."
            return false
        }

        if (_password.value != _confirmPassword.value) {
            _validationError.value = "Passwords don't match"
            return false
        }
        if(!isphoneNumberValid(_phoneNumber.value)){
            _validationError.value = "Invalid Phone number"
            return  false
        }

        _validationError.value = null
        return true
    }


    fun onCountryCodeChanged(countryCode: String) {
        _countryCode.value = countryCode
    }

    fun onOtpChanged(otp: String) {
        _otp.value = otp
    }



    fun onFnameChanged(value: String) {
        _fname.value = value
        Log.d("RegisterViewModel", "First Name: $value")
    }

    fun onLnameChanged(value: String) {
        _lname.value = value
        Log.d("RegisterViewModel", "Last Name: $value")

    }

    fun onEmailChanged(value:String){
        _email.value = value
        Log.d("RegisterViewModel", "Email: $value")

    }
    fun onPhoneChanged(value:String){
        _phoneNumber.value = value
        Log.d("RegisterViewModel", "phone Number: $value")

    }


    fun onPasswordChanged(value:String){
        _password.value = value
        Log.d("RegisterViewModel", "Password: $value")
    }

    fun onPhoneNumberChanged(value: String) {
        _phoneNumber.value = value
    }

    fun onConfirmPassword(value:String){
        _confirmPassword.value = value
        Log.d("RegisterViewModel", "confirm password: $value")
    }

    private fun verifyPhoneNumber(){
        viewModelScope.launch {
            try {
                if(isphoneNumberValid(_phoneNumber.value)){
                    val fullPhoneNumber  = _countryCode.value + _phoneNumber.value
//                    val verificationId = auth
                }

            } catch (e: Exception){
                Log.d("VerifyPhoneNumber", "phone verification failed")
            }
        }
    }

//    fun setPin() {
//        viewModelScope.launch {
//            val pinValues = _pinDigits.map { it.value }
//            if (pinValues.none { it.isEmpty() }) {
//                val setPinResult = registrationRepository.setPin(pinValues.joinToString(""))
//                if (setPinResult) {
//                    _registrationCompleted.value = true
//                } else {
//                    _registrationError.value = "Setting PIN failed"
//                }
//            } else {
//                _registrationError.value = "PIN cannot be empty"
//            }
//        }
//    }

    fun setPin() {
        viewModelScope.launch {
            val pinValues = _pinDigits.map { it.value }
            if (pinValues.none { it.isEmpty() }) {
                try {
                    _registrationInProgress.value = true  // Set loading state to true

//                    val setPinResult = registrationRepository.setPin(pinValues.joinToString(""))
                    val setPinResult = true


                    if (setPinResult) {
                        _registrationCompleted.value = true
                    } else {
                        _registrationError.value = "Setting PIN failed"
                    }
                } catch (e: Exception) {
                    _registrationError.value = "Setting PIN failed: ${e.message}"
                } finally {
                    _registrationInProgress.value = false  // Set loading state to false after completion
                }
            } else {
                _registrationError.value = "PIN cannot be empty"
            }
        }
    }


    private  fun isphoneNumberValid(phoneNumber: String): Boolean {
        return phoneNumber.all { it.isDigit() } && phoneNumber.length >= 9

    }




    @SuppressLint("SuspiciousIndentation")
    fun onSignUpClicked(){

        if(isInputValid()){

            viewModelScope.launch {
                val success = registerUser()
                Log.d("RegisterViewModel", "Sign-up:$success")

//                    val success = true
                if (success) {
                    _registrationSuccessful.value = true
                    Log.d("RegisterViewModel", "Sign-up successful!")
                    Log.d("RegisterViewModel", "First Name: ${fname.value}")
                } else {
                    Log.d("RegisterViewModel", "Error registering user")
                }

            }


//            Log.d("RegisterViewModel", "Sign-up successful!")
//            Log.d("RegisterViewModel", "First Name: ${fname.value}")


        }else{
            Log.d("RegisterViewModel", "Error: ${_validationError.value}")
        }

    }

//
//
//    @SuppressLint("SuspiciousIndentation")
//    fun onSignUpClicked() {
//        if (isInputValid()) {
//            viewModelScope.launch {
//                try {
//                    // Step 1: Send OTP to the user's phone number
//                    _verificationId.value = registrationRepository.sendOtp(
//                        phoneNumber = "${_countryCode.value}${_phoneNumber.value}",
//                        activity = TODO() // Pass your activity reference here
//                    ).toString()
//
//                    // Step 2: Verify OTP
//                    val success = registrationRepository.registerUser(
//                        registrationData = createRegistrationData(),
//                        phoneNumber = _phoneNumber.value,
//                        otp = _otp.value,
//                        activity = TODO() // Pass your activity reference here
//                    )
//
//                    if (success) {
//                        _registrationSuccessful.value = true
//                        Log.d("RegisterViewModel", "Sign-up successful!")
//                        Log.d("RegisterViewModel", "First Name: ${fname.value}")
//                    } else {
//                        Log.d("RegisterViewModel", "Error registering user")
//                    }
//                } catch (e: Exception) {
//                    Log.e("RegisterViewModel", "Error during sign-up", e)
//                }
//            }
//        } else {
//            Log.d("RegisterViewModel", "Error: ${_validationError.value}")
//        }
//    }

    private fun createRegistrationData(): RegistrationData {
        return RegistrationData(
            fname = fname.value,
            lname = lname.value,
            email = email.value,
            phoneNumber = _phoneNumber.value,
            password = password.value,
            confirmPassword = confirmPassword.value
        )
    }


//    suspend fun registerUser(activity: Context, phoneNumber: String, otp: String): Boolean {
//        val registerData = RegistrationData(
//            fname = fname.value,
//            lname = lname.value,
//            email = email.value,
//            phoneNumber = phoneNumber,
//            password = password.value,
//            confirmPassword = confirmPassword.value
//        )
//
//        return registrationRepository.registerUser(registerData, phoneNumber, otp, activity)
//    }


    suspend fun registerUser(): Boolean {
        val registerData = RegistrationData(
            fname = fname.value,
            lname = lname.value,
            email = email.value,
            phoneNumber = phoneNumber.value,
            password = password.value,
            confirmPassword = confirmPassword.value
        )

        return registrationRepository.registerUser(registerData)
    }










}