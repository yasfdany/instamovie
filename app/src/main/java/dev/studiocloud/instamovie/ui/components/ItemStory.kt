package dev.studiocloud.instamovie.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage
import dev.studiocloud.instamovie.BuildConfig
import dev.studiocloud.instamovie.data.model.tvResponse.TvItem

@Composable
fun ItemStory(
    index: Int,
    tvItem: TvItem,
    onTapStory: (index: Int, selected: TvItem) -> Unit
) {
    GlideImage(
        BuildConfig.IMAGE_BASE_URL + "w780/" + tvItem.posterPath,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        circularReveal = CircularReveal(),
        modifier = Modifier
            .padding(4.dp)
            .size(46.dp)
            .clip(CircleShape)
            .clickable {
                onTapStory(index, tvItem)
            }
    )
}