package com.example.git_friends.data.webconnection

import com.example.git_friends.domain.UserEntity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Part

/**   Внимание - UserEntity - это заглушка*/

interface RetrofitUserProfile {
    @GET("users/{user}/repos")
    fun loadUsers(@Part("user")user: String): Call<List<UserEntity>>
}

//public interface GitHubService {
//    @GET("users/{user}/repos")
//    Call<List<Repo>> listRepos(@Path("user") String user);
//}

class WebConnectionRetrofit {

    private val baseURl: String = "https://api.github.com/"

    fun getDataFromGit(){

        val retrofit = Retrofit.Builder()
            .baseUrl(baseURl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val retrofitService = retrofit.create(RetrofitUserProfile::class.java)
        val call = retrofitService.loadUsers("Harrier55")

        call.enqueue(object : Callback<List<UserEntity>>{
            override fun onResponse(
                call: Call<List<UserEntity>>,
                response: Response<List<UserEntity>>
            ) {
                // todo
            }

            override fun onFailure(call: Call<List<UserEntity>>, t: Throwable) {
                //todo
            }


        })


    }
}

// https://api.github.com/users/harrier55/repos