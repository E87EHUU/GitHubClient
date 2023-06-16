package com.example.githubclient.mvp.model

class RepositoryImpl : Repository {

    private val users = listOf(
        GithubUser("User profile 1"),
        GithubUser("User profile 2"),
        GithubUser("User profile 3"),
        GithubUser("User profile 4"),
        GithubUser("User profile 5")
    )

    override fun getUsers() = users
}