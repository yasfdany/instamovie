package dev.studiocloud.instamovie.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity
class Tv {
    @SerializedName("first_air_date")
    @ColumnInfo(name = "first_air_date")
    var firstAirDate: String? = null

    @SerializedName("overview")
    @ColumnInfo(name = "overview")
    var overview: String? = null

    @SerializedName("original_language")
    @ColumnInfo(name = "original_language")
    var originalLanguage: String? = null

    @SerializedName("poster_path")
    @ColumnInfo(name = "poster_path")
    var posterPath: String? = null

    @SerializedName("backdrop_path")
    @ColumnInfo(name = "backdrop_path")
    var backdropPath: String? = null

    @SerializedName("original_name")
    @ColumnInfo(name = "original_name")
    var originalName: String? = null

    @SerializedName("popularity")
    @ColumnInfo(name = "popularity")
    var popularity = 0.0

    @SerializedName("vote_average")
    @ColumnInfo(name = "vote_average")
    var voteAverage = 0.0

    @SerializedName("name")
    @ColumnInfo(name = "name")
    var name: String? = null

    @SerializedName("id")
    @ColumnInfo(name = "id")
    @PrimaryKey
    var id = 0

    @SerializedName("vote_count")
    @ColumnInfo(name = "vote_count")
    var voteCount = 0
}
