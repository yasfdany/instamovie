package dev.studiocloud.instamovie.ui.pages

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowInsets
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.lifecycle.ViewModelProvider
import coil.compose.rememberImagePainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.studiocloud.instamovie.BuildConfig
import dev.studiocloud.instamovie.data.viewModels.TvViewModel

@ExperimentalPagerApi
class StoryActivity : ComponentActivity() {
    private lateinit var tvViewModel: TvViewModel
    private var pagerState : PagerState? = null

    @ExperimentalPagerApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        val pageIndex : Int? = intent.extras?.getInt("index")

        tvViewModel = ViewModelProvider(this)[TvViewModel::class.java]
        tvViewModel.getTvs(
            reset = true,
            onFinish = {
                pagerState = PagerState(currentPage = pageIndex ?: 0)
            }
        )

        setContent {
            val systemUiController = rememberSystemUiController()
            systemUiController.setSystemBarsColor(
                darkIcons = true,
                color = Color.White,
            )

            HorizontalPager(
                state = pagerState ?: PagerState(),
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
    }
}