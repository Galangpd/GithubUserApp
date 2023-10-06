package com.example.githubuserapp.data.helper

import androidx.recyclerview.widget.DiffUtil
import com.example.githubuserapp.data.database.FavoriteUser

class FavoriteDiffCallBack (private val oldList: List<FavoriteUser>, private val newList: List<FavoriteUser>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldFavorite = oldList[oldItemPosition]
        val newFavorite = newList[newItemPosition]
        return oldFavorite.username == newFavorite.username && oldFavorite.avatarUrl == newFavorite.avatarUrl
    }
}