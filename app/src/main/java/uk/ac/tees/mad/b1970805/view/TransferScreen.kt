package uk.ac.tees.mad.b1970805.view

import uk.ac.tees.mad.b1970805.viewmodel.UserViewModel


import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import uk.ac.tees.mad.b1970805.R
import uk.ac.tees.mad.b1970805.components.AmountTextField
import uk.ac.tees.mad.b1970805.components.ButtonTransfer
import uk.ac.tees.mad.b1970805.components.TextArea
import uk.ac.tees.mad.b1970805.components.TransferText
import uk.ac.tees.mad.b1970805.model.UserRepo
import uk.ac.tees.mad.b1970805.viewmodel.TransferViewModel


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun SendMoney(modifier: Modifier = Modifier, transfersViewModel: TransferViewModel, navController: NavController, viewModel: UserViewModel) {

    val  recipientNumber by transfersViewModel.recipientNUmber
    val amount by transfersViewModel.amount
    val narration by transfersViewModel.narration
    val isPinRequired  by transfersViewModel.isPinRequired.collectAsState()
    val validationError by transfersViewModel.validationError.collectAsState()
    val userModel by viewModel.user.collectAsState()


    Log.d("validationError", "Validation: $validationError")


    if(isPinRequired){
        navController.navigate("confirmPin")
    }

    if (userModel == null){
        navController.navigate("login")
    }



    Box(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = Color(0xff005496))
    ) {


        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 0.dp,
                    y = 79.dp
                )
                .fillMaxWidth()
                .fillMaxHeight()
                .clip(
                    shape = RoundedCornerShape(
                        topStart = 30.dp,
                        topEnd = 30.dp,
//                        bottomStart = 20.dp,
//                        bottomEnd = 20.dp
                    )
                )
                .background(color = Color.White)
        ) {

            validationError?.let {
                Text(
                    text = it,
                    color = Color.Red,
                    textAlign = TextAlign.End,
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal
                    ),
                    modifier = Modifier
                        .padding(start = 4.dp, top = 4.dp)
                        .offset (y = 380.dp, x = 120.dp)
                )
            }


            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(
                        y = 50.dp
                    )
            ) {
                Column{
                    Text(
                        text = "Recipient Mobile Number",
                        color = Color.Black,
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium),
                        modifier = Modifier
                    )

                    TransferText(
                        modifier= Modifier,
                        value = recipientNumber,
                        validationError = transfersViewModel.validationError.value,
                        onValueChange ={ transfersViewModel.setRecipientNumber(it)}
                    )
                }


                Spacer(modifier = Modifier.height(16.dp))

                Column{
                    Text(
                        text = "Amount",
                        color = Color.Black,
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium),
                        modifier = Modifier
                    )
                    AmountTextField(
                        modifier,
                        value = amount,
                        onValueChange = {transfersViewModel.setAmount(it)},
                        validationError = transfersViewModel.validationError.value,

                        );

                }


                Spacer(modifier = Modifier.height(16.dp))

                Column {
                    Text(
                        text = "Narration",
                        color = Color.Black,
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium),
                        modifier = Modifier

                    )

                    TextArea(modifier,
                        value = narration,
                        validationError = transfersViewModel.validationError.value,
                        onValueChange = {transfersViewModel.setNarration(it)}
                    )
                }


                Spacer(modifier = Modifier.height(300.dp))

                Column{
                    ButtonTransfer( modifier = Modifier.offset(y = 645.dp),
                        onClick = {
                            val formatedString = "+44$recipientNumber"
                            val userId = userModel?.userId
                            val userPin = userModel?.pin
                            val userBalance = userModel?.balance
                            Log.d ("userID", "User ID: $userId")

                            if (userId != null && userBalance !=  null) {
                                transfersViewModel.initiateTransfer( userId, formatedString,userBalance= userBalance)
                            }
                            // Handle button click
                            Log.d("TransferData", "Recipient No.:${recipientNumber}")
                            Log.d("TransferData", "Amount: ${amount}")
                            Log.d("TransferData", "Narration:${narration}")
                        }
                    )

                }



            }




            Surface(
                shape = RoundedCornerShape(5.dp),
                color = Color.White.copy(alpha = 0.6f),
                border = BorderStroke(1.dp, Color.Black.copy(alpha = 0.2f)),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 26.dp,
                        y = 58.dp
                    )
                    .clip(shape = RoundedCornerShape(5.dp))
            ) {

//                TextfieldComponenent(Modifier)

            }





        }
        Text(
            text = "Send Money",
            color = Color.White,
//            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .align(alignment = Alignment.TopCenter)
                .offset(
                    x = (-0.5).dp,
                    y = 35.dp
                ))
        Image(
            painter = painterResource(id = R.drawable.arrowback),
            contentDescription = "arrow",
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 26.42108154296875.dp,
                    y = 44.94775390625.dp
                )
                .requiredWidth(width = 20.dp)
                .requiredHeight(height = 14.dp))
    }
}

@Preview(widthDp = 390, heightDp = 844)
@Composable
private fun SendMoneyPreview() {
    val userRepo = UserRepo()
    val transfersViewModel = TransferViewModel(userRepo)
    val navController = rememberNavController()
    val userViewModel = UserViewModel(userRepo)

    SendMoney(Modifier,transfersViewModel = transfersViewModel, navController = navController, viewModel = userViewModel)
}

