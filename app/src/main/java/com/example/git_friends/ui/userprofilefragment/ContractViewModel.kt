package com.example.git_friends.ui.userprofilefragment

import UserReposGitHub
import androidx.lifecycle.LiveData

interface ContractViewModel {

    fun getListUserRepoGitHub(loginUser:String): LiveData<List<UserReposGitHub>>

}