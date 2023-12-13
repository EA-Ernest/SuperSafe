package uk.ac.tees.mad.b1970805.components


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.AlertDialog
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



@Composable
fun ErrorCard(
    modifier: Modifier = Modifier,
    errorMessage: String
) {
    var showDialog by remember { mutableStateOf(true) }

    if(showDialog){

//        BackHandler(onBack = { showDialog = false })
        AlertDialog(
            onDismissRequest = { showDialog = false},
            title = {
                Text(
                    text = "Log Out",
                    modifier = Modifier
                        .offset ( x = 80.dp ),
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 17.sp,
//                    fontFamily = FontFamily(Font(R.font.poppins)),
                        fontWeight = FontWeight(500),
                        color = Color(0xFF1A3C59),
                        textAlign = TextAlign.Center,
                    )
                )
            },
            text = {
                Text(
                    text = errorMessage,
                    color = Color(0xff1a3c59),
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 15.sp),
                    modifier = Modifier
                        .requiredWidth(width = 245.dp)
                )
            },
            backgroundColor = Color.White,
            shape = RoundedCornerShape(15.dp),
            buttons = {
                Divider(color = Color.Gray, modifier = Modifier
                    .fillMaxWidth()
                    .height(0.8.dp)
                    .offset(y = 15.dp)
                )
//            Column(
//                modifier = Modifier
//                    .padding(8.dp)
//                    .fillMaxWidth(),
//                verticalArrangement = Arrangement.Center,
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
                // Two buttons with transparent background and a vertical divider
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // First button (Cancel)
                    Button(
                        onClick = {
                            // Handle the action for the first button
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        modifier = Modifier
                            .padding(8.dp)
                            .background(Color.Transparent)
                            .offset (x = (-10).dp)

                    ) {
                        Text(
                            text = "Cancel",
                            style = TextStyle(
                                fontSize = 15.sp,
//                                fontFamily = FontFamily(Font(R.font.poppins)),
                                fontWeight = FontWeight(400),
                                color = Color(0xFF1A3C59),
                                textAlign = TextAlign.Center,
                            )
                        )
                    }

                    // Vertical Divider
                    Divider(color = Color.Gray, modifier = Modifier
                        .height(50.dp)
                        .width(0.8.dp)
                        .offset(x= (-7).dp)
                    )

                    // Second button (OK) with transparent background
                    Button(
                        onClick = {
                            // Handle the action for the second button
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),

                        modifier = Modifier
                            .padding(8.dp)
                            .background(Color.Transparent)
                            .offset (x = 15.dp)
                    ) {
                        Text(
                            text = "Ok",
                            style = TextStyle(
                                fontSize = 15.sp,
//                                fontFamily = FontFamily(Font(R.font.poppins)),
                                fontWeight = FontWeight(400),
                                color = Color(0xFF1A3C59),
                                textAlign = TextAlign.Center,
                            )
                        )
                    }
                }
//            }
            },
            modifier = modifier
        )

    }

}

@Preview(widthDp = 290, heightDp = 188)
@Composable
private fun Frame48Preview() {
//    Frame48(Modifier)
}