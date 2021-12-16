package dev.studiocloud.instamovie.data.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dev.studiocloud.instamovie.data.MainRepository
import dev.studiocloud.instamovie.data.remote.ApiClient
import dev.studiocloud.instamovie.data.remote.ApiService
import dev.studiocloud.instamovie.data.remote.RemoteRepository
import dev.studiocloud.instamovie.data.remote.response.tvResponse.TvItem

class TvViewModel : ViewModel() {
    val loading: MutableState<Boolean> = mutableStateOf(false);
    val tvs = mutableStateListOf<TvItem>();
    var page: Int = 1;
    var maxPage: Int = -1;

    private val client: ApiService = ApiClient.get()
    private val remoteRepository: RemoteRepository? = RemoteRepository.getInstance(client);
    private val repository: MainRepository? = MainRepository.getInstance(remoteRepository!!)

    fun getTvs(
        reset: Boolean = false,
        onFinish: () -> Unit = {},
    ){
        if(reset){
            loading.value = true
            tvs.clear()
        }

        repository?.getTvs(page){
            loading.value = false
            tvs.addAll(it?.data ?: mutableStateListOf())
            maxPage = it?.maxPage ?: 1
            page = it?.page ?: 1
            onFinish()
        }
    }
}