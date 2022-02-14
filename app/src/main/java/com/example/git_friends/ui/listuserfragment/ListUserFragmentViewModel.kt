package com.example.git_friends.ui.listuserfragment


import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.git_friends.data.App
import com.example.git_friends.domain.UserEntity
import io.reactivex.disposables.Disposable
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.subscribeBy

class ListUserFragmentViewModel : ViewModel(),ContractViewModelListUserFragment {
    private var listUser: List<UserEntity> = mutableListOf()
    private val listUserViewModel = MutableLiveData<List<UserEntity>>()


    init {
        listUser = App.instance.getInstanceUserEntityRepo().readUser()
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