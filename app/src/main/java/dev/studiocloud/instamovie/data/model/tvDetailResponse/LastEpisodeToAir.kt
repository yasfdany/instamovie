package dev.studiocloud.instamovie.data.model.tvDetailResponse

import com.google.gson.annotations.SerializedName

class LastEpisodeToAir {
    @SerializedName("production_code")
    var productionCode: String? = null

    @SerializedName("air_date")
    var airDate: String? = null

    @SerializedName("overview")
    var overview: String? = null

    @SerializedName("episode_number")
    var episodeNumber = 0

    @SerializedName("show_id")
    var showId = 0

    @SerializedName("vote_average")
    var voteAverage = 0.0

    @SerializedName("name")
    var name: String? = null

    @SerializedName("season_number")
    var seasonNumber = 0

    @SerializedName("id")
    var id = 0

    @SerializedName("still_path")
    var stillPath: String? = null

    @SerializedName("vote_count")
    var voteCount = 0
    override fun toString(): String {
        return "LastEpisodeToAir{" +
                "production_code = '" + productionCode + '\'' +
                ",air_date = '" + airDate + '\'' +
                ",overview = '" + overview + '\'' +
                ",episode_number = '" + episodeNumber + '\'' +
                ",show_id = '" + showId + '\'' +
                ",vote_average = '" + voteAverage + '\'' +
                ",name = '" + name + '\'' +
                ",season_number = '" + seasonNumber + '\'' +
                ",id = '" + id + '\'' +
                ",still_path = '" + stillPath + '\'' +
                ",vote_count = '" + voteCount + '\'' +
                "}"
    }
}