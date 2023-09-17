package com.example.githubclient.di.users.module

import com.example.githubclient.App
import com.example.githubclient.di.users.IUsersScopeContainer
import com.example.githubclient.di.users.UsersScope
import com.example.githubclient.mvp.model.api.IDataSource
import com.example.githubclient.mvp.model.cache.IUserCache
import com.example.githubclient.mvp.model.cache.UserCacheImpl
import com.example.githubclient.mvp.model.database.AppDatabase
import com.example.githubclient.mvp.model.network.INetworkStatus
import com.example.githubclient.mvp.repository.IRepositoryGithubUser
import com.example.githubclient.mvp.repository.RepositoryGithubUserImpl
import dagger.Module
import dagger.Provides

@Module
open class UsersModule {

    @UsersScope
    @Provides
    fun usersCache(db: AppDatabase): IUserCache = UserCacheImpl(db)

    @UsersScope
    @Provides
    fun userListRepository(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: IUserCache,
    ): IRepositoryGithubUser = RepositoryGithubUserImpl(api, networkStatus, cache)

    @UsersScope
    @Provides
    open fun scopeContainer (app:App): IUsersScopeContainer = app
}