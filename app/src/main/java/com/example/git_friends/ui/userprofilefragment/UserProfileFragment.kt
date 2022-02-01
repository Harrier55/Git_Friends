package com.example.git_friends.ui.userprofilefragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.git_friends.R
import com.example.git_friends.databinding.FragmentListUsersBinding


class UserProfileFragment : Fragment() {

    private var _binding:FragmentListUsersBinding?=null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_prifile, container, false)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}