package dev.studiocloud.instamovie.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.studiocloud.instamovie.data.local.entity.Movie
import dev.studiocloud.instamovie.data.local.entity.MovieDetail
import dev.studiocloud.instamovie.data.local.entity.Tv
import dev.studiocloud.instamovie.data.local.room.dao.MovieDao
import dev.studiocloud.instamovie.data.local.room.dao.MovieDetailDao
import dev.studiocloud.instamovie.data.local.room.dao.TvDao

@Database(
    entities = [Movie::class, MovieDetail::class, Tv::class],
    version = 1,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun movieDetailDao(): MovieDetailDao
    abstract fun tvDao(): TvDao
}
