package dev.studiocloud.instamovie.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
class MovieDetail {
    @PrimaryKey(autoGenerate = true)
    var id_room = 0

    @ColumnInfo(name = "original_language")
    var originalLanguage: String? = null

    @ColumnInfo(name = "imdb_id")
    var imdbId: String? = null

    @ColumnInfo(name = "video")
    var isVideo = false

    @ColumnInfo(name = "title")
    var title: String? = null

    @ColumnInfo(name = "backdrop_path")
    var backdropPath: String? = null

    @ColumnInfo(name = "revenue")
    var revenue = 0

    @ColumnInfo(name = "popularity")
    var popularity = 0.0

    @ColumnInfo(name = "id")
    var id = 0

    @ColumnInfo(name = "vote_count")
    var voteCount = 0

    @ColumnInfo(name = "budget")
    var budget = 0

    @ColumnInfo(name = "overview")
    var overview: String? = null

    @ColumnInfo(name = "original_title")
    var originalTitle: String? = null

    @ColumnInfo(name = "runtime")
    var runtime = 0

    @ColumnInfo(name = "poster_path")
    var posterPath: String? = null

    @ColumnInfo(name = "release_date")
    var releaseDate: String? = null

    @ColumnInfo(name = "vote_average")
    var voteAverage = 0.0

    @ColumnInfo(name = "tagline")
    var tagline: String? = null

    @ColumnInfo(name = "homepage")
    var homepage: String? = null

    @ColumnInfo(name = "status")
    var status: String? = null
}
