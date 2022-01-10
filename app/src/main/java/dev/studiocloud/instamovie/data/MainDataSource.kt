package dev.studiocloud.instamovie.data

import androidx.lifecycle.LiveData
import dev.studiocloud.instamovie.data.model.MovieData
import dev.studiocloud.instamovie.data.model.TvData
import dev.studiocloud.instamovie.data.model.movieDetailResponse.MovieDetailData
import dev.studiocloud.instamovie.data.model.movieResponse.MovieItem
import dev.studiocloud.instamovie.data.model.movieResponse.MovieResponse
import dev.studiocloud.instamovie.data.model.similarMovieResponse.SimilarMovieResponse
import dev.studiocloud.instamovie.data.model.tvResponse.TvResponse

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
    ): LiveData<MovieDetailData>

    fun getSimilarMovies(
        id: Int,
        onFinish: (data: MutableList<MovieItem>?) -> Unit
    ): LiveData<SimilarMovieResponse>
}
