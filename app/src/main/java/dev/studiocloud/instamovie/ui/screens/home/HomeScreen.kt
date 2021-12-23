package dev.studiocloud.instamovie.ui.screens.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.accompanist.insets.systemBarsPadding
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.studiocloud.instamovie.R
import dev.studiocloud.instamovie.data.viewModels.MovieViewModel
import dev.studiocloud.instamovie.data.viewModels.TvViewModel
import dev.studiocloud.instamovie.ui.components.Line
import dev.studiocloud.instamovie.ui.screens.home.pages.MovieList
import dev.studiocloud.instamovie.ui.screens.home.pages.TvList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@Composable
fun HeaderHome(){
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
}

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@ExperimentalPagerApi
@ExperimentalAnimationApi
@Composable
fun HomeScreen(
    navController: NavHostController,
    movieViewModel: MovieViewModel,
    tvViewModel: TvViewModel,
){
    var selectedTab by remember { mutableStateOf("movie") }
    val pagerState = rememberPagerState(initialPage = 0)
    val coroutineScope = rememberCoroutineScope()

    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        darkIcons = true,
        color = Color.White,
    )
    systemUiController.isSystemBarsVisible = true
    systemUiController.isNavigationBarVisible = true

    val navigationItems = listOf(
        NavigationItem.Movie,
        NavigationItem.Tv,
    )

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { index ->
            selectedTab = if(index == 0) "movie" else "tv"
        }
    }

    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxHeight()
            .systemBarsPadding()
    ){
        Column {
            HeaderHome()
            HorizontalPager(
                state = pagerState,
                count =  navigationItems.count(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(0.dp)
                    .weight(1f),
            ) { index ->
                when(index){
                    0 -> {
                        MovieList(
                            navController = navController,
                            movieViewModel = movieViewModel,
                            tvViewModel = tvViewModel,
                        )
                    }
                    1 -> {
                        TvList(
                            navController = navController,
                            tvViewModel = tvViewModel,
                        )
                    }
                }
            }

            Line()
            BottomNavigation(
                backgroundColor = Color.White,
                contentColor = Color.Magenta,
            ) {
                navigationItems.forEach { item ->
                    BottomNavigationItem(
                        icon = {
                            Icon(
                                painterResource(id = item.icon),
                                contentDescription = item.title,
                            )
                        },
                        label = { Text(text = item.title) },
                        selectedContentColor = Color.Black,
                        unselectedContentColor = Color.Black.copy(0.4f),
                        alwaysShowLabel = true,
                        selected = selectedTab == item.route,
                        onClick = {
                            selectedTab = item.route
                            coroutineScope.launch(Dispatchers.Main) {
                                pagerState.animateScrollToPage(if(item.route == "movie") 0 else 1)
                            }
                        },
                    )
                }
            }
        }
    }
}