package dev.studiocloud.instamovie.data

import dev.studiocloud.instamovie.data.local.LocalRepository
import dev.studiocloud.instamovie.data.models.MovieData
import dev.studiocloud.instamovie.data.models.TvData
import dev.studiocloud.instamovie.data.remote.RemoteRepository
import dev.studiocloud.instamovie.data.remote.response.movieDetailResponse.MovieDetailData
import dev.studiocloud.instamovie.data.remote.response.movieResponse.MovieItem
import dev.studiocloud.instamovie.data.remote.response.movieResponse.MovieResponse
import dev.studiocloud.instamovie.data.remote.response.tvResponse.TvItem
import dev.studiocloud.instamovie.data.remote.response.tvResponse.TvResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainRepository(
    private val remoteRepository: RemoteRepository,
    private val localRepository: LocalRepository,
) : MainDataSource {
    companion object{
        private var INSTANCE: MainRepository? = null

        fun getInstance(
            remoteRepository: RemoteRepository,
            localRepository: LocalRepository,
        ): MainRepository? {
            if (INSTANCE == null) {
                synchronized(RemoteRepository::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = MainRepository(
                            remoteRepository,
                            localRepository,
                        )
                    }
                }
            }

            return INSTANCE
        }
    }

    override fun getMovies(page: Int, onFinish : (data: MovieData?) -> Unit) {
        val movies : MutableList<MovieItem> = mutableListOf()
        var currentPage: Int
        var currentMaxPage: Int

        remoteRepository.getMovies(page, object: Callback<MovieResponse?>{
            override fun onResponse(
                call: Call<MovieResponse?>,
                response: Response<MovieResponse?>
            ) {
                if(response.code() == 200){
                    currentMaxPage = response.body()?.totalPages!!
                    movies.addAll(response.body()?.results!!.toMutableList())

                    currentPage = page + 1

                    onFinish(MovieData(currentPage,currentMaxPage, movies))
                } else {
                    onFinish(null)
                }
            }

            override fun onFailure(call: Call<MovieResponse?>, t: Throwable) {
                onFinish(null)
            }
        });
    }

    override fun getTvs(page: Int, onFinish : (data: TvData?) -> Unit) {
        val tvs : MutableList<TvItem> = mutableListOf()
        var currentPage: Int
        var currentMaxPage: Int

        remoteRepository.getTvs(page, object: Callback<TvResponse?>{
            override fun onResponse(call: Call<TvResponse?>, response: Response<TvResponse?>) {
                if(response.code() == 200){
                    currentMaxPage = response.body()?.totalPages!!
                    tvs.addAll(response.body()?.results!!.toMutableList())

                    currentPage = page + 1

                    onFinish(TvData(currentPage, currentMaxPage, tvs))
                } else {
                    onFinish(null)
                }
            }

            override fun onFailure(call: Call<TvResponse?>, t: Throwable) {
                onFinish(null)
            }
        })
    }

    override fun getMovieDetail(id: Int, onFinish: (data: MovieDetailData?) -> Unit) {
        remoteRepository.getMovieDetail(id, object: Callback<MovieDetailData?>{
            override fun onResponse(
                call: Call<MovieDetailData?>,
                response: Response<MovieDetailData?>
            ) {
                if (response.code() == 200){
                    onFinish(response.body())
                } else {
                    onFinish(null)
                }
            }

            override fun onFailure(call: Call<MovieDetailData?>, t: Throwable) {
                onFinish(null)
            }
        });
    }
}