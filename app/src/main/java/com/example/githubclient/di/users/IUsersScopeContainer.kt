package com.example.githubclient.di.users

interface IUsersScopeContainer {

    fun initUserSubComponent(): UsersSubComponent

    fun releaseUserSubComponent()
}