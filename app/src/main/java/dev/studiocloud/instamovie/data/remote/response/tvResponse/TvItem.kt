package dev.studiocloud.instamovie.data.remote.response.tvResponse

import com.google.gson.annotations.SerializedName

class TvItem {
    @SerializedName("first_air_date")
    var firstAirDate: String? = null

    @SerializedName("overview")
    var overview: String? = null

    @SerializedName("original_language")
    var originalLanguage: String? = null

    @SerializedName("genre_ids")
    var genreIds: List<Int?>? = null

    @SerializedName("poster_path")
    var posterPath: String? = null

    @SerializedName("origin_country")
    var originCountry: List<String>? = null

    @SerializedName("backdrop_path")
    var backdropPath: String? = null

    @SerializedName("original_name")
    var originalName: String? = null

    @SerializedName("popularity")
    var popularity = 0.0

    @SerializedName("vote_average")
    var voteAverage = 0.0

    @SerializedName("name")
    var name: String? = null

    @SerializedName("id")
    var id = 0

    @SerializedName("vote_count")
    var voteCount = 0
}