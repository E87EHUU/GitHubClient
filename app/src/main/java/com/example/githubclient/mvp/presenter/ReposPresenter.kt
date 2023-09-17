package com.example.githubclient.mvp.presenter

import android.util.Log
import com.example.githubclient.di.repos.IReposScopeContainer
import com.example.githubclient.mvp.model.entity.GithubUser
import com.example.githubclient.mvp.model.entity.GithubUserRepos
import com.example.githubclient.mvp.navigation.IScreens
import com.example.githubclient.mvp.presenter.list.IReposListPresenter
import com.example.githubclient.mvp.repository.IRepositoryGithubUserRepos
import com.example.githubclient.mvp.view.ReposView
import com.example.githubclient.mvp.view.list.IReposItemView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter
import com.example.githubclient.utils.disposeBy
import javax.inject.Inject

class ReposPresenter(private val user: GithubUser?) : MvpPresenter<ReposView>() {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var repositoryGithubUserReposImpl: IRepositoryGithubUserRepos

    @Inject
    lateinit var uiScheduler: Scheduler

    @Inject
    lateinit var screen: IScreens

    @Inject
    lateinit var reposScopeModule: IReposScopeContainer

    private var disposable = CompositeDisposable()

    class ReposListPresenter : IReposListPresenter {

        val repos = mutableListOf<GithubUserRepos>()

        override var itemClickListener: ((IReposItemView) -> Unit)? = null

        override fun getCount() = repos.size

        override fun bindView(view: IReposItemView) {
            val repo = repos[view.pos]
            view.setName(repo.name)
        }
    }

    val reposListPresenter = ReposListPresenter()

    override fun onFirstViewAttach() {

        super.onFirstViewAttach()

        user?.let {viewState.loadAvatarAndLogin(it) }

        loadData()

        user?.let { viewState.init(it) }

        reposListPresenter.itemClickListener = {
            router.navigateTo(screen.forks(reposListPresenter.repos[it.pos]))
        }
    }

    private fun loadData() {

        user?.let {
            repositoryGithubUserReposImpl.getRepos(it)
                .observeOn(uiScheduler)
                .subscribe({ repos ->
                    reposListPresenter.repos.addAll(repos)
                    viewState.updateList()
                }, {
                    Log.e("@@@", "Repo Something went wrong")
                }).disposeBy(disposable)
        }
    }

    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}