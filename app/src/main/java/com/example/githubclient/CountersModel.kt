package com.example.githubclient

class CountersModel {

    private val firstCount = 0
    private val secondCount = 0
    private val thirdCount = 0

    private val counters = mutableListOf(firstCount, secondCount, thirdCount)

    fun getCurrent(index: Int) : Int {
        return counters[index]
    }

    fun next(index: Int): Int {
        counters[index]++
        return getCurrent(index)
    }
}