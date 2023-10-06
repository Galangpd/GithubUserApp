package com.example.githubuserapp.ui

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubuserapp.data.database.FavoriteUser
import com.example.githubuserapp.data.repository.FavoriteRepository

class FavoriteUserModel(application: Application, private val mFavoriteRepository: FavoriteRepository) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getAllFavorites(): LiveData<List<FavoriteUser>> {
       return mFavoriteRepository.getAllFavorites()
    }
}