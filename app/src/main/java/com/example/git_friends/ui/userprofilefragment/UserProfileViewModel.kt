package com.example.git_friends.ui.userprofilefragment

import UserReposGitHub
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.git_friends.data.App
import com.example.git_friends.data.webconnection.RetrofitUserProfile
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import java.io.IOException
import kotlin.concurrent.thread


private const val BASEURl: String = "https://api.github.com/"

class UserProfileViewModel : ViewModel(), ContractViewModelUserProfileFragment {

    private val listUserProfileViewModel = MutableLiveData<List<UserReposGitHub>>()
    private val avatar = MutableLiveData<String>()

    override fun getListUserRepoGitHub(loginUser: String): LiveData<List<UserReposGitHub>> {
        webRequest(loginUser)
        return listUserProfileViewModel
    }

    override fun getAvatar(): LiveData<String> {
        return avatar
    }


    private fun webRequest(loginUser: String) {
        val retrofit = App.instance.retrofitInstanceRx(BASEURl)
        val serviceApi = retrofit.create(RetrofitUserProfile::class.java)

            serviceApi.loadUsers(loginUser)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext {
                    if (it != null) {
                        listUserProfileViewModel.postValue(it)

                        try {
                            val avatarUrl = it[0].owner.avatar_url
                            avatar.postValue(avatarUrl)
                        } catch (e: Exception) {
                            // todo что то пошло не так
                        }
                    }
                }
                .doOnError {
                    Log.d("@@@", "webRequest: $it")

                }
                .doOnComplete { }
                .subscribe()

    }

}