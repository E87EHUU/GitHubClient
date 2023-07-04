package com.example.githubclient.mvp.model

import io.reactivex.rxjava3.core.Single
import com.example.githubclient.mvp.model.entity.GithubUser

interface Repository {
    fun getUsers() : Single<List<GithubUser>>
}