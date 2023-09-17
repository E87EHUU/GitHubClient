package com.example.githubclient.di.repos.module

import com.example.githubclient.App
import com.example.githubclient.di.repos.IReposScopeContainer
import com.example.githubclient.di.repos.ReposScope
import com.example.githubclient.mvp.model.api.IDataSource
import com.example.githubclient.mvp.model.cache.IReposCache
import com.example.githubclient.mvp.model.cache.ReposCacheImpl
import com.example.githubclient.mvp.model.database.AppDatabase
import com.example.githubclient.mvp.model.network.INetworkStatus
import com.example.githubclient.mvp.repository.IRepositoryGithubUserRepos
import com.example.githubclient.mvp.repository.RepositoryGithubUserReposImpl
import dagger.Module
import dagger.Provides

@Module
open class ReposModule {

    @ReposScope
    @Provides
    fun reposCache(db: AppDatabase): IReposCache = ReposCacheImpl(db)

    @ReposScope
    @Provides
    fun userDetailsRepository(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: IReposCache,
    ): IRepositoryGithubUserRepos = RepositoryGithubUserReposImpl(api,networkStatus, cache)

    @ReposScope
    @Provides
    open fun scopeContainer(app: App): IReposScopeContainer = app
}