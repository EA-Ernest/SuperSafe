package uk.ac.tees.mad.b1970805.components


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uk.ac.tees.mad.b1970805.R

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    label: String,
    isEnabled: Boolean = true,
    validationError: String? = null,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(2.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (validationError != null) Color.Gray else Color(0xff005496),
            disabledContainerColor = if (validationError != null) Color.Gray else Color(0xff005496),

            ),
        enabled = isEnabled,
        modifier = modifier
//            .align(alignment = Alignment.TopCenter)
            .offset(x = 25.dp, y = 700.dp)
            .width(340.dp)
            .height(45.dp)
            .clip(shape = RoundedCornerShape(5.dp))
//            .background(color = Color(0xff005496))
    ) {
        Text(
            text = label,
            textAlign= TextAlign.Center,
            color = Color.White,
            style = TextStyle(fontSize = 20.sp),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CustomButtonPreview() {
    CustomButton(label = "Sign Up", onClick = {})
    Column(
        modifier = Modifier
//            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomButton(label = "Enabled Button", onClick = {}, isEnabled = true)
        Spacer(modifier = Modifier.height(16.dp))
        CustomButton(label = "Disabled Button", onClick = {}, isEnabled = false)
    }
}


@Composable
fun Frame23(modifier: Modifier = Modifier) {
    Surface(
        shape = RoundedCornerShape(5.dp),
        color = Color.White.copy(alpha = 0.6f),
        border = BorderStroke(1.dp, Color.Black.copy(alpha = 0.2f)),
        modifier = modifier
            .clip(shape = RoundedCornerShape(5.dp))
    ) {
        Box(
            modifier = Modifier
                .requiredWidth(width = 340.dp)
                .requiredHeight(height = 50.dp)
        ) {
//            Image(
//                painter = painterResource(id = R.drawable.download1),
//                contentDescription = "download 1",
//                modifier = Modifier
//                    .align(alignment = Alignment.TopStart)
//                    .offset(x = 10.dp,
//                        y = 17.dp)
//                    .requiredWidth(width = 27.dp)
//                    .requiredHeight(height = 18.dp)
//                    .clip(shape = RoundedCornerShape(1.dp)))
//            Image(
//                painter = painterResource(id = R.drawable.vector),
//                contentDescription = "Vector",
//                modifier = Modifier
//                    .fillMaxSize()
//                    .rotate(degrees = -90f))
            Divider(
                color = Color.Black.copy(alpha = 0.4f),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 67.5.dp,
                        y = 7.99267578125.dp
                    )
                    .requiredWidth(width = 34.dp)
                    .rotate(degrees = -90f))
            Divider(
                color = Color.Black.copy(alpha = 0.4f),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 111.5.dp,
                        y = 7.99267578125.dp
                    )
                    .requiredWidth(width = 34.dp)
                    .rotate(degrees = -90f))
            Text(
                text = "+44",
                color = Color(0xff565d64),
                style = TextStyle(
                    fontSize = 13.sp),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 78.dp,
                        y = 17.dp
                    ))
            Text(
                text = "Enter Number",
                color = Color(0xff565d64).copy(alpha = 0.8f),
                style = TextStyle(
                    fontSize = 13.sp),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 122.dp,
                        y = 17.dp
                    ))
        }
    }
}

@Preview(widthDp = 340, heightDp = 50)
@Composable
private fun Frame23Preview() {
    Frame23(Modifier)
}

@Composable
fun TransferText(
    modifier: Modifier,
    value: String,
    validationError: String? = null,
    onValueChange: (String) -> Unit,) {


    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        textStyle = TextStyle(
            fontSize = 13.sp,
//            fontFamily = FontFamily(Font(R.font.helvetica neue)),
            fontWeight = FontWeight(400),
            color = Color(0xFF000000),
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),

        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
//                    .padding(horizontal = 64.dp) // margin left and right
//                    .fillMaxWidth()

                    .background(color = Color.White, shape = RoundedCornerShape(size = 5.dp))
                    .border(
                        width = 1.dp,
                        color =if (validationError != null) Color.Red else Color(0x33000000),
                        shape = RoundedCornerShape(size = 5.dp)

                    )
                    .height(50.dp)
                    .width(340.dp)
                    .padding(all = 10.dp), // inner padding
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.uk),
                    contentDescription = "download 1",
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .offset(
                            x = 4.dp,
                            y = 1.dp
                        )
                        .requiredWidth(width = 27.dp)
                        .requiredHeight(height = 18.dp)
                        .clip(shape = RoundedCornerShape(1.dp)))
                Spacer(modifier = Modifier.width(width = 8.dp))

                // Add Divider between the icons
                Divider(
                    color = Color(0xFF565D64),
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(0.8.dp)
                )

                Spacer(modifier = Modifier.width(width = 8.dp))
                Text(
                    text = "+44",
                    style = TextStyle(
                        fontSize = 13.sp,
//                        fontFamily = FontFamily(Font(R.font.helvetica neue)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFF565D64),
                    )
                )
                Spacer(modifier = Modifier.width(width = 8.dp))
                Divider(
                    color = Color(0xFF565D64),
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(0.8.dp)
                )

                Spacer(modifier = Modifier.width(width = 8.dp))
                innerTextField()

            }
        }
    )
}

