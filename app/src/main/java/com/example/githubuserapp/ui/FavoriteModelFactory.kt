package com.example.githubuserapp.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.githubuserapp.data.repository.FavoriteRepository

class FavoriteModelFactory (private val application: Application,
private val repository: FavoriteRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoriteUserModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FavoriteUserModel(application, repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}