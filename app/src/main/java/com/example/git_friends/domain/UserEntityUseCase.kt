package com.example.git_friends.domain

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface UserEntityUseCase {
    fun createUser(userEntity: UserEntity)
    fun readUser(): List<UserEntity>
    fun updateUser()
    fun deleteUser(userEntity: UserEntity): Boolean

    val observableListUserEntity:Observable<List<UserEntity>>

    val singleListUser:Single<List<UserEntity>>

}