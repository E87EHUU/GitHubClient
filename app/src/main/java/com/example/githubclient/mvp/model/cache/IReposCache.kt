package com.example.githubclient.mvp.model.cache

import com.example.githubclient.mvp.model.entity.GithubUser
import com.example.githubclient.mvp.model.entity.GithubUserRepos
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IReposCache {

    fun insertReposToDb(githubUserRepos: List<GithubUserRepos>, githubUser: GithubUser):Completable

    fun getReposFromDb(user:GithubUser): Single<List<GithubUserRepos>>
}