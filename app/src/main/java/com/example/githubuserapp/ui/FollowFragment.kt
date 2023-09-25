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
        binding = FragmentFollowBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        val position = arguments?.getInt(ARG_POSITION) ?: 0
        val username = arguments?.getString(ARG_USERNAME)

        val viewModel = ViewModelProvider(requireActivity()).get(DetailUserModel::class.java)
        binding.rvFollow.layoutManager = LinearLayoutManager(requireActivity())

        if (position == 1) {
            username?.let { viewModel.getFollowers(it) }
            viewModel.followers.observe(viewLifecycleOwner) { followers ->
                setUserFollow(followers)
            }
        } else {
            username?.let { viewModel.getFollowing(it) }
            viewModel.following.observe(viewLifecycleOwner) { following ->
                setUserFollow(following)
            }
        }


    }

    private fun setUserFollow(userFollow: List<ItemsItem>) {
        val adapter = UserAdapter()
        adapter.submitList(userFollow)
        binding.rvFollow.adapter = adapter
    }

    companion object {
        const val ARG_POSITION = "position"
        const val ARG_USERNAME = "username"
    }

}
