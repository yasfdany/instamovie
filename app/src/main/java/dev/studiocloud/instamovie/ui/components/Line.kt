package dev.studiocloud.instamovie.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Line(
    color: Color = Color(0xFFE0E0E0),
    modifier: Modifier? = null,
) {
    Surface(
        color = color,
        modifier = modifier ?: Modifier.fillMaxWidth().height(1.dp),
        content = {},
    )
}