package com.musinsa.jth.presentation.views.main

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.musinsa.jth.data.repository.local.ContentsType
import com.musinsa.jth.domain.model.remote.ContentsItem
import com.musinsa.jth.domain.model.remote.DataItem
import com.musinsa.jth.presentation.R
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.text.DecimalFormat

@BindingAdapter(value = ["banner_items"])
fun setBanners(view: ViewPager2, items: DataItem?) {
    items?.contents?.banners?.let {
        view.adapter = BannerViewPagerAdapter(it)
        view.orientation = ViewPager2.ORIENTATION_HORIZONTAL
    }
}

@BindingAdapter(value = ["activity", "original_map", "current_map"])
fun setMainContents(
    view: RecyclerView,
    activity: MainActivity,
    originalMap: Map<String, DataItem>?,
    currentMap: Map<String, List<ContentsItem>>?
) {
    currentMap?.let {
        val layoutManager = LinearLayoutManager(view.context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        view.layoutManager = layoutManager
        view.adapter =
            ContentsMainAdapter(activity, originalMap = originalMap!!, currentMap = currentMap)
    }
}

@BindingAdapter(value = ["contents_type", "sub_items"])
fun setSubContents(view: RecyclerView, type: String, item: List<ContentsItem>?) {
    item?.let {
        var layoutManager = LinearLayoutManager(view.context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        when (type) {
            ContentsType.SCROLL.name -> {
                layoutManager.orientation = LinearLayoutManager.HORIZONTAL
                view.layoutManager = layoutManager
            }

            ContentsType.GRID.name -> {
                layoutManager = GridLayoutManager(view.context, 3)
                view.layoutManager = layoutManager
                view.adapter = ContentsSubAdapter(type)
            }

            ContentsType.STYLE.name -> {
                layoutManager = GridLayoutManager(view.context, 2)
                view.layoutManager = layoutManager
            }
        }

        view.adapter = ContentsSubAdapter(type)
        (view.adapter as ContentsSubAdapter).submitList(item)
    }
}

@BindingAdapter(value = ["price"])
fun setPrice(view: TextView, price: Int?) {
    price?.let {
        view.text = view.context.getString(R.string.price, DecimalFormat("#,###").format(it))
    }
}



