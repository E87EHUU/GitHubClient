package com.example.githubclient.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.githubclient.databinding.ItemUserBinding
import com.example.githubclient.mvp.presenter.list.IUserListPresenter
import com.example.githubclient.mvp.view.IImageLoader
import com.example.githubclient.mvp.view.list.IUserItemView
import com.example.githubclient.utils.RV_INVALID_INDEX
import javax.inject.Inject

class UsersRVAdapter(
    private val presenter: IUserListPresenter
    ) :
    RecyclerView.Adapter<UsersRVAdapter.ViewHolder>() {

    @Inject
    lateinit var imageLoader: IImageLoader<ImageView>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false)).apply {
            itemView.setOnClickListener {
                presenter.itemClickListener?.invoke(this)
            }
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        presenter.bindView(holder.apply { pos = position })

    override fun getItemCount() = presenter.getCount()

    inner class ViewHolder(
        private val viewBinding: ItemUserBinding
    ) :
        RecyclerView.ViewHolder(viewBinding.root), IUserItemView {

        override fun setLogin(login: String) {
            viewBinding.userLogin.text = login
        }

        override fun loadAvatar(url: String) {
            imageLoader.loadInto(url, viewBinding.userAvatar)
        }

        override var pos = RV_INVALID_INDEX
    }
}