package com.example.super_safe.Viewmodels

import android.os.Build
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import uk.ac.tees.mad.b1970805.model.LoginRepository


class LoginViewModel(private val loginRepository: LoginRepository, navController: NavController) : ViewModel(){


    private val _email = mutableStateOf("")
    val email: State<String> get() = _email

    private val _password = mutableStateOf("")
    val password: State<String> get() = _password

    private val _loginSuccessful = MutableStateFlow(false)
    val loginSuccessful: StateFlow<Boolean> = _loginSuccessful

    private val _validationError = mutableStateOf<String?>(null)
    val validationError: State<String?> get() = _validationError

    private val _currentUser = MutableStateFlow<FirebaseUser?>(null)
    val currentUser: StateFlow<FirebaseUser?> = _currentUser

    private val _navigationError = MutableStateFlow<String?>(null)
    val navigationError: StateFlow<String?> = _navigationError

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _authenticationError = MutableStateFlow<String?>(null)
    val authenticationError: StateFlow<String?> = _authenticationError



    fun onEmailChanged(value:String){
        _email.value = value
        Log.d("LoginViewModel", "email address. $value")

    }

    fun onPasswordChanged(value:String){
        _password.value = value
        Log.d("LoginViewModel", "password. $value")

    }


    fun getDeviceName(): String {
        return Build.MODEL
    }


//    fun onLoginClicked() {
//        if (isInputValid()) {
////            login()
//        }
//    }

    fun isInputValid(): Boolean{

        _validationError.value = null

        if ( _email.value.isBlank() || _password.value.isBlank()) {
            _validationError.value = "All Fields are required"
            return false
        }

        // Validate Email
        if (_email.value.isBlank() || !android.util.Patterns.EMAIL_ADDRESS.matcher(_email.value).matches()) {
            _validationError.value = "Invalid email address."

            return false
        }


        // Validate Password
        if (_password.value.isBlank()) {
            _validationError.value = "Password cannot be empty."
            return false
        }

        return true


    }

    fun onLoginClicked(){
        if(isInputValid()){
            viewModelScope.launch {
                try {
                    _loading.value = true
                    val success = loginUser()
                    Log.d("LoginViewModel", "Error: $success")

                    if (success) {
                        _loginSuccessful.value = true
                    } else {
                        // Handle unsuccessful login (e.g., show an error message)
                        _authenticationError.value = "Login failed. Incorrect email or password."
                    }
                } catch (e: Exception){
                    _authenticationError.value = "Firebase Authentication Error: ${e.message}"
                    Log.d("LoginViewModel", "Error: ${_validationError.value}")
                }catch (e: Exception){
                    _authenticationError.value = "Error: ${e.message}"

                }
                finally {
                    _loading.value = false
                }
            }
        }else{
            Log.d("LoginViewModel", "Error: ${_validationError.value}")
            _authenticationError.value = "Error: ${_validationError.value}"

        }
    }




    private fun navigateToDashboard() {
        try {
            _loginSuccessful.value = true
        } catch (e: Exception) {
            _navigationError.value = "Navigation failed: ${e.message}"
        }
    }

    suspend fun loginUser(): Boolean {
        return loginRepository.loginUser(_email.value, _password.value)
    }



}