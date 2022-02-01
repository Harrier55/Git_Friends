package com.example.git_friends.ui.userprofilefragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.git_friends.R
import com.example.git_friends.databinding.FragmentListUsersBinding


class UserProfileFragment : Fragment() {

    private var _binding:FragmentListUsersBinding?=null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val bundle = arguments
        if (bundle != null) {
            val recieveInfo = bundle.getInt("KEY")
            Toast.makeText(requireContext(),recieveInfo.toString(),Toast.LENGTH_SHORT).show()
        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_profile, container, false)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}