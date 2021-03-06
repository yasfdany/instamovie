package dev.studiocloud.instamovie.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun GradientBox(
    modifier: Modifier = Modifier,
    isReversed: Boolean = false,
) {
    var colors = remember {
        listOf(
            Color.Black.copy(alpha = 0.6f),
            Color.Black.copy(alpha = 0f)
        )
    }
    if(isReversed){
        colors = colors.reversed()
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(
                brush = Brush.verticalGradient(
                    colors = colors
                )
            ),
        content = {}
    )
}