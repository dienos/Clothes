package com.musinsa.jth.presentation.views.main

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.musinsa.jth.data.model.local.ContentsType
import com.musinsa.jth.domain.model.remote.ContentsItem
import com.musinsa.jth.domain.model.remote.DataItem
import com.musinsa.jth.domain.model.remote.Footer
import com.musinsa.jth.domain.model.remote.Header
import com.musinsa.jth.presentation.MuSinSaApplication
import com.musinsa.jth.presentation.databinding.BannerMainItemBinding
import com.musinsa.jth.presentation.databinding.ContentMainItemBinding
import com.musinsa.jth.presentation.views.web.Const
import com.musinsa.jth.presentation.views.web.WebViewActivity

class ContentsMainAdapter(
    private val _activity: MainActivity,
    private val originalMap: Map<String, DataItem>,
    var currentMap: Map<String, List<ContentsItem>>
) : ListAdapter<List<ContentsItem>, RecyclerView.ViewHolder>(DiffCallback) {

    init {
        setHasStableIds(true)
    }

    private val contentsKeys: List<String> = currentMap.keys.toList()

    inner class ContentsViewHolder(itemView: View, _bind: ContentMainItemBinding) :
        RecyclerView.ViewHolder(itemView) {
        private val bind = _bind

        fun bind(
            _item: List<ContentsItem>?,
            _originalItem: List<ContentsItem>?,
            _header: Header?,
            _footer: Footer?,
            _type: String?
        ) {
            bind.activity = _activity
            bind.item = _item

            _type?.let {
                bind.contentsType = it
            }

            _header?.let {
                bind.contentsHeader = it
            }

            _footer?.let {
                bind.contentsFooter = it
            }

            bind.footerVisible = (_type == ContentsType.SCROLL.name ) || (_originalItem?.size != _item?.size)
        }
    }

    inner class BannerViewHolder(itemView: View, _bind: BannerMainItemBinding) :
        RecyclerView.ViewHolder(itemView) {
        private val bind = _bind

        fun bind(_item: List<ContentsItem>?) {
            _item?.let {
                bind.item = it
            }
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

                bind.adapter = this
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

                bind.adapter = this
                ContentsViewHolder(bind.root, bind)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ContentsViewHolder -> {
                val originalItem: List<ContentsItem> =
                    when (originalMap[contentsKeys[position]]?.contents?.type) {
                        ContentsType.STYLE.name -> {
                            originalMap[contentsKeys[position]]?.contents?.styles!!
                        }

                        ContentsType.GRID.name, ContentsType.SCROLL.name -> {
                            originalMap[contentsKeys[position]]?.contents?.goods!!
                        }

                        else -> {
                            originalMap[contentsKeys[position]]?.contents?.goods!!
                        }
                    }

                holder.bind(
                    getItem(position),
                    originalItem,
                    originalMap[contentsKeys[position]]?.header,
                    originalMap[contentsKeys[position]]?.footer,
                    originalMap[contentsKeys[position]]?.contents?.type
                )
            }

            is BannerViewHolder -> {
                holder.bind(getItem(position))
            }

            else -> {
                (holder as ContentsViewHolder).bind(
                    getItem(position),
                    originalMap[contentsKeys[position]]?.contents?.goods,
                    originalMap[contentsKeys[position]]?.header,
                    originalMap[contentsKeys[position]]?.footer,
                    originalMap[contentsKeys[position]]?.contents?.type
                )
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (originalMap[contentsKeys[position]]?.contents?.type) {
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

    fun onContentsItemClick(url: String) {
        val context = MuSinSaApplication.applicationContext()
        val intent = Intent(context, WebViewActivity::class.java)
        intent.putExtra(Const.WEB_URL, url)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }
}

object DiffCallback : DiffUtil.ItemCallback<List<ContentsItem>>() {
    override fun areItemsTheSame(
        oldItem: List<ContentsItem>,
        newItem: List<ContentsItem>
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: List<ContentsItem>,
        newItem: List<ContentsItem>
    ): Boolean {
        return oldItem == newItem
    }
}
