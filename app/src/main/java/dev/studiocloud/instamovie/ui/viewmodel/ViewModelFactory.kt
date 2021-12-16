package dev.studiocloud.instamovie.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.studiocloud.instamovie.data.viewModels.MovieViewModel
import dev.studiocloud.instamovie.data.viewModels.TvViewModel

class ViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MovieViewModel::class.java)){
            return MovieViewModel() as T
        } else if(modelClass.isAssignableFrom(TvViewModel::class.java)){
            return TvViewModel() as T
        }
        throw IllegalArgumentException("Unknown class")
    }
}