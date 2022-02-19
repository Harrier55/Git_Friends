package com.example.git_friends.ui.userprofilefragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.git_friends.data.App
import com.example.git_friends.data.userentityrepo.UserEntityRepo
import retrofit2.Retrofit
import javax.inject.Inject

class ProfileViewModelFactory: ViewModelProvider.Factory {
    @Inject
    lateinit var userEntityRepo: UserEntityRepo
    @Inject
    lateinit var retrofit: Retrofit

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        App.instance.appComponent.injectUserProfileFactory(this)

        return modelClass
            .getConstructor(UserEntityRepo::class.java, Retrofit::class.java)
            .newInstance(userEntityRepo, retrofit)
    }
}