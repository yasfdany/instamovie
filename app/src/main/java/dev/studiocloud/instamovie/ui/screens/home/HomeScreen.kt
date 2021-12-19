package dev.studiocloud.instamovie.ui.screens.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Surface
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.accompanist.insets.systemBarsPadding
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.studiocloud.instamovie.R
import dev.studiocloud.instamovie.data.viewModels.MovieViewModel
import dev.studiocloud.instamovie.data.viewModels.TvViewModel
import dev.studiocloud.instamovie.ui.Screen
import dev.studiocloud.instamovie.ui.components.ItemPost
import dev.studiocloud.instamovie.ui.components.Line

@Composable
private fun LazyListState.isScrollingUp(): Boolean {
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
fun HomeScreen(
    navController: NavHostController,
    movieViewModel: MovieViewModel,
    tvViewModel: TvViewModel,
){
    var visibleStory by remember { mutableStateOf(true) }

    val scrollState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        darkIcons = true,
        color = Color.White,
    )
    systemUiController.isSystemBarsVisible = true
    systemUiController.isNavigationBarVisible = true

    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxHeight()
            .systemBarsPadding()
    ){
        visibleStory = scrollState.isScrollingUp()

        Column {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painterResource(R.drawable.ic_camera),
                    contentDescription = null,
                    modifier = Modifier
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple(bounded = false),
                            onClick = {

                            },
                        )
                        .padding(14.dp)
                )
                Image(
                    painterResource(R.drawable.ic_email),
                    contentDescription = null,
                    modifier = Modifier
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple(bounded = false),
                            onClick = {

                            },
                        )
                        .padding(14.dp)
                )
            }
            AnimatedVisibility(visible = visibleStory) {
                StoryView(
                    tvs = tvViewModel.tvs,
                    onTapStory = { index, _ ->
                        navController.navigate(Screen.Story.route+"/page=$index")
                    }
                )
            }
            Line()
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                state = scrollState,
            ){
                itemsIndexed( movieViewModel.movies){ index, movie ->
                    if (index == movieViewModel.movies.count() - 2 && movieViewModel.page <= movieViewModel.maxPage){
                        movieViewModel.getMovies()
                    }
                    ItemPost(
                        item =  movie,
                        onTapPost = {
                            movieViewModel.getMovieDetail(it.id)
                            navController.navigate(Screen.DetailMovie.route)
                        }
                    )
                }
            }
        }
    }
}