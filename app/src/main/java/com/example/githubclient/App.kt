package com.example.githubclient

import android.app.Application
import com.example.githubclient.ui.network.ConnectivityListener
import com.example.githubclient.mvp.model.database.AppDatabase
import com.example.githubclient.mvp.model.network.INetworkStatus
import com.example.githubclient.mvp.navigation.IScreens
import com.example.githubclient.mvp.navigation.Screens
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router

class App : Application() {

    companion object {
        lateinit var instance: App
        lateinit var networkStatus: INetworkStatus
    }

    private val cicerone: Cicerone<Router> by lazy {
        Cicerone.create()
    }
    val router get() = cicerone.router
    val navigatorHolder get() = cicerone.getNavigatorHolder()

    val screens: IScreens = Screens()

    override fun onCreate() {
        super.onCreate()

        instance = this

        networkStatus = ConnectivityListener(instance)

        AppDatabase.create(this)
    }
}