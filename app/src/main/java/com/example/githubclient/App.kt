package com.example.githubclient

import android.app.Application
import com.example.githubclient.di.AppComponent
import com.example.githubclient.di.DaggerAppComponent
import com.example.githubclient.di.modules.AppModule
import com.example.githubclient.di.repos.IReposScopeContainer
import com.example.githubclient.di.repos.ReposSubComponent
import com.example.githubclient.di.users.IUsersScopeContainer
import com.example.githubclient.di.users.UsersSubComponent

class App : Application(), IUsersScopeContainer, IReposScopeContainer {

    lateinit var appComponent: AppComponent

    private var usersSubComponent: UsersSubComponent? = null

    private var reposSubComponent: ReposSubComponent? = null

    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()

        instance = this

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    override fun initUserSubComponent() = appComponent.usersSubComponent().also {
        usersSubComponent = it
    }


    override fun releaseUserSubComponent() {
        usersSubComponent = null
    }

    override fun initReposSubComponent() = usersSubComponent?.reposSubComponent().also {
        reposSubComponent = it
    }

    override fun releaseReposSubComponent() {
        reposSubComponent = null
    }
}