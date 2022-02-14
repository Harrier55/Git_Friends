package com.example.git_friends.data

import android.app.Application
import com.example.git_friends.data.userentityrepo.UserEntityRepo
import com.example.git_friends.domain.UserEntity
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class App: Application() {

    private val userEntityRepo by lazy { UserEntityRepo() }



    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: App
            private set
    }

    fun getInstanceUserEntityRepo(): UserEntityRepo {
        return userEntityRepo
    }

    fun generateTestListUser(){
        userEntityRepo.createUser(UserEntity(1,"kshalnov"))
        userEntityRepo.createUser(UserEntity(2,"Harrier55"))
        userEntityRepo.createUser(UserEntity(3,"kshalnov"))
        userEntityRepo.createUser(UserEntity(4,"Rogoz208"))
        userEntityRepo.createUser(UserEntity(5,"niqmarin"))
        userEntityRepo.createUser(UserEntity(6,"niqmarin"))
        userEntityRepo.createUser(UserEntity(7,"test log 7"))
    }
    /** Используем с обычным вызовом Call  */
    fun retrofitInstance(baseURl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseURl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    /**  Используем с вызовом Rx  */
    fun retrofitInstanceRx(baseURl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseURl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }


}