package uk.ac.tees.mad.b1970805


import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.*
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.*
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import uk.ac.tees.mad.b1970805.components.CustomButton
import uk.ac.tees.mad.b1970805.model.UserRepo
import uk.ac.tees.mad.b1970805.viewmodel.TransferViewModel


@Composable
fun Start_screen(modifier: Modifier = Modifier, navController:NavController) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.White)
    ) {
        Text(
            text = "Making Mobile Banking Easy",
            color = Color.Black,
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 25.sp),
            modifier = Modifier
                .align(alignment = Alignment.TopCenter)
                .offset(
                    x = (-0.5).dp,
                    y = 403.dp
                )
                .requiredWidth(width = 369.dp))
        Image(
            painter = painterResource(id = R.drawable.bro),
            contentDescription = "finance-app/bro",
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 70.dp,
                    y = 94.dp
                )
                .requiredWidth(width = 250.dp)
                .requiredHeight(height = 241.dp))
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopCenter)
                .offset(
                    x = 0.dp,
                    y = 664.dp
                )
                .requiredWidth(width = 340.dp)
                .requiredHeight(height = 45.dp)
                .clip(shape = RoundedCornerShape(5.dp))
                .background(color = Color(0xff005496))
                .clickable {
                    navController.navigate("registration_screen")
                }

        ) {
            Text(
                text = "Sign Up",
                color = Color.White,
                style = TextStyle(
                    fontSize = 20.sp),
                modifier = Modifier
                    .align(alignment = Alignment.Center)
                    .offset(
                        x = 0.dp,
                        y = 0.5.dp
                    ))
        }
        Surface(
            shape = RoundedCornerShape(5.dp),
            border = BorderStroke(1.dp, Color(0xff005496)),
            modifier = Modifier
                .align(alignment = Alignment.TopCenter)
                .offset(
                    x = 0.dp,
                    y = 734.dp
                )
                .clip(shape = RoundedCornerShape(5.dp))
        ) {
            Box(
                modifier = Modifier
                    .requiredWidth(width = 340.dp)
                    .requiredHeight(height = 45.dp)
                    .clickable {
                        navController.navigate("login_screen")
                    }
            ) {
                Text(
                    text = "Log In",
                    color = Color.Black,
                    style = TextStyle(
                        fontSize = 20.sp),
                    modifier = Modifier
                        .align(alignment = Alignment.Center)
                        .offset(
                            x = 0.dp,
                            y = 0.5.dp
                        ))
            }
        }
        Text(
            text = "Lorem ipsum dolor sit amet consectetur. Sit interdum risus at ipsum eget massa curabitur. Ante cum quis vulputate duis rhoncus nulla accumsan duis porta. Lorem ipsum dolor sit amet consectetur. Sit interdum risus at ipsum eget massa ",
            color = Color(0xff565d64),
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 17.sp),
            modifier = Modifier
                .align(alignment = Alignment.TopCenter)
                .offset(
                    x = (-0.5).dp,
                    y = 488.dp
                )
                .requiredWidth(width = 347.dp))
    }
}

@Preview(widthDp = 390, heightDp = 844)
@Composable
private fun Start_screenPreview() {

    val navController = rememberNavController()
    Start_screen(Modifier, navController= navController)
}




@Composable
fun Success(modifier: Modifier = Modifier, navController: NavController) {
    Box(
        modifier = modifier
            .fillMaxHeight()
            .fillMaxHeight()
//            .clip(shape = RoundedCornerShape(20.dp))
            .background(color = Color.White)
    ) {
        Text(
            text = "Success",
            color = Color.Black,
            style = TextStyle(
                fontSize = 30.sp),
            modifier = Modifier
                .align(alignment = Alignment.TopCenter)
                .offset(
                    x = 0.5.dp,
                    y = 50.dp
                ))
        CustomButton(label = "Log in", onClick = {
            navController.navigate("login_screen")
        })
        Image(
            painter = painterResource(id = R.drawable.bro),
            contentDescription = "successful-purchase/pana",
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 63.dp,
                    y = 158.dp
                )
                .requiredWidth(width = 320.dp)
                .requiredHeight(height = 324.dp))
        Text(
            text = "Success",
            color = Color(0xff08ba25),
            style = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 206.dp,
                    y = 333.dp
                ))
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
                    fontSize = 15.sp))
            Text(
                text = "Sign Up",
                color = Color(0xff005496),
                style = TextStyle(
                    fontSize = 15.sp),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 111.dp,
                        y = 0.dp
                    ))
        }
        Text(
            text = "Your have successfully signed up to superSafe. Enjoy our wonderful experience.",
            color = Color(0xff565d64),
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 17.sp),
            modifier = Modifier
                .align(alignment = Alignment.TopCenter)
                .offset(
                    x = 0.dp,
                    y = 533.dp
                )
                .requiredWidth(width = 314.dp))
    }
}

