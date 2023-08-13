package com.example.githubclient.mvp.repository

import com.example.githubclient.mvp.model.api.IDataSource
import com.example.githubclient.mvp.model.cache.IReposCache
import com.example.githubclient.mvp.model.entity.GithubUser
import com.example.githubclient.mvp.model.entity.GithubUserRepos
import com.example.githubclient.mvp.model.network.INetworkStatus
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RepositoryGithubUserReposImpl(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val cache: IReposCache
) : IRepositoryGithubUserRepos {

    override fun getRepos(user: GithubUser): Single<List<GithubUserRepos>> =
        networkStatus.isOnlineSingle().flatMap { hasConnection ->
            if (hasConnection) {
                user.reposUrl?.let { reposUrl ->
                    api.getRepos(reposUrl)
                        .flatMap { userRepos ->
                            cache.insertReposToDb(userRepos, user).andThen(Single.just(userRepos))
                        }
                } ?: Single.error(RuntimeException("User has no repos url"))
            } else {
                cache.getReposFromDb(user)
            }
        }.subscribeOn(Schedulers.io())
}