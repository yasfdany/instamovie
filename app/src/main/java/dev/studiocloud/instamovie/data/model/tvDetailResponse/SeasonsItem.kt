package dev.studiocloud.instamovie.data.model.tvDetailResponse

import com.google.gson.annotations.SerializedName

class SeasonsItem {
    @SerializedName("air_date")
    var airDate: String? = null

    @SerializedName("overview")
    var overview: String? = null

    @SerializedName("episode_count")
    var episodeCount = 0

    @SerializedName("name")
    var name: String? = null

    @SerializedName("season_number")
    var seasonNumber = 0

    @SerializedName("id")
    var id = 0

    @SerializedName("poster_path")
    var posterPath: String? = null
    override fun toString(): String {
        return "SeasonsItem{" +
                "air_date = '" + airDate + '\'' +
                ",overview = '" + overview + '\'' +
                ",episode_count = '" + episodeCount + '\'' +
                ",name = '" + name + '\'' +
                ",season_number = '" + seasonNumber + '\'' +
                ",id = '" + id + '\'' +
                ",poster_path = '" + posterPath + '\'' +
                "}"
    }
}