package com.example.githubclient.mvp.presenter

import com.example.githubclient.mvp.view.UsersView

import com.example.githubclient.mvp.model.CountersModel
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class UsersPresenter(private val model: CountersModel, private val router: Router) : MvpPresenter<UsersView>() {

    fun onFirstBtnClicked() {
        viewState.setDigitOne(model.next(0).toString())
    }

    fun onSecondBtnClicked() {
        viewState.setDigitTwo(model.next(1).toString())
    }

    fun onThirdBtnClicked() {
        viewState.setDigitThree(model.next(2).toString())
    }

    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }
}