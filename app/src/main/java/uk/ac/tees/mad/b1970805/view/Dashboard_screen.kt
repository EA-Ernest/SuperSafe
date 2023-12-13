package uk.ac.tees.mad.b1970805.view


import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import uk.ac.tees.mad.b1970805.R
import uk.ac.tees.mad.b1970805.components.ButtonNavigation
import uk.ac.tees.mad.b1970805.model.UserFirestore
import uk.ac.tees.mad.b1970805.viewmodel.UserViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Dashboard(modifier: Modifier = Modifier, navController: NavController, viewModel: UserViewModel) {

    var selectedTabIndex by remember { mutableStateOf(0) }
    val userModel by viewModel.user.collectAsState()
    val logoutEvent by viewModel.logoutEvent.collectAsState()

    Log.d("UserData", "User:$userModel ")

    if (logoutEvent) {
        // Navigate to the login screen
        navController.navigate("login")
    }


//    if (userModel == null){
//        navController.navigate("login")
//    }

    Scaffold(
        bottomBar = {
            // Call your existing BottomNavigation function here
            ButtonNavigation(
                navController = navController,
                selectedTabIndex = selectedTabIndex,
                onTabSelected = { index ->
                    // Handle tab selection
                }
            )
        }
    ) {

        Box(
            modifier = modifier
                .fillMaxHeight()
                .fillMaxWidth()
//            .clip(shape = RoundedCornerShape(20.dp))
                .background(color = Color(0xfff9f6ee))
        ) {
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 14.dp,
                        y = 34.dp
                    )
                    .requiredSize(size = 50.dp)
                    .clip(shape = CircleShape)
                    .background(color = Color.White)
            )
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Color(0xff565d64),
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Light
                        )
                    ) { append("Hello") }
                    withStyle(
                        style = SpanStyle(
                            color = Color(0xff565d64),
                            fontSize = 17.sp
                        )
                    ) { append(" ") }
                    withStyle(
                        style = SpanStyle(
                            color = Color.Black,
                            fontSize = 20.sp
                        )
                    ) { append("${userModel?.fname}") }
                },
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 77.dp,
                        y = 46.dp
                    )
            )
            Image(
                painter = painterResource(id = R.drawable.bell),
                contentDescription = "bell",
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 342.dp,
                        y = 47.dp
                    )
                    .requiredSize(size = 24.dp)
                    .clickable{
//                        viewModel.logout()
                    }
            )
            Image(
                painter = painterResource(id = R.drawable.power),
                contentDescription = "bell",
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 370.dp,
                        y = 47.dp
                    )
                    .requiredSize(size = 24.dp)
                    .clickable{
                        viewModel.logout()
                    }
            )
            Image(
                painter = painterResource(id = R.drawable.rectangle),
                contentDescription = "Rectangle",
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 24.dp,
                        y = 44.dp
                    )
                    .requiredSize(size = 30.dp)
                    .clip(shape = RoundedCornerShape(50.dp))
            )
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopCenter)
                    .offset(
                        x = 0.dp,
                        y = 205.dp
                    )
                    .requiredWidth(width = 390.dp)
                    .fillMaxHeight()
                    .clip(shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
                    .background(color = Color.White)
            ) {
                Box(
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = 14.dp,
                            y = 280.dp
                        )
                        .requiredWidth(width = 352.dp)
                        .requiredHeight(height = 35.dp)
                ) {
                    Text(
                        text = "+£250.00",
                        color = Color(0xff05870a),
                        style = TextStyle(
                            fontSize = 16.sp
                        ),
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(
                                x = 284.dp,
                                y = 0.dp
                            )
                    )
                    Text(
                        text = "Sarah Tasha",
                        color = Color.Black,
                        style = TextStyle(
                            fontSize = 16.sp
                        ),
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(
                                x = 45.dp,
                                y = 0.dp
                            )
                    )
                    Text(
                        text = "12th Jan, 2023",
                        color = Color(0xff565d64),
                        style = TextStyle(
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Light
                        ),
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(
                                x = 45.dp,
                                y = 23.dp
                            )
                    )
                    Image(
                        painter = painterResource(id = R.drawable.rectangle),
                        contentDescription = "Rectangle",
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(
                                x = 0.dp,
                                y = 4.dp
                            )
                            .requiredSize(size = 30.dp)
                            .clip(shape = RoundedCornerShape(50.dp))
                    )
                }
                Box(
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = 14.dp,
                            y = 342.dp
                        )
                        .requiredWidth(width = 352.dp)
                        .requiredHeight(height = 35.dp)
                ) {
                    Text(
                        text = "KFC",
                        color = Color.Black,
                        style = TextStyle(
                            fontSize = 16.sp
                        ),
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(
                                x = 46.dp,
                                y = 0.dp
                            )
                    )
                    Text(
                        text = "-£50.00",
                        color = Color(0xffe80d0d),
                        style = TextStyle(
                            fontSize = 16.sp
                        ),
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(
                                x = 296.dp,
                                y = 0.dp
                            )
                    )
                    Text(
                        text = "12th Jan, 2023",
                        color = Color(0xff565d64),
                        style = TextStyle(
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Light
                        ),
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(
                                x = 45.dp,
                                y = 23.dp
                            )
                    )
                    Image(
                        painter = painterResource(id = R.drawable.emojionepotoffood),
                        contentDescription = "emojione:pot-of-food",
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(
                                x = 0.dp,
                                y = 8.dp
                            )
                            .requiredSize(size = 20.dp)
                    )
                }
                Divider(
                    color = Color.Black.copy(alpha = 0.4f),
                    thickness = 0.5.dp,
                    modifier = Modifier
                        .align(alignment = Alignment.TopCenter)
                        .offset(
                            x = 0.0113067626953125.dp,
                            y = 329.dp
                        )
                        .requiredWidth(width = 366.dp)
                )
                Divider(
                    color = Color.Black.copy(alpha = 0.4f),
                    thickness = 0.5.dp,
                    modifier = Modifier
                        .align(alignment = Alignment.TopCenter)
                        .offset(
                            x = 0.0113067626953125.dp,
                            y = 390.dp
                        )
                        .requiredWidth(width = 366.dp)
                )
                Divider(
                    color = Color.Black.copy(alpha = 0.4f),
                    thickness = 0.5.dp,

                    modifier = Modifier
                        .align(alignment = Alignment.TopCenter)
                        .offset(
                            x = 0.0113067626953125.dp,
                            y = 451.dp
                        )
                        .requiredWidth(width = 366.dp)
                )
                Divider(
                    color = Color.Black.copy(alpha = 0.4f),
                    thickness = 0.5.dp,

                    modifier = Modifier
                        .align(alignment = Alignment.TopCenter)
                        .offset(
                            x = 0.0113067626953125.dp,
                            y = 512.dp
                        )
                        .requiredWidth(width = 366.dp)
                )
                Box(
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = 14.dp,
                            y = 403.dp
                        )
                        .requiredWidth(width = 352.dp)
                        .requiredHeight(height = 35.dp)
                ) {
                    Text(
                        text = "Joham Hotel",
                        color = Color.Black,
                        style = TextStyle(
                            fontSize = 16.sp
                        ),
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(
                                x = 45.dp,
                                y = 0.dp
                            )
                    )
                    Text(
                        text = "-£78.00",
                        color = Color(0xffe80d0d),
                        style = TextStyle(
                            fontSize = 16.sp
                        ),
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(
                                x = 296.dp,
                                y = 0.dp
                            )
                    )
                    Text(
                        text = "12th Jan, 2023",
                        color = Color(0xff565d64),
                        style = TextStyle(
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Light
                        ),
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(
                                x = 45.dp,
                                y = 23.dp
                            )
                    )
                    Image(
                        painter = painterResource(id = R.drawable.notov1lovehotel),
                        contentDescription = "noto-v1:love-hotel",
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(
                                x = 0.dp,
                                y = 8.dp
                            )
                            .requiredSize(size = 20.dp)
                    )
                }
                Box(
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = 14.dp,
                            y = 464.dp
                        )
                        .requiredWidth(width = 352.dp)
                        .requiredHeight(height = 35.dp)
                ) {
                    Text(
                        text = "Franklin John",
                        color = Color.Black,
                        style = TextStyle(
                            fontSize = 16.sp
                        ),
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(
                                x = 45.dp,
                                y = 0.dp
                            )
                    )
                    Text(
                        text = "+£110.00",
                        color = Color(0xff05870a),
                        style = TextStyle(
                            fontSize = 16.sp
                        ),
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(
                                x = 284.dp,
                                y = 0.dp
                            )
                    )
                    Text(
                        text = "12th Jan, 2023",
                        color = Color(0xff565d64),
                        style = TextStyle(
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Light
                        ),
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(
                                x = 45.dp,
                                y = 23.dp
                            )
                    )
                    Image(
                        painter = painterResource(id = R.drawable.rectangle),
                        contentDescription = "Rectangle",
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(
                                x = 0.dp,
                                y = 5.dp
                            )
                            .requiredSize(size = 25.dp)
                            .clip(shape = RoundedCornerShape(50.dp))
                    )
                }
                Box(
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = 14.dp,
                            y = 525.dp
                        )
                        .requiredWidth(width = 352.dp)
                        .requiredHeight(height = 35.dp)
                ) {
                    Text(
                        text = "Christian Dior",
                        color = Color.Black,
                        style = TextStyle(
                            fontSize = 16.sp
                        ),
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(
                                x = 45.dp,
                                y = 0.dp
                            )
                    )
                    Text(
                        text = "+£110.00",
                        color = Color(0xff05870a),
                        style = TextStyle(
                            fontSize = 16.sp
                        ),
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(
                                x = 284.dp,
                                y = 0.dp
                            )
                    )
                    Text(
                        text = "12th Jan, 2023",
                        color = Color(0xff565d64),
                        style = TextStyle(
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Light
                        ),
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(
                                x = 45.dp,
                                y = 23.dp
                            )
                    )
                    Image(
                        painter = painterResource(id = R.drawable.rectangle),
                        contentDescription = "Rectangle",
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(
                                x = 0.dp,
                                y = 5.dp
                            )
                            .requiredSize(size = 25.dp)
                            .clip(shape = RoundedCornerShape(50.dp))
                    )
                }
                Text(
                    text = "View",
                    color = Color(0xff005496),
                    textDecoration = TextDecoration.Underline,
                    style = TextStyle(
                        fontSize = 10.sp
                    ),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = 344.dp,
                            y = 303.dp
                        )
                )
                Text(
                    text = "View",
                    color = Color(0xff005496),
                    textDecoration = TextDecoration.Underline,
                    style = TextStyle(
                        fontSize = 10.sp
                    ),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = 344.dp,
                            y = 363.dp
                        )
                )
                Text(
                    text = "View",
                    color = Color(0xff005496),
                    textDecoration = TextDecoration.Underline,
                    style = TextStyle(
                        fontSize = 10.sp
                    ),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = 344.dp,
                            y = 424.dp
                        )
                )
                Text(
                    text = "View",
                    color = Color(0xff005496),
                    textDecoration = TextDecoration.Underline,
                    style = TextStyle(
                        fontSize = 10.sp
                    ),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = 344.dp,
                            y = 485.dp
                        )
                )
                Text(
                    text = "View",
                    color = Color(0xff005496),
                    textDecoration = TextDecoration.Underline,
                    style = TextStyle(
                        fontSize = 10.sp
                    ),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = 344.dp,
                            y = 546.dp
                        )
                )
            }

            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 14.dp,
                        y = 230.dp
                    )
                    .requiredWidth(width = 352.dp)
                    .requiredHeight(height = 230.dp)
            ) {
                Text(
                    text = "Recent Transactions",
                    color = Color.Black,
                    style = TextStyle(
                        fontSize = 20.sp
                    ),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = 0.dp,
                            y = 146.dp
                        )
                )
                Text(
                    text = "Quick Access",
                    color = Color.Black,
                    style = TextStyle(
                        fontSize = 20.sp
                    )
                )
                Text(
                    text = "See all",
                    color = Color(0xff005496),
                    style = TextStyle(
                        fontSize = 15.sp
                    ),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = 287.dp,
                            y = 149.dp
                        )
                )
                Text(
                    text = "See all",
                    color = Color(0xff005496),
                    style = TextStyle(
                        fontSize = 15.sp
                    ),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = 287.dp,
                            y = 3.dp
                        )
                )
                Surface(
                    shape = RoundedCornerShape(50.dp),
                    border = BorderStroke(0.800000011920929.dp, Color.Black.copy(alpha = 0.45f)),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = 38.dp,
                            y = 187.dp
                        )
                        .clip(shape = RoundedCornerShape(50.dp))
                ) {
                    Box(
                        modifier = Modifier
                            .requiredWidth(width = 287.dp)
                            .requiredHeight(height = 43.dp)
                    ) {
                        Text(
                            text = "Search transaction by date, service etc",
                            color = Color(0xff565d64).copy(alpha = 0.6f),
                            style = TextStyle(
                                fontSize = 12.sp
                            ),
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(
                                    x = 40.dp,
                                    y = 15.dp
                                )
                        )
                        Image(
                            painter = painterResource(id = R.drawable.searchd),
                            contentDescription = "search",
                            modifier = Modifier
                                .align(alignment = Alignment.TopStart)
                                .offset(
                                    x = 18.dp,
                                    y = 16.dp
                                )
                                .requiredSize(size = 13.dp)
                        )
                    }
                }
                Image(
                    painter = painterResource(id = R.drawable.arrow),
                    contentDescription = "arrow",
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = 339.66748046875.dp,
                            y = 155.dp
                        )
                        .requiredWidth(width = 12.dp)
                        .requiredHeight(height = 8.dp)
                        .rotate(degrees = 180f)
                )
                Image(
                    painter = painterResource(id = R.drawable.arrow),
                    contentDescription = "arrow",
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = 340.0008239746094.dp,
                            y = 9.dp
                        )
                        .requiredWidth(width = 12.dp)
                        .requiredHeight(height = 8.dp)
                        .rotate(degrees = 180f)
                )
            }
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 14.dp,
                        y = 278.dp
                    )
                    .requiredWidth(width = 344.dp)
                    .requiredHeight(height = 68.dp)
            ) {
                Box(
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = 0.dp,
                            y = 54.dp
                        )
                        .requiredWidth(width = 80.dp)
                        .requiredHeight(height = 14.dp)
                ) {
                    Text(
                        text = "Funds Transfer",
                        color = Color(0xff565d64),
                        style = TextStyle(
                            fontSize = 12.sp
                        )
                    )
                }
                Box(
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = 102.dp,
                            y = 54.dp
                        )
                        .requiredWidth(width = 62.dp)
                        .requiredHeight(height = 14.dp)
                ) {
                    Text(
                        text = "Buy Airtime",
                        color = Color(0xff565d64),
                        style = TextStyle(
                            fontSize = 12.sp
                        )
                    )
                }
                Box(
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = 202.dp,
                            y = 54.dp
                        )
                        .requiredWidth(width = 50.dp)
                        .requiredHeight(height = 14.dp)
                ) {
                    Text(
                        text = "Buy Data",
                        color = Color(0xff565d64),
                        style = TextStyle(
                            fontSize = 12.sp
                        )
                    )
                }
                Box(
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(
                            x = 17.dp,
                            y = 0.dp
                        )
                        .requiredWidth(width = 327.dp)
                        .requiredHeight(height = 68.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(49.dp, Alignment.Start)
                    ) {
                        Box(
                            modifier = Modifier
                                .requiredSize(size = 45.dp)
                                .clip(shape = CircleShape)
                                .background(color = Color(0xffcbffb2))
                        )
                        Box(
                            modifier = Modifier
                                .requiredSize(size = 45.dp)
                                .clip(shape = CircleShape)
                                .background(color = Color(0xfffdbfbf))
                        )
                        Box(
                            modifier = Modifier
                                .requiredSize(size = 45.dp)
                                .clip(shape = CircleShape)
                                .background(color = Color(0xfffed0a6))
                        )
                        Box(
                            modifier = Modifier
                                .requiredSize(size = 45.dp)
                                .clip(shape = CircleShape)
                                .background(color = Color(0xffb9adff))
                        )
                    }
                    Text(
                        text = "Help",
                        color = Color(0xff565d64),
                        style = TextStyle(
                            fontSize = 12.sp
                        ),
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(
                                x = 292.dp,
                                y = 54.dp
                            )
                    )
                }
            }
            Image(
                painter = painterResource(id = R.drawable.radio),
                contentDescription = "radio",
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 229.dp,
                        y = 289.dp
                    )
                    .requiredSize(size = 24.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.messagecircle),
                contentDescription = "message-circle",
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 323.dp,
                        y = 289.dp
                    )
                    .requiredSize(size = 24.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.barchart2),
                contentDescription = "bar-chart-2",
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 135.dp,
                        y = 289.dp
                    )
                    .requiredSize(size = 24.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.group22),
                contentDescription = "Group 22",
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 47.dp,
                        y = 294.dp
                    )
                    .requiredWidth(width = 13.dp)
                    .requiredHeight(height = 15.dp)
            )
            Box(
                modifier = Modifier
                    .offset(y = 100.dp, x = 19.dp)
            ) {
                AmountCard(Modifier, user = userModel)
            }


        }

    }
}


