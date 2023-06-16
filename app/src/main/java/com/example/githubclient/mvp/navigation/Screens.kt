package com.example.githubclient.mvp.navigation

import com.example.githubclient.ui.UsersFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {

    fun users() = FragmentScreen { UsersFragment.newInstance() }
}