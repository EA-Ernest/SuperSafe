package uk.ac.tees.mad.b1970805


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.super_safe.Viewmodels.LoginViewModel
import com.google.firebase.FirebaseApp
import uk.ac.tees.mad.b1970805.model.LoginRepository
import uk.ac.tees.mad.b1970805.model.Registration
import uk.ac.tees.mad.b1970805.model.UserRepo
import uk.ac.tees.mad.b1970805.ui.theme.SuperSafeTheme
import uk.ac.tees.mad.b1970805.viewmodel.RegisterViewModel
import uk.ac.tees.mad.b1970805.viewmodel.SharedViewModel
import uk.ac.tees.mad.b1970805.viewmodel.TransferViewModel
import uk.ac.tees.mad.b1970805.viewmodel.UserViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SuperSafeTheme {
                // A surface container using the 'background' color from the theme

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FirebaseApp.initializeApp(this)



                    val viewModel = UserViewModel(userRepo = UserRepo()) // Inject  your UserRepository here
                    val userRepo = UserRepo()
                    val transfersViewModel = TransferViewModel(userRepo)
                    val navController = rememberNavController()
                    val context = LocalContext.current
                    val loginRepository = LoginRepository()
                    val registration = Registration()
                    val registrationViewModel = RegisterViewModel(registration)
                    val loginViewModel = LoginViewModel(loginRepository, navController = navController)
                    val sharedViewModel = SharedViewModel()
                    val userViewModel = UserViewModel(userRepo)
                    AppNavigation(
                        navController = navController,
                        viewModel = viewModel, loginViewModel = loginViewModel,
                        transferViewModel = transfersViewModel,
                        context = context,
                        registration=registration,
                        registrationViewModel = registrationViewModel,
                        sharedViewModel = sharedViewModel,
                        userViewModel = userViewModel,
                        loginRepository = loginRepository
                    )

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SuperSafeTheme {
        Greeting("Android")
    }
}