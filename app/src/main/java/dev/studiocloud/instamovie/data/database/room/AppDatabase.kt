package dev.studiocloud.instamovie.data.database.room

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.studiocloud.instamovie.data.database.entity.Movie
import dev.studiocloud.instamovie.data.database.entity.MovieDetail
import dev.studiocloud.instamovie.data.database.entity.Tv
import dev.studiocloud.instamovie.data.database.room.dao.MovieDao
import dev.studiocloud.instamovie.data.database.room.dao.MovieDetailDao
import dev.studiocloud.instamovie.data.database.room.dao.TvDao

@Database(
    entities = [Movie::class, MovieDetail::class, Tv::class],
    version = 1,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun movieDetailDao(): MovieDetailDao
    abstract fun tvDao(): TvDao
}
