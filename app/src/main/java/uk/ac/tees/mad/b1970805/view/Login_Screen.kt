package uk.ac.tees.mad.b1970805.view

import uk.ac.tees.mad.b1970805.model.LoginRepository


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.super_safe.Viewmodels.LoginViewModel
import uk.ac.tees.mad.b1970805.R
import uk.ac.tees.mad.b1970805.components.CustomButton
import uk.ac.tees.mad.b1970805.components.CustomTextField
import uk.ac.tees.mad.b1970805.components.ErrorCard


@Composable
fun Login(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: LoginViewModel
) {

//    var password by rememberSaveable { mutableStateOf("") }
//    var text by remember { mutableStateOf("") }

    val email by viewModel.email
    val password by viewModel.password
    val loginSuccessful by viewModel.loginSuccessful.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val authenticationError by viewModel.authenticationError.collectAsState()
    var deviceName by remember { mutableStateOf(viewModel.getDeviceName()) }

    Log.d("Device name", "name: $deviceName")








    if (loading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f)),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = Color.White)
        }
    } else {




        Box(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight()
//            .clip(shape = RoundedCornerShape(20.dp))
                .background(color = Color.White)
        ) {

            Text(
                text = "Log In",
                color = Color.Black,
                style = TextStyle(
                    fontSize = 30.sp
                ),
                modifier = Modifier
                    .align(alignment = Alignment.TopCenter)
                    .offset(
                        x = 0.5.dp,
                        y = 50.dp
                    )
            )

            authenticationError?.let { errorMessage ->
                if (errorMessage.isNotBlank()) {
                    ErrorCard(modifier = Modifier.padding(16.dp), errorMessage = errorMessage)
                }
            }
            Log.d("Errors", "Error:$authenticationError")



            Log.d("loginModel", "Successful Login value : $loginSuccessful")
            LaunchedEffect(loginSuccessful) {
                if (loginSuccessful) {
                    // Navigation logic after a successful login
                    navController.navigate("dashboard") {
                        // Optionally, you can also pop up the back stack to remove the login screen
                        popUpTo("login") { inclusive = true }
                    }
                }
            }

            CustomButton(
                label = "Log in",
                isEnabled = true,
                validationError = viewModel.validationError.value,
                onClick = {
                    Log.d(" LoginViewModel", "Email: $email")
                    Log.d("LoginViewModel", "Password: $password")

                    if (viewModel.isInputValid()) {
                        viewModel.onLoginClicked()
                    }
//                    viewModel.onLoginClicked()
                }
            )
            Image(
                painter = painterResource(id = R.drawable.loginbro),
                contentDescription = "successful-purchase/pana",
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .size(180.dp)
                    .offset(
                        x = 103.dp,
                        y = 158.dp
                    )
                    .requiredWidth(width = 320.dp)
                    .requiredHeight(height = 324.dp)
            )

            Column(

                modifier = Modifier
                    .offset(x = 20.dp, y = 444.dp)
            ) {
                CustomTextField(
                    textValue = email,
                    modifier = Modifier
                        .offset(x = 22.dp, y = 549.dp),
                    label = "Email",
                    validationError = viewModel.validationError.value,
                    svg = R.drawable.solarletteroutline,
                    onValueChange = { viewModel.onEmailChanged(it) }
                )
                Spacer(modifier = Modifier.height(50.dp))
                CustomTextField(
                    textValue = password,
                    modifier = Modifier
                        .offset(x = 22.dp, y = 619.dp),
                    label = "Password",
                    svg = R.drawable.solarlockpasswordbroken,
                    validationError = viewModel.validationError.value,
                    onValueChange = { viewModel.onPasswordChanged(it) },
                    visualTransformation = PasswordVisualTransformation()
                )
            }



            Text(
                text = "Success",
                color = Color(0xff08ba25),
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                ),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 206.dp,
                        y = 333.dp
                    )
            )
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 113.dp,
                        y = 790.dp
                    )
                    .requiredWidth(width = 165.dp)
                    .requiredHeight(height = 18.dp)
            ) {
                Text(
                    text = "Not a member?",
                    color = Color.Black,
                    style = TextStyle(
                        fontSize = 15.sp
                    )
                )
                Text(
                    text = "Sign Up",
                    color = Color(0xff005496),
                    style = TextStyle(fontSize = 15.sp),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = 111.dp,
                            y = 0.dp
                        )
                        .clickable {
                            navController.navigate("registration_screen")

                        }
                )
            }

        }
    }
}

@Preview(widthDp = 390, heightDp = 844)
@Composable
private fun LoginPreview() {
    val navController = rememberNavController()
    val loginRepository = LoginRepository()

    val loginModel = remember {LoginViewModel(loginRepository, navController)}
    Login(Modifier, navController = navController, viewModel = loginModel)
}
