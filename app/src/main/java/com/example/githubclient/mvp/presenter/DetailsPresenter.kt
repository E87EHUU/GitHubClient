package com.example.githubclient.mvp.presenter

import com.example.githubclient.mvp.model.entity.GithubUser
import com.example.githubclient.mvp.view.DetailsView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class DetailsPresenter(private val user: GithubUser?, private val router: Router) :
    MvpPresenter<DetailsView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        user?.let { viewState.init(it.login) }
    }

    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }
}