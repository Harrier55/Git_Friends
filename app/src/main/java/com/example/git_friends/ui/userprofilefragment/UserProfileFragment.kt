package com.example.git_friends.ui.userprofilefragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.git_friends.R
import com.example.git_friends.databinding.FragmentListUsersBinding
import com.example.git_friends.databinding.FragmentUserProfileBinding
import com.example.git_friends.domain.UserEntity


class UserProfileFragment : Fragment() {

    private var _binding:FragmentUserProfileBinding?=null
    private val binding get() = _binding!!

    private lateinit var  recieveInfo: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle = arguments
        if (bundle != null) {
            recieveInfo = bundle.getString("KEY").toString()

            Toast.makeText(requireContext(),recieveInfo,Toast.LENGTH_SHORT).show()
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_user_profile, container, false)
        _binding = FragmentUserProfileBinding.bind(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loginUserProfileTextView.text = recieveInfo
    }



    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}