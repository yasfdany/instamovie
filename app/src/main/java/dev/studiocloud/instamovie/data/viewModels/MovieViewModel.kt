package dev.studiocloud.instamovie.data.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dev.studiocloud.instamovie.BuildConfig
import dev.studiocloud.instamovie.data.remote.ApiClient
import dev.studiocloud.instamovie.data.remote.ApiService
import dev.studiocloud.instamovie.data.remote.response.movieResponse.MovieItem
import dev.studiocloud.instamovie.data.remote.response.movieResponse.MovieResponse
import retrofit2.Call
import retrofit2.Response

class MovieViewModel : ViewModel() {
    val loading: MutableState<Boolean> = mutableStateOf(false);
    val movies = mutableStateListOf<MovieItem>()
    var page: Int = 1;
    var maxPage: Int = -1;

    fun getMovies(reset : Boolean = false){
        if(reset){
            loading.value = true
            movies.clear()
        }

        val client: ApiService = ApiClient.get()

        client.getMovies(
            api_key = BuildConfig.MOVIE_API_KEY,
            page = page,
            language = "id",
        )?.enqueue(object : retrofit2.Callback<MovieResponse?> {
            override fun onResponse(
                call: Call<MovieResponse?>,
                response: Response<MovieResponse?>
            ) {
                loading.value = false

                if(response.code() == 200){
                    maxPage = response.body()?.totalPages!!
                    movies.addAll(response.body()?.results!!.toMutableList())

                    page++
                }
            }

            override fun onFailure(call: Call<MovieResponse?>, t: Throwable) {
            }

        });
    }
}