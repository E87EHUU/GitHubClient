package com.example.githubclient.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.githubclient.App
import com.example.githubclient.databinding.FragmentForksCountBinding
import com.example.githubclient.mvp.model.entity.GithubUserRepos
import com.example.githubclient.mvp.navigation.BackPressedListener
import com.example.githubclient.mvp.presenter.ForksCountPresenter
import com.example.githubclient.mvp.view.ForksCountView
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class ForksCountFragment : MvpAppCompatFragment(), ForksCountView, BackPressedListener {

    companion object {
        const val BUNDLE_FORKS_COUNT = "BUNDLE_GITHUB_USER"
        fun newInstance(repos: GithubUserRepos) = ForksCountFragment().apply {
            arguments = Bundle().apply {
                putParcelable(BUNDLE_FORKS_COUNT, repos)
            }
        }
    }

    private var _viewBinding: FragmentForksCountBinding? = null
    private val viewBinding get() = _viewBinding!!

    private val presenter: ForksCountPresenter by moxyPresenter {

        ForksCountPresenter().apply {
            App.instance.appComponent.inject(
                this
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = FragmentForksCountBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getParcelable<GithubUserRepos>(BUNDLE_FORKS_COUNT)
            ?.let { presenter.show(it) }
    }

    override fun onBackPressed(): Boolean {
        presenter.onBackPressed()
        return true
    }

    override fun showNumberOfForks(forks: String) {
        viewBinding.forksCount.text = forks
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }
}