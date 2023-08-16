package com.example.githubclient.mvp.presenter

import com.example.githubclient.mvp.navigation.IScreens
import com.example.githubclient.mvp.view.MainView
import com.example.githubclient.mvp.navigation.Screens
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import javax.inject.Inject

class MainPresenter : MvpPresenter<MainView>() {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var screen: IScreens

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screen.users())
    }

    fun onBackPressed() {
        router.exit()
    }
}