@Preview(widthDp = 390, heightDp = 844)
@Composable
private fun SuccessPreview() {

    val navController = rememberNavController()
    Success(Modifier, navController = navController)
}




@Composable
fun Pre_Login(modifier: Modifier = Modifier) {
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
                fontSize = 30.sp),
            modifier = Modifier
                .align(alignment = Alignment.TopCenter)
                .offset(
                    x = 0.5.dp,
                    y = 50.dp
                ))
        CustomButton(label = "Log in", isEnabled =  false, onClick = {})
        Image(
            painter = painterResource(id = R.drawable.loginbro),
            contentDescription = "successful-purchase/pana",
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 63.dp,
                    y = 158.dp
                )
                .requiredWidth(width = 320.dp)
                .requiredHeight(height = 324.dp))
        Text(
            text = "Success",
            color = Color(0xff08ba25),
            style = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 206.dp,
                    y = 333.dp
                ))
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
                    fontSize = 15.sp))
            Text(
                text = "Sign Up",
                color = Color(0xff005496),
                style = TextStyle(
                    fontSize = 15.sp),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 111.dp,
                        y = 0.dp
                    ))
        }

    }
}

@Preview(widthDp = 390, heightDp = 844)
@Composable
private fun Pre_LoginPreview() {
    Pre_Login(Modifier)
}
//
//@Composable
//fun SendMoney(modifier: Modifier = Modifier) {
//    Box(
//        modifier = modifier
//            .fillMaxWidth()
//            .fillMaxHeight()
//            .background(color = Color(0xff005496))
//    ) {
//        Box(
//            modifier = Modifier
//                .align(alignment = Alignment.TopStart)
//                .offset(
//                    x = 0.dp,
//                    y = 79.dp
//                )
//                .fillMaxWidth()
//                .fillMaxHeight()
//                .clip(
//                    shape = RoundedCornerShape(
//                        topStart = 30.dp,
//                        topEnd = 30.dp,
////                        bottomStart = 20.dp,
////                        bottomEnd = 20.dp
//                    )
//                )
//                .background(color = Color.White)
//        ) {
//
//
//
//            Column(
//                horizontalAlignment = Alignment.CenterHorizontally,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .offset(
//                        y = 50.dp
//                    )
//            ) {
//                Column{
//                    Text(
//                        text = "Recipient Mobile Number",
//                        color = Color.Black,
//                        style = TextStyle(
//                            fontSize = 16.sp,
//                            fontWeight = FontWeight.Medium),
//                        modifier = Modifier
//                    )
//
//                    TransferText(modifier= Modifier)
//                }
//
//
//                Spacer(modifier = Modifier.height(16.dp))
//
//                Column{
//                    Text(
//                        text = "Amount",
//                        color = Color.Black,
//                        style = TextStyle(
//                            fontSize = 16.sp,
//                            fontWeight = FontWeight.Medium),
//                        modifier = Modifier
//                    )
//                    AmountTextField(modifier);
//
//                }
//
//
//                Spacer(modifier = Modifier.height(16.dp))
//
//                Column {
//                    Text(
//                        text = "Narration",
//                        color = Color.Black,
//                        style = TextStyle(
//                            fontSize = 16.sp,
//                            fontWeight = FontWeight.Medium),
//                        modifier = Modifier
//
//                    )
//
//                    TextArea(modifier)
//                }
//
//
//                Spacer(modifier = Modifier.height(300.dp))
//
//                Column{
//                    ButtonTransfer( modifier = Modifier.offset(y = 645.dp),
//                        onClick = {
//                            // Handle button click
//                        }
//                    )
//
//                }
//
//
//
//            }
//
//
//
//
//            Surface(
//                shape = RoundedCornerShape(5.dp),
//                color = Color.White.copy(alpha = 0.6f),
//                border = BorderStroke(1.dp, Color.Black.copy(alpha = 0.2f)),
//                modifier = Modifier
//                    .align(alignment = Alignment.TopStart)
//                    .offset(
//                        x = 26.dp,
//                        y = 58.dp
//                    )
//                    .clip(shape = RoundedCornerShape(5.dp))
//            ) {
//
////                TextfieldComponenent(Modifier)
//
//            }
//
//
//
//
//
//        }
//        Text(
//            text = "Send Money",
//            color = Color.White,
////            style = MaterialTheme.typography.titleLarge,
//            modifier = Modifier
//                .align(alignment = Alignment.TopCenter)
//                .offset(
//                    x = (-0.5).dp,
//                    y = 35.dp
//                ))
//        Image(
//            painter = painterResource(id = R.drawable.arrowback),
//            contentDescription = "arrow",
//            modifier = Modifier
//                .align(alignment = Alignment.TopStart)
//                .offset(
//                    x = 26.42108154296875.dp,
//                    y = 44.94775390625.dp
//                )
//                .requiredWidth(width = 20.dp)
//                .requiredHeight(height = 14.dp))
//    }
//}
//
//@Preview(widthDp = 390, heightDp = 844)
//@Composable
//private fun SendMoneyPreview() {
//    SendMoney(Modifier)
//}
@Composable
fun Cards(modifier: Modifier = Modifier) {
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
                .fillMaxWidth()
                .fillMaxHeight()
                .clip(
                    shape = RoundedCornerShape(
                        topStart = 30.dp,
                        topEnd = 30.dp,
                        bottomStart = 20.dp,
                        bottomEnd = 20.dp
                    )
                )
                .background(color = Color.White)
        ) {

            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopCenter)
                    .offset(
                        x = 0.dp,
                        y = 40.dp
                    )
                    .requiredWidth(width = 350.dp)
                    .requiredHeight(height = 88.dp)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(
                        brush = Brush.linearGradient(
                            0f to Color(0xffff9921),
//                        0.52f to Color(0xffce124a).copy(alpha = 0.84f),
//                        0.62f to Color(0xff4dff21),
                            0.79f to Color(0xfff20000),
//                        1f to Color(0xffe021ff),
                            start = Offset(-153f, -55f),
                            end = Offset(793.5f, 208.51f)
                        )
                    )
            ) {
                Text(
                    text = "Current Account",
                    color = Color.White,
                    style = TextStyle(
                        fontSize = 17.sp),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = 13.dp,
                            y = 58.dp
                        ))
                Image(
                    painter = painterResource(id = R.drawable.simpleiconsvisa),
                    contentDescription = "simple-icons:visa",
                    colorFilter = ColorFilter.tint(Color.White),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = 13.dp,
                            y = 10.dp
                        )
                        .requiredSize(size = 24.dp))
                Text(
                    text = "Card Info",
                    color = Color.White,
                    textDecoration = TextDecoration.Underline,
                    style = TextStyle(
                        fontSize = 10.sp),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = 288.dp,
                            y = 62.dp
                        ))
            }
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopCenter)
                    .offset(
                        x = 0.dp,
                        y = 148.dp
                    )
                    .requiredWidth(width = 350.dp)
                    .requiredHeight(height = 88.dp)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(
                        brush = Brush.linearGradient(
                            0f to Color(0xff018787),
                            0.24f to Color(0xff189004).copy(alpha = 0.84f),
//                        0.78f to Color(0xfff26600),
                            0.78f to Color(0xff6c7d01),
                            1f to Color(0xff565803),
                            start = Offset(-25.65f, -14.15f),
                            end = Offset(1045.47f, 283.69f)
                        )
                    )
            ) {
                Text(
                    text = "Savings Account",
                    color = Color.White,
                    style = TextStyle(
                        fontSize = 17.sp),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = 13.dp,
                            y = 58.dp
                        ))
                Image(
                    painter = painterResource(id = R.drawable.simpleiconsvisa),
                    contentDescription = "simple-icons:visa",
                    colorFilter = ColorFilter.tint(Color.White),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = 13.dp,
                            y = 10.dp
                        )
                        .requiredSize(size = 24.dp))
                Text(
                    text = "Card Info",
                    color = Color.White,
                    textDecoration = TextDecoration.Underline,
                    style = TextStyle(
                        fontSize = 10.sp),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = 288.dp,
                            y = 62.dp
                        ))
            }

        }
        Text(
            text = "Cards",
            color = Color.White,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .align(alignment = Alignment.TopCenter)
                .offset(
                    x = 0.5.dp,
                    y = 35.dp
                ))
    }
}

