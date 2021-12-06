package dev.studiocloud.meditation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import dev.studiocloud.meditation.R

@Composable
fun StoryView(){
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = 14.dp,
            ),
        contentPadding = PaddingValues(horizontal = 14.dp)
    ) {
        items(12){
            Surface(
                modifier = Modifier
                    .padding(end = 12.dp)
                    .border(color = Color.Magenta, width = 2.dp, shape = CircleShape)
            ) {
                Image(
                    painter = painterResource(R.drawable.dog),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(4.dp)
                        .size(54.dp)
                        .clip(CircleShape)
                )
            }
        }
    }
}