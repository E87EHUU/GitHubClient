package com.example.githubclient

import moxy.MvpPresenter

class MainPresenter(private val model: CountersModel) : MvpPresenter<MainView>() {

    fun onFirstBtnClicked() {
        viewState.setDigitOne(model.next(0).toString())
    }

    fun onSecondBtnClicked() {
        viewState.setDigitTwo(model.next(1).toString())
    }

    fun onThirdBtnClicked() {
        viewState.setDigitThree(model.next(2).toString())
    }
}