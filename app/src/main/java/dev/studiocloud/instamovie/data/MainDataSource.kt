package dev.studiocloud.instamovie.data

import dev.studiocloud.instamovie.data.models.MovieData
import dev.studiocloud.instamovie.data.models.TvData
import dev.studiocloud.instamovie.data.remote.response.movieDetailResponse.MovieDetailData
import dev.studiocloud.instamovie.data.remote.response.movieResponse.MovieItem

interface MainDataSource {
    fun getMovies(
        page: Int,
        onFinish : (data: MovieData?) -> Unit
    );

    fun getTvs(
        page: Int,
        search: String,
        onFinish : (data: TvData?) -> Unit
    );

    fun getMovieDetail(
        id: Int,
        onFinish: (data: MovieDetailData?) -> Unit
    )

    fun getSimilarMovies(
        id: Int,
        onFinish: (data: MutableList<MovieItem>?) -> Unit
    )
}
