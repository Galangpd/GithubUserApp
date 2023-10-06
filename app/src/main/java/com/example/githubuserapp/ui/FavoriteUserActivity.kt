package com.example.githubuserapp.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuserapp.data.database.FavoriteUser
import com.example.githubuserapp.data.repository.FavoriteRepository
import com.example.githubuserapp.databinding.ActivityFavoriteUserBinding

class FavoriteUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteUserBinding

    private lateinit var favoriteUserModel: FavoriteUserModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFavoriteUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val repository = FavoriteRepository(application)
        val viewModelFactory = FavoriteModelFactory(application, repository)
        favoriteUserModel = ViewModelProvider(this, viewModelFactory).get(FavoriteUserModel::class.java)

        favoriteUserModel.getAllFavorites().observe(this){
            setDataFavorite(it)
        }
        favoriteUserModel.isLoading.observe(this){
            showLoading(it)
        }
    }

    private fun setDataFavorite(user: List<FavoriteUser>) {
        val adapter = FavoriteAdapter()
        adapter.setListFavorite(user)
        binding.rvFavoriteUser.adapter = adapter
        binding.rvFavoriteUser.layoutManager = LinearLayoutManager(this)
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

}