@Preview(widthDp = 390, heightDp = 844)
@Composable
private fun CardsPreview() {
    Cards(Modifier)
}

@Composable
fun Help(modifier: Modifier = Modifier) {
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
                .fillMaxWidth()
                .fillMaxHeight()
//                .clip(shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp, bottomStart = 20.dp, bottomEnd = 20.dp))
                .background(color = Color.White)
        ) {
            Spacer(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 323.dp,
                        y = 444.dp
                    )
                    .requiredSize(size = 24.dp))

            Text(
                text = "Call Us",
                color = Color.Black,
                style = TextStyle(
                    fontSize = 17.sp),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 51.dp,
                        y = 54.dp
                    ))
            Text(
                text = "Email Us",
                color = Color.Black,
                style = TextStyle(
                    fontSize = 17.sp),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 51.dp,
                        y = 194.dp
                    ))
            Text(
                text = "+44 678 123 890",
                color = Color(0xff005496),
                style = TextStyle(
                    fontSize = 16.sp),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 17.dp,
                        y = 93.dp
                    ))
            Text(
                text = "+44 676 423 800",
                color = Color(0xff005496),
                style = TextStyle(
                    fontSize = 16.sp),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 17.dp,
                        y = 132.dp
                    ))
            Text(
                text = "helpdesk@gmail.com.uk",
                color = Color(0xff005496),
                style = TextStyle(
                    fontSize = 16.sp),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 20.dp,
                        y = 234.dp
                    ))
            Divider(
                color = Color.Black.copy(alpha = 0.4f),
                modifier = Modifier
                    .align(alignment = Alignment.TopCenter)
                    .offset(
                        x = 0.0113067626953125.dp,
                        y = 169.dp
                    )
                    .requiredWidth(width = 366.dp))
            Divider(
                color = Color.Black.copy(alpha = 0.4f),
                modifier = Modifier
                    .align(alignment = Alignment.TopCenter)
                    .offset(
                        x = 0.0113067626953125.dp,
                        y = 271.dp
                    )
                    .requiredWidth(width = 366.dp))
            Divider(
                color = Color.Black.copy(alpha = 0.4f),
                modifier = Modifier
                    .align(alignment = Alignment.TopCenter)
                    .offset(
                        x = 0.0113067626953125.dp,
                        y = 373.dp
                    )
                    .requiredWidth(width = 366.dp))
            Icon(
                painter = painterResource(id = R.drawable.nototelephonereceiver),
                contentDescription = "noto:telephone-receiver",
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 17.dp,
                        y = 54.dp
                    )
                    .requiredSize(size = 20.dp))
            Image(
                painter = painterResource(id = R.drawable.dashiconsemailalt2),
                contentDescription = "dashicons:email-alt2",
                colorFilter = ColorFilter.tint(Color.Black),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 17.dp,
                        y = 194.dp
                    )
                    .requiredSize(size = 20.dp))
            Text(
                text = "Social Media Platforms",
                color = Color.Black,
                style = TextStyle(
                    fontSize = 17.sp),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 17.dp,
                        y = 291.dp
                    ))
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 12.dp,
                        y = 327.dp
                    )
                    .requiredSize(size = 30.dp)
                    .clip(shape = RoundedCornerShape(100.dp))
                    .border(
                        border = BorderStroke(0.699999988079071.dp, Color.Black.copy(alpha = 0.4f)),
                        shape = RoundedCornerShape(100.dp)
                    ))
            Image(
                painter = painterResource(id = R.drawable.logoswhatsappicon),
                contentDescription = "logos:whatsapp-icon",
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 19.dp,
                        y = 334.dp
                    )
                    .requiredSize(size = 16.dp))
            Surface(
                shape = RoundedCornerShape(100.dp),
                border = BorderStroke(0.699999988079071.dp, Color.Black.copy(alpha = 0.4f)),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 72.dp,
                        y = 327.dp
                    )
                    .clip(shape = RoundedCornerShape(100.dp))
            ) {
                Box(
                    modifier = Modifier
                        .requiredSize(size = 30.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.pajamastwitter),
                        contentDescription = "pajamas:twitter",
                        colorFilter = ColorFilter.tint(Color.Black),
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(
                                x = 7.dp,
                                y = 7.dp
                            )
                            .requiredSize(size = 16.dp))
                }
            }
            Surface(
                shape = RoundedCornerShape(100.dp),
                border = BorderStroke(0.699999988079071.dp, Color.Black.copy(alpha = 0.4f)),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 132.dp,
                        y = 327.dp
                    )
                    .clip(shape = RoundedCornerShape(100.dp))
            ) {
                Box(
                    modifier = Modifier
                        .requiredSize(size = 30.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.skilliconsinstagram),
                        contentDescription = "skill-icons:instagram",
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(
                                x = 7.dp,
                                y = 7.dp
                            )
                            .requiredSize(size = 16.dp))
                }
            }
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 192.dp,
                        y = 327.dp
                    )
                    .requiredSize(size = 30.dp)
                    .clip(shape = RoundedCornerShape(100.dp))
                    .border(
                        border = BorderStroke(0.699999988079071.dp, Color.Black.copy(alpha = 0.4f)),
                        shape = RoundedCornerShape(100.dp)
                    ))
            Surface(
                shape = RoundedCornerShape(100.dp),
                border = BorderStroke(0.699999988079071.dp, Color.Black.copy(alpha = 0.4f)),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 252.dp,
                        y = 327.dp
                    )
                    .clip(shape = RoundedCornerShape(100.dp))
            ) {
                Box(
                    modifier = Modifier
                        .requiredSize(size = 30.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logosfacebook),
                        contentDescription = "logos:facebook",
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(
                                x = 7.dp,
                                y = 7.dp
                            )
                            .requiredSize(size = 16.dp))
                }
            }
            Surface(
                shape = RoundedCornerShape(100.dp),
                border = BorderStroke(0.699999988079071.dp, Color.Black.copy(alpha = 0.4f)),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 312.dp,
                        y = 327.dp
                    )
                    .clip(shape = RoundedCornerShape(100.dp))
            ) {
                Box(
                    modifier = Modifier
                        .requiredSize(size = 30.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logostelegram),
                        contentDescription = "logos:telegram",
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(
                                x = 7.dp,
                                y = 7.dp
                            )
                            .requiredSize(size = 16.dp))
                }
            }
            Image(
                painter = painterResource(id = R.drawable.logostiktokicon),
                contentDescription = "logos:tiktok-icon",
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 199.dp,
                        y = 334.dp
                    )
                    .requiredWidth(width = 16.dp)
                    .requiredHeight(height = 18.dp))
            Spacer(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 175.dp,
                        y = 551.dp
                    )
                    .requiredSize(size = 20.dp))
        }
        Text(
            text = "Help",
            color = Color.White,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .align(alignment = Alignment.TopCenter)
                .offset(
                    x = 0.dp,
                    y = 35.dp
                ))
    }
}

