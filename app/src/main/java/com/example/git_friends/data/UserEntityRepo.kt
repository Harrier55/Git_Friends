package com.example.git_friends.data

import com.example.git_friends.domain.UserEntity
import com.example.git_friends.domain.UserEntityUseCase

class UserEntityRepo : UserEntityUseCase {

    private val listUsers: ArrayList<UserEntity> = ArrayList()

    // CRUD
    override fun createUser(userEntity: UserEntity) {
        listUsers.add(userEntity)
    }

    override fun readUser(): List<UserEntity> {
        return ArrayList<UserEntity>(listUsers)
    }

    override fun updateUser() {

    }

    override fun deleteUser(userEntity: UserEntity): Boolean {
        for (user in listUsers) {
            if (user.id == userEntity.id) {
                listUsers.remove(user)
                return true
            }
        }
        return false
//        for (int i = 0; i < cache.size(); i++) {
//            if (cache.get(i).getId() == id) {
//                cache.remove(i);
//                return true;
//            }
//        }
//        return false;
    }


}