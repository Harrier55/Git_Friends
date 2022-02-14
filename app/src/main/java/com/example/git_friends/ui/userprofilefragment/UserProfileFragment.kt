package com.example.git_friends.ui.userprofilefragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.git_friends.R
import com.example.git_friends.databinding.FragmentUserProfileBinding
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide

class UserProfileFragment : Fragment() {

    private val TAG: String = "@@@"

    private var _binding: FragmentUserProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var recieveInfoLoginUser: String
    private val viewModel by lazy { ViewModelProvider(this)[UserProfileViewModel::class.java] }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle = arguments
        if (bundle != null) {
            recieveInfoLoginUser = bundle.getString("KEY").toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user_profile, container, false)
        _binding = FragmentUserProfileBinding.bind(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loginUserProfileTextView.text = recieveInfoLoginUser

        viewModel.getListUserRepoGitHub(recieveInfoLoginUser)
            .observe(viewLifecycleOwner, Observer { it ->
                val users = arrayListOf<String>()

                it.forEach {
                    users.add(it.name)
                }

                val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_list_item_1, users
                )
                binding.loginUserProfileListView.adapter = arrayAdapter
            })

        viewModel.getAvatar().observe(viewLifecycleOwner, Observer { avatarUrl ->
            Glide.with(requireContext())
                .load(avatarUrl)
                .circleCrop()
                .placeholder(R.drawable.ic_baseline_attribution_24)
                .into(binding.avatarUserProfileImageView)
        })
        /** если пользователь есть в репозитории, то кнопка ДОБАВИТЬ не будет видна*/
        viewModel.checkUserInTheList(recieveInfoLoginUser).observe(viewLifecycleOwner, Observer {
            binding.addUserUserProfileButton.isVisible = it
        })

        binding.addUserUserProfileButton.setOnClickListener {
            viewModel.addUser(recieveInfoLoginUser)
            binding.addUserUserProfileButton.isVisible = false
            Toast.makeText(requireContext(),"Друг добавлен в список",Toast.LENGTH_SHORT).show()
        }


    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}