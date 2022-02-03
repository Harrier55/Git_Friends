package com.example.git_friends.domain

data class UserEntity(
    val id:     Long?    = null,
    var login:  String?  = null,
    val avatar: String?  = null
)
