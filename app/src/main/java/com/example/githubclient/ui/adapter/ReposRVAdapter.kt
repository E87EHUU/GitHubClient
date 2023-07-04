package com.example.githubclient.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubclient.databinding.ItemReposBinding
import com.example.githubclient.mvp.presenter.list.IReposListPresenter
import com.example.githubclient.mvp.view.list.IReposItemView
import com.example.githubclient.utils.RV_INVALID_INDEX

class ReposRVAdapter(
    private val presenter: IReposListPresenter
) :
    RecyclerView.Adapter<ReposRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemReposBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        ).apply {
            itemView.setOnClickListener {
                presenter.itemClickListener?.invoke(this)
            }
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        presenter.bindView(holder.apply { pos = position })

    override fun getItemCount() = presenter.getCount()

    inner class ViewHolder(
        private val viewBinding: ItemReposBinding
    ) :
        RecyclerView.ViewHolder(viewBinding.root), IReposItemView {

        override fun setName(name: String) {
            viewBinding.reposName.text = name
        }

        override var pos = RV_INVALID_INDEX
    }
}