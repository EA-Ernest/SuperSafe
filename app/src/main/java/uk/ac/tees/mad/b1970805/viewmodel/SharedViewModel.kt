package uk.ac.tees.mad.b1970805.viewmodel

import uk.ac.tees.mad.b1970805.model.LoginRepository
import uk.ac.tees.mad.b1970805.model.UserRepo

class SharedViewModel {
    val loginRepository = LoginRepository()
    //    val navController = rememberNavController()
//    val loginViewModel = LoginViewModel(loginRepository)
    val userRepo = UserRepo()

    val transferViewModel = TransferViewModel(userRepo)
}