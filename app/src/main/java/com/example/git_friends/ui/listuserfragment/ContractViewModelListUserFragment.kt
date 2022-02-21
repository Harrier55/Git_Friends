package com.example.git_friends.ui.listuserfragment

import androidx.lifecycle.MutableLiveData
import com.example.git_friends.domain.UserEntity

interface ContractViewModelListUserFragment {
    fun loadData()
    fun getListUsersFromViewModel(): MutableLiveData<List<UserEntity>>
    fun deleteUserEntity(userEntity:UserEntity)

}