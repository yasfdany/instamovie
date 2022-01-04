package dev.studiocloud.instamovie.data

import androidx.lifecycle.LiveData
import dev.studiocloud.instamovie.data.models.MovieData
import dev.studiocloud.instamovie.data.models.TvData
import dev.studiocloud.instamovie.data.remote.response.movieDetailResponse.MovieDetailData
import dev.studiocloud.instamovie.data.remote.response.movieResponse.MovieItem
import dev.studiocloud.instamovie.data.remote.response.movieResponse.MovieResponse
import dev.studiocloud.instamovie.data.remote.response.tvResponse.TvResponse

interface MainDataSource {
    fun getMovies(
        page: Int,
        onFinish : (data: MovieData?) -> Unit
    ): LiveData<MovieResponse>;

    fun getTvs(
        page: Int,
        search: String,
        onFinish : (data: TvData?) -> Unit
    ): LiveData<TvResponse>;

    fun getMovieDetail(
        id: Int,
        onFinish: (data: MovieDetailData?) -> Unit
    )

    fun getSimilarMovies(
        id: Int,
        onFinish: (data: MutableList<MovieItem>?) -> Unit
    )
}
