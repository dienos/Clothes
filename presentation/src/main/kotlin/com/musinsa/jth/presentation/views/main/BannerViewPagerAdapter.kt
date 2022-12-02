package com.musinsa.jth.presentation.views.main

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.musinsa.jth.domain.model.remote.Banner
import com.musinsa.jth.presentation.MuSinSaApplication.Companion.applicationContext
import com.musinsa.jth.presentation.databinding.BannerItemBinding
import com.musinsa.jth.presentation.views.web.Const
import com.musinsa.jth.presentation.views.web.WebViewActivity

class BannerViewPagerAdapter(private val list: List<Banner>) :
    RecyclerView.Adapter<BannerViewPagerAdapter.PagerViewHolder>() {

    private lateinit var binding: BannerItemBinding

    inner class PagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Banner, position: Int) {
            binding.bannerItem = item
            binding.bannerNumber = position.plus(1)
        }
    }

    fun onBannerItemClick(url: String) {
        val context =  applicationContext()
        val intent = Intent(context, WebViewActivity::class.java)
        intent.putExtra(Const.WEB_URL, url)
        intent.addFlags(FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        binding = BannerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.bannerTotalCount =  list.size
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