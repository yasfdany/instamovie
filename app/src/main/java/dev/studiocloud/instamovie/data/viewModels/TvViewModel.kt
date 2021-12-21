package dev.studiocloud.instamovie.data.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dev.studiocloud.instamovie.data.MainRepository
import dev.studiocloud.instamovie.data.remote.response.tvResponse.TvItem

class TvViewModel(private val mainRepository: MainRepository?) : ViewModel() {
    val loading: MutableState<Boolean> = mutableStateOf(false);
    val loadMore: MutableState<Boolean> = mutableStateOf(false);
    val tvs = mutableStateListOf<TvItem>();
    val tvExplore = mutableStateListOf<TvItem>();
    var page: Int = 1;
    var maxPage: Int = -1;

    fun getTvs(
        reset: Boolean = false,
        onFinish: () -> Unit = {},
    ){
        if(reset){
            page = 1
            loading.value = true
            tvs.clear()
        }

        mainRepository?.getTvs(page, ""){
            loading.value = false
            tvs.addAll(it?.data ?: mutableStateListOf())
            onFinish()
        }
    }

    fun searchTv(
        reset: Boolean = false,
        onFinish: () -> Unit = {},
        search: String = "",
    ){
        loadMore.value = true
        if(reset){
            page = 1
            loading.value = true
            tvExplore.clear()
        }

        mainRepository?.getTvs(page, search){
            loadMore.value = false
            loading.value = false
            tvExplore.addAll(it?.data ?: mutableStateListOf())
            maxPage = it?.maxPage ?: 1
            page = it?.page ?: 1
            onFinish()
        }
    }
}