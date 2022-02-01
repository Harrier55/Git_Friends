package com.example.git_friends.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.example.git_friends.R
import com.example.git_friends.data.App
import com.example.git_friends.databinding.ActivityMainBinding
import com.example.git_friends.ui.listuserfragment.ListUsersFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val listUsersFragment: ListUsersFragment = ListUsersFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        App.instance.generateTestListUser()

        initFragmentManager()


    }

    private fun initFragmentManager() {
        val fragmentManager: FragmentManager = supportFragmentManager
        fragmentManager.beginTransaction()
            .add(R.id.fragment_container,listUsersFragment)
            .addToBackStack("ListUserFragment")
            .commit()

    }


}