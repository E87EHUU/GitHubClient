package com.example.githubclient.mvp.repository

import com.example.githubclient.mvp.model.api.IDataSource
import com.example.githubclient.mvp.model.cache.IUserCache
import com.example.githubclient.mvp.model.entity.GithubUser
import com.example.githubclient.mvp.model.network.INetworkStatus
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RepositoryGithubUserImpl(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val cache: IUserCache
) : IRepositoryGithubUser {

    override fun getUsers(): Single<List<GithubUser>> =
        networkStatus.isOnlineSingle().flatMap { hasConnection ->
            if (hasConnection) {
                api.getAllUsers()
                    .flatMap { users ->
                        cache.insertUsersToDb(users).andThen(Single.just(users))
                    }
            } else {
                cache.getUsersFromDb()
            }
        }.subscribeOn(Schedulers.io())
}