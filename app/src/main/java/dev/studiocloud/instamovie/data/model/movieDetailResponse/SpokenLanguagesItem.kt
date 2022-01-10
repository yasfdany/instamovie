package dev.studiocloud.instamovie.data.model.movieDetailResponse

import com.google.gson.annotations.SerializedName

class SpokenLanguagesItem {
    @SerializedName("name")
    var name: String? = null

    @SerializedName("iso_639_1")
    var iso6391: String? = null
    override fun toString(): String {
        return "SpokenLanguagesItem{" +
                "name = '" + name + '\'' +
                ",iso_639_1 = '" + iso6391 + '\'' +
                "}"
    }
}