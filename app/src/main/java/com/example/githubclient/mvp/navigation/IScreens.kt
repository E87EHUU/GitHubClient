package com.example.githubclient.mvp.navigation

import com.example.githubclient.mvp.model.entity.GithubUser
import com.example.githubclient.mvp.model.entity.GithubUserRepos
import com.github.terrakok.cicerone.androidx.FragmentScreen

interface IScreens {

    fun users(): FragmentScreen

    fun details(user: GithubUser): FragmentScreen

    fun forks(forksCount: GithubUserRepos):
            FragmentScreen
}
