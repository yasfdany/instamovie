package dev.studiocloud.instamovie.ui

import dev.studiocloud.instamovie.ui.screens.detail_movie.DetailMovieScreen
import dev.studiocloud.instamovie.ui.screens.home.HomeScreen
import dev.studiocloud.instamovie.ui.screens.story.StoryScreen
import dev.studiocloud.instamovie.ui.screens.upload.UploadScreen

sealed class Screen(val route: String) {
    object Home : Screen(HomeScreen.route)
    object Story : Screen(StoryScreen.route)
    object DetailMovie : Screen(DetailMovieScreen.route)
    object Upload : Screen(UploadScreen.route)
}