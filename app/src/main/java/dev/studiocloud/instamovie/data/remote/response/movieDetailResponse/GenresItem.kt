package dev.studiocloud.instamovie.data.remote.response.movieDetailResponse

import com.google.gson.annotations.SerializedName

class GenresItem(
    @SerializedName("id") var id: Int = 0,
    @SerializedName("name") val name: String?,
)