package dev.studiocloud.instamovie.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dev.studiocloud.instamovie.data.repository.MainRepository
import dev.studiocloud.instamovie.data.model.MovieData
import dev.studiocloud.instamovie.data.model.movieResponse.MovieItem

class MovieViewModel(private val mainRepository: MainRepository?) : ViewModel() {
    val loading: MutableState<Boolean> = mutableStateOf(false);

    val movies = mutableStateListOf<MovieItem>()

    var page: Int = 1;
    var maxPage: Int = -1;

    fun getMovies(
        reset : Boolean = false,
        onFinish : (data: MovieData?) -> Unit = {},
    ){
        if(reset){
            page = 1
            loading.value = true
            movies.clear()
        }

        mainRepository?.getMovies(page) {
            loading.value = false
            movies.addAll(it?.data ?: mutableStateListOf())
            maxPage = it?.maxPage ?: 1
            page = it?.page ?: 1
            onFinish(it)
        }
    }
}