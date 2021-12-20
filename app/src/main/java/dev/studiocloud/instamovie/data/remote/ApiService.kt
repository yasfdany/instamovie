package dev.studiocloud.instamovie.data.remote

import dev.studiocloud.instamovie.data.remote.response.movieDetailResponse.MovieDetailData
import dev.studiocloud.instamovie.data.remote.response.movieResponse.MovieResponse
import dev.studiocloud.instamovie.data.remote.response.tvDetailResponse.TvDetailResponse
import dev.studiocloud.instamovie.data.remote.response.tvResponse.TvResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {
    @GET("movie/{id}")
    fun getDetailMovies(
        @Path("id") id: Int,
        @Query("api_key") api_key: String?
    ): Call<MovieDetailData?>?

    @GET("tv/{id}")
    fun getDetailTv(
        @Path("id") id: Int,
        @Query("api_key") api_key: String?
    ): Call<TvDetailResponse?>?

    @GET("discover/movie")
    fun getMovies(
        @Query("api_key") api_key: String?,
        @Query("language") language: String?,
        @Query("page") page: Int
    ): Call<MovieResponse?>?

    @GET("search/movie")
    fun getSearchMovies(
        @Query("api_key") api_key: String?,
        @Query("language") language: String?,
        @Query("query") query: String?,
        @Query("page") page: Int
    ): Call<MovieResponse?>?

    @GET("discover/tv")
    fun getTv(
        @Query("api_key") api_key: String?,
        @Query("language") language: String?,
        @Query("page") page: Int
    ): Call<TvResponse?>?

    @GET("search/tv/")
    fun getSearchTv(
        @Query("api_key") api_key: String?,
        @Query("language") language: String?,
        @Query("query") query: String?,
        @Query("page") page: Int
    ): Call<TvResponse?>?
}
