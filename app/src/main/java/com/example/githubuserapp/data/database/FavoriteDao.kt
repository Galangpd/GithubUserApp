package com.example.githubuserapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(note: FavoriteUser)

    @Update
    fun update(note: FavoriteUser)

    @Delete
    fun delete(note: FavoriteUser)

    @Query("SELECT * from favoriteUser ORDER BY username ASC")
    fun getAllNotes(): LiveData<List<FavoriteUser>>
}