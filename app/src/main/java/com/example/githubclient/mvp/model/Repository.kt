package com.example.githubclient.mvp.model

import io.reactivex.rxjava3.core.Observable

interface Repository {
    fun getUsers() : Observable<GithubUser>
}