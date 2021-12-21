package dev.studiocloud.instamovie.data.remote

import dev.studiocloud.instamovie.BuildConfig
import dev.studiocloud.instamovie.data.remote.response.movieDetailResponse.MovieDetailData
import dev.studiocloud.instamovie.data.remote.response.movieResponse.MovieResponse
import dev.studiocloud.instamovie.data.remote.response.similarMovieResponse.SimilarMovieResponse
import dev.studiocloud.instamovie.data.remote.response.tvResponse.TvResponse
import retrofit2.Callback

class RemoteRepository(private val client: ApiService) {
    companion object{
        private var INSTANCE: RemoteRepository? = null

        fun getInstance(apiClient: ApiService?): RemoteRepository? {
            if (INSTANCE == null) {
                INSTANCE = RemoteRepository(apiClient!!)
            }
            return INSTANCE
        }
    }

    fun getTvs(page: Int, search: String, callback: Callback<TvResponse?>){
        if(search.isEmpty()){
            client.getTv(
                api_key = BuildConfig.MOVIE_API_KEY,
                page = page,
                language = "id",
            )?.enqueue(callback);
        } else {
            client.getSearchTv(
                api_key = BuildConfig.MOVIE_API_KEY,
                page = page,
                language = "id",
                query = search,
            )?.enqueue(callback);
        }
    }

    fun getMovies(page: Int, callback: Callback<MovieResponse?>){
        client.getMovies(
            api_key = BuildConfig.MOVIE_API_KEY,
            page = page,
            language = "id",
        )?.enqueue(callback);
    }

    fun getMovieDetail(id: Int, callback: Callback<MovieDetailData?>){
        client.getDetailMovies(
            id = id,
            api_key = BuildConfig.MOVIE_API_KEY,
        )?.enqueue(callback);
    }

    fun getSimilarMovies(id: Int, callback: Callback<SimilarMovieResponse?>){
        client.getSimilarMovies(
            id = id,
            api_key = BuildConfig.MOVIE_API_KEY,
            language = "id",
        )?.enqueue(callback);
    }
}