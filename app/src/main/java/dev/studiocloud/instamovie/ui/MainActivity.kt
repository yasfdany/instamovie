package dev.studiocloud.instamovie.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModelProvider
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.pager.ExperimentalPagerApi
import dev.studiocloud.instamovie.data.viewModels.MovieViewModel
import dev.studiocloud.instamovie.data.viewModels.TvViewModel
import dev.studiocloud.instamovie.ui.viewmodel.ViewModelFactory
import kotlinx.coroutines.InternalCoroutinesApi

@ExperimentalMaterialApi
@ExperimentalPagerApi
@InternalCoroutinesApi
@ExperimentalAnimationApi
class MainActivity : ComponentActivity() {
    private lateinit var movieViewModel: MovieViewModel
    private lateinit var tvViewModel: TvViewModel
    private val viewModelFactory: ViewModelFactory? = ViewModelFactory.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        movieViewModel = ViewModelProvider(this,viewModelFactory!!)[MovieViewModel::class.java]
        tvViewModel = ViewModelProvider(this, viewModelFactory)[TvViewModel::class.java]
        movieViewModel.getMovies(reset = true)
        tvViewModel.getTvs(reset = true)

        setContent {
            ProvideWindowInsets {
                AppNavigation(movieViewModel = movieViewModel, tvViewModel = tvViewModel);
            }
        }
    }
}