package com.example.git_friends.ui.listuserfragment


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.git_friends.data.App
import com.example.git_friends.domain.UserEntity

class ListUserFragmentViewModel: ViewModel() {
    private var listUser: List<UserEntity> = mutableListOf()
     val listUserViewModel = MutableLiveData<List<UserEntity>>()

    init {
        /** попробовал изменить значение поля прямо в классе*/
        listUser = App.instance.getInstanceUserEntityRepo().readUser()
        listUser.forEach {
            if(it.id == 4L){it.login = "ytrytyrtyurtruyt"}
        }

        listUser = App.instance.getInstanceUserEntityRepo().readUser()
    }

    fun getDataFromViewModel(){
        listUserViewModel.postValue(listUser)
    }




}