package com.example.githubclient.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface UsersView : MvpView {

    fun setDigitOne(text: String)
    fun setDigitTwo(text: String)
    fun setDigitThree(text: String)
}