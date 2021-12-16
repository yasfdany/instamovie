package dev.studiocloud.instamovie.data.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dev.studiocloud.instamovie.data.MainRepository
import dev.studiocloud.instamovie.data.remote.ApiClient
import dev.studiocloud.instamovie.data.remote.ApiService
import dev.studiocloud.instamovie.data.remote.RemoteRepository
import dev.studiocloud.instamovie.data.remote.response.movieResponse.MovieItem

class MovieViewModel : ViewModel() {
    val loading: MutableState<Boolean> = mutableStateOf(false);
    val movies = mutableStateListOf<MovieItem>()
    var page: Int = 1;
    var maxPage: Int = -1;

    private val client: ApiService = ApiClient.get()
    private val remoteRepository: RemoteRepository? = RemoteRepository.getInstance(client);
    private val repository: MainRepository? = MainRepository.getInstance(remoteRepository!!)

    fun getMovies(reset : Boolean = false){
        if(reset){
            page = 1
            loading.value = true
            movies.clear()
        }

        repository?.getMovies(page) {
            loading.value = false
            movies.addAll(it?.data ?: mutableStateListOf())
            maxPage = it?.maxPage ?: 1
            page = it?.page ?: 1
        }
    }
}