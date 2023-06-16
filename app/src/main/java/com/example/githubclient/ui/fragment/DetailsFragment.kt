package com.example.githubclient.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.githubclient.App
import com.example.githubclient.databinding.FragmentDetailsBinding
import com.example.githubclient.mvp.model.GithubUser
import com.example.githubclient.mvp.navigation.BackPressedListener
import com.example.githubclient.mvp.presenter.DetailsPresenter
import com.example.githubclient.mvp.view.DetailsView
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

@Suppress("DEPRECATION")
class DetailsFragment : MvpAppCompatFragment(), DetailsView, BackPressedListener {

    private var _viewBinding: FragmentDetailsBinding? = null
    private val viewBinding get() = _viewBinding!!

    private val presenter by moxyPresenter {
        val user = arguments?.getParcelable(BUNDLE_GITHUB_USER) as GithubUser?
        DetailsPresenter(user, App.instance.router)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        super.onCreate(savedInstanceState)

        _viewBinding = FragmentDetailsBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun init(login: String) {
        viewBinding.userLogin.text = login
    }

    override fun onBackPressed() = presenter.onBackPressed()

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }

    companion object {
        const val BUNDLE_GITHUB_USER = "BUNDLE_GITHUB_USER"
        fun newInstance(user: GithubUser) = DetailsFragment().apply {
            arguments = Bundle().apply {
                putParcelable(BUNDLE_GITHUB_USER, user)
            }
        }
    }
}