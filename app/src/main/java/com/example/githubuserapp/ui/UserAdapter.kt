package com.example.githubuserapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.githubuserapp.data.response.ItemsItem
import com.example.githubuserapp.databinding.UserItemLayoutBinding

class UserAdapter : ListAdapter<ItemsItem, UserAdapter.MyViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = UserItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int, ) {
        val user = getItem(position)
        holder.bind(user)

        holder.itemView.setOnClickListener {
            val user = getItem(position)
            val intent = Intent(holder.itemView.context, DetailUser::class.java)
            intent.putExtra("USERNAME", user.login)

            val intent2 = Intent(holder.itemView.context, SectionsPagerAdapter::class.java)
            intent2.putExtra("USERNAME", user.login)
            holder.itemView.context.startActivity(intent)

//            val position = holder.adapterPosition
//            val username = getItem(position)?.login // Mengambil username dari data pada posisi yang diklik
//
//            val bundle = Bundle()
//            bundle.putInt("POSITION_KEY", position)
//            bundle.putString("USERNAME_KEY", username)
//
//            val fragment = FollowFragment()
//            fragment.arguments = bundle
        }
    }

    class MyViewHolder(val binding: UserItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: ItemsItem){
            binding.tvItemName.text = user.login
            Glide.with(binding.imgItemPhoto).load(user.avatarUrl).transform(CircleCrop()).into(binding.imgItemPhoto)
        }
    }
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ItemsItem>() {
            override fun areItemsTheSame(oldItem: ItemsItem, newItem: ItemsItem): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: ItemsItem, newItem: ItemsItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}
