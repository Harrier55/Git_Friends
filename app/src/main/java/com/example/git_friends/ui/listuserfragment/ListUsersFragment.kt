package com.example.git_friends.ui.listuserfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.git_friends.R
import com.example.git_friends.databinding.FragmentListUsersBinding


class ListUsersFragment : Fragment() {

    private var _binding: FragmentListUsersBinding? = null
    private val binding get() = _binding!!
    private val listUserAdapter by lazy { ListUserAdapterRecyclerView() }
    private val viewModel by lazy { ViewModelProvider(this)[ListUserFragmentViewModel::class.java] }


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

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }


}