@Composable
fun AmountCard(modifier: Modifier = Modifier, user: UserFirestore?) {

    var isHidden by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .requiredWidth(width = 352.dp)
            .requiredHeight(height = 89.dp)
            .clip(shape = RoundedCornerShape(10.dp))
            .background(brush = Brush.linearGradient(
                0f to Color(0xff005496),
                0.66f to Color(0xff0085ff),
//                0.7f to Color(0xff24e9f6).copy(alpha = 0.35f),
//                1f to Color(0xff10d6f1),
                start = Offset(-126.5f, -52.5f),
                end = Offset(766.7f, 184.57f))))
    {
        Box(
            modifier = Modifier
                .offset (y = 23.dp, x = 119.dp)
        ){
            Group44(isHidden, user = user,  onToggle = { isHidden = !isHidden })
        }

    }
}



@Composable
fun Group44(isHidden: Boolean, user: UserFirestore?, onToggle: () -> Unit, modifier: Modifier = Modifier) {


    Box(
        modifier = modifier
            .requiredWidth(width = 145.dp)
            .requiredHeight(height = 44.dp)

    ) {
        Text(
            text = "Available Balance",
            color = Color.White,
            style = TextStyle(
                fontSize = 10.sp),
            modifier = Modifier
                .align(alignment = Alignment.TopCenter)
                .offset(x = (-16).dp,
                    y = 0.dp))
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 0.dp,
                    y = 20.dp)
                .requiredWidth(width = 145.dp)
                .requiredHeight(height = 24.dp)
        ) {
            val balanceText = if (isHidden) "*****" else " ${user?.balance}"

            Text(
                text = "£$balanceText",
                color = Color.White,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium),
                modifier = Modifier
                    .align(alignment = Alignment.TopCenter)
                    .offset(x = (-19.5).dp,
                        y = 0.dp))
            Surface(
                shape = RoundedCornerShape(2.dp),
                color = Color(0xff1f9dff),
                border = BorderStroke(0.5.dp, Color.White),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)

                    .offset(x = 116.dp,
                        y = 6.dp)
                    .clip(shape = RoundedCornerShape(2.dp))
            ) {
                Box(
                    modifier = Modifier
                        .requiredWidth(width = 29.dp)
                        .requiredHeight(height = 12.dp)
                ) {
                    Text(
                        text = "Hide",

                        color = Color.White,
                        style = TextStyle(
                            fontSize = 8.sp),
                        modifier = Modifier
                            .align(alignment = Alignment.Center)
                            .clickable { onToggle() }
                            .offset(x = 0.dp,
                                y = 0.dp))
                }
            }
        }
    }
}

@Preview(widthDp = 390, heightDp = 844)
@Composable
private fun DashboardPreview() {
//    Dashboard(Modifier)
}


