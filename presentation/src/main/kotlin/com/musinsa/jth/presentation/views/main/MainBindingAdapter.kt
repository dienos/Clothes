package com.musinsa.jth.presentation.views.main

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.musinsa.jth.data.repository.local.ContentsType
import com.musinsa.jth.domain.model.remote.DataItem
import com.musinsa.jth.presentation.R
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

@BindingAdapter(value = ["activity", "items"])
fun setMainContents(view: RecyclerView, activity: MainActivity, map: Map<String, DataItem>?) {
    map?.let {
        val layoutManager = LinearLayoutManager(view.context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        view.layoutManager = layoutManager
        view.adapter = ContentsMainAdapter(activity, map)
    }
}

@BindingAdapter(value = ["activity", "sub_items"])
fun setSubContents(view: RecyclerView, activity: MainActivity, item: DataItem?) {
    item?.let {
        var layoutManager = LinearLayoutManager(view.context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        when (it.contents.type) {
            ContentsType.SCROLL.name -> {
                layoutManager.orientation = LinearLayoutManager.HORIZONTAL
            }

            ContentsType.GRID.name -> {
                layoutManager = GridLayoutManager(view.context, 3)
            }

            ContentsType.STYLE.name -> {
                layoutManager = GridLayoutManager(view.context, 2)
            }
        }

        view.layoutManager = layoutManager
        view.adapter = ContentsSubAdapter(item.contents.type)

        activity.lifecycleScope.launch {
            activity.viewModel.getContentsByType(it.contents.type).collectLatest { data ->
                (view.adapter as ContentsSubAdapter).submitData(data)
            }
        }
    }
}

@BindingAdapter(value = ["price"])
fun setPrice(view: TextView, price: Int?) {
    price?.let {
        view.text = view.context.getString(R.string.price, DecimalFormat("#,###").format(it))
    }
}