@Preview(widthDp = 390, heightDp = 844)
@Composable
private fun HelpPreview() {
    Help(Modifier)
}



@Preview(widthDp = 352, heightDp = 89)
@Composable
private fun Frame43Preview() {
//    AmountCard(Modifier)
}


//@Preview(widthDp = 145, heightDp = 44)
//@Composable
//private fun Group44Preview() {
//    Group44(Modifier)
//}
@Composable
fun Transaction_Sucess(modifier: Modifier = Modifier, navController: NavController, transferViewModel: TransferViewModel) {

    val amount = transferViewModel.amount.value
    val number = transferViewModel.recipientNUmber.value
    val narration = transferViewModel.narration.value

    Log.d("UserDataPayment", "number:$number")
    Log.d("UserDataPayment", "number:$amount")
    Log.d("UserDataPayment", "number:$narration")




    Box(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = Color.White)
    ) {
        Spacer(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 323.dp,
                    y = 444.dp
                )
                .requiredSize(size = 24.dp))
        Surface(
            shape = RoundedCornerShape(5.dp),
            border = BorderStroke(1.dp, Color(0xff005496)),
            modifier = Modifier
                .align(alignment = Alignment.TopCenter)
                .offset(
                    x = 0.dp,
                    y = 679.dp
                )
                .clip(shape = RoundedCornerShape(5.dp))
        ) {
            Box(
                modifier = Modifier
                    .requiredWidth(width = 340.dp)
                    .requiredHeight(height = 45.dp)
            ) {
                Text(
                    text = "View Receipt",
                    color = Color.Black,
                    style = TextStyle(
                        fontSize = 20.sp),
                    modifier = Modifier
                        .align(alignment = Alignment.Center)
                        .offset(
                            x = 0.5.dp,
                            y = 0.5.dp
                        ))
            }
        }
        Button(
            onClick = { navController.navigate("dashboard")},
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xff005496)),
            modifier = Modifier
                .align(alignment = Alignment.TopCenter)
                .offset(
                    x = 0.dp,
                    y = 738.dp
                )
                .requiredWidth(width = 340.dp)
                .requiredHeight(height = 45.dp)
        ) {
            Box(
                modifier = Modifier
                    .requiredWidth(width = 340.dp)
                    .requiredHeight(height = 45.dp)
            ) {
                Text(
                    text = "Go Home",
                    color = Color.White,
                    style = TextStyle(
                        fontSize = 20.sp),
                    modifier = Modifier
                        .align(alignment = Alignment.Center)
                        .offset(
                            x = 0.dp,
                            y = 0.5.dp
                        ))
            }
        }
        Text(
            text = "You have successfully transferred £$amount.00\nto +44$number",
            color = Color(0xff565d64),
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 16.sp),
            modifier = Modifier
                .align(alignment = Alignment.TopCenter)
                .offset(
                    x = 0.dp,
                    y = 422.dp
                ))
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 97.dp,
                    y = 114.dp
                )
                .requiredWidth(width = 200.dp)
                .requiredHeight(height = 210.dp)
        ) {

            Image(
                painter = painterResource(id = R.drawable.successicon),
                contentDescription = "image description",
                contentScale = ContentScale.None,
                modifier = Modifier
                    .size(250.dp)
            )



        }
        Text(
            text = "Transaction Successful",
            color = Color.Black,
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium),
            modifier = Modifier
                .align(alignment = Alignment.TopCenter)
                .offset(
                    x = 0.dp,
                    y = 378.dp
                ))
    }
}

