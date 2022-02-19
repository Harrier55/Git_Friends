package com.example.git_friends.di

import com.example.git_friends.ui.MainActivity
import com.example.git_friends.ui.listuserfragment.ListModelFactory
import com.example.git_friends.ui.listuserfragment.ListUserFragmentViewModel
import com.example.git_friends.ui.listuserfragment.ListUsersFragment

import com.example.git_friends.ui.userprofilefragment.UserProfileFragment
import dagger.Component
import javax.inject.Singleton
@Singleton
@Component(modules = [StorageModule::class, NetworkModule::class])
interface AppComponent {

    fun injectMain(mainActivity: MainActivity)
    fun injectUserProfile(userProfileFragment: UserProfileFragment)

    fun injectUserList(listUserProfileFragment: ListUsersFragment)
    fun injectModel(listUserFragmentViewModel: ListUserFragmentViewModel)
    fun injectListFactory(listModelFactory: ListModelFactory)


}