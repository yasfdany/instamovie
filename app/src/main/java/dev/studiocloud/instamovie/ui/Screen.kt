package dev.studiocloud.instamovie.ui

sealed  class Screen(val route: String) {
    object Home : Screen("home")
    object Story : Screen("story")
}