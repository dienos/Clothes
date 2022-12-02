package com.musinsa.jth.presentation.views.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.musinsa.jth.data.model.local.ContentsType
import com.musinsa.jth.domain.model.remote.ContentsItem
import com.musinsa.jth.domain.model.remote.DataItem
import com.musinsa.jth.presentation.databinding.ContentSubGoodsItemBinding
import com.musinsa.jth.presentation.databinding.ContentSubStyleItemBinding

class ContentsSubAdapter(private val item: DataItem) :
    PagingDataAdapter<ContentsItem, RecyclerView.ViewHolder>(ContentsDiffCallback) {

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ContentsType.GRID.intType, ContentsType.SCROLL.intType -> {
                val bind = ContentSubGoodsItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                GoodsViewHolder(bind.root, bind)
            }

            ContentsType.STYLE.intType -> {
                val bind = ContentSubStyleItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                StyleViewHolder(bind.root, bind)
            }

            else -> {
                val binding = ContentSubGoodsItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                GoodsViewHolder(binding.root, binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.let { item ->
            when (holder) {
                is GoodsViewHolder -> {
                    holder.bind(item)
                }

                is StyleViewHolder -> {
                    holder.bind(item)
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (item.contents.type) {
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
}

object ContentsDiffCallback : DiffUtil.ItemCallback<ContentsItem>() {
    override fun areItemsTheSame(oldItem: ContentsItem, newItem: ContentsItem): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: ContentsItem, newItem: ContentsItem): Boolean {
        return oldItem.title == newItem.title

    }
}