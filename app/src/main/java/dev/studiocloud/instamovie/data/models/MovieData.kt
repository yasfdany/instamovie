package dev.studiocloud.instamovie.data.models

import dev.studiocloud.instamovie.data.remote.response.movieResponse.MovieItem

class MovieData(
    val page: Int,
    val maxPage: Int,
    val data: MutableList<MovieItem>,
)
