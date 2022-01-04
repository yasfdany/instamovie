package dev.studiocloud.instamovie.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import dev.studiocloud.instamovie.data.local.LocalRepository
import dev.studiocloud.instamovie.data.local.entity.MovieDetail
import dev.studiocloud.instamovie.data.models.MovieData
import dev.studiocloud.instamovie.data.models.TvData
import dev.studiocloud.instamovie.data.remote.RemoteRepository
import dev.studiocloud.instamovie.data.remote.response.movieDetailResponse.GenresItem
import dev.studiocloud.instamovie.data.remote.response.movieDetailResponse.MovieDetailData
import dev.studiocloud.instamovie.data.remote.response.movieResponse.MovieItem
import dev.studiocloud.instamovie.data.remote.response.movieResponse.MovieResponse
import dev.studiocloud.instamovie.data.remote.response.similarMovieResponse.SimilarMovieResponse
import dev.studiocloud.instamovie.data.remote.response.tvResponse.TvItem
import dev.studiocloud.instamovie.data.remote.response.tvResponse.TvResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class FakeMainRepository(
    private val remoteRepository: RemoteRepository,
    private val localRepository: LocalRepository,
) : MainDataSource {
    companion object {
        private var INSTANCE: FakeMainRepository? = null

        fun getInstance(
            remoteRepository: RemoteRepository,
            localRepository: LocalRepository,
        ): FakeMainRepository? {
            if (INSTANCE == null) {
                synchronized(RemoteRepository::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = FakeMainRepository(
                            remoteRepository,
                            localRepository,
                        )
                    }
                }
            }

            return INSTANCE
        }
    }

    override fun getMovies(
        page: Int,
        onFinish: (data: MovieData?) -> Unit
    ): LiveData<MovieResponse> {
        val response: MutableLiveData<MovieResponse> = MutableLiveData()
        val movies: MutableList<MovieItem> = mutableListOf()
        var currentPage: Int
        var currentMaxPage: Int

        remoteRepository.getMovies(page, object : RemoteRepository.LoadMovieCallback {
            override fun onAllMovieReceived(movieResponse: MovieResponse?) {
                response.value = movieResponse
                currentMaxPage = movieResponse?.totalPages!!
                movies.addAll(movieResponse.results!!.toMutableList())

                currentPage = page + 1

                onFinish(MovieData(currentPage, currentMaxPage, movies))
            }

            override fun onDataNotAvailable() {
            }
        })

        return response
    }

    override fun getTvs(
        page: Int,
        search: String,
        onFinish: (data: TvData?) -> Unit
    ): LiveData<TvResponse> {
        val response: MutableLiveData<TvResponse> = MutableLiveData()
        val tvs: MutableList<TvItem> = mutableListOf()
        var currentPage: Int
        var currentMaxPage: Int

        remoteRepository.getTvs(page, search, object : RemoteRepository.LoadTvCallback{
            override fun onAllTvReceived(tvResponse: TvResponse?) {
                response.value = tvResponse
                currentMaxPage = tvResponse?.totalPages!!
                tvs.addAll(tvResponse.results!!.toMutableList())

                currentPage = page + 1

                onFinish(TvData(currentPage, currentMaxPage, tvs))
            }

            override fun onDataNotAvailable() {
            }
        })

        return response
    }

    override fun getMovieDetail(id: Int, onFinish: (data: MovieDetailData?) -> Unit) {
        remoteRepository.getMovieDetail(id, object : Callback<MovieDetailData?> {
            override fun onResponse(
                call: Call<MovieDetailData?>,
                response: Response<MovieDetailData?>
            ) {
                if (response.code() == 200) {
                    onFinish(response.body())
                    val jsonDetail = Gson().toJson(response.body())
                    val convertedDetail = Gson().fromJson(jsonDetail, MovieDetail::class.java)
                    val genres = response.body()?.genres?.joinToString { it.name.toString() }
                    convertedDetail.genres = genres
                    convertedDetail.id = id
                    Log.d("id", id.toString())

                    localRepository.insertMovieDetail(convertedDetail)
                } else {
                    onFinish(null)
                }
            }

            override fun onFailure(call: Call<MovieDetailData?>, t: Throwable) {
                val localDetails = localRepository.getMovieDetailById(id)
                if (localDetails.isNotEmpty()) {
                    val jsonDetail = Gson().toJson(localDetails[0])
                    val detailData = Gson().fromJson(jsonDetail, MovieDetailData::class.java)
                    val genres = if (localDetails.isNotEmpty()) localDetails[0].genres?.split(",")
                        ?: listOf() else listOf()

                    val genreItems = mutableListOf<GenresItem>()
                    for (genre in genres) {
                        genreItems.add(GenresItem(0, genre))
                    }
                    detailData.genres = genreItems

                    onFinish(detailData)
                } else {
                    onFinish(null)
                }
            }
        });
    }

    override fun getSimilarMovies(id: Int, onFinish: (data: MutableList<MovieItem>?) -> Unit) {
        remoteRepository.getSimilarMovies(id, object : Callback<SimilarMovieResponse?> {
            override fun onResponse(
                call: Call<SimilarMovieResponse?>,
                response: Response<SimilarMovieResponse?>
            ) {
                if (response.code() == 200) {
                    onFinish(response.body()?.results)
                }
            }

            override fun onFailure(call: Call<SimilarMovieResponse?>, t: Throwable) {
            }
        })
    }
}