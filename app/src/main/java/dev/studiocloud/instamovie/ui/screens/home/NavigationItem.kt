package dev.studiocloud.instamovie.ui.screens.home

import dev.studiocloud.instamovie.R

sealed class NavigationItem(val route: String, val icon: Int, val title: String) {
    object Movie : NavigationItem("movie", R.drawable.ic_movie, "Movie");
    object Tv : NavigationItem("tv", R.drawable.ic_tv, "Tv Series");
}