package dev.studiocloud.instamovie.ui

import android.content.Context
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.*
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import dev.studiocloud.instamovie.ui.screens.detail_movie.DetailMovieScreen
import dev.studiocloud.instamovie.ui.screens.home.HomeScreen
import dev.studiocloud.instamovie.ui.screens.story.StoryScreen
import dev.studiocloud.instamovie.ui.screens.upload.UploadScreen
import dev.studiocloud.instamovie.viewModel.MovieViewModel
import dev.studiocloud.instamovie.viewModel.TvViewModel
import dev.studiocloud.instamovie.viewModel.ViewModelFactory
import kotlinx.coroutines.InternalCoroutinesApi

@ExperimentalPagerApi
@ExperimentalMaterialApi
@ExperimentalAnimationApi
fun animatedComposable(
    route: String,
    navGraphBuilder: NavGraphBuilder,
    arguments: List<NamedNavArgument> = listOf(),
    content: @Composable AnimatedVisibilityScope.(NavBackStackEntry) -> Unit,
) {
    navGraphBuilder.composable(
        route = route,
        enterTransition = {
            slideIntoContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(300))
        },
        exitTransition = {
            slideOutOfContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(300))
        },
        popEnterTransition = {
            slideIntoContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = tween(300))
        },
        popExitTransition = {
            slideOutOfContainer(
                AnimatedContentScope.SlideDirection.Right,
                animationSpec = tween(300)
            )
        },
        arguments = arguments,
        content = content,
    )
}

@OptIn(ExperimentalFoundationApi::class)
@ExperimentalAnimationApi
@InternalCoroutinesApi
@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
fun AppNavigation (
    context: Context,
    movieViewModel: MovieViewModel,
    tvViewModel: TvViewModel,
    viewModelStoreOwner: ViewModelStoreOwner,
    viewModelFactory: ViewModelFactory?,
){
    val navController = rememberAnimatedNavController()
    AnimatedNavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        animatedComposable(
            route = Screen.Home.route,
            navGraphBuilder = this,
        ) {
            HomeScreen(
                navController = navController,
                movieViewModel = movieViewModel,
                tvViewModel = tvViewModel,
                context = context,
            )
        }
        animatedComposable(
            route = Screen.Story.route + "/page={page}",
            arguments = listOf(
                navArgument("page"){type = NavType.IntType}
            ),
            navGraphBuilder = this,
        ) {
            it.arguments?.getInt("page").let { page ->
                StoryScreen(
                    tvViewModel = tvViewModel,
                    page = page ?: 0
                )
            }
        }
        animatedComposable(
            route = Screen.DetailMovie.route + "/id={id}",
            arguments = listOf(
                navArgument("id"){type = NavType.IntType}
            ),
            navGraphBuilder = this,
        ) {
            it.arguments?.getInt("id").let { id ->
                DetailMovieScreen(
                    id = id!!,
                    viewModelStoreOwner = viewModelStoreOwner,
                    viewModelFactory = viewModelFactory,
                    navController = navController,
                )
            }
        }
        animatedComposable(
            route = Screen.Upload.route + "/path={path}",
            arguments = listOf(
                navArgument("path"){type = NavType.StringType}
            ),
            navGraphBuilder = this,
        ) {
            it.arguments?.getString("path").let { path ->
                UploadScreen(
                    navController = navController,
                    bitmapPath = path!!,
                )
            }
        }
    }
}