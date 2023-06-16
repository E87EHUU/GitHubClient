package com.example.githubclient.mvp.model

interface Repository {
    fun getUsers() : List<GithubUser>
}