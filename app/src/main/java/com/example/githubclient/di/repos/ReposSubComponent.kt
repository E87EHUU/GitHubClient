package com.example.githubclient.di.repos

import com.example.githubclient.di.repos.module.ReposModule
import com.example.githubclient.mvp.presenter.ReposPresenter
import com.example.githubclient.ui.fragment.ReposFragment
import dagger.Subcomponent


@ReposScope
@Subcomponent(modules = [ReposModule::class])
interface ReposSubComponent {
    fun inject(githubUserReposPresenter: ReposPresenter)
    fun inject(fragment: ReposFragment)
}