package com.example.githubclient.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubclient.App
import com.example.githubclient.databinding.FragmentReposBinding
import com.example.githubclient.mvp.model.entity.GithubUser
import com.example.githubclient.mvp.navigation.BackPressedListener
import com.example.githubclient.mvp.presenter.ReposPresenter
import com.example.githubclient.mvp.view.IImageLoader
import com.example.githubclient.mvp.view.ReposView
import com.example.githubclient.ui.adapter.ReposRVAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class ReposFragment : MvpAppCompatFragment(), ReposView, BackPressedListener {

    private var _viewBinding: FragmentReposBinding? = null
    private val viewBinding get() = _viewBinding!!

    @Inject
    lateinit var imageLoader: IImageLoader<ImageView>

    private val presenter by moxyPresenter {
        val user = arguments?.getParcelable(BUNDLE_GITHUB_USER) as GithubUser?
        ReposPresenter(user).apply {App.instance.initReposSubComponent()?.inject(this)}
    }

    private var adapter: ReposRVAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        super.onCreate(savedInstanceState)

        App.instance.initReposSubComponent()?.inject(this)

        _viewBinding = FragmentReposBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun init(user: GithubUser) {
        viewBinding.rvRepos.layoutManager = LinearLayoutManager(context)
        adapter = ReposRVAdapter(presenter.reposListPresenter)
        viewBinding.rvRepos.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun loadAvatarAndLogin(user: GithubUser) {
        user.avatarUrl?.let { imageLoader.loadInto(it, viewBinding.userAvatar) }
        viewBinding.userLogin.text = user.login
    }

    override fun onBackPressed() = presenter.onBackPressed()

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }

    companion object {
        const val BUNDLE_GITHUB_USER = "BUNDLE_GITHUB_USER"
        fun newInstance(user: GithubUser) = ReposFragment().apply {
            arguments = Bundle().apply {
                putParcelable(BUNDLE_GITHUB_USER, user)
            }
        }
    }
}