package com.example.githubclient.mvp.presenter

import com.example.githubclient.mvp.view.MainView
import com.example.githubclient.mvp.navigation.Screens
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class MainPresenter(private val router: Router) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(Screens.users())
    }

    fun onBackPressed() {
        router.exit()
    }
}