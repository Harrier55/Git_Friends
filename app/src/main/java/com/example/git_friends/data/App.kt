package com.example.git_friends.data

import android.app.Application

import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import com.example.git_friends.di.DaggerAppComponent
import com.example.git_friends.di.Di
import com.example.git_friends.di.NetworkModule
import com.example.git_friends.di.StorageModule


class App : Application() {

    val appComponent = DaggerAppComponent.builder()
        .storageModule(StorageModule())
        .networkModule(NetworkModule())
        .build()

    override fun onCreate() {
        super.onCreate()
        Di.init(this)
        instance = this
        /**  Переключились на темную тему */
//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
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
}