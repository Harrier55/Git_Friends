package com.example.git_friends.ui.listuserfragment

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.git_friends.R
import com.example.git_friends.data.App
import com.example.git_friends.domain.UserEntity

class ListUserFragmentViewModel: ViewModel() {
    private var listUser: List<UserEntity> = mutableListOf()
     val listUserViewModel = MutableLiveData<List<UserEntity>>()

    init {
        listUser = App.instance.getInstanceUserEntityRepo().readUser()
    }

    fun getDataFromViewModel(){
        listUserViewModel.postValue(listUser)
    }




}