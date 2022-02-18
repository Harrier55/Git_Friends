package com.example.git_friends.di

import android.annotation.SuppressLint
import android.content.Context
import com.example.git_friends.data.userentityrepo.UserEntityRepo
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory



@SuppressLint("StaticFieldLeak")
object Di {
    const val BASEURL: String = "https://api.github.com/"

    private lateinit var context: Context

     fun init(context: Context){
        this.context = context.applicationContext
    }


    /** получаем экземпляр репозитория*/
    val userEntityRepo by lazy { UserEntityRepo() } // теперь экземпляр репозитория мы можем получить так


    /** было так - получаем экземпляр репозитория*/
//    fun getInstanceUserEntityRepo(): UserEntityRepo {
//        return userEntityRepo
//    }


    /**  Используем Retrofit с вызовом Rx  и вызовем его как метод, для разнообразия*/
    fun retrofitInstanceRx(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }
}

inline fun <reified T> inject(): T {
    return when(T::class.java){
        UserEntityRepo::class.java -> Di.userEntityRepo as T
        Retrofit::class.java -> Di.retrofitInstanceRx(Di.BASEURL) as T

        else -> throw IllegalArgumentException("Not class")
    }
}