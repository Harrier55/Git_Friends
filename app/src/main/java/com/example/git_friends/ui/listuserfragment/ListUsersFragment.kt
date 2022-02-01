package com.example.git_friends.ui.listuserfragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.git_friends.R
import com.example.git_friends.databinding.FragmentListUsersBinding
import com.example.git_friends.domain.UserEntity
import com.example.git_friends.ui.MainActivity
import com.example.git_friends.ui.userprofilefragment.UserProfileFragment


class ListUsersFragment : Fragment() {

    private var _binding: FragmentListUsersBinding? = null
    private val binding get() = _binding!!
    private val listUserAdapter by lazy { ListUserAdapterRecyclerView(onClickItemListUsersFragment) }
    private val viewModel by lazy { ViewModelProvider(this)[ListUserFragmentViewModel::class.java] }
    private val userProfileFragment: UserProfileFragment = UserProfileFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getDataFromViewModel()
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
    }

    private fun initRecycler(view: View) {
        binding.listUsersRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.listUsersRecyclerView.adapter = listUserAdapter
        viewModel.listUserViewModel.observe(viewLifecycleOwner, Observer {
            listUserAdapter.refreshListRecyclerView(it)
        })
    }

    private var onClickItemListUsersFragment = object : OnClickItemListUsersFragment {
        override fun onClickItemUser(userEntity: UserEntity) {
            Toast.makeText(requireContext(), userEntity.login, Toast.LENGTH_SHORT).show()

            val bundle = bundleOf(Pair("KEY",10000))
            userProfileFragment.arguments = bundle


        parentFragmentManager.beginTransaction().replace(R.id.fragment_container,userProfileFragment).commit()

//            initFragmentManager()

        }
    }



    private fun initFragmentManager() {
        val fragmentManager: FragmentManager = parentFragmentManager

        fragmentManager.beginTransaction()
            .replace(R.id.fragment_container,userProfileFragment)
            .addToBackStack("ListUserFragment")
            .commit()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }


}