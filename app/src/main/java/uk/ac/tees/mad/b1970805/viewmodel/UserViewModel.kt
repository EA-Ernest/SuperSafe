package uk.ac.tees.mad.b1970805.viewmodel


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import uk.ac.tees.mad.b1970805.model.TransferResult
import uk.ac.tees.mad.b1970805.model.UserFirestore
import uk.ac.tees.mad.b1970805.model.UserRepo

class UserViewModel(private val  userRepo: UserRepo):ViewModel() {


    private val _user = MutableStateFlow<UserFirestore?>(null)
    val user: StateFlow<UserFirestore?> = _user

    // LiveData to observe the logout event
    // StateFlow to observe the logout event
    private val _logoutEvent = MutableStateFlow<Boolean>(false)
    val logoutEvent: StateFlow<Boolean> get() = _logoutEvent

    // State for representing the result of a transfer operation
    private val _transferResult = MutableStateFlow<TransferResult?>(null)
    val transferResult: StateFlow<TransferResult?> = _transferResult


    init {
        // Fetch user data from Firestore when the ViewModel is created
        fetchUserData()
    }

    private fun fetchUserData() {
        // Use Firebase Authentication to get the current user's UID
        val uid = FirebaseAuth.getInstance().currentUser?.uid

        Log.d("UserData", "User Email:$uid")

        if (uid != null) {
            viewModelScope.launch {
                try {
                    val userData = userRepo.fetchUserData(uid)
                    _user.value = userData

                    Log.d("UserViewModel", "User data fetched successfully: $userData")
                } catch (e: Exception) {
                    // Handle error fetching user data
                    Log.e("UserViewModel", "Error fetching user data", e)
                }
            }
        } else {
            Log.e("UserViewModel", "User UID is null. User may not be authenticated.")
        }
    }


    fun logout() {
        userRepo.logoutUser()
        _logoutEvent.value = true
    }



}

