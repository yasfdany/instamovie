package dev.studiocloud.instamovie.data.remote

import dev.studiocloud.instamovie.BuildConfig
import dev.studiocloud.instamovie.data.remote.response.movieResponse.MovieResponse
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

    fun getTvs(page: Int, callback: Callback<TvResponse?>){
        client.getTv(
            api_key = BuildConfig.MOVIE_API_KEY,
            page = page,
            language = "id",
        )?.enqueue(callback);
    }

    fun getMovies(page: Int, callback: Callback<MovieResponse?>){
        client.getMovies(
            api_key = BuildConfig.MOVIE_API_KEY,
            page = page,
            language = "id",
        )?.enqueue(callback);
    }
}