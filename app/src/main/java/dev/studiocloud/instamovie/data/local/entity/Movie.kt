package dev.studiocloud.instamovie.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity
class Movie {
    @PrimaryKey(autoGenerate = true)
    var id_room = 0

    @ColumnInfo(name = "progress")
    var overview: String? = null

    @ColumnInfo(name = "original_language")
    var originalLanguage: String? = null

    @ColumnInfo(name = "original_title")
    var originalTitle: String? = null

    @ColumnInfo(name = "video")
    var isVideo = false

    @ColumnInfo(name = "title")
    var title: String? = null

    @ColumnInfo(name = "poster_path")
    var posterPath: String? = null

    @ColumnInfo(name = "backdrop_path")
    var backdropPath: String? = null

    @ColumnInfo(name = "release_date")
    var releaseDate: String? = null

    @ColumnInfo(name = "vote_average")
    var voteAverage = 0.0

    @ColumnInfo(name = "popularity")
    var popularity = 0.0

    @ColumnInfo(name = "id")
    var id = 0

    @ColumnInfo(name = "adult")
    var isAdult = false

    @SerializedName("vote_count")
    var voteCount = 0
}
