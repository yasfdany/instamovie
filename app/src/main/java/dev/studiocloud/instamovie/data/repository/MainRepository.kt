package dev.studiocloud.instamovie.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import dev.studiocloud.instamovie.data.MainDataSource
import dev.studiocloud.instamovie.data.database.entity.Movie
import dev.studiocloud.instamovie.data.database.entity.MovieDetail
import dev.studiocloud.instamovie.data.database.entity.Tv
import dev.studiocloud.instamovie.data.model.MovieData
import dev.studiocloud.instamovie.data.model.TvData
import dev.studiocloud.instamovie.data.model.movieDetailResponse.GenresItem
import dev.studiocloud.instamovie.data.model.movieDetailResponse.MovieDetailData
import dev.studiocloud.instamovie.data.model.movieResponse.MovieItem
import dev.studiocloud.instamovie.data.model.movieResponse.MovieResponse
import dev.studiocloud.instamovie.data.model.similarMovieResponse.SimilarMovieResponse
import dev.studiocloud.instamovie.data.model.tvResponse.TvItem
import dev.studiocloud.instamovie.data.model.tvResponse.TvResponse
import dev.studiocloud.instamovie.utils.EspressoIdlingResource

open class MainRepository(
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

    override fun getMovies(page: Int, onFinish : (data: MovieData?) -> Unit): LiveData<MovieResponse> {
        val response: MutableLiveData<MovieResponse> = MutableLiveData()
        val movies : MutableList<MovieItem> = mutableListOf()
        var currentPage: Int
        var currentMaxPage: Int

        EspressoIdlingResource.increment()
        remoteRepository.getMovies(page, object: RemoteRepository.LoadMovieCallback{
            override fun onAllMovieReceived(movieResponse: MovieResponse?) {
                response.value = movieResponse
                currentMaxPage = movieResponse?.totalPages!!
                movies.addAll(movieResponse.results!!.toMutableList())

                currentPage = page + 1

                onFinish(MovieData(currentPage,currentMaxPage, movies))

                for(movie in movies){
                    val jsonMovie = Gson().toJson(movie)
                    val convertedMovie = Gson().fromJson(jsonMovie, Movie::class.java)

                    localRepository.insertMovie(convertedMovie)
                }

                if (!EspressoIdlingResource.idlingresource.isIdleNow) {
                    EspressoIdlingResource.decrement()
                }
            }

            override fun onDataNotAvailable() {
                val localMovies = localRepository.getAllMovie()
                for(movie in localMovies){
                    val movieJson = Gson().toJson(movie)
                    val convertedMovie = Gson().fromJson(movieJson, MovieItem::class.java)
                    movies.add(convertedMovie)
                }

                onFinish(MovieData(1,1, movies))
                if (!EspressoIdlingResource.idlingresource.isIdleNow) {
                    EspressoIdlingResource.decrement()
                }
            }
        })

        return response
    }

    override fun getTvs(page: Int, search: String, onFinish: (data: TvData?) -> Unit): LiveData<TvResponse> {
        val response: MutableLiveData<TvResponse> = MutableLiveData()
        val tvs : MutableList<TvItem> = mutableListOf()
        var currentPage: Int
        var currentMaxPage: Int

        EspressoIdlingResource.increment()
        remoteRepository.getTvs(page, search, object : RemoteRepository.LoadTvCallback{
            override fun onAllTvReceived(tvResponse: TvResponse?) {
                response.value = tvResponse

                currentMaxPage = tvResponse?.totalPages!!
                tvs.addAll(tvResponse.results!!.toMutableList())

                currentPage = page + 1

                onFinish(TvData(currentPage, currentMaxPage, tvs))

                for(tvItem in tvs){
                    val jsonTv = Gson().toJson(tvItem)
                    val convertedTv = Gson().fromJson(jsonTv, Tv::class.java)

                    localRepository.insertTv(convertedTv)
                }

                if (!EspressoIdlingResource.idlingresource.isIdleNow) {
                    EspressoIdlingResource.decrement()
                }
            }

            override fun onDataNotAvailable() {
                if (search.isEmpty()){
                    val localTvs = localRepository.getAllTv()
                    for(tv in localTvs){
                        val tvJson = Gson().toJson(tv)
                        val convertedTv = Gson().fromJson(tvJson, TvItem::class.java)
                        tvs.add(convertedTv)
                    }

                    onFinish(TvData(1,1, tvs))
                } else {
                    val localTvs = localRepository.searchTv(search)
                    for(tv in localTvs){
                        val tvJson = Gson().toJson(tv)
                        val convertedTv = Gson().fromJson(tvJson, TvItem::class.java)
                        tvs.add(convertedTv)
                    }

                    onFinish(TvData(1,1, tvs))
                }

                if (!EspressoIdlingResource.idlingresource.isIdleNow) {
                    EspressoIdlingResource.decrement()
                }
            }
        })

        return response
    }

    override fun getMovieDetail(id: Int, onFinish: (data: MovieDetailData?) -> Unit) : LiveData<MovieDetailData> {
        val response: MutableLiveData<MovieDetailData> = MutableLiveData()

        EspressoIdlingResource.increment()
        remoteRepository.getMovieDetail(id, object: RemoteRepository.LoadDetailMovieCallback{
            override fun onDetailMovieReceived(movieDetailData: MovieDetailData?) {
                response.value = movieDetailData
                onFinish(movieDetailData)
                val jsonDetail = Gson().toJson(movieDetailData)
                val convertedDetail = Gson().fromJson(jsonDetail, MovieDetail::class.java)
                val genres = movieDetailData?.genres?.joinToString { it.name.toString() }
                convertedDetail.genres = genres
                convertedDetail.id = id

                localRepository.insertMovieDetail(convertedDetail)

                if (!EspressoIdlingResource.idlingresource.isIdleNow) {
                    EspressoIdlingResource.decrement()
                }
            }

            override fun onDataNotAvailable() {
                val localDetails = localRepository.getMovieDetailById(id)
                if(localDetails.isNotEmpty()){
                    val jsonDetail = Gson().toJson(localDetails[0])
                    val detailData = Gson().fromJson(jsonDetail, MovieDetailData::class.java)
                    val genres = if(localDetails.isNotEmpty()) localDetails[0].genres?.split(",") ?: listOf() else listOf()

                    val genreItems = mutableListOf<GenresItem>()
                    for(genre in genres){
                        genreItems.add(GenresItem(0,genre))
                    }
                    detailData.genres = genreItems

                    onFinish(detailData)
                } else {
                    onFinish(null)
                }

                if (!EspressoIdlingResource.idlingresource.isIdleNow) {
                    EspressoIdlingResource.decrement()
                }
            }

        })

        return response
    }

    override fun getSimilarMovies(id: Int, onFinish: (data: MutableList<MovieItem>?) -> Unit) : LiveData<SimilarMovieResponse> {
        val response: MutableLiveData<SimilarMovieResponse> = MutableLiveData()

        EspressoIdlingResource.increment()
        remoteRepository.getSimilarMovies(id, object : RemoteRepository.LoadSimilarMovieCallback{
            override fun onSimilarMovieReceived(similarMovieResponse: SimilarMovieResponse?) {
                response.value = similarMovieResponse
                onFinish(similarMovieResponse?.results)

                if (!EspressoIdlingResource.idlingresource.isIdleNow) {
                    EspressoIdlingResource.decrement()
                }
            }

            override fun onDataNotAvailable() {
                if (!EspressoIdlingResource.idlingresource.isIdleNow) {
                    EspressoIdlingResource.decrement()
                }
            }
        })

        return response
    }
}