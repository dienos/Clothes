package com.musinsa.jth.presentation.views.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.musinsa.jth.domain.model.remote.Banner
import com.musinsa.jth.presentation.BR
import com.musinsa.jth.presentation.databinding.BannerItemBinding

class BannerViewPagerAdapter(private val list: List<Banner>) :
    RecyclerView.Adapter<BannerViewPagerAdapter.PagerViewHolder>() {

    private lateinit var binding: BannerItemBinding


    inner class PagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Banner) {
            binding.setVariable(BR.banner_item, item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        binding = BannerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PagerViewHolder(binding.root)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.bind(list[position])
    }
}