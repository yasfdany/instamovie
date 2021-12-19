package dev.studiocloud.instamovie.ui

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.*
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import dev.studiocloud.instamovie.data.viewModels.MovieViewModel
import dev.studiocloud.instamovie.data.viewModels.TvViewModel
import dev.studiocloud.instamovie.ui.screens.detail_movie.DetailMovieScreen
import dev.studiocloud.instamovie.ui.screens.home.HomeScreen
import dev.studiocloud.instamovie.ui.screens.story.StoryScreen
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

@ExperimentalAnimationApi
@InternalCoroutinesApi
@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
fun AppNavigation (
    movieViewModel: MovieViewModel,
    tvViewModel: TvViewModel,
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
            route = Screen.DetailMovie.route,
            navGraphBuilder = this,
        ) {
            DetailMovieScreen(
                navController = navController,
                movieViewModel = movieViewModel
            )
        }
    }
}