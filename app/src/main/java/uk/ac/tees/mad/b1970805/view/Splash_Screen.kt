package uk.ac.tees.mad.b1970805.view


import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextMotion
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay
import uk.ac.tees.mad.b1970805.R


@Composable
fun Splash_screen(modifier: Modifier = Modifier, navController: NavController) {

    val infiniteTransition = rememberInfiniteTransition(label = "infinite transition")
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 5f,
        animationSpec = infiniteRepeatable(tween(1000), RepeatMode.Reverse),
        label = "scale"
    )


    Box(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = Color(0xff005496))
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.Center)
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                    transformOrigin = TransformOrigin.Center
                }
        )
        {
            Image(
                painter = painterResource(id = R.drawable.box),
                contentDescription = "box-solid",
                colorFilter = ColorFilter.tint(Color.White),
                modifier = Modifier
//                    .align(alignment = Alignment.CenterStart)
                    .offset(
//                        x = 90.dp,
                        y = 0.dp
                    )
                    .size(size = 50.dp)
            )

            Text(
                text = "SuperSafe",
                color = Color.White,
//                style = TextStyle(
//                    fontSize = 30.sp),
                style = LocalTextStyle.current.copy(
                    textMotion = TextMotion.Animated,
                    fontSize = 30.sp
                ),
                modifier = Modifier
//                    .align(alignment = Alignment.CenterEnd)
                    .offset(
                        x = 10.dp,
                        y = 0.dp
                    )

            )
        }



    }
    // Use LaunchedEffect to navigate after a delay
    LaunchedEffect(key1 = true) {
        delay(3000) // Adjust the delay duration as needed (in milliseconds)
        navController.navigate("start_screen") // Replace "start_screen" with your actual destination route
    }
}

@Composable
@Preview(showBackground = true)
private fun Splash_screenPreview() {
    val navController = rememberNavController()
    Splash_screen(Modifier, navController = navController)
}
