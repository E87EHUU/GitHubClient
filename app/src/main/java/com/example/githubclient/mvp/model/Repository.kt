package com.example.githubclient.mvp.model

import io.reactivex.rxjava3.core.Single
import com.example.githubclient.mvp.model.entity.GithubUser
import com.example.githubclient.mvp.model.entity.GithubUserRepos

interface Repository {
    fun getUsers() : Single<List<GithubUser>>

    fun getRepos(reposUrl: String) :
            Single<List<GithubUserRepos>>
}