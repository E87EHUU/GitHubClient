package com.example.githubclient.mvp.repository

import com.example.githubclient.mvp.model.entity.GithubUser
import com.example.githubclient.mvp.model.entity.GithubUserRepos
import io.reactivex.rxjava3.core.Single

interface IRepositoryGithubUserRepos {
    fun getRepos(user: GithubUser): Single<List<GithubUserRepos>>
}