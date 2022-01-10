package dev.studiocloud.instamovie.data.database.room.dao

import androidx.room.*
import dev.studiocloud.instamovie.data.database.entity.Tv


@Dao
interface TvDao {
    @get:Query("SELECT COUNT(id) FROM Tv")
    val count: Int

    @get:Query("SELECT * FROM Tv")
    val allData: MutableList<Tv>

    @Query("SELECT * FROM Tv WHERE id = :id")
    fun getData(id: Int): MutableList<Tv>

    @Query("SELECT * FROM Tv WHERE name LIKE '%' || :title || '%'")
    fun searchData(title: String): MutableList<Tv>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(tvRoom: Tv?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllData(tvRooms: MutableList<Tv>)

    @Delete
    fun deleteData(tvRoom: Tv?)

    @Update
    fun updateData(tvRoom: Tv?)

    @Query("DELETE FROM Tv")
    fun clearData()
}
