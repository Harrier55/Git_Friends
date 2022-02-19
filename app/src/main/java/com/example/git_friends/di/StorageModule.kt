package com.example.git_friends.di

import com.example.git_friends.data.userentityrepo.UserEntityRepo

import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class StorageModule {

    /** получаем экземпляр репозитория*/
    @Singleton
    @Provides
     fun provideUserEntityRepo(): UserEntityRepo {
        return UserEntityRepo()
    }
}