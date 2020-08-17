package com.healtdiary.app.data

import androidx.room.*

@Dao
interface DataDao {

    @Query("SELECT * from Data ORDER BY id DESC")
    fun getData(): List<Data>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(data: Data?)

    @Update
    fun update(data: Data?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(places: List<Data>)

    @Query("DELETE FROM Data WHERE id=:id")
    fun deleteEntry(id: Int)

    @Query("DELETE FROM Data")
    fun deleteAll()

}