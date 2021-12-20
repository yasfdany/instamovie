package dev.studiocloud.instamovie.ui.screens.home

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.studiocloud.instamovie.data.remote.response.tvResponse.TvItem
import dev.studiocloud.instamovie.data.viewModels.TvViewModel
import dev.studiocloud.instamovie.ui.components.ItemStory

@Composable
fun StoryList(
    tvViewModel: TvViewModel,
    tvs: SnapshotStateList<TvItem>,
    onTapStory: (index: Int, selected: TvItem) -> Unit
){
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = 14.dp,
            ),
        contentPadding = PaddingValues(horizontal = 14.dp)
    ) {
        itemsIndexed(tvs){ index, tv ->
            if (index == tvViewModel.tvs.count() - 2 && tvViewModel.page < tvViewModel.maxPage){
                tvViewModel.getTvs()
            }
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