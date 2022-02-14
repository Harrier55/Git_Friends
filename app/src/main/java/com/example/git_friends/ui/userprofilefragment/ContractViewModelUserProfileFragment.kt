package com.example.git_friends.ui.userprofilefragment

import UserReposGitHub
import androidx.lifecycle.LiveData

interface ContractViewModelUserProfileFragment {

    fun getListUserRepoGitHub(loginUser:String): LiveData<List<UserReposGitHub>>
    fun getAvatar():LiveData<String>

}