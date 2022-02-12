package com.example.git_friends.ui.userprofilefragment

import UserReposGitHub
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.git_friends.data.App
import com.example.git_friends.data.webconnection.RetrofitUserProfile
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers


private const val BASEURl: String = "https://api.github.com/"

class UserProfileViewModel: ViewModel(),ContractViewModel {

    private val listUserProfileViewModel = MutableLiveData<List<UserReposGitHub>>()

    override fun getListUserRepoGitHub(loginUser: String): LiveData<List<UserReposGitHub>> {
        webRequest(loginUser)
        return listUserProfileViewModel
    }

    private fun webRequest(loginUser: String){
        val retrofit = App.instance.retrofitInstanceRx(BASEURl)
        val serviceApi = retrofit.create(RetrofitUserProfile::class.java)

        serviceApi.loadUsers(loginUser)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext {
                listUserProfileViewModel.postValue(it)
            }
            .doOnError {  }
            .subscribe()

    }

}