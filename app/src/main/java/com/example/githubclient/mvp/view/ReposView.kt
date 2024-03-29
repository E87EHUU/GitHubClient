package com.example.githubclient.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import com.example.githubclient.mvp.model.entity.GithubUser

@StateStrategyType(AddToEndSingleStrategy::class)
interface ReposView : MvpView {

    fun init(user: GithubUser)
    fun updateList()
    fun loadAvatarAndLogin(user: GithubUser)
}