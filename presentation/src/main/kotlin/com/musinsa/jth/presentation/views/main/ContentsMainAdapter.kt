package com.musinsa.jth.presentation.views.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.musinsa.jth.domain.model.remote.Contents
import com.musinsa.jth.presentation.databinding.ContentMainItemBinding

class ContentsMainAdapter :  RecyclerView.Adapter<ContentsMainAdapter.ContentMainViewHolder>() {
    lateinit var binding: ContentMainItemBinding

    inner class ContentMainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Contents) {
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentMainViewHolder {
        binding = ContentMainItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContentMainViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ContentMainViewHolder, position: Int) {
        //holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int = 0
}
