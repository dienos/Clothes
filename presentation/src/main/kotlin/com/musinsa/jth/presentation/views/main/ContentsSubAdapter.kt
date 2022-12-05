package com.musinsa.jth.presentation.views.main

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.musinsa.jth.data.model.local.ContentsType
import com.musinsa.jth.domain.model.remote.ContentsItem
import com.musinsa.jth.presentation.databinding.ContentSubGoodsItemBinding
import com.musinsa.jth.presentation.databinding.ContentSubStyleItemBinding
import com.musinsa.jth.presentation.views.web.Const
import com.musinsa.jth.presentation.views.web.WebViewActivity

class ContentsSubAdapter(private val context : Context, private val type: String) :
    ListAdapter<ContentsItem, RecyclerView.ViewHolder>(ContentsDiffCallback) {

    init {
        setHasStableIds(true)
    }

    inner class GoodsViewHolder(itemView: View, _bind: ContentSubGoodsItemBinding) :
        RecyclerView.ViewHolder(itemView) {
        private val bind = _bind

        fun bind(item: ContentsItem) {
            bind.item = item
        }
    }

    inner class StyleViewHolder(itemView: View, _bind: ContentSubStyleItemBinding) :
        RecyclerView.ViewHolder(itemView) {
        private val bind = _bind

        fun bind(item: ContentsItem) {
            bind.item = item
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
             ContentsType.GRID.intType, ContentsType.SCROLL.intType -> {
                val bind = ContentSubGoodsItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                bind.adapter = this

                val layoutParams: ViewGroup.LayoutParams = bind.root.layoutParams
                layoutParams.width = parent.width / 3
                bind.root.layoutParams = layoutParams

                GoodsViewHolder(bind.root, bind)
            }

            ContentsType.STYLE.intType -> {
                val bind = ContentSubStyleItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                bind.adapter = this

                val layoutParams: ViewGroup.LayoutParams = bind.root.layoutParams
                layoutParams.width = parent.width / 2
                bind.root.layoutParams = layoutParams

                StyleViewHolder(bind.root, bind)
            }

            else -> {
                val bind = ContentSubGoodsItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )

                bind.adapter = this
                GoodsViewHolder(bind.root, bind)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is GoodsViewHolder -> {
                holder.bind(getItem(position))
            }

            is StyleViewHolder -> {
                holder.bind(getItem(position))
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (type) {
            ContentsType.GRID.name -> {
                ContentsType.GRID.intType
            }

            ContentsType.SCROLL.name -> {
                ContentsType.SCROLL.intType
            }

            ContentsType.STYLE.name -> {
                ContentsType.STYLE.intType
            }

            else -> {
                -1
            }
        }
    }

    fun onContentsItemClick(url: String) {
        val intent = Intent(context, WebViewActivity::class.java)
        intent.putExtra(Const.WEB_URL, url)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }
}

object ContentsDiffCallback : DiffUtil.ItemCallback<ContentsItem>() {

    override fun areItemsTheSame(oldItem: ContentsItem, newItem: ContentsItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ContentsItem, newItem: ContentsItem): Boolean {
        return oldItem == newItem
    }
}