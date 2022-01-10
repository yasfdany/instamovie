package dev.studiocloud.instamovie.data.model

import dev.studiocloud.instamovie.data.model.movieResponse.MovieItem

class MovieData(
    val page: Int,
    val maxPage: Int,
    val data: MutableList<MovieItem>,
)
