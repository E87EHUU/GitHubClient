package com.example.githubclient.mvp.presenter

import com.example.githubclient.mvp.view.UsersView

import com.example.githubclient.mvp.model.GithubUser
import com.example.githubclient.mvp.model.RepositoryImpl
import com.example.githubclient.mvp.navigation.Screens
import com.example.githubclient.mvp.presenter.list.IUserListPresenter
import com.example.githubclient.mvp.view.list.IUserItemView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class UsersPresenter(private val repositoryImpl: RepositoryImpl, private val router: Router) :
    MvpPresenter<UsersView>() {

    class UserListPresenter : IUserListPresenter {

        val users = mutableListOf<GithubUser>()

        override var itemClickListener: ((IUserItemView) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: IUserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
        }
    }

    val usersListPresenter = UserListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        usersListPresenter.itemClickListener = {
            router.navigateTo(Screens.details(usersListPresenter.users[it.pos]))
        }
    }

    private fun loadData() {
        val users = repositoryImpl.getUsers()

        usersListPresenter.users.addAll(users)

        viewState.updateList()
    }

    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }
}