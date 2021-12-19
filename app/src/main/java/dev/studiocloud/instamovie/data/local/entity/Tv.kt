package dev.studiocloud.instamovie.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
class Tv {
    @PrimaryKey(autoGenerate = true)
    var id_room = 0

    @ColumnInfo(name = "first_air_date")
    var firstAirDate: String? = null

    @ColumnInfo(name = "overview")
    var overview: String? = null

    @ColumnInfo(name = "original_language")
    var originalLanguage: String? = null

    @ColumnInfo(name = "poster_path")
    var posterPath: String? = null

    @ColumnInfo(name = "backdrop_path")
    var backdropPath: String? = null

    @ColumnInfo(name = "original_name")
    var originalName: String? = null

    @ColumnInfo(name = "popularity")
    var popularity = 0.0

    @ColumnInfo(name = "vote_average")
    var voteAverage = 0.0

    @ColumnInfo(name = "name")
    var name: String? = null

    @ColumnInfo(name = "id")
    var id = 0

    @ColumnInfo(name = "vote_count")
    var voteCount = 0
}
