package uk.ac.tees.mad.b1970805.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import uk.ac.tees.mad.b1970805.R

@Composable
fun ButtonNavigation(
    navController: NavController,
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit
) {
    val items = listOf("Home", "Transfers", "Cards", "help")


    BottomNavigation(
        backgroundColor = Color.White,
        contentColor = Color.White,
        modifier = Modifier
            .fillMaxWidth()
            .height(92.dp)
    ) {
        items.forEachIndexed { index, item ->
            BottomNavigationItem(
                icon = {
                    CustomIcon(
                        imagePainter = getTabIcon(index),
                        contentDescription = null,
                        isSelected = selectedTabIndex == index
                    )
                },
                label = {
                    if (selectedTabIndex == index) {
                        Text(
                            text = item,
                            style = LocalTextStyle.current.copy(
                                fontWeight = FontWeight.Bold,
//                                fontFamily = fontFamily,
                                fontSize = 10.sp,
                                color = Color(0xff565d64).copy(alpha = 0.6f)
                            )
                        )
                    } else {
                        Text(text = "") // Empty Text when not selected
                    }
                },
                selected = selectedTabIndex == index,
                onClick = {
                    onTabSelected(index)
                    when (index) {
                        0 -> navController.navigate("dashboard")
                        1 -> navController.navigate("transfers")
                        2 -> navController.navigate("cards")
                        3 -> navController.navigate("help")
                    }

                }
            )
        }
    }
}


@Composable
fun CustomIcon(imagePainter: Painter, contentDescription: String?, isSelected: Boolean) {
    val iconColor = if (isSelected) Color(0xff005496) else Color.Gray
    Icon(
        painter = imagePainter,
        contentDescription = contentDescription,
        tint = iconColor,
    )
}
@Composable
fun getTabIcon(index: Int): Painter {
    return when (index) {
        0 -> painterResource(id = R.drawable.homess)
        1 -> painterResource(id = R.drawable.group22)
        2 -> painterResource(id = R.drawable.creditcardoutlines)
        3 -> painterResource(id = R.drawable.messagenavcircle)
        else -> painterResource(id = R.drawable.creditcardoutlines)
    }
}


@Composable
@Preview(showBackground = true)
fun BottonNavPreview(){
    val navController = rememberNavController()
    var selectedTabIndex by remember { mutableStateOf(0) }


    ButtonNavigation(
        navController = navController,
        selectedTabIndex = selectedTabIndex,
        onTabSelected = { newIndex ->
            selectedTabIndex = newIndex
            // Handle tab selection as needed
        }
    )
}

@Composable
fun Frame11(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .requiredWidth(width = 390.dp)
            .requiredHeight(height = 77.dp)
            .background(color = Color.White.copy(alpha = 0.5f))
    ) {
        Image(
            painter = painterResource(id = R.drawable.homess),
            contentDescription = "Home",
            colorFilter = ColorFilter.tint(Color(0xff005496)),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 29.dp,
                    y = 25.dp
                )
                .requiredSize(size = 27.dp))
        Image(
            painter = painterResource(id = R.drawable.group22),
            contentDescription = "CreditCardOutline",
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 183.dp,
                    y = 25.dp
                )
                .requiredSize(size = 27.dp))
        Image(
            painter = painterResource(id = R.drawable.messagecircle),
            contentDescription = "message-circle",
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 337.dp,
                    y = 25.dp
                )
                .requiredSize(size = 25.dp))
        Text(
            text = "Home",
            color = Color(0xff005496),
            style = TextStyle(
                fontSize = 12.sp),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 27.dp,
                    y = 53.dp
                ))
        Text(
            text = "Cards",
            color = Color(0xff565d64).copy(alpha = 0.6f),
            style = TextStyle(
                fontSize = 12.sp),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 181.dp,
                    y = 53.dp
                ))
        Text(
            text = "Help",
            color = Color(0xff565d64).copy(alpha = 0.6f),
            style = TextStyle(
                fontSize = 12.sp),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 337.dp,
                    y = 53.dp
                ))
    }
}

@Preview(widthDp = 390, heightDp = 77)
@Composable
private fun Frame11Preview() {
    Frame11(Modifier)
}