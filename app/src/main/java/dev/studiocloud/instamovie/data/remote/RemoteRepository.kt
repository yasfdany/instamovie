package dev.studiocloud.instamovie.data.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dev.studiocloud.instamovie.BuildConfig
import dev.studiocloud.instamovie.data.remote.response.movieDetailResponse.MovieDetailData
import dev.studiocloud.instamovie.data.remote.response.movieResponse.MovieResponse
import dev.studiocloud.instamovie.data.remote.response.similarMovieResponse.SimilarMovieResponse
import dev.studiocloud.instamovie.data.remote.response.tvResponse.TvResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


open class RemoteRepository() {
    private val client = ApiClient.get()

    companion object{
        private var INSTANCE: RemoteRepository? = null

        fun getInstance(): RemoteRepository? {
            if (INSTANCE == null) {
                INSTANCE = RemoteRepository()
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

    fun getMovies(page: Int, callback: LoadMovieCallback?) : LiveData<MovieResponse> {
        val data: MutableLiveData<MovieResponse> = MutableLiveData()

        client.getMovies(
            api_key = BuildConfig.MOVIE_API_KEY,
            page = page,
            language = "id",
        )?.enqueue(object: Callback<MovieResponse?>{
            override fun onResponse(
                call: Call<MovieResponse?>,
                response: Response<MovieResponse?>
            ) {
                if (response.code() == 200) {
                    data.value = response.body()
                    callback?.onAllMovieReceived(response.body())
                } else {
                    callback?.onDataNotAvailable()
                }
            }

            override fun onFailure(call: Call<MovieResponse?>, t: Throwable) {
                callback?.onDataNotAvailable()
            }
        });

        return data
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

    interface LoadMovieCallback {
        fun onAllMovieReceived(movieResponse: MovieResponse?)
        fun onDataNotAvailable()
    }
}