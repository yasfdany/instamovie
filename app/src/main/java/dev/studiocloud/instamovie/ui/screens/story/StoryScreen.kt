package dev.studiocloud.instamovie.ui.screens.story

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage
import dev.studiocloud.instamovie.BuildConfig
import dev.studiocloud.instamovie.viewModel.TvViewModel

class StoryScreen{
    companion object {
        const val route = "story"
    }
}

@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun StoryScreen(
    page: Int,
    tvViewModel: TvViewModel,
){
    val pagerState = PagerState(currentPage = page)
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        darkIcons = true,
        color = Color.White,
    )
    systemUiController.isSystemBarsVisible = false

    HorizontalPager(
        state = pagerState,
        count =  tvViewModel.tvs.count(),
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) { index ->
        val item = tvViewModel.tvs[index]

        GlideImage(
            BuildConfig.IMAGE_BASE_URL + "w780/" + item.posterPath,
            contentScale = ContentScale.Crop,
            circularReveal = CircularReveal(),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .testTag("story_image")
        )
    }
}