@Preview(widthDp = 390, heightDp = 844)
@Composable
private fun Transaction_SucessPreview() {
    val navController = rememberNavController()
    val userRepo = UserRepo()
    val transferViewModel = TransferViewModel(userRepo)

    Transaction_Sucess(Modifier, navController = navController, transferViewModel = transferViewModel)
}


@Composable
fun Failed(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = Color.White)
    ) {
        Spacer(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 323.dp,
                    y = 444.dp
                )
                .requiredSize(size = 24.dp))
        OutlinedButton(
            onClick = { },
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            border = BorderStroke(1.dp, Color(0xff005496)),
            modifier = Modifier
                .align(alignment = Alignment.TopCenter)
                .offset(
                    x = 0.dp,
                    y = 679.dp
                )
                .requiredWidth(width = 340.dp)
                .requiredHeight(height = 45.dp)
        ) {
            Box(
                modifier = Modifier
                    .requiredWidth(width = 340.dp)
                    .requiredHeight(height = 45.dp)
            ) {
                Text(
                    text = "Go to Transfers",
                    color = Color.Black,
                    style = TextStyle(
                        fontSize = 20.sp),
                    modifier = Modifier
                        .align(alignment = Alignment.Center)
                        .offset(
                            x = 0.dp,
                            y = 0.5.dp
                        ))
            }
        }
        Button(
            onClick = { },
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xff005496)),
            modifier = Modifier
                .align(alignment = Alignment.TopCenter)
                .offset(
                    x = 0.dp,
                    y = 738.dp
                )
                .requiredWidth(width = 340.dp)
                .requiredHeight(height = 45.dp)
        ) {
            Box(
                modifier = Modifier
                    .requiredWidth(width = 340.dp)
                    .requiredHeight(height = 45.dp)
            ) {
                Text(
                    text = "Go Home",
                    color = Color.White,
                    style = TextStyle(
                        fontSize = 20.sp),
                    modifier = Modifier
                        .align(alignment = Alignment.Center)
                        .offset(
                            x = 0.dp,
                            y = 0.5.dp
                        ))
            }
        }
        Text(
            text = "Your transfer of £500.00 to +44123456789 was \nunsuccessful.  ",
            color = Color(0xff565d64),
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 16.sp),
            modifier = Modifier
                .align(alignment = Alignment.TopCenter)
                .offset(
                    x = 0.dp,
                    y = 422.dp
                ))
        Text(
            text = "Transaction Failed",
            color = Color.Black,
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium),
            modifier = Modifier
                .align(alignment = Alignment.TopCenter)
                .offset(
                    x = 0.5.dp,
                    y = 378.dp
                ))
        Image(
            painter = painterResource(id = R.drawable.failed),
            contentDescription = "Group 37",
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 130.51953125.dp,
                    y = 152.54736328125.dp
                )
                .requiredSize(size = 133.dp))
    }
}

