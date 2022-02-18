package com.example.git_friends.data

import android.app.Application
import com.example.git_friends.data.userentityrepo.UserEntityRepo
import com.example.git_friends.di.Di
import com.example.git_friends.domain.UserEntity
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory



class App: Application() {

//    val di: Di by lazy { Di() }

    override fun onCreate() {
        super.onCreate()
        Di.init(this)
        instance = this
    }

    companion object {
        lateinit var instance: App
            private set
    }

//    private val userEntityRepo by lazy { UserEntityRepo() }
//
//    /** получаем экземпляр репозитория*/
//    fun getInstanceUserEntityRepo(): UserEntityRepo {
//        return userEntityRepo
//    }
//
//
//    /** Используем Retrofit с обычным вызовом Call  */
//    fun retrofitInstance(baseURl: String): Retrofit {
//        return Retrofit.Builder()
//            .baseUrl(baseURl)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    }
//    /**  Используем Retrofit с вызовом Rx  */
//    fun retrofitInstanceRx(baseURl: String): Retrofit {
//        return Retrofit.Builder()
//            .baseUrl(baseURl)
//            .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
//            .build()
//    }



//    fun generateTestListUser(){
//        userEntityRepo.createUser(UserEntity(1,"kshalnov"))
//        userEntityRepo.createUser(UserEntity(2,"Harrier55"))
//        userEntityRepo.createUser(UserEntity(3,"kshalnov"))
//        userEntityRepo.createUser(UserEntity(4,"Rogoz208"))
//        userEntityRepo.createUser(UserEntity(5,"niqmarin"))
//        userEntityRepo.createUser(UserEntity(6,"niqmarin"))
//        userEntityRepo.createUser(UserEntity(7,"test log 7"))
//    }


}