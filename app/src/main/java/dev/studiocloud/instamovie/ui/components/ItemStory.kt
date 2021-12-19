package dev.studiocloud.instamovie.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import dev.studiocloud.instamovie.BuildConfig
import dev.studiocloud.instamovie.data.remote.response.tvResponse.TvItem

@Composable
fun ItemStory(
    index: Int,
    tvItem: TvItem,
    onTapStory: (index: Int, selected: TvItem) -> Unit
) {
    Image(
        painter = rememberImagePainter(
            BuildConfig.IMAGE_BASE_URL+"w780/"+tvItem.posterPath,
            builder = {
                crossfade(true)
            },
        ),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(4.dp)
            .size(54.dp)
            .clip(CircleShape)
            .clickable {
                onTapStory(index, tvItem)
            }
    )
}