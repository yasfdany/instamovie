package dev.studiocloud.instamovie.data.database.room.dao

import androidx.room.*
import dev.studiocloud.instamovie.data.database.entity.MovieDetail

@Dao
interface MovieDetailDao {
    @get:Query("SELECT COUNT(id) FROM MovieDetail")
    val count: Int

    @get:Query("SELECT * FROM MovieDetail")
    val allData: MutableList<MovieDetail>

    @Query("SELECT * FROM MovieDetail WHERE id = :id")
    fun getData(id: Int): MutableList<MovieDetail>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(movieDetail: MovieDetail?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllData(movieDetails: MutableList<MovieDetail>)

    @Delete
    fun deleteData(movieDetail: MovieDetail?)

    @Update
    fun updateData(movieDetail: MovieDetail?)

    @Query("DELETE FROM MovieDetail")
    fun clearData()
}
