package com.example.githubuserapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuserapp.data.response.ItemsItem
import com.example.githubuserapp.databinding.FragmentFollowBinding


class FollowFragment : Fragment() {
    private lateinit var binding: FragmentFollowBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFollowBinding.inflate(layoutInflater, container, false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var username = arguments?.getString(ARG_USERNAME)
        var position = 0

//        Log.d("arguments: position", position.toString())
//        Log.d("arguments: username", username.toString())

        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProvider(
            this, ViewModelProvider.NewInstanceFactory()
        ).get(DetailUserModel::class.java)
        binding.rvFollow.layoutManager = LinearLayoutManager(requireActivity())

        arguments?.let {
            position = it.getInt(ARG_POSITION)
            username = it.getString(ARG_USERNAME)
        }
        if (position == 1) {
            username?.let { viewModel.getFollowers(it) }
            viewModel.followers.observe(viewLifecycleOwner) {
                setUserFollow(it)
            }
        } else {
            username?.let { viewModel.getFollowing(it) }
            viewModel.following.observe(viewLifecycleOwner) {
                setUserFollow(it)
            }
        }

    }

    private fun setUserFollow(userfollow: List<ItemsItem>) {
        val adapter = UserAdapter()
        adapter.submitList(userfollow)
        binding.rvFollow.adapter = adapter
    }

    companion object {
        const val ARG_POSITION = "position"
        const val ARG_USERNAME = "username"
    }
}
