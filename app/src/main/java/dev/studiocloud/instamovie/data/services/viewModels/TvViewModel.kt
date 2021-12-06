package dev.studiocloud.instamovie.data.services.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import dev.studiocloud.instamovie.BuildConfig
import dev.studiocloud.instamovie.data.services.ApiClient
import dev.studiocloud.instamovie.data.services.ApiService
import dev.studiocloud.instamovie.data.services.response.tvResponse.TvItem
import dev.studiocloud.instamovie.data.services.response.tvResponse.TvResponse
import retrofit2.Call
import retrofit2.Response

class TvViewModel : ViewModel() {
    val loading: MutableState<Boolean> = mutableStateOf(false);
    val tvs : SnapshotStateList<TvItem> = mutableStateListOf();
    var page: Int = 1;
    var maxPage: Int = -1;

    fun getTvs(reset : Boolean = false){
        if(reset){
            loading.value = true
            tvs.clear()
        }

        val client: ApiService = ApiClient.get()

        client.getTv(
            api_key = BuildConfig.MOVIE_API_KEY,
            page = page,
            language = "id",
        )?.enqueue(object : retrofit2.Callback<TvResponse?> {
            override fun onResponse(
                call: Call<TvResponse?>,
                response: Response<TvResponse?>
            ) {
                loading.value = false

                if(response.code() == 200){
                    maxPage = response.body()?.totalPages!!
                    tvs.addAll(response.body()?.results!!.toMutableList())

                    page++
                }
            }

            override fun onFailure(call: Call<TvResponse?>, t: Throwable) {
            }

        });
    }
}