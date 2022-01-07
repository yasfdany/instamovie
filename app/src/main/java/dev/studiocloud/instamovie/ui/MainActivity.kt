package dev.studiocloud.instamovie.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModelProvider
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.pager.ExperimentalPagerApi
import dev.studiocloud.instamovie.data.MainRepository
import dev.studiocloud.instamovie.data.viewModels.MovieViewModel
import dev.studiocloud.instamovie.data.viewModels.TvViewModel
import dev.studiocloud.instamovie.di.Injection
import dev.studiocloud.instamovie.viewModel.ViewModelFactory
import kotlinx.coroutines.InternalCoroutinesApi


@ExperimentalMaterialApi
@ExperimentalPagerApi
@InternalCoroutinesApi
@ExperimentalAnimationApi
class MainActivity : ComponentActivity() {
    private lateinit var movieViewModel: MovieViewModel
    private lateinit var tvViewModel: TvViewModel
    private var mainRepository: MainRepository? = null
    private var viewModelFactory: ViewModelFactory? = null

    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        mainRepository = Injection.provideRepository(application = application)!!
        viewModelFactory = ViewModelFactory.getInstance(mainRepository)!!

        movieViewModel = ViewModelProvider(this,viewModelFactory!!)[MovieViewModel::class.java]
        tvViewModel = ViewModelProvider(this, viewModelFactory!!)[TvViewModel::class.java]

        movieViewModel.getMovies(reset = true)
        tvViewModel.getTvs(reset = true)
        tvViewModel.searchTv(
            reset = true,
            search = "",
        )

        setContent {
            ProvideWindowInsets {
                AppNavigation(
                    movieViewModel = movieViewModel,
                    tvViewModel = tvViewModel,
                    context = this,
                );
            }
        }
    }
}