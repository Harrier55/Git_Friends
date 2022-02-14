package com.example.git_friends.ui.listuserfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.git_friends.R
import com.example.git_friends.databinding.FragmentListUsersBinding
import com.example.git_friends.domain.UserEntity
import com.example.git_friends.ui.ManageFragment


class ListUsersFragment(private val manageFragment: ManageFragment) : Fragment() {

    private var _binding: FragmentListUsersBinding? = null
    private val binding get() = _binding!!
    private val listUserAdapter by lazy { ListUserAdapterRecyclerView(onClickItemListUsersFragment) }
    private val viewModel by lazy { ViewModelProvider(this)[ListUserFragmentViewModel::class.java] }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list_users, container, false)
        _binding = FragmentListUsersBinding.bind(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler(view)
        binding.listUsersSearchButton.setOnClickListener {
            val searchLoginUser = binding.listUsersSearchUsersEditText.text.toString()
            manageFragment.sendUserProfileFragment(searchLoginUser)
        }

    }

//    private fun searchUser(){
//        val searchLoginUser = binding.listUsersSearchUsersEditText.text.toString()
//        Toast.makeText(requireContext(),searchLoginUser,Toast.LENGTH_SHORT).show()
//    }

    private fun initRecycler(view: View) {
        binding.listUsersRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.listUsersRecyclerView.adapter = listUserAdapter

        viewModel.getListUsersFromViewModel().observe(viewLifecycleOwner, Observer {
            listUserAdapter.refreshListRecyclerView(it)
        })
    }

    private var onClickItemListUsersFragment = object : OnClickItemListUsersFragmentRecyclerView {
        override fun onClickItemUser(userEntity: UserEntity) {
            manageFragment.sendUserProfileFragment(userEntity.login)
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }


}