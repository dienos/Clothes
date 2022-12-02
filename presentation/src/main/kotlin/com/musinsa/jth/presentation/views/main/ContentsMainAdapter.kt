package com.musinsa.jth.presentation.views.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.musinsa.jth.data.model.local.ContentsType
import com.musinsa.jth.domain.model.remote.DataItem
import com.musinsa.jth.presentation.databinding.BannerMainItemBinding
import com.musinsa.jth.presentation.databinding.ContentMainItemBinding

class ContentsMainAdapter(private val _activity: MainActivity, private val map: Map<String, DataItem>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val contentsKeys: List<String> = map.keys.toList()

    inner class ContentsViewHolder(itemView: View, _bind: ContentMainItemBinding) :
        RecyclerView.ViewHolder(itemView) {
        private val bind = _bind

        fun bind(item: DataItem?) {
            bind.activity = _activity
            bind.item = item
        }
    }

    inner class BannerViewHolder(itemView: View, _bind: BannerMainItemBinding) :
        RecyclerView.ViewHolder(itemView) {
        private val bind = _bind

        fun bind(item: DataItem?) {
            bind.item = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ContentsType.GRID.intType, ContentsType.SCROLL.intType, ContentsType.STYLE.intType -> {
                val bind = ContentMainItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                ContentsViewHolder(bind.root, bind)
            }

            ContentsType.BANNER.intType -> {
                val bind = BannerMainItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                BannerViewHolder(bind.root, bind)
            }

            else -> {
                val bind = ContentMainItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                ContentsViewHolder(bind.root, bind)
            }
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ContentsViewHolder -> {
                holder.bind(map[contentsKeys[position]])
            }

            is BannerViewHolder -> {
                holder.bind(map[contentsKeys[position]])
            }

            else -> {
                (holder as ContentsViewHolder).bind(map[contentsKeys[position]])
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (map[contentsKeys[position]]?.contents?.type) {
            ContentsType.BANNER.name -> {
                ContentsType.BANNER.intType
            }

            ContentsType.SCROLL.name -> {
                ContentsType.SCROLL.intType
            }

            ContentsType.STYLE.name -> {
                ContentsType.STYLE.intType
            }

            ContentsType.GRID.name -> {
                ContentsType.GRID.intType
            }

            else -> {
                -1
            }
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int = contentsKeys.size
}
