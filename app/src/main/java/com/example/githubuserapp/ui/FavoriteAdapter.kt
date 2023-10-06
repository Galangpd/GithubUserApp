package com.example.githubuserapp.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.githubuserapp.data.database.FavoriteUser
import com.example.githubuserapp.data.helper.FavoriteDiffCallBack
import com.example.githubuserapp.databinding.UserItemLayoutBinding

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.ListViewHolder>() {

    companion object {
        const val EXTRA_NAME = "USERNAME"
        const val EXTRA_URL = "AVATAR_URL"
    }

    private val listUser = ArrayList<FavoriteUser>()
    fun setListFavorite(listUser: List<FavoriteUser>) {
        val diffCallBack = FavoriteDiffCallBack(this.listUser, listUser)
        val diffResult = DiffUtil.calculateDiff(diffCallBack)
        this.listUser.clear()
        this.listUser.addAll(listUser)
        diffResult.dispatchUpdatesTo(this)
    }

    inner class ListViewHolder(private val binding: UserItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: FavoriteUser) {
            with(binding) {
                tvItemName.text = user.username
                Glide.with(binding.root.context).load(user.avatarUrl).transform(CircleCrop())
                    .into(binding.imgItemPhoto)

                cardView.setOnClickListener {
                    val intent = Intent(it.context, DetailUserActivity::class.java)
                    intent.putExtra(EXTRA_NAME, user.username)
                    intent.putExtra(EXTRA_URL, user.avatarUrl)
                    it.context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding =
            UserItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = listUser.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listUser[position])
    }
}