package dev.studiocloud.instamovie.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dev.studiocloud.instamovie.data.repository.MainRepository
import dev.studiocloud.instamovie.data.model.movieDetailResponse.MovieDetailData
import dev.studiocloud.instamovie.data.model.movieResponse.MovieItem

class DetailMovieViewModel(private val mainRepository: MainRepository?) : ViewModel() {
    val loadingDetail: MutableState<Boolean> = mutableStateOf(false);
    val movieDetail : MutableState<MovieDetailData?> = mutableStateOf(null)
    val similarMovies = mutableStateListOf<MovieItem>()

    fun getSimilarMovies(
        id: Int,
        onFinish : (data: MutableList<MovieItem>?) -> Unit = {},
    ){
        similarMovies.clear()

        mainRepository?.getSimilarMovies(id){
            similarMovies.addAll(it ?: mutableStateListOf())
            onFinish(it)
        }
    }

    fun getMovieDetail(
        id: Int,
        onFinish : (data: MovieDetailData?) -> Unit = {},
    ){
        loadingDetail.value = true;
        movieDetail.value = null;

        mainRepository?.getMovieDetail(id){
            loadingDetail.value = false
            movieDetail.value = it
            onFinish(it)
        }
    }
}