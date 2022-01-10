package dev.studiocloud.instamovie.ui.screens.home.pages

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.booleanResource
import androidx.navigation.NavHostController
import dev.studiocloud.instamovie.R
import dev.studiocloud.instamovie.data.viewModels.MovieViewModel
import dev.studiocloud.instamovie.data.viewModels.TvViewModel
import dev.studiocloud.instamovie.ui.Screen
import dev.studiocloud.instamovie.ui.components.ItemMovie
import dev.studiocloud.instamovie.ui.components.Line
import dev.studiocloud.instamovie.ui.screens.home.StoryMobileList

@Composable
fun LazyListState.isScrollingUp(): Boolean {
    var previousIndex by remember(this) { mutableStateOf(firstVisibleItemIndex) }
    var previousScrollOffset by remember(this) { mutableStateOf(firstVisibleItemScrollOffset) }
    return remember(this) {
        derivedStateOf {
            if (previousIndex != firstVisibleItemIndex) {
                previousIndex > firstVisibleItemIndex
            } else {
                previousScrollOffset >= firstVisibleItemScrollOffset
            }.also {
                previousIndex = firstVisibleItemIndex
                previousScrollOffset = firstVisibleItemScrollOffset
            }
        }
    }.value
}

@ExperimentalAnimationApi
@Composable
fun MovieList(
    navController: NavHostController,
    movieViewModel: MovieViewModel,
    tvViewModel: TvViewModel,
){
    val scrollState = rememberLazyListState()
    var visibleStory by remember { mutableStateOf(true) }
    visibleStory = scrollState.isScrollingUp()

    Surface(
        color = Color.White
    ) {
        Column {
            if(!booleanResource(id = R.bool.isTablet)){
                AnimatedVisibility(
                    visible = visibleStory,
                ) {
                    StoryMobileList(
                        tvViewModel = tvViewModel,
                        onTapStory = { index, _ ->
                            navController.navigate(Screen.Story.route + "/page=$index")
                        },
                    )
                }
            }
            Line()
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .testTag("movie_list"),
                state = scrollState,
            ){
                itemsIndexed( movieViewModel.movies){ index, movie ->
                    if (
                        index == movieViewModel.movies.count() - 2
                        && movieViewModel.page < movieViewModel.maxPage
                        && !movieViewModel.loading.value
                    ){
                        movieViewModel.loading.value = true
                        movieViewModel.getMovies()
                    }
                    ItemMovie(
                        item =  movie,
                        onTapPost = {
                            navController.navigate(Screen.DetailMovie.route + "/id=" + it.id)
                        }
                    )
                }
            }
        }
    }
}