package com.example.git_friends.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.git_friends.R
import com.example.git_friends.data.App
import com.example.git_friends.databinding.ActivityMainBinding
import com.example.git_friends.domain.UserEntity
import com.example.git_friends.ui.listuserfragment.ListUsersFragment
import com.example.git_friends.ui.listuserfragment.OnClickItemListUsersFragment
import com.example.git_friends.ui.userprofilefragment.UserProfileFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val listUsersFragment by lazy { ListUsersFragment(manageFragment) }

    private val userProfileFragment: UserProfileFragment = UserProfileFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        App.instance.generateTestListUser() /** тестовый репозиторий*/

        initFragmentManager(listUsersFragment)
    }

     private fun initFragmentManager(fragment: Fragment) {
        val fragmentManager: FragmentManager = supportFragmentManager
//         val bundle = bundleOf(Pair("KEY",10000))
//         fragment.arguments = bundle

        fragmentManager.beginTransaction()
            .replace(R.id.fragment_container,fragment)
            .addToBackStack("ListUserFragment")
            .commit()
    }

    private var manageFragment = object : ManageFragment{
        override fun clickListUserFragment(userEntity: UserEntity) {

            Toast.makeText(this@MainActivity, userEntity.login, Toast.LENGTH_SHORT).show()
            val bundle = bundleOf(Pair("KEY",userEntity.login))
            userProfileFragment.arguments = bundle
            initFragmentManager(userProfileFragment)

        }

    }






}