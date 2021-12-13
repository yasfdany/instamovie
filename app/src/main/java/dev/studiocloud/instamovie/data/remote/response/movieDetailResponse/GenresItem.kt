package dev.studiocloud.instamovie.data.remote.response.movieDetailResponse

import com.google.gson.annotations.SerializedName

class GenresItem {
    @SerializedName("name")
    var name: String? = null

    @SerializedName("id")
    var id = 0
    override fun toString(): String {
        return "GenresItem{" +
                "name = '" + name + '\'' +
                ",id = '" + id + '\'' +
                "}"
    }
}