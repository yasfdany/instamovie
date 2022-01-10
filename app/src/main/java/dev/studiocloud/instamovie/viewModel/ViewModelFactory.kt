package dev.studiocloud.instamovie.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.studiocloud.instamovie.data.repository.MainRepository

class ViewModelFactory(private val mainRepository: MainRepository?) : ViewModelProvider.Factory {
    companion object{
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(mainRepository: MainRepository?): ViewModelFactory? {
            if (INSTANCE == null) {
                INSTANCE = ViewModelFactory(mainRepository)
            }
            return INSTANCE
        }
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MovieViewModel::class.java)){
            return MovieViewModel(mainRepository) as T
        } else if(modelClass.isAssignableFrom(TvViewModel::class.java)){
            return TvViewModel(mainRepository) as T
        } else if(modelClass.isAssignableFrom(DetailMovieViewModel::class.java)){
            return DetailMovieViewModel(mainRepository) as T
        }
        throw IllegalArgumentException("Unknown class")
    }
}