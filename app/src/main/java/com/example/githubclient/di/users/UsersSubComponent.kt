package com.example.githubclient.di.users

import com.example.githubclient.di.repos.ReposSubComponent
import com.example.githubclient.di.users.module.UsersModule
import com.example.githubclient.mvp.presenter.UsersPresenter
import com.example.githubclient.ui.adapter.UsersRVAdapter
import dagger.Subcomponent

@UsersScope
@Subcomponent(modules = [UsersModule::class])

interface UsersSubComponent {

    fun reposSubComponent(): ReposSubComponent

    fun inject(githubUsersPresenter: UsersPresenter)
    fun inject(usersRVAdapter: UsersRVAdapter)
}