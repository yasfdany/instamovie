package dev.studiocloud.instamovie.data.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dev.studiocloud.instamovie.BuildConfig
import dev.studiocloud.instamovie.data.remote.ApiClient
import dev.studiocloud.instamovie.data.remote.ApiService
import dev.studiocloud.instamovie.data.remote.response.tvResponse.TvItem
import dev.studiocloud.instamovie.data.remote.response.tvResponse.TvResponse
import retrofit2.Call
import retrofit2.Response

class TvViewModel : ViewModel() {
    val loading: MutableState<Boolean> = mutableStateOf(false);
    val tvs = mutableStateListOf<TvItem>();
    var page: Int = 1;
    var maxPage: Int = -1;

    fun getTvs(
        reset : Boolean = false,
        onFinish : (response: TvResponse) -> Unit = {},
    ){
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

                onFinish(response.body()!!)
            }

            override fun onFailure(call: Call<TvResponse?>, t: Throwable) {
            }

        });
    }
}