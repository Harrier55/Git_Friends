package com.example.git_friends.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.git_friends.R
import com.example.git_friends.data.App
import com.example.git_friends.data.userentityrepo.UserEntityRepo
import com.example.git_friends.databinding.ActivityMainBinding

import com.example.git_friends.ui.listuserfragment.ListUsersFragment

import com.example.git_friends.ui.userprofilefragment.UserProfileFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val listUsersFragment by lazy { ListUsersFragment(manageFragment) }
    private val userProfileFragment by lazy { UserProfileFragment() }
    @Inject
    lateinit var userEntityRepo: UserEntityRepo


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initDependenciesDagger()
        initFragmentManager(listUsersFragment)
        userEntityRepo.generateTestListUser() /** тестовый репозиторий*/
    }
    private fun initDependenciesDagger(){
        App.instance.appComponent.injectMain(this)
    }

     private fun initFragmentManager(fragment: Fragment) {
        val fragmentManager: FragmentManager = supportFragmentManager
        fragmentManager.beginTransaction()
            .replace(R.id.fragment_container,fragment)
            .addToBackStack("ListUserFragment")
            .commit()
    }

    private var manageFragment = object : ManageFragment{
        override fun sendUserProfileFragment(login: String?) {
            val bundle = bundleOf(Pair("KEY",login))
            userProfileFragment.arguments = bundle
            initFragmentManager(userProfileFragment)

        }

    }

}