package com.example.githubclient

class MainPresenter(val view : MainView) {
    private val model = CountersModel()
    fun onFirstBtnClicked() {
        view.setDigitOne(model.next(0).toString())
    }
    fun onSecondBtnClicked() {
        view.setDigitTwo(model.next(1).toString())
    }
    fun onThirdBtnClicked() {
        view.setDigitThree(model.next(2).toString())
    }
}