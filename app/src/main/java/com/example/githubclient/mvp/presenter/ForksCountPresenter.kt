package com.example.githubclient.mvp.presenter

import com.example.githubclient.mvp.model.entity.GithubUserRepos
import com.example.githubclient.mvp.view.ForksCountView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import javax.inject.Inject

class ForksCountPresenter : MvpPresenter<ForksCountView>() {

    @Inject
    lateinit var router: Router

    fun show(repos: GithubUserRepos) {
        viewState.showNumberOfForks(repos.forksCount.toString())
    }

    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }
}