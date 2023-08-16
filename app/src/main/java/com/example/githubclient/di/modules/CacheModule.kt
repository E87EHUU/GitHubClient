package com.example.githubclient.di.modules

import androidx.room.Room
import com.example.githubclient.App
import com.example.githubclient.mvp.model.cache.IReposCache
import com.example.githubclient.mvp.model.cache.IUserCache
import com.example.githubclient.mvp.model.cache.ReposCacheImpl
import com.example.githubclient.mvp.model.cache.UserCacheImpl
import com.example.githubclient.mvp.model.database.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheModule {

    @Singleton
    @Provides
    fun database(app: App) = Room.databaseBuilder(
        app, AppDatabase::class.java,
        AppDatabase.DB_NAME
    ).build()

    @Singleton
    @Provides
    fun usersCache(db: AppDatabase): IUserCache = UserCacheImpl(db)

    @Singleton
    @Provides
    fun reposCache(db: AppDatabase): IReposCache = ReposCacheImpl(db)
}