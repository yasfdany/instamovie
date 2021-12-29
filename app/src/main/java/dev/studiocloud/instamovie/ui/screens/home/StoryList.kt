package dev.studiocloud.instamovie.ui.screens.home

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import dev.studiocloud.instamovie.data.remote.response.tvResponse.TvItem
import dev.studiocloud.instamovie.data.viewModels.TvViewModel
import dev.studiocloud.instamovie.ui.components.ItemStory

@Composable
fun StoryTabletList(
    tvViewModel: TvViewModel,
    onTapStory: (index: Int, selected: TvItem) -> Unit,
){
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .padding(
                vertical = 14.dp,
            ),
        contentPadding = PaddingValues(
            all = 14.dp
        )
    ) {
        itemsIndexed(tvViewModel.tvs){ index, tv ->
            Surface(
                modifier = Modifier
                    .padding(bottom = 12.dp)
                    .border(color = Color.Magenta, width = 2.dp, shape = CircleShape)
            ) {
                ItemStory(index = index, tvItem = tv, onTapStory = onTapStory)
            }
        }
    }
}

@Composable
fun StoryMobileList(
    tvViewModel: TvViewModel,
    onTapStory: (index: Int, selected: TvItem) -> Unit,
){
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = 14.dp,
            )
            .testTag("story_list"),
        contentPadding = PaddingValues(horizontal = 14.dp)
    ) {
        itemsIndexed(tvViewModel.tvs){ index, tv ->
            Surface(
                modifier = Modifier
                    .padding(end = 12.dp)
                    .border(color = Color.Magenta, width = 2.dp, shape = CircleShape)
            ) {
                ItemStory(index = index, tvItem = tv, onTapStory = onTapStory)
            }
        }
    }
}