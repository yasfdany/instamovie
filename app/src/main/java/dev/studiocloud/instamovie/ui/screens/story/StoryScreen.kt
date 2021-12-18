package dev.studiocloud.instamovie.ui.screens.story

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.studiocloud.instamovie.BuildConfig
import dev.studiocloud.instamovie.data.viewModels.TvViewModel

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

        Image(
            painter = rememberImagePainter(
                data = BuildConfig.IMAGE_BASE_URL+"w342/"+item.posterPath,
                builder = {
                    crossfade(true)
                },
            ),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        )
    }
}