@Preview(widthDp = 340, heightDp = 50)
@Composable
private fun BasicTextFieldDemoPreview() {

    TransferText(modifier = Modifier, value = "", onValueChange = {})
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextfieldComponenent(modifier: Modifier = Modifier) {
    val containerColor = Color.White
    OutlinedTextField(
        value = "",
        onValueChange = {},
        label = {
            Text(
                text = "+44",
                color = Color(0xff565d64),
                style = TextStyle(
                    fontSize = 13.sp))
        },
        supportingText = {
            Text(
                text = "Enter Number",
                color = Color(0xff565d64).copy(alpha = 0.8f),
                style = TextStyle(
                    fontSize = 13.sp))
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = containerColor,
            unfocusedContainerColor = containerColor,
            disabledContainerColor = containerColor,
        ),
        modifier = modifier
            .requiredWidth(width = 340.dp)
            .fillMaxHeight()
            .clip(shape = RoundedCornerShape(5.dp)))
}

@Preview(widthDp = 340, heightDp = 50)
@Composable
private fun TextfieldComponenentPreview() {
    TextfieldComponenent(Modifier)
}

@Suppress("DEPRECATION")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextArea(
    modifier: Modifier = Modifier,
    value:String,
    validationError: String? = null,
    onValueChange: (String) -> Unit
) {
    var text by remember { mutableStateOf("") }

    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .requiredWidth(width = 340.dp)
            .requiredHeight(height = 79.dp)
            .clip(shape = RoundedCornerShape(5.dp))
            .background(color = Color.White.copy(alpha = 0.6f))
            .border(
                //                color =if (validationError != null) Color.Red else Color(0x33000000),

                border = BorderStroke(1.dp, if (validationError != null) Color.Red else Color(0x33000000))
            ),
        textStyle =TextStyle(
            fontSize = 13.sp,
//            fontFamily = FontFamily(Font(R.font.helvetica neue)),
            fontWeight = FontWeight(400),
            color = Color(0xFF000000),

            ),
        placeholder = { Text("Enter text here") },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.White.copy(alpha = 0.6f),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

@Preview(widthDp = 340, heightDp = 79)
@Composable
private fun Frame25Preview() {
    TextArea(Modifier, value = "", onValueChange= {})
}

@Suppress("DEPRECATION")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AmountTextField(
    modifier: Modifier = Modifier,
    value: String,
    validationError: String? = null,
    onValueChange: (String) -> Unit
) {
    TextField(
        value = value, // Set the initial value as needed
        onValueChange = onValueChange,
        modifier = modifier
            .requiredWidth(width = 340.dp)
            .requiredHeight(height = 50.dp)
            .clip(shape = RoundedCornerShape(5.dp))
            .background(color = Color.White.copy(alpha = 0.6f))
            .border(
//                color =if (validationError != null) Color.Red else Color(0x33000000),

                border = BorderStroke(1.dp,
                    if (validationError != null) Color.Red else Color(0x33000000))
            ),
        placeholder = { Text("GBP") },
        textStyle = LocalTextStyle.current.copy(
            fontSize = 13.sp,
//            fontFamily = FontFamily(Font(R.font.helvetica neue)),
            fontWeight = FontWeight(400),
            color = Color(0xFF000000),
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.White.copy(alpha = 0.6f),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )

}

@Preview(widthDp = 340, heightDp = 50)
@Composable
private fun Frame24Preview() {
    AmountTextField(Modifier, value = "", onValueChange = {})
}

@Composable
fun ButtonTransfer(
    onClick: () -> Unit,
    modifier: Modifier,
    validationError: String? = null,
){
    var isButtonEnabled by remember { mutableStateOf(true) }


    Button(
        onClick = onClick,
        enabled = isButtonEnabled, // Enable or disable the button based on text field content
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xff005496),
            disabledContainerColor = Color(0xff005496)

        ),
        shape = RoundedCornerShape(2.dp),
        modifier = Modifier
            .height(45.dp)
            .width(340.dp)
            .clip(shape = RoundedCornerShape(5.dp))
            .background(
                if (isButtonEnabled) Color(0xff005496) else Color(0xff005496).copy(alpha = 0.4f)
            )
    ) {
        Text(
            text = "Next",
            color = Color.White,
            style = TextStyle(fontSize = 20.sp),
            modifier = Modifier.align(alignment = Alignment.CenterVertically)
        )
    }
}



@Preview
@Composable
fun FrameWithButtonPreview() {
    ButtonTransfer( modifier = Modifier,
        onClick = {
            // Handle button click
        }
    )
}



