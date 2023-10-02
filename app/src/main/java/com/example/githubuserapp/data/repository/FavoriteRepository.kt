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

    fun getAllNotes(): LiveData<List<FavoriteUser>> = mFavoriteDao.getAllNotes()

    fun insert(favoriteUser: FavoriteUser) {
        executorService.execute { mFavoriteDao.insert(favoriteUser) }
    }

    fun delete(favoriteUser: FavoriteUser) {
        executorService.execute { mFavoriteDao.delete(favoriteUser) }
    }

    fun update(favoriteUser: FavoriteUser) {
        executorService.execute { mFavoriteDao.update(favoriteUser) }
    }
}