@Preview(widthDp = 390, heightDp = 844)
@Composable
private fun FailedPreview() {
    Failed(Modifier)
}

@Composable
fun Receipt(modifier: Modifier = Modifier) {
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
                .requiredWidth(width = 390.dp)
                .requiredHeight(height = 765.dp)
                .clip(shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                .background(color = Color.White)
        ) {
            Spacer(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 323.dp,
                        y = 444.dp
                    )
                    .requiredSize(size = 24.dp))
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopCenter)
                    .offset(
                        x = 0.dp,
                        y = 91.dp
                    )
                    .requiredWidth(width = 292.dp)
                    .requiredHeight(height = 377.dp)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(color = Color.White)
            ) {
                Box(
                    modifier = Modifier
                        .requiredWidth(width = 292.dp)
                        .requiredHeight(height = 42.dp)
                        .background(color = Color(0xff005496))
                ) {
                    Text(
                        text = "Funds Transfer",
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            fontSize = 16.sp),
                        modifier = Modifier
                            .align(alignment = Alignment.TopCenter)
                            .offset(
                                x = 0.5.dp,
                                y = 11.dp
                            ))
//                    Image(
//                        painter = painterResource(id = R.drawable.systemuiconsdocument),
//                        contentDescription = "system-uicons:document",
//                        modifier = Modifier
//                            .align(alignment = Alignment.TopStart)
//                            .offset(x = 15.dp,
//                                y = 11.dp)
//                            .requiredSize(size = 21.dp))
                }
                Text(
                    text = "Date",
                    color = Color(0xff565d64).copy(alpha = 0.6f),
                    style = TextStyle(
                        fontSize = 16.sp),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = 15.dp,
                            y = 93.dp
                        ))
                Text(
                    text = "Status",
                    color = Color(0xff565d64).copy(alpha = 0.6f),
                    style = TextStyle(
                        fontSize = 16.sp),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = 15.dp,
                            y = 130.dp
                        ))
                Text(
                    text = "Amount",
                    color = Color(0xff565d64).copy(alpha = 0.6f),
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 16.sp),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = 15.dp,
                            y = 167.dp
                        ))
                Text(
                    text = "Mobile Number",
                    color = Color(0xff565d64).copy(alpha = 0.6f),
                    style = TextStyle(
                        fontSize = 16.sp),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = 15.dp,
                            y = 204.dp
                        ))
                Text(
                    text = "Narration",
                    color = Color(0xff565d64).copy(alpha = 0.6f),
                    style = TextStyle(
                        fontSize = 16.sp),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = 15.dp,
                            y = 241.dp
                        ))
                Text(
                    text = "Transaction ID",
                    color = Color(0xff565d64).copy(alpha = 0.6f),
                    style = TextStyle(
                        fontSize = 16.sp),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = 15.dp,
                            y = 278.dp
                        ))
                Text(
                    text = "Recipient",
                    color = Color(0xff565d64).copy(alpha = 0.6f),
                    style = TextStyle(
                        fontSize = 16.sp),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = 15.dp,
                            y = 56.dp
                        ))
                Text(
                    text = "20th March, 2023",
                    color = Color.Black,
                    textAlign = TextAlign.End,
                    style = TextStyle(
                        fontSize = 16.sp),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = 152.dp,
                            y = 93.dp
                        ))
                Text(
                    text = "Successful",
                    color = Color.Black,
                    textAlign = TextAlign.End,
                    style = TextStyle(
                        fontSize = 16.sp),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = 198.dp,
                            y = 130.dp
                        ))
                Text(
                    text = "£150.00",
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 16.sp),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = 219.dp,
                            y = 167.dp
                        ))
                Text(
                    text = "+44123456789",
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 16.sp),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = 169.dp,
                            y = 204.dp
                        ))
                Text(
                    text = "Food and Cloths",
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 16.sp),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = 157.dp,
                            y = 241.dp
                        ))
                Text(
                    text = "167XNMIS92",
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 16.sp),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = 182.dp,
                            y = 278.dp
                        ))
                Text(
                    text = "James Kennedy",
                    color = Color.Black,
                    textAlign = TextAlign.End,
                    style = TextStyle(
                        fontSize = 16.sp),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = 162.dp,
                            y = 56.dp
                        ))
                Surface(
                    shape = RoundedCornerShape(500.dp),
                    border = BorderStroke(0.699999988079071.dp, Color.Black.copy(alpha = 0.4f)),
                    modifier = Modifier
                        .align(alignment = Alignment.TopCenter)
                        .offset(
                            x = 0.5.dp,
                            y = 330.dp
                        )
                        .clip(shape = RoundedCornerShape(500.dp))
                ) {
                    Box(
                        modifier = Modifier
                            .requiredWidth(width = 113.dp)
                            .requiredHeight(height = 35.dp)
                    ) {
                        Text(
                            text = "Share",
                            color = Color.Black,
                            style = TextStyle(
                                fontSize = 16.sp),
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(
                                    x = 51.dp,
                                    y = 8.dp
                                ))
//                        Image(
//                            painter = painterResource(id = R.drawable.mdilightshare),
//                            contentDescription = "mdi-light:share",
//                            colorFilter = ColorFilter.tint(Color.Black),
//                            modifier = Modifier
//                                .align(alignment = Alignment.TopStart)
//                                .offset(x = 20.dp,
//                                    y = 6.dp)
//                                .requiredSize(size = 24.dp))
                    }
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
                    .background(color = Color(0xff005496))
            ) {
                Text(
                    text = "Go Home",
                    color = Color.White,
                    style = TextStyle(
                        fontSize = 20.sp),
                    modifier = Modifier
                        .align(alignment = Alignment.Center)
                        .offset(
                            x = 0.dp,
                            y = 0.5.dp
                        ))
            }
        }
        Text(
            text = "Receipt",
            color = Color.White,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .align(alignment = Alignment.TopCenter)
                .offset(
                    x = 0.dp,
                    y = 35.dp
                ))
        Image(
            painter = painterResource(id = R.drawable.arrow),
            contentDescription = "arrow",
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 26.421142578125.dp,
                    y = 44.94775390625.dp
                )
                .requiredWidth(width = 20.dp)
                .requiredHeight(height = 14.dp))
    }
}

@Preview(widthDp = 390, heightDp = 844)
@Composable
private fun ReceiptPreview() {
    Receipt(Modifier)
}