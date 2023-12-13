package uk.ac.tees.mad.b1970805.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Suppress("DEPRECATION")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    textValue: String,
    modifier: Modifier = Modifier,
    label: String,
    svg: Int,
    validationError: String? = null,
    onValueChange: (String) -> Unit,
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {

    var isFocused by remember { mutableStateOf(false) }
//    var text by remember { mutableStateOf("") }




    TextField(
        value = textValue,
        onValueChange = {
            onValueChange(it)
        },
        label = {
            if (!isFocused && textValue.isEmpty()) {
                Text(
                    text = label,
                    color = if (validationError != null) Color.Red else Color(0xff565d64).copy(alpha = 0.8f),
                    modifier = Modifier
//                            .align(Alignment.BottomEnd)
                )
            }

        },
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(
            capitalization = KeyboardCapitalization.Words
        ),
        textStyle = LocalTextStyle.current.copy(fontSize = 15.sp),
        modifier = Modifier
//                .align(alignment = Alignment.TopStart)
//                .offset(x = 34.dp, y = 4.dp)
            .width(349.dp)
//                .align(Alignment.Center)
            .background(Color.Transparent),
        leadingIcon = {
            Icon(
                painterResource(id = svg),
                contentDescription = null,
                tint = Color(0xFF0D63E5),
                modifier = Modifier.requiredSize(22.dp)
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            cursorColor = LocalContentColor.current.copy(alpha = 0.8f),
            focusedIndicatorColor = if (validationError != null) Color.Red else Color.Black,
            unfocusedIndicatorColor = Color.Gray,
            disabledIndicatorColor = Color.Gray,
            containerColor = Color.Transparent

        ),
        visualTransformation = visualTransformation,
//            indication = rememberRipple(color = Color.Transparent)
    )

    if (validationError != null) {
        Text(
            text = validationError,
            color = Color.Red,
            fontSize = 12.sp,
            modifier = Modifier.padding(start = 16.dp, top = 4.dp)
        )
    }


}

//@Preview(showBackground = true, widthDp = 339, heightDp = 80)
//@Composable
//fun CustomTextFieldPreview() {
//    CustomTextField(label = "Full Name", svg = R.drawable.person, value =  text,  onValueChange = {})
//}


