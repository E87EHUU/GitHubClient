package com.example.githubclient.mvp.presenter

import android.util.Log
import com.example.githubclient.mvp.view.UsersView
import io.reactivex.rxjava3.core.Scheduler

import com.example.githubclient.mvp.model.entity.GithubUser
import com.example.githubclient.mvp.model.RepositoryImpl
import com.example.githubclient.mvp.navigation.Screens
import com.example.githubclient.mvp.presenter.list.IUserListPresenter
import com.example.githubclient.mvp.view.list.IUserItemView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import io.reactivex.rxjava3.disposables.Disposable

class UsersPresenter(
    private val repositoryImpl: RepositoryImpl,
    private val router: Router,
    private val uiScheduler: Scheduler
) :
    MvpPresenter<UsersView>() {

    private var disposable: Disposable? = null

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
        disposable = repositoryImpl.getUsers().observeOn(uiScheduler)
            .subscribe({
                usersListPresenter.users.addAll(it)
                viewState.updateList()
            }, {
                Log.e("@@@", "Something went wrong")
            })
    }

    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }
}