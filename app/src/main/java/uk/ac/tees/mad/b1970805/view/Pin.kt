package uk.ac.tees.mad.b1970805.view

import uk.ac.tees.mad.b1970805.R
import uk.ac.tees.mad.b1970805.model.Registration
import uk.ac.tees.mad.b1970805.viewmodel.RegisterViewModel


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField(
    textValue: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var passwordVisibility by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }


    TextField(
        value = textValue,
        onValueChange = {
            if (it.length <= 1 && it.all { char -> char.isDigit() }) {
                onValueChange(it) }
            if (it.length == 1) {
                focusRequester.requestFocus()
            }

        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        ),
//        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        singleLine = true,
        keyboardActions = KeyboardActions(
            onNext = {
                focusRequester.requestFocus()
            }
        ),

        visualTransformation = PasswordVisualTransformation(),
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            containerColor = Color(0xffe9e8e8).copy(alpha = 0.5f),
            focusedTextColor=Color.Transparent,
            unfocusedTextColor=  Color.Transparent,
        ),
        textStyle = TextStyle(
            color = Color.Black, // Set the text color
            fontWeight = FontWeight.Bold, // Set the font weight
            fontSize = 35.sp, // Set the font size
            textAlign = TextAlign.Center
            // Add any other styling properties you need
        ),
        modifier = modifier
            .requiredWidth(width = 50.dp)
            .requiredHeight(height = 60.dp)
            .clip(shape = RoundedCornerShape(5.dp))
            .focusRequester(focusRequester)

    )
}

@Composable
@Preview(showBackground = true)
fun PasswordTextFieldScreen() {
    var password by remember { mutableStateOf("") }

    PasswordTextField(
        textValue = password,
        onValueChange = { password = it },
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    )
}

@Composable
fun Pin_Screen(modifier: Modifier = Modifier, registerViewModel: RegisterViewModel,  navController: NavController) {
    var password by remember { mutableStateOf("") }
//    val pin by registerViewModel.pin.collectAsState()
    val pinDigits = registerViewModel.pinDigits
    val registrationInProgress = registerViewModel.registrationInProgress.collectAsState()

    val registrationCompleted by remember { registerViewModel.registrationCompleted }.collectAsState()






    if (registrationInProgress.value) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f)),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = Color.White)
        }
    } else {


        if (registrationCompleted == true) {
            navController.navigate("success")
        }


        Box(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight()
//            .clip(shape = RoundedCornerShape(20.dp))
                .background(color = Color(0xff005496))
        ) {
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 0.dp,
                        y = 79.dp
                    )
                    .requiredHeight(height = 765.dp)
                    .fillMaxWidth()
                    .clip(
                        shape = RoundedCornerShape(
                            topStart = 30.dp,
                            topEnd = 30.dp,

                            )
                    )
                    .background(color = Color.White)
            ) {

                // Display the PIN entered so far
//            Text(
//                text = "PIN: $pinDigit.",
//                fontSize = 20.sp,
//                fontWeight = FontWeight.Bold,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(8.dp),
//                textAlign = TextAlign.Center
//            )
                // Row of PasswordTextFields for PIN entry
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = 105.dp)
                ) {
                    pinDigits.forEachIndexed { index, pinDigit ->
                        PasswordTextField(
                            textValue = pinDigit.value,
                            onValueChange = {
                                // Update the PIN in the ViewModel
                                registerViewModel.updatePin(index, it)
                            },
                            modifier = Modifier
                                .weight(1f)
                        )
                    }
                }



                Box(
                    modifier = Modifier
                        .align(alignment = Alignment.TopCenter)
                        .offset(
                            x = 0.dp,
                            y = 659.dp
                        )
                        .requiredWidth(width = 340.dp)
                        .requiredHeight(height = 45.dp)
                        .clip(shape = RoundedCornerShape(5.dp))
                        .clickable {
                            // Confirm button clicked
                            registerViewModel.setPin()
                        }
                        .background(color = Color(0xff005496))
                ) {
                    if (registrationInProgress.value) {
                        // Show loading indicator when registration is in progress
                        CircularProgressIndicator(
                            color = Color.White,
                            modifier = Modifier
                                .size(24.dp)
                                .padding(8.dp)
                        )
                    } else {
                        Text(
                            text = "Confirm",
                            color = Color.White,
                            style = TextStyle(fontSize = 20.sp),
                            modifier = Modifier
                                .align(alignment = Alignment.Center)
                                .offset(
                                    x = 0.dp,
                                    y = 0.5.dp
                                )
                        )
                    }
                }
            }
            Text(
                text = "Create PIN",
                color = Color.White,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .align(alignment = Alignment.TopCenter)
                    .offset(
                        x = 0.dp,
                        y = 35.dp
                    )
            )
            Image(
                painter = painterResource(id = R.drawable.arrow),
                contentDescription = "arrow",
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 26.42108154296875.dp,
                        y = 44.94775390625.dp
                    )
                    .requiredWidth(width = 20.dp)
                    .requiredHeight(height = 14.dp)
            )
        }
    }
}

@Preview(widthDp = 390, heightDp = 844)
@Composable
private fun SendMoneyPreview() {

    val context = LocalContext.current
    val registration = Registration()
    val navController = rememberNavController()


    val registerViewModel = RegisterViewModel(registration)
    Pin_Screen(Modifier, registerViewModel = registerViewModel, navController = navController)
}

