package dev.studiocloud.instamovie.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.studiocloud.instamovie.ui.theme.blackText14

@Composable
fun ItemRow(
    leftText: String,
    rightText: String,
    modifier: Modifier,
) {
    Row(
        modifier = modifier
    ) {
        Surface(modifier = Modifier.width(100.dp)) {
            Text(text = leftText,style = blackText14())
        }
        Text(text = rightText,style = blackText14().copy(color = Color.Magenta))
    }
}