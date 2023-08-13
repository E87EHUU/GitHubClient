package com.example.githubclient.mvp.model.cache

import com.example.githubclient.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IUserCache {
    fun insertUsersToDb(githubUser: List<GithubUser>) : Completable
    fun getUsersFromDb(): Single<List<GithubUser>>
}