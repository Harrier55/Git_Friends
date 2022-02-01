package com.example.git_friends.data

import android.app.Application
import com.example.git_friends.domain.UserEntity

class App: Application() {

    private val userEntityRepo by lazy { UserEntityRepo() }



    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: App
            private set
    }

    fun getInstanceUserEntityRepo(): UserEntityRepo{
        return userEntityRepo
    }

    fun generateTestListUser(){
        userEntityRepo.createUser(UserEntity(1,"test log 1"))
        userEntityRepo.createUser(UserEntity(2,"test log 1"))
        userEntityRepo.createUser(UserEntity(3,"test log 1"))
        userEntityRepo.createUser(UserEntity(4,"test log 1"))
        userEntityRepo.createUser(UserEntity(5,"test log 1"))
        userEntityRepo.createUser(UserEntity(6,"test log 1"))
        userEntityRepo.createUser(UserEntity(7,"test log 1"))
    }
}