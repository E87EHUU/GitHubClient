package com.example.githubclient.mvp.repository

import com.example.githubclient.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Single

interface IRepositoryGithubUser {
    fun getUsers(): Single<List<GithubUser>>
}