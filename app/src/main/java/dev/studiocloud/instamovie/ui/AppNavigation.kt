package dev.studiocloud.instamovie.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.pager.ExperimentalPagerApi
import dev.studiocloud.instamovie.data.viewModels.MovieViewModel
import dev.studiocloud.instamovie.data.viewModels.TvViewModel
import dev.studiocloud.instamovie.ui.pages.HomeScreen
import dev.studiocloud.instamovie.ui.pages.StoryScreen
import kotlinx.coroutines.InternalCoroutinesApi

@ExperimentalAnimationApi
@InternalCoroutinesApi
@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
fun AppNavigation (
    movieViewModel: MovieViewModel,
    tvViewModel: TvViewModel,
){
    val navController = rememberNavController() 
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ){
        composable(route = Screen.Home.route){
            HomeScreen(
                navController = navController,
                movieViewModel = movieViewModel,
                tvViewModel = tvViewModel,
            )
        }
        composable(
            route = Screen.Story.route+"/page={page}",
            arguments = listOf(
                navArgument("page"){type = NavType.IntType}
            )
        ){
            it.arguments?.getInt("page").let { page ->
                StoryScreen(
                    navController = navController,
                    tvViewModel = tvViewModel,
                    page = page ?: 0
                )
            }
        }
    }
}