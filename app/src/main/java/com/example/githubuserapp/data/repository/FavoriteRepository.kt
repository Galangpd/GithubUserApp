package com.example.githubuserapp.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.githubuserapp.data.database.FavoriteDao
import com.example.githubuserapp.data.database.FavoriteRoomDatabase
import com.example.githubuserapp.data.database.FavoriteUser
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FavoriteRepository(application: Application) {
    private val mFavoriteDao: FavoriteDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = FavoriteRoomDatabase.getDatabase(application)
        mFavoriteDao = db.favoriteDao()
    }


    fun getAllFavorites(): LiveData<List<FavoriteUser>> = mFavoriteDao.getAllFavorites()

    fun getUserFavorite(username: String): FavoriteUser? {
        return mFavoriteDao.getUserFavorite(username)
    }
    fun insert(username: FavoriteUser) {
        executorService.execute { mFavoriteDao.insert(username) }
    }

    fun delete(username: String) {
        executorService.execute { mFavoriteDao.delete(username) }
    }

}