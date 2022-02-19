package com.example.git_friends.ui.listuserfragment


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.git_friends.data.userentityrepo.UserEntityRepo
import com.example.git_friends.domain.UserEntity
import io.reactivex.rxjava3.disposables.Disposable

class ListUserFragmentViewModel(
    private val userEntityRepo: UserEntityRepo
) : ViewModel(),
    ContractViewModelListUserFragment {
    private var listUser: List<UserEntity> = mutableListOf()
    private val listUserViewModel = MutableLiveData<List<UserEntity>>()
    private var disposable:Disposable? = null

    init {
        listUser = userEntityRepo.readUsersList()
    }

    override fun loadData() {
        /**  прямой синхронный метод*/
        //       listUserViewModel.postValue(listUser)

        /** асинхронный метод через Rx */
         disposable = userEntityRepo.singleListUser
            .doOnSuccess { listUserViewModel.postValue(it) }
            .subscribe()
    }

    override fun getListUsersFromViewModel(): MutableLiveData<List<UserEntity>> {
        return listUserViewModel
    }

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }

}

