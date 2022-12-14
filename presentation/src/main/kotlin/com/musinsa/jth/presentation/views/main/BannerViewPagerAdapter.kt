package com.musinsa.jth.presentation.views.main

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.musinsa.jth.domain.model.remote.ContentsItem
import com.musinsa.jth.presentation.databinding.BannerSubItemBinding
import com.musinsa.jth.presentation.views.const.WebConst.WEB_URL
import com.musinsa.jth.presentation.views.web.WebViewActivity

class BannerViewPagerAdapter(private val context: Context, private val list: List<ContentsItem>) :
    RecyclerView.Adapter<BannerViewPagerAdapter.PagerViewHolder>() {

    private lateinit var binding: BannerSubItemBinding

    inner class PagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: ContentsItem, position: Int) {
            binding.bannerItem = item
            binding.bannerNumber = position.plus(1)
        }
    }

    fun onBannerItemClick(url: String) {
        val intent = Intent(context, WebViewActivity::class.java)
        intent.putExtra(WEB_URL, url)
        intent.addFlags(FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        binding = BannerSubItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.bannerTotalCount = list.size
        binding.adapter = this
        return PagerViewHolder(binding.root)
    }

    override fun getItemCount(): Int = list.size

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.bind(list[position], position)
    }
}