package uk.ac.tees.mad.b1970805.view


import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import uk.ac.tees.mad.b1970805.R
import uk.ac.tees.mad.b1970805.components.CustomButton
import uk.ac.tees.mad.b1970805.components.CustomTextField
import uk.ac.tees.mad.b1970805.model.Registration
import uk.ac.tees.mad.b1970805.viewmodel.RegisterViewModel


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun Register_screen(modifier: Modifier = Modifier, viewModel: RegisterViewModel, navController:NavController) {

    var showPassword by remember { mutableStateOf(false) }

//    val fname by rememberUpdatedState(newValue = viewModel.fname.value)

    val fname  by viewModel.fname
    val lname  by viewModel.lname
    val email by viewModel.email
    val password by viewModel.password
    val confirmPassword by viewModel.confirmPassword
    val phoneNumber by viewModel.phoneNumber
    val registrationSuccessful by remember { viewModel.registrationSuccessful }.collectAsState()


    if (registrationSuccessful == true) {
        navController.navigate("pin")
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = Color.White)
    ) {
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .fillMaxHeight()
                .offset(
                    x = 20.dp,
                    y = 0.dp
                )
                .requiredWidth(width = 349.dp)
//                .requiredHeight(height = 226.dp)
        ) {

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()


            ){



                CustomTextField(
                    textValue = fname,
                    label = "First Name",
                    svg = R.drawable.person,
                    onValueChange = {viewModel.onFnameChanged(it) },
                    validationError = viewModel.validationError.value,
                    modifier = Modifier
                        .offset(y = 470.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))

                CustomTextField(
                    textValue = lname,
                    label = "Last Name",
                    svg = R.drawable.person,
                    validationError = viewModel.validationError.value,
                    onValueChange = {viewModel.onLnameChanged(it) },
                    modifier = Modifier
                        .offset(y = 470.dp)
                )



                Spacer(modifier = Modifier.height(20.dp))
                CustomTextField(
                    textValue = email,
                    modifier = Modifier
                        .offset (x = 1.dp, y = 65.dp),
                    label = "Email",
                    validationError = viewModel.validationError.value,
                    svg = R.drawable.solarletteroutline,
                    onValueChange = {viewModel.onEmailChanged(it)}
                )

                Spacer(modifier = Modifier.height(20.dp))
                CustomTextField(
                    textValue = phoneNumber,
                    modifier = Modifier
                        .offset (x = 1.dp, y = 65.dp),
                    label = "Phone Number",
                    validationError = viewModel.validationError.value,

                    svg = R.drawable.solarletteroutline,
                    onValueChange = {viewModel.onPhoneChanged(it)}
                )
                Spacer(modifier = Modifier.height(20.dp))

                CustomTextField(
                    textValue = password,
                    modifier = Modifier
                        .offset (x = 1.dp, y = 135.dp),
                    label = "Password ",
                    validationError = viewModel.validationError.value,

                    svg = R.drawable.solarlockpasswordbroken,
                    onValueChange = {viewModel.onPasswordChanged(it)},
                    visualTransformation = PasswordVisualTransformation()

                )
                Spacer(modifier = Modifier.height(20.dp))


                CustomTextField(
                    textValue = confirmPassword,
                    modifier = Modifier
                        .offset (x = 1.dp, y = 300.dp),
                    label = "Confirm Password",
                    validationError = viewModel.validationError.value,

                    svg = R.drawable.solarlockpasswordbroken,
                    onValueChange = { viewModel.onConfirmPassword(it)},
                    visualTransformation = PasswordVisualTransformation()
                )
            }





            Text(
                text = "Show",
                color = Color(0xff005496),
                style = TextStyle(
                    fontSize = 10.sp),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .clickable {
                        showPassword = !showPassword
                    }
                    .offset(
                        x = 323.dp,
                        y = 550.dp
                    ))
            Text(
                text = "Show",
                color = Color(0xff005496),
                style = TextStyle(
                    fontSize = 10.sp),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 323.dp,
                        y = 497.dp
                    ))
        }
        Text(
            text = "Sign Up",
            color = Color.Black,
            style = TextStyle(
                fontSize = 30.sp),
            modifier = Modifier
                .align(alignment = Alignment.TopCenter)
                .offset(
                    x = 0.dp,
                    y = 50.dp
                ))
//        Image(
//            painter = painterResource(id = R.drawable.bro),
//            contentDescription = "sign-up/rafiki",
//            modifier = Modifier
//                .align(alignment = Alignment.TopStart)
//                .offset(
//                    x = 70.dp,
//                    y = 94.dp
//                )
//                .requiredWidth(width = 250.dp)
//                .requiredHeight(height = 241.dp))


        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 106.dp,
                    y = 750.dp
                )
                .requiredWidth(width = 179.dp)
                .requiredHeight(height = 18.dp)
        ) {
            Text(
                text = "Already a member?",
                color = Color.Black,
                style = TextStyle(
                    fontSize = 15.sp)
            )
            Text(
                text = "Log In",
                color = Color(0xff005496),
                style = TextStyle(
                    fontSize = 15.sp),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 137.dp,
                        y = 0.dp
                    )
                    .clickable { navController.navigate("login") }
            )
        }

        CustomButton(label = "Sign Up", isEnabled = true,
            validationError = viewModel.validationError.value,

            onClick = {
                if (viewModel.isInputValid()) {
                    viewModel.onSignUpClicked()

                }
                Log.d("Register_screen", "First Name: $fname")
                Log.d("Register_screen", "Last Name: $lname")
                Log.d("Register_screen", "Email: $email")
                Log.d("Register_screen", "Password: $password")
                Log.d("Register_screen", "Confirm Password: $confirmPassword")

                viewModel.onSignUpClicked()
            })
    }
}

@Preview(widthDp = 390, heightDp = 844)
@Composable
private fun Register_screenPreview() {


    val context = LocalContext.current

    val register = Registration()
    val registerViewModel = remember{ RegisterViewModel(register) }
    val navController = rememberNavController()
    Register_screen(Modifier, viewModel = registerViewModel, navController = navController)
}
