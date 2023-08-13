package com.example.githubclient.mvp.navigation

import com.example.githubclient.mvp.model.entity.GithubUser
import com.example.githubclient.mvp.model.entity.GithubUserRepos
import com.example.githubclient.ui.fragment.ForksCountFragment
import com.example.githubclient.ui.fragment.ReposFragment
import com.example.githubclient.ui.fragment.UsersFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

class Screens:IScreens {

    override fun users() = FragmentScreen { UsersFragment.newInstance() }
    override fun details(user: GithubUser)=FragmentScreen {ReposFragment.newInstance(user)}
    override fun forks(forksCount:GithubUserRepos)=FragmentScreen { ForksCountFragment.newInstance(forksCount) }
}