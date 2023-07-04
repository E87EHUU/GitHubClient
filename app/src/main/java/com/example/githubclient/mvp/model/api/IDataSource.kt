package com.example.githubclient.mvp.model.api

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import com.example.githubclient.mvp.model.entity.GithubUser
import com.example.githubclient.mvp.model.entity.GithubUserRepos
import retrofit2.http.Url


interface IDataSource {
    @GET("/users")
    fun getAllUsers(): Single<List<GithubUser>>

    @GET
    fun getRepos(@Url reposUrl: String): Single<List<GithubUserRepos>>
}