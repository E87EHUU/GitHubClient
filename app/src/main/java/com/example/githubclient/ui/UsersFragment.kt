package com.example.githubclient.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.githubclient.App
import com.example.githubclient.databinding.FragmentUsersBinding
import com.example.githubclient.mvp.model.CountersModel
import com.example.githubclient.mvp.navigation.BackPressedListener
import com.example.githubclient.mvp.presenter.UsersPresenter
import com.example.githubclient.mvp.view.UsersView
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackPressedListener {

    private var _viewBinding: FragmentUsersBinding? = null
    private val viewBinding get() = _viewBinding!!

    private val presenter by moxyPresenter { UsersPresenter(CountersModel(), App.instance.router) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        super.onCreate(savedInstanceState)

        _viewBinding = FragmentUsersBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewBinding) {
            btnCounter1.setOnClickListener { presenter.onFirstBtnClicked() }
            btnCounter2.setOnClickListener { presenter.onSecondBtnClicked() }
            btnCounter3.setOnClickListener { presenter.onThirdBtnClicked() }
        }
    }

    override fun setDigitOne(text: String) {
        viewBinding.btnCounter1.text = text
    }

    override fun setDigitTwo(text: String) {
        viewBinding.btnCounter2.text = text
    }

    override fun setDigitThree(text: String) {
        viewBinding.btnCounter3.text = text
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }

    companion object {
        fun newInstance() = UsersFragment()
    }

    override fun onBackPressed() = presenter.onBackPressed()

}