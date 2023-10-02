package com.example.githubuserapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.githubuserapp.R
import com.example.githubuserapp.data.database.FavoriteUser
import com.example.githubuserapp.data.repository.FavoriteRepository
import com.example.githubuserapp.databinding.ActivityDetailUserBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailUser : AppCompatActivity() {

    private lateinit var binding: ActivityDetailUserBinding

    private var isFavorite = false

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detailUserModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(DetailUserModel::class.java)

        supportActionBar?.hide()

        val username = intent.getStringExtra("USERNAME")

        if (username != null) {
            detailUserModel.getDetailUser(username)
            detailUserModel.getFollowers(username)
            detailUserModel.getFollowing(username)
        }

        detailUserModel.isLoading.observe(this){
            showLoading(it)
        }

        detailUserModel.detailUser.observe(this, Observer { detailUser ->
            val followers = detailUser.followers
            val following = detailUser.following

            binding.tvDetailName.text = detailUser.name
            binding.tvDetailDescription.text = detailUser.login
            binding.tvFollowersValue.text = "$followers"
            binding.tvFollowingValue.text = "$following"
            Glide.with(binding.ivDetailPhoto)
                .load(detailUser.avatarUrl)
                .transform(CircleCrop())
                .into(binding.ivDetailPhoto)
    })

        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        val viewPager: ViewPager2 = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter

        val tabs: TabLayout = binding.tabs
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
        supportActionBar?.elevation = 0f

        binding.fabFavorite.setOnClickListener {
            // Tangani aksi klik pada FAB di sini
            if (isFavorite) {
                // Hapus user dari database favorite jika sudah ada
//                removeFromFavorites()
            } else {
                // Tambahkan user ke dalam database favorite jika belum ada
                addToFavorites()
            }
        }

    }

    private fun addToFavorites() {
        val mFavoriteRepository = FavoriteRepository(application)
        val detailUserModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(DetailUserModel::class.java)
        val username = intent.getStringExtra("USERNAME")
        val avatarUrl = detailUserModel.detailUser.value?.avatarUrl

        // Buat objek FavoriteUser
        val favoriteUser = FavoriteUser(id = 0,username ?: "", avatarUrl)

        // Simpan objek ke dalam database favorit
        mFavoriteRepository.insert(favoriteUser)

        // Ubah ikon FAB menjadi ikon favorit
        binding.fabFavorite.setImageResource(R.drawable.baseline_favorite_24)

        // Set status user menjadi favorit
        isFavorite = true
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

}