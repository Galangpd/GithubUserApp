package com.example.githubuserapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(username: FavoriteUser)

    @Update
    fun update(username: FavoriteUser)

    @Query("DELETE FROM favoriteUser WHERE username = :username")
    fun delete(username: String)

    @Query("SELECT * from favoriteUser ORDER BY username ASC")
    fun getAllFavorites(): LiveData<List<FavoriteUser>>

    @Query("SELECT * FROM FavoriteUser WHERE username = :username LIMIT 1")
    fun getUserFavorite(username: String) : FavoriteUser?
}