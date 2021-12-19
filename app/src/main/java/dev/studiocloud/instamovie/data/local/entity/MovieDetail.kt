package dev.studiocloud.instamovie.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity
class MovieDetail {
    @SerializedName("original_language")
    @ColumnInfo(name = "original_language")
    var originalLanguage: String? = null

    @SerializedName("imdb_id")
    @ColumnInfo(name = "imdb_id")
    var imdbId: String? = null

    @SerializedName("video")
    @ColumnInfo(name = "video")
    var isVideo = false

    @SerializedName("title")
    @ColumnInfo(name = "title")
    var title: String? = null

    @SerializedName("backdrop_path")
    @ColumnInfo(name = "backdrop_path")
    var backdropPath: String? = null

    @SerializedName("revenue")
    @ColumnInfo(name = "revenue")
    var revenue = 0

    @SerializedName("popularity")
    @ColumnInfo(name = "popularity")
    var popularity = 0.0

    @SerializedName("id")
    @ColumnInfo(name = "id")
    @PrimaryKey
    var id = 0

    @SerializedName("vote_count")
    @ColumnInfo(name = "vote_count")
    var voteCount = 0

    @SerializedName("budget")
    @ColumnInfo(name = "budget")
    var budget = 0

    @SerializedName("overview")
    @ColumnInfo(name = "overview")
    var overview: String? = null

    @SerializedName("original_title")
    @ColumnInfo(name = "original_title")
    var originalTitle: String? = null

    @SerializedName("runtime")
    @ColumnInfo(name = "runtime")
    var runtime = 0

    @SerializedName("poster_path")
    @ColumnInfo(name = "poster_path")
    var posterPath: String? = null

    @SerializedName("release_date")
    @ColumnInfo(name = "release_date")
    var releaseDate: String? = null

    @SerializedName("vote_average")
    @ColumnInfo(name = "vote_average")
    var voteAverage = 0.0

    @SerializedName("tagline")
    @ColumnInfo(name = "tagline")
    var tagline: String? = null

    @SerializedName("homepage")
    @ColumnInfo(name = "homepage")
    var homepage: String? = null

    @SerializedName("status")
    @ColumnInfo(name = "status")
    var status: String? = null

    @SerializedName("genres_list")
    @ColumnInfo(name = "genres_list")
    var genres: String? = null
}
