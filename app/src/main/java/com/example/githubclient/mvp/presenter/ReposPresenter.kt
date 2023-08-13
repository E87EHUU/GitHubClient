package com.example.githubclient.mvp.presenter

import android.util.Log
import com.example.githubclient.mvp.model.entity.GithubUser
import com.example.githubclient.mvp.model.entity.GithubUserRepos
import com.example.githubclient.mvp.navigation.IScreens
import com.example.githubclient.mvp.presenter.list.IReposListPresenter
import com.example.githubclient.mvp.repository.RepositoryGithubUserReposImpl
import com.example.githubclient.mvp.view.ReposView
import com.example.githubclient.mvp.view.list.IReposItemView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter
import com.example.githubclient.utils.disposeBy

class ReposPresenter(
    private val user: GithubUser?,
    private val router: Router,
    private val repositoryGithubUserReposImpl: RepositoryGithubUserReposImpl,
    private val uiScheduler: Scheduler,
    private val screen: IScreens
) :
    MvpPresenter<ReposView>() {

    private var bag = CompositeDisposable()

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

        loadData()

        user?.let { viewState.init(it) }

        reposListPresenter.itemClickListener = {
            router.navigateTo(screen.forks(reposListPresenter.repos[it.pos]))
        }
    }

    private fun loadData() {

        user?.let { user ->
            repositoryGithubUserReposImpl.getRepos(user)
                .observeOn(uiScheduler)
                .subscribe({ repos ->
                    reposListPresenter.repos.addAll(repos)
                    viewState.updateList()
                }, {
                    Log.e("@@@", "Repo Something went wrong")
                }).disposeBy(bag)
        }
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