package dev.studiocloud.instamovie.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dev.studiocloud.instamovie.BuildConfig
import dev.studiocloud.instamovie.data.network.ApiClient
import dev.studiocloud.instamovie.data.model.movieDetailResponse.MovieDetailData
import dev.studiocloud.instamovie.data.model.movieResponse.MovieResponse
import dev.studiocloud.instamovie.data.model.similarMovieResponse.SimilarMovieResponse
import dev.studiocloud.instamovie.data.model.tvResponse.TvResponse
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

    open fun getTvs(page: Int, search: String, callback: LoadTvCallback?) : LiveData<TvResponse>{
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

    open fun getMovies(page: Int, callback: LoadMovieCallback?) : LiveData<MovieResponse> {
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

    open fun getMovieDetail(id: Int, callback: LoadDetailMovieCallback?) : LiveData<MovieDetailData>{
        val data: MutableLiveData<MovieDetailData> = MutableLiveData()

        ApiClient.get().getDetailMovies(
            id = id,
            api_key = BuildConfig.MOVIE_API_KEY,
        )?.enqueue(object : Callback<MovieDetailData?>{
            override fun onResponse(
                call: Call<MovieDetailData?>,
                response: Response<MovieDetailData?>
            ) {
                if (response.code() == 200) {
                    data.value = response.body()
                    callback?.onDetailMovieReceived(response.body())
                } else {
                    callback?.onDataNotAvailable()
                }
            }

            override fun onFailure(call: Call<MovieDetailData?>, t: Throwable) {
                callback?.onDataNotAvailable()
            }
        });

        return data
    }

    open fun getSimilarMovies(id: Int, callback: LoadSimilarMovieCallback?) : LiveData<SimilarMovieResponse>{
        val data: MutableLiveData<SimilarMovieResponse> = MutableLiveData()

        ApiClient.get().getSimilarMovies(
            id = id,
            api_key = BuildConfig.MOVIE_API_KEY,
            language = "id",
        )?.enqueue(object : Callback<SimilarMovieResponse?>{
            override fun onResponse(
                call: Call<SimilarMovieResponse?>,
                response: Response<SimilarMovieResponse?>
            ) {
                if (response.code() == 200) {
                    data.value = response.body()
                    callback?.onSimilarMovieReceived(response.body())
                } else {
                    callback?.onDataNotAvailable()
                }
            }

            override fun onFailure(call: Call<SimilarMovieResponse?>, t: Throwable) {
                callback?.onDataNotAvailable()
            }
        });

        return data
    }

    interface LoadTvCallback {
        fun onAllTvReceived(tvResponse: TvResponse?)
        fun onDataNotAvailable()
    }

    interface LoadMovieCallback {
        fun onAllMovieReceived(movieResponse: MovieResponse?)
        fun onDataNotAvailable()
    }

    interface LoadDetailMovieCallback {
        fun onDetailMovieReceived(movieDetailData: MovieDetailData?)
        fun onDataNotAvailable()
    }

    interface LoadSimilarMovieCallback {
        fun onSimilarMovieReceived(similarMovieResponse: SimilarMovieResponse?)
        fun onDataNotAvailable()
    }
}