package com.example.git_friends.ui.userprofilefragment

import UserReposGitHub
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.git_friends.data.userentityrepo.UserEntityRepo
import com.example.git_friends.data.webconnection.RetrofitUserProfile
import com.example.git_friends.di.Di
import com.example.git_friends.di.inject
import com.example.git_friends.domain.UserEntity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import retrofit2.Retrofit


private const val BASEURl: String = "https://api.github.com/"

class UserProfileViewModel : ViewModel(), ContractViewModelUserProfileFragment {

    private val listUserProfileViewModel = MutableLiveData<List<UserReposGitHub>>()
    private val avatar = MutableLiveData<String>()
    private var checkUserInTheList = MutableLiveData<Boolean>()

    private val userEntityRepo: UserEntityRepo = inject()
    private val retrofit: Retrofit = inject()


    init {
        checkUserInTheList.postValue(true)
    }

    override fun getListUserRepoGitHub(loginUser: String): LiveData<List<UserReposGitHub>> {
        webRequest(loginUser)
        return listUserProfileViewModel
    }

    override fun getAvatar(): LiveData<String> {
        return avatar
    }

    override fun addUser(loginUser: String) {
        userEntityRepo.createUser(UserEntity(login = loginUser))
    }

    fun checkUserInTheList(loginUser: String):LiveData<Boolean>{
       val listUsers =  userEntityRepo.readUsersList()
        listUsers.forEach { userEntity->
           if( userEntity.login?.lowercase() == loginUser.lowercase()){
               checkUserInTheList.postValue(false)  // пользователь есть в списке-кнопка невидима
               return checkUserInTheList
           }
        }
        checkUserInTheList.postValue(true)/// пользователя нет в списке, кнопка видима
        return checkUserInTheList
    }


    private fun webRequest(loginUser: String) {
//        val retrofit = App.instance.retrofitInstanceRx(BASEURl)
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
            .subscribe()

    }

}