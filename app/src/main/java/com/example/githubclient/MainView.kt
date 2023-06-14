package com.example.githubclient

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface MainView : MvpView {
    fun setDigitOne(counter: String)
    fun setDigitTwo(counter: String)
    fun setDigitThree(counter: String)
}