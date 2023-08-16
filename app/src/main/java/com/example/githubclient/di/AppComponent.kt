package com.example.githubclient.di

import com.example.githubclient.di.modules.ApiModule
import com.example.githubclient.di.modules.AppModule
import com.example.githubclient.di.modules.CacheModule
import com.example.githubclient.di.modules.CiceroneModule
import com.example.githubclient.di.modules.ImageLoaderModule
import com.example.githubclient.di.modules.RepositoriesModule
import com.example.githubclient.mvp.presenter.ForksCountPresenter
import com.example.githubclient.mvp.presenter.MainPresenter
import com.example.githubclient.mvp.presenter.ReposPresenter
import com.example.githubclient.mvp.presenter.UsersPresenter
import com.example.githubclient.ui.activity.MainActivity
import com.example.githubclient.ui.adapter.UsersRVAdapter
import com.example.githubclient.ui.fragment.ReposFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class,
        AppModule::class,
        CacheModule::class,
        CiceroneModule::class,
        ImageLoaderModule::class,
        RepositoriesModule::class
    ]
)

interface AppComponent{

    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)

    fun inject(githubUsersPresenter: UsersPresenter)
    fun inject(usersRVAdapter: UsersRVAdapter)

    fun inject(githubUserReposPresenter: ReposPresenter)
    fun inject(fragment: ReposFragment)

    fun inject(forksCountPresenter: ForksCountPresenter)
}