package dev.studiocloud.instamovie.data.model.movieResponse

import com.google.gson.annotations.SerializedName

class MovieItem {
    @SerializedName("overview")
    var overview: String? = null

    @SerializedName("original_language")
    var originalLanguage: String? = null

    @SerializedName("original_title")
    var originalTitle: String? = null

    @SerializedName("video")
    var isVideo = false

    @SerializedName("title")
    var title: String? = null

    @SerializedName("genre_ids")
    var genreIds: List<Int?>? = null

    @SerializedName("poster_path")
    var posterPath: String? = null

    @SerializedName("backdrop_path")
    var backdropPath: String? = null

    @SerializedName("release_date")
    var releaseDate: String? = null

    @SerializedName("vote_average")
    var voteAverage = 0.0

    @SerializedName("popularity")
    var popularity = 0.0

    @SerializedName("id")
    var id = 0

    @SerializedName("adult")
    var isAdult = false

    @SerializedName("vote_count")
    var voteCount = 0

    @SerializedName("loved")
    var loved = false

    @SerializedName("saved")
    var saved = false
}