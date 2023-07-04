package com.example.githubclient.mvp.model.api

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import com.example.githubclient.mvp.model.entity.GithubUser


interface IDataSource {

    @GET("/users")
    fun getAllUsers(): Single<List<GithubUser>>
}