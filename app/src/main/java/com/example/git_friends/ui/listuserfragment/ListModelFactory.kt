package com.example.git_friends.ui.listuserfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.git_friends.data.App
import com.example.git_friends.data.userentityrepo.UserEntityRepo
import com.example.git_friends.di.Di.userEntityRepo
import javax.inject.Inject

class ListModelFactory: ViewModelProvider.Factory {
    @Inject
    lateinit var userEntityRepo: UserEntityRepo

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        App.instance.appComponent.injectListFactory(this)

        return modelClass
            .getConstructor(UserEntityRepo::class.java)
            .newInstance(userEntityRepo)
    }

}