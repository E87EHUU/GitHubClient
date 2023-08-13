package com.example.githubclient.mvp.presenter

import android.util.Log
import com.example.githubclient.mvp.view.UsersView
import io.reactivex.rxjava3.core.Scheduler

import com.example.githubclient.mvp.model.entity.GithubUser
import com.example.githubclient.mvp.navigation.Screens
import com.example.githubclient.mvp.presenter.list.IUserListPresenter
import com.example.githubclient.mvp.repository.RepositoryGithubUserImpl
import com.example.githubclient.mvp.view.list.IUserItemView
import com.example.githubclient.utils.disposeBy
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter

class UsersPresenter(
    private val repositoryGithubUserReposImpl: RepositoryGithubUserImpl,
    private val router: Router,
    private val uiScheduler: Scheduler
) :
    MvpPresenter<UsersView>() {

    private var bag = CompositeDisposable()

    class UserListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()

        override var itemClickListener: ((IUserItemView) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: IUserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
            user.avatarUrl?.let { view.loadAvatar(it) }
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
        repositoryGithubUserReposImpl.getUsers().observeOn(uiScheduler)
            .subscribe({
                usersListPresenter.users.addAll(it)
                viewState.updateList()
            }, {
                Log.e("@@@", "Something went wrong")
            }).disposeBy(bag)
    }

    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        bag.dispose()
    }
}