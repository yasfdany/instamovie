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
    companion object{
        private var INSTANCE: RemoteRepository? = null

        fun getInstance(): RemoteRepository? {
            if (INSTANCE == null) {
                INSTANCE = RemoteRepository()
            }
            return INSTANCE
        }
    }

    fun getTvs(page: Int, search: String, callback: LoadTvCallback?) : LiveData<TvResponse>{
        val data: MutableLiveData<TvResponse> = MutableLiveData()
        val listener = object : Callback<TvResponse?>{
            override fun onResponse(call: Call<TvResponse?>, response: Response<TvResponse?>) {
                if (response.code() == 200) {
                    data.value = response.body()
                    callback?.onAllTvReceived(response.body())
                } else {
                    callback?.onDataNotAvailable()
                }
            }

            override fun onFailure(call: Call<TvResponse?>, t: Throwable) {
                callback?.onDataNotAvailable()
            }
        }

        if(search.isEmpty()){
            ApiClient.get().getTv(
                api_key = BuildConfig.MOVIE_API_KEY,
                page = page,
                language = "id",
            )?.enqueue(listener);
        } else {
            ApiClient.get().getSearchTv(
                api_key = BuildConfig.MOVIE_API_KEY,
                page = page,
                language = "id",
                query = search,
            )?.enqueue(listener);
        }

        return data
    }

    fun getMovies(page: Int, callback: LoadMovieCallback?) : LiveData<MovieResponse> {
        val data: MutableLiveData<MovieResponse> = MutableLiveData()

        ApiClient.get().getMovies(
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
        ApiClient.get().getDetailMovies(
            id = id,
            api_key = BuildConfig.MOVIE_API_KEY,
        )?.enqueue(callback);
    }

    fun getSimilarMovies(id: Int, callback: Callback<SimilarMovieResponse?>){
        ApiClient.get().getSimilarMovies(
            id = id,
            api_key = BuildConfig.MOVIE_API_KEY,
            language = "id",
        )?.enqueue(callback);
    }

    interface LoadTvCallback {
        fun onAllTvReceived(tvResponse: TvResponse?)
        fun onDataNotAvailable()
    }

    interface LoadMovieCallback {
        fun onAllMovieReceived(movieResponse: MovieResponse?)
        fun onDataNotAvailable()
    }
}