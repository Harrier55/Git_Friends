package com.example.git_friends.di

import com.example.git_friends.ui.MainActivity
import com.example.git_friends.ui.listuserfragment.ListModelFactory
import com.example.git_friends.ui.userprofilefragment.ProfileViewModelFactory


import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [StorageModule::class, NetworkModule::class])
interface AppComponent {
    fun injectMain(mainActivity: MainActivity)
    fun injectListFactory(listModelFactory: ListModelFactory)
    fun injectUserProfileFactory(profileViewModelFactory: ProfileViewModelFactory)

}