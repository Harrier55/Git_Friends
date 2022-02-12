package com.example.git_friends.data.userentityrepo

import com.example.git_friends.domain.UserEntity
import com.example.git_friends.domain.UserEntityUseCase
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.subjects.BehaviorSubject

class UserEntityRepo() : UserEntityUseCase {

    private val listUsers: MutableList<UserEntity> =
        mutableListOf<UserEntity>() as ArrayList<UserEntity>

 //   private  var  behaviorSubject : BehaviorSubject<List<UserEntity>>? = null

    // CRUD
    override fun createUser(userEntity: UserEntity) {
        listUsers.add(userEntity)
    }

    override fun readUser(): List<UserEntity> {
        return ArrayList<UserEntity>(listUsers)
    }

    override fun updateUser() {
        // todo
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
        get() = Observable.empty()


    override val singleListUser: Single<List<UserEntity>>
        get() {
           return Single.just(listUsers)
        }



}