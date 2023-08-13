package com.example.githubclient.mvp.presenter

import com.example.githubclient.mvp.model.entity.GithubUserRepos
import com.example.githubclient.mvp.view.ForksCountView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class ForksCountPresenter (
    private val router: Router
) : MvpPresenter<ForksCountView>() {

    fun show(repos: GithubUserRepos) {
        viewState.showNumberOfForks(repos.forksCount.toString())
    }

    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }
}