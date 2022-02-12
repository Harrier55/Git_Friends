package com.example.git_friends.data.webconnection

import UserReposGitHub
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitUserProfile {

    @GET("users/{user}/repos")
    fun loadUsers(@Path("user") user: String): io.reactivex.rxjava3.core.Observable<List<UserReposGitHub>>
}