package dev.studiocloud.instamovie.ui.pages

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.studiocloud.instamovie.BuildConfig
import dev.studiocloud.instamovie.data.viewModels.TvViewModel
import dev.studiocloud.instamovie.ui.viewmodel.ViewModelFactory

@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun StoryScreen(
    navController: NavController,
    page: Int,
    tvViewModel: TvViewModel,
){
    val pagerState = PagerState(currentPage = page)
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        darkIcons = true,
        color = Color.White,
    )

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