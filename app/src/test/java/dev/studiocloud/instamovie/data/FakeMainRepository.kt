package dev.studiocloud.instamovie.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dev.studiocloud.instamovie.data.repository.LocalRepository
import dev.studiocloud.instamovie.data.model.MovieData
import dev.studiocloud.instamovie.data.model.TvData
import dev.studiocloud.instamovie.data.repository.RemoteRepository
import dev.studiocloud.instamovie.data.model.movieDetailResponse.MovieDetailData
import dev.studiocloud.instamovie.data.model.movieResponse.MovieItem
import dev.studiocloud.instamovie.data.model.movieResponse.MovieResponse
import dev.studiocloud.instamovie.data.model.similarMovieResponse.SimilarMovieResponse
import dev.studiocloud.instamovie.data.model.tvResponse.TvItem
import dev.studiocloud.instamovie.data.model.tvResponse.TvResponse

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

    override fun getMovieDetail(id: Int, onFinish: (data: MovieDetailData?) -> Unit) : LiveData<MovieDetailData> {
        val response: MutableLiveData<MovieDetailData> = MutableLiveData()

        remoteRepository.getMovieDetail(id, object: RemoteRepository.LoadDetailMovieCallback{
            override fun onDetailMovieReceived(movieDetailData: MovieDetailData?) {
                response.value = movieDetailData
                onFinish(movieDetailData)
            }

            override fun onDataNotAvailable() {
            }

        })

        return response
    }

    override fun getSimilarMovies(id: Int, onFinish: (data: MutableList<MovieItem>?) -> Unit) : LiveData<SimilarMovieResponse> {
        val response: MutableLiveData<SimilarMovieResponse> = MutableLiveData()

        remoteRepository.getSimilarMovies(id, object : RemoteRepository.LoadSimilarMovieCallback{
            override fun onSimilarMovieReceived(similarMovieResponse: SimilarMovieResponse?) {
                response.value = similarMovieResponse
                onFinish(similarMovieResponse?.results)
            }

            override fun onDataNotAvailable() {
            }
        })

        return response
    }
}