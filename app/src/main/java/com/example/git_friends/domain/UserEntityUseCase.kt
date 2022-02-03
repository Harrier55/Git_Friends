package com.example.git_friends.domain

interface UserEntityUseCase {
    fun createUser(userEntity: UserEntity)
    fun readUser(): List<UserEntity>
    fun updateUser()
    fun deleteUser(userEntity: UserEntity): Boolean

}