package com.example.git_friends.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import com.example.git_friends.R
import com.example.git_friends.data.App
import com.example.git_friends.databinding.ActivityMainBinding
import com.example.git_friends.domain.UserEntity
import com.example.git_friends.ui.listuserfragment.ListUsersFragment
import com.example.git_friends.ui.listuserfragment.OnClickItemListUsersFragment
import com.example.git_friends.ui.userprofilefragment.UserProfileFragment

class MainActivity : AppCompatActivity(), OnClickItemListUsersFragment {

    private lateinit var binding: ActivityMainBinding
    private val listUsersFragment: ListUsersFragment = ListUsersFragment()
    private val userProfileFragment: UserProfileFragment = UserProfileFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        App.instance.generateTestListUser() // тестовый репозиторий

        initFragmentManager()

    }

     private fun initFragmentManager() {
        val fragmentManager: FragmentManager = supportFragmentManager
        fragmentManager.beginTransaction()
            .add(R.id.fragment_container,listUsersFragment)
            .addToBackStack("ListUserFragment")
            .commit()
    }

    override fun onClickItemUser(userEntity: UserEntity) {
        Toast.makeText(this, userEntity.login, Toast.LENGTH_SHORT).show()
    }

    companion object{
        fun foo(){

        }

    }


}