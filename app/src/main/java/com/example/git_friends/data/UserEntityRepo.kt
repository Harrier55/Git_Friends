package com.example.git_friends.data

import com.example.git_friends.domain.UserEntity
import com.example.git_friends.domain.UserEntityUseCase
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

class UserEntityRepo() : UserEntityUseCase {

    private val listUsers: MutableList<UserEntity> = mutableListOf<UserEntity>() as ArrayList<UserEntity>

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
    }

    override val observableListUserEntity: Observable<List<UserEntity>>
        get() {
            return observableListUserEntity
        }


    override val singleListUser: Single<List<UserEntity>>
        get() {
            return singleListUser
        }


}