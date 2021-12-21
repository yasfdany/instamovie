package dev.studiocloud.instamovie.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.studiocloud.instamovie.ui.theme.blackText14

@ExperimentalMaterialApi
@Composable
fun TextArea(
    modifier: Modifier? = Modifier,
    backgroundColor: Color? = null,
    value: String = "",
    hint: String = "",
    onValueChange: (value: String) -> Unit = {},
    textStyle: TextStyle = blackText14(),
    hintStyle: TextStyle = blackText14().copy(color = Color.Gray),
    radiusCorner: Dp = 8.dp,
    singleLine: Boolean = true,
    trailingIcon: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
){
    TextField(
        value = value,
        onValueChange = onValueChange,
        textStyle = textStyle,
        modifier = modifier ?: Modifier.fillMaxWidth(),
        placeholder = {
            Text(
                text = hint,
                style = hintStyle
            )
        },
        shape = RoundedCornerShape(radiusCorner),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = backgroundColor ?: Color(0xFFEEEEEE),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        singleLine = singleLine,
        trailingIcon = trailingIcon,
        leadingIcon = leadingIcon
    )
}

@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun Preview(){
    TextArea(
    )
}