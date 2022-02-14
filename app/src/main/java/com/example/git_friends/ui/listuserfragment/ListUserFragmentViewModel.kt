package com.example.git_friends.ui.listuserfragment


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.git_friends.data.App
import com.example.git_friends.domain.UserEntity

class ListUserFragmentViewModel : ViewModel(),ContractViewModelListUserFragment {
    private var listUser: List<UserEntity> = mutableListOf()
    private val listUserViewModel = MutableLiveData<List<UserEntity>>()


    init {
        listUser = App.instance.getInstanceUserEntityRepo().readUsersList()
    }

    override fun loadData() {
        /**  прямой синхронный метод*/
 //       listUserViewModel.postValue(listUser)

        /** асинхронный метод через Rx */
        App.instance.getInstanceUserEntityRepo().singleListUser
            .doOnSuccess {  listUserViewModel.postValue(it)}
            .subscribe()
    }

    override fun getListUsersFromViewModel():MutableLiveData<List<UserEntity>>{
        return listUserViewModel
    }

}