package com.example.githubuserapp.ui.insert

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.githubuserapp.data.database.FavoriteUser
import com.example.githubuserapp.data.repository.FavoriteRepository

class FavoriteAddUpdateViewModel(application: Application) : ViewModel() {

    private val mFavoriteRepository: FavoriteRepository = FavoriteRepository(application)

    fun insert(note: FavoriteUser) {
        mFavoriteRepository.insert(note)
    }

    fun update(note: FavoriteUser) {
        mFavoriteRepository.update(note)
    }

    fun delete(note: FavoriteUser) {
        mFavoriteRepository.delete(note)
    }

}