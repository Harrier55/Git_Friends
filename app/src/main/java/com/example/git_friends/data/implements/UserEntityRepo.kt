package com.example.git_friends.data.implements

import com.example.git_friends.domain.UserEntity
import com.example.git_friends.domain.UserEntityUseCase

class UserEntityRepo: UserEntityUseCase {

    private val listUsers:ArrayList<UserEntity> = ArrayList()

    override fun getListUsers(): List<UserEntity> {
        return ArrayList<UserEntity>(listUsers)
    }

    // CRUD
    override fun createUser(userEntity: UserEntity) {
        listUsers.add(userEntity)
    }

    override fun readUser(userEntity: UserEntity) {
       //todo
    }

    override fun updateUser() {
       //todo
    }

    override fun deleteUser(userEntity: UserEntity) {
        // todo
    }


}