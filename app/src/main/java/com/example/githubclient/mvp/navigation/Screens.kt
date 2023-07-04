package com.example.githubclient.mvp.navigation

import com.example.githubclient.mvp.model.entity.GithubUser
import com.example.githubclient.ui.fragment.DetailsFragment
import com.example.githubclient.ui.fragment.UsersFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {

    fun users() = FragmentScreen { UsersFragment.newInstance() }
    fun details(user: GithubUser)=FragmentScreen {DetailsFragment.newInstance(user)}
}