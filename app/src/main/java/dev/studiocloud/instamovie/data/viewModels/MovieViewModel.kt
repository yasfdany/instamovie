package dev.studiocloud.instamovie.data.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dev.studiocloud.instamovie.data.MainRepository
import dev.studiocloud.instamovie.data.remote.response.movieDetailResponse.MovieDetailData
import dev.studiocloud.instamovie.data.remote.response.movieResponse.MovieItem

class MovieViewModel(private val mainRepository: MainRepository?) : ViewModel() {
    val loading: MutableState<Boolean> = mutableStateOf(false);
    val loadingDetail: MutableState<Boolean> = mutableStateOf(false);
    val movieDetail : MutableState<MovieDetailData?> = mutableStateOf(null)
    val movies = mutableStateListOf<MovieItem>()
    var page: Int = 1;
    var maxPage: Int = -1;

    fun getMovieDetail(id: Int){
        loadingDetail.value = true;
        movieDetail.value = null;

        mainRepository?.getMovieDetail(id){
            loadingDetail.value = false
            movieDetail.value = it
        }
    }

    fun getMovies(reset : Boolean = false){
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
        }
    }
}