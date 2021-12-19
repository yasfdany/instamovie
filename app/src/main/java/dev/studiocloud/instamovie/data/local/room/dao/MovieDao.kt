package dev.studiocloud.instamovie.data.local.room.dao

import androidx.room.*
import dev.studiocloud.instamovie.data.local.entity.Movie

@Dao
interface MovieDao {
    @get:Query("SELECT COUNT(id) FROM Movie")
    val count: Int

    @get:Query("SELECT * FROM Movie")
    val allData: MutableList<Movie?>?

    @Query("SELECT * FROM Movie WHERE id = :id")
    fun getData(id: Int): MutableList<Movie?>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(movieRoom: Movie?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllData(movieRooms: MutableList<Movie?>?)

    @Delete
    fun deleteData(movieRoom: Movie?)

    @Update
    fun updateData(movieRoom: Movie?)

    @Query("DELETE FROM Movie")
    fun clearData()

}