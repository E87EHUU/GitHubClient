package com.example.githubclient.mvp.presenter

import android.util.Log
import com.example.githubclient.mvp.model.RepositoryImpl
import com.example.githubclient.mvp.model.entity.GithubUser
import com.example.githubclient.mvp.model.entity.GithubUserRepos
import com.example.githubclient.mvp.presenter.list.IReposListPresenter
import com.example.githubclient.mvp.view.ReposView
import com.example.githubclient.mvp.view.list.IReposItemView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter

class ReposPresenter(
    private val user: GithubUser?,
    private val router: Router,
    private val repositoryImpl: RepositoryImpl,
    private val uiScheduler: Scheduler
) :
    MvpPresenter<ReposView>() {

    private var disposable: Disposable? = null

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
    }

    private fun loadData() {

        disposable = user?.reposUrl?.let { reposUrl ->
            repositoryImpl.getRepos(reposUrl)
                .observeOn(uiScheduler)
                .subscribe({
                    reposListPresenter.repos.addAll(it)
                    viewState.updateList()
                }, {
                    Log.e("@@@", "Repo Something went wrong")
                })
        }
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