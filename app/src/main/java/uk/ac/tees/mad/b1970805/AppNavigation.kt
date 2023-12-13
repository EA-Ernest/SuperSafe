package uk.ac.tees.mad.b1970805


import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.super_safe.Viewmodels.LoginViewModel
import uk.ac.tees.mad.b1970805.model.LoginRepository
import uk.ac.tees.mad.b1970805.model.Registration
import uk.ac.tees.mad.b1970805.view.ConfirmPin_Screen
import uk.ac.tees.mad.b1970805.view.Dashboard
import uk.ac.tees.mad.b1970805.view.Login
import uk.ac.tees.mad.b1970805.view.Pin_Screen
import uk.ac.tees.mad.b1970805.view.Register_screen
import uk.ac.tees.mad.b1970805.view.SendMoney
import uk.ac.tees.mad.b1970805.view.Splash_screen
import uk.ac.tees.mad.b1970805.viewmodel.RegisterViewModel
import uk.ac.tees.mad.b1970805.viewmodel.SharedViewModel
import uk.ac.tees.mad.b1970805.viewmodel.TransferViewModel
import uk.ac.tees.mad.b1970805.viewmodel.UserViewModel


@Composable
fun AppNavigation (
    navController: NavHostController,
    viewModel: UserViewModel,
    userViewModel: UserViewModel,
    loginViewModel: LoginViewModel,
    transferViewModel: TransferViewModel,
    registration: Registration,
    registrationViewModel: RegisterViewModel,
    loginRepository: LoginRepository,
    context: Context,
    sharedViewModel: SharedViewModel
){
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    val loginSuccessful by loginViewModel.loginSuccessful.collectAsState()
    Log.d("LoginDebug", "value for successful login: $loginSuccessful")


    NavHost(navController = navController, startDestination = if (loginSuccessful) "dashboard" else "splash_screen") {
        composable("splash_screen"){
            Splash_screen(navController = navController, modifier = Modifier.fillMaxSize())
        }
        composable("Start_screen"){
            Start_screen(navController = navController)
        }
        composable("transfers"){
            SendMoney(Modifier, transfersViewModel = transferViewModel, navController= navController, viewModel = userViewModel)
        }
        composable("login"){
            Login(navController = navController, viewModel = LoginViewModel(loginRepository, navController))
        }
        composable("success"){
            Success(navController = navController)
        }
        composable("transaction_successful"){
            Transaction_Sucess(navController = navController, transferViewModel = transferViewModel)
        }
        composable("transaction_Failed"){
            Failed()
        }
        composable("confirmPin"){
            ConfirmPin_Screen(
                transferViewModel = transferViewModel,
                navController = navController,
                viewModel = userViewModel
            )
        }
        composable("pin"){
            Pin_Screen(Modifier, registerViewModel = registrationViewModel, navController = navController)

        }
        composable("dashboard"){

            Dashboard(navController = navController, viewModel = viewModel)
        }
        composable("cards"){
            Dashboard(navController = navController, viewModel = viewModel)
        }
        composable("Help"){
            Help()
        }
        composable("registration_screen"){
            val registerViewModel = remember{ RegisterViewModel(registration) }
            Register_screen(viewModel = registerViewModel, navController = navController)
        }
        composable("cards"){
            Cards()
        }
        composable("login_screen"){
            val loginRepository = LoginRepository()
            val loginModel = LoginViewModel(loginRepository, navController)
            Login(navController = navController, viewModel = loginModel)
        }
    }


}