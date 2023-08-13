package com.example.githubclient.mvp.presenter

import com.example.githubclient.mvp.navigation.IScreens
import com.example.githubclient.mvp.view.MainView
import com.example.githubclient.mvp.navigation.Screens
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class MainPresenter(
    private val router: Router,
    private val screen: IScreens
) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screen.users())
    }

    fun onBackPressed() {
        router.exit()
    }
}