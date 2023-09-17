package com.example.githubclient.di.repos

interface IReposScopeContainer {

    fun initReposSubComponent(): ReposSubComponent?

    fun releaseReposSubComponent()

}