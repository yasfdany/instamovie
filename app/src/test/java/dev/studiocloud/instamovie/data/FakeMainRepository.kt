
import android.util.Log
import androidx.lifecycle.LiveData
import com.google.gson.Gson
import dev.studiocloud.instamovie.data.MainDataSource
import dev.studiocloud.instamovie.data.local.LocalRepository
import dev.studiocloud.instamovie.data.local.entity.Movie
import dev.studiocloud.instamovie.data.local.entity.MovieDetail
import dev.studiocloud.instamovie.data.local.entity.Tv
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

class FakeMainRepository(
    private val remoteRepository: RemoteRepository,
    private val localRepository: LocalRepository,
) : MainDataSource {
    companion object{
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

    override fun getMovies(page: Int, onFinish : (data: MovieData?) -> Unit) : LiveData<MovieResponse> {
        val movies : MutableList<MovieItem> = mutableListOf()
        var currentPage: Int
        var currentMaxPage: Int

        return remoteRepository.getMovies(page, object: RemoteRepository.LoadMovieCallback{
            override fun onAllMovieReceived(movieResponse: MovieResponse?) {
                currentMaxPage = movieResponse?.totalPages!!
                movies.addAll(movieResponse.results!!.toMutableList())

                currentPage = page + 1

                onFinish(MovieData(currentPage,currentMaxPage, movies))

                for(movie in movies){
                    val jsonMovie = Gson().toJson(movie)
                    val convertedMovie = Gson().fromJson(jsonMovie, Movie::class.java)

                    localRepository.insertMovie(convertedMovie)
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
            }
        })
    }

    override fun getTvs(page: Int, search : String, onFinish : (data: TvData?) -> Unit) {
        val tvs : MutableList<TvItem> = mutableListOf()
        var currentPage: Int
        var currentMaxPage: Int

        remoteRepository.getTvs(page, search, object: Callback<TvResponse?>{
            override fun onResponse(call: Call<TvResponse?>, response: Response<TvResponse?>) {
                if(response.code() == 200){
                    currentMaxPage = response.body()?.totalPages!!
                    tvs.addAll(response.body()?.results!!.toMutableList())

                    currentPage = page + 1

                    onFinish(TvData(currentPage, currentMaxPage, tvs))

                    for(tvItem in tvs){
                        val jsonTv = Gson().toJson(tvItem)
                        val convertedTv = Gson().fromJson(jsonTv, Tv::class.java)

                        localRepository.insertTv(convertedTv)
                    }
                } else {
                    onFinish(null)
                }
            }

            override fun onFailure(call: Call<TvResponse?>, t: Throwable) {
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
                    val jsonDetail = Gson().toJson(response.body())
                    val convertedDetail = Gson().fromJson(jsonDetail, MovieDetail::class.java)
                    val genres = response.body()?.genres?.joinToString { it.name.toString() }
                    convertedDetail.genres = genres
                    convertedDetail.id = id
                    Log.d("id",id.toString())

                    localRepository.insertMovieDetail(convertedDetail)
                } else {
                    onFinish(null)
                }
            }

            override fun onFailure(call: Call<MovieDetailData?>, t: Throwable) {
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
            }
        });
    }

    override fun getSimilarMovies(id: Int, onFinish: (data: MutableList<MovieItem>?) -> Unit) {
        remoteRepository.getSimilarMovies(id, object: Callback<SimilarMovieResponse?>{
            override fun onResponse(
                call: Call<SimilarMovieResponse?>,
                response: Response<SimilarMovieResponse?>
            ) {
                if (response.code() == 200){
                    onFinish(response.body()?.results)
                }
            }

            override fun onFailure(call: Call<SimilarMovieResponse?>, t: Throwable) {
            }
        })
    }
}