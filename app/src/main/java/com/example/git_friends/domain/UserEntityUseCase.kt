package com.example.git_friends.domain

interface UserEntityUseCase {
    fun createUser(userEntity: UserEntity)
    fun readUser(userEntity: UserEntity)
    fun updateUser()
    fun deleteUser(userEntity: UserEntity)
    fun getListUsers(): List<UserEntity>

}