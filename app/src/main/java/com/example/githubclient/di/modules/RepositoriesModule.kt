package com.example.githubclient.di.modules

import com.example.githubclient.mvp.model.api.IDataSource
import com.example.githubclient.mvp.model.cache.IReposCache
import com.example.githubclient.mvp.model.cache.IUserCache
import com.example.githubclient.mvp.model.network.INetworkStatus
import com.example.githubclient.mvp.repository.IRepositoryGithubUser
import com.example.githubclient.mvp.repository.IRepositoryGithubUserRepos
import com.example.githubclient.mvp.repository.RepositoryGithubUserImpl
import com.example.githubclient.mvp.repository.RepositoryGithubUserReposImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoriesModule {

    @Singleton
    @Provides
    fun userListRepository(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: IUserCache,
    ): IRepositoryGithubUser = RepositoryGithubUserImpl(api, networkStatus, cache)

    @Singleton
    @Provides
    fun userDetailsRepository(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: IReposCache,
    ): IRepositoryGithubUserRepos = RepositoryGithubUserReposImpl(api, networkStatus, cache)
}