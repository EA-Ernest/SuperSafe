package uk.ac.tees.mad.b1970805.view


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
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import uk.ac.tees.mad.b1970805.R
import uk.ac.tees.mad.b1970805.model.Registration
import uk.ac.tees.mad.b1970805.viewmodel.RegisterViewModel
import uk.ac.tees.mad.b1970805.viewmodel.TransferViewModel
import uk.ac.tees.mad.b1970805.viewmodel.UserViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ConfirmPin_Screen(modifier: Modifier = Modifier, transferViewModel: TransferViewModel, navController: NavController, viewModel: UserViewModel) {
    var password by remember { mutableStateOf("") }
//    val pin by registerViewModel.pin.collectAsState()
    val pinDigits = transferViewModel.confirmpinDigits
    val transferInProgress = transferViewModel.transferInProgress.collectAsState()

    val transferCompleted by remember { transferViewModel.transferCompleted }.collectAsState()
    val userModel by viewModel.user.collectAsState()
    val transactionSuccessful by transferViewModel.transferSuccessful.collectAsState()
    var focusedIndex by remember { mutableStateOf(0) }
    val focusRequesters = remember { List(pinDigits.size) { FocusRequester() } }


    val amount = transferViewModel.amount.value
    val number = transferViewModel.recipientNUmber.value
    val narration = transferViewModel.narration.value

    Log.d("TransFerDebug", "Number: $number")
    Log.d("TransFerDebug", "Number: $amount")
    Log.d("UserDataConfirmScreen", "User:$userModel ")
    Log.d("TransferDebug", "Value:$transactionSuccessful")

//    if(transactionSuccessful){
//        navController.navigate("transaction_successful")
//    }else{
//        navController.navigate("transaction_Failed")
//    }
    if(transactionSuccessful){
        navController.navigate("transaction_successful")
    }




    if (userModel == null){
        navController.navigate("login")
    }



    if (transferInProgress.value) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f)),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = Color.White)
        }
    } else {


        if (transferCompleted) {
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
                            onValueChange = { enteredText ->
                                // Update the PIN in the ViewModel
                                transferViewModel.updateConfirmPin(index, enteredText)

                                // Move focus to the next TextField when current one is filled
                                // Move focus to the next TextField when current one is filled
                                if (enteredText.length == 1 && index < pinDigits.lastIndex) {
                                    // Increment the focused index
                                    focusedIndex = index + 1
                                }
                            },
                            modifier = Modifier
                                .weight(1f)
                                .focusRequester(focusRequesters[index])
                                .onFocusChanged { focusState ->
                                    // Update the focused index when focus changes
                                    if (focusState.isFocused) {
                                        focusedIndex = index
                                    }
                                }


                        )
                    }
                }

                // Use LocalSoftwareKeyboardController to control the keyboard focus
                val keyboardController = LocalSoftwareKeyboardController.current
                DisposableEffect(focusedIndex) {
                    // Request keyboard focus when the focused index changes
                    focusRequesters[focusedIndex].requestFocus()
                    onDispose { }
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
                            val userId = userModel?.userId
                            val  userPin = userModel?.pin
                            val userBalance = userModel?.balance
                            val pin = pinDigits.map { it.value }.joinToString("") // Join digits into a single String
                            Log.d("Pin", "userID: $userId")
                            if (userId != null && userPin != null && userBalance != null)  {
                                transferViewModel.performTransfer( userId =userId, narration =  narration, enteredPin = pin , userBalance = userBalance, userPin = userPin  )
                            }

                        }
                        .background(color = Color(0xff005496))
                ) {
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
            Text(
                text = "Enter PIN",
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

