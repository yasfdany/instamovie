package dev.studiocloud.meditation.data.services.viewModels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.studiocloud.meditation.BuildConfig
import dev.studiocloud.meditation.data.services.ApiClient
import dev.studiocloud.meditation.data.services.ApiService
import dev.studiocloud.meditation.data.services.response.movieResponse.MovieItem
import dev.studiocloud.meditation.data.services.response.movieResponse.MovieResponse
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.Call
import retrofit2.Response

class MovieViewModel : ViewModel() {
    val loading: MutableState<Boolean> = mutableStateOf(false);
    val movies : SnapshotStateList<MovieItem> = mutableStateListOf();
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