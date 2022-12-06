package com.musinsa.jth.presentation.views.main

import android.os.Handler
import android.os.Looper
import android.widget.ScrollView
import android.widget.TextView
import androidx.core.widget.NestedScrollView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.*
import androidx.viewpager2.widget.ViewPager2
import com.musinsa.jth.data.model.local.ContentsType
import com.musinsa.jth.domain.model.remote.ContentsItem
import com.musinsa.jth.domain.model.remote.DataItem
import com.musinsa.jth.presentation.R
import java.text.DecimalFormat

@BindingAdapter(value = ["content_type"])
fun scrollToPosition(view: NestedScrollView, type: String?) {
    type?.let {
        if (type == ContentsType.STYLE.name) {
            Handler(Looper.getMainLooper()).postDelayed({
                view.fullScroll(ScrollView.FOCUS_DOWN)
                view.clearFocus()
            }, 300)
        }
    }
}

@BindingAdapter(value = ["banner_items"])
fun setBanners(view: ViewPager2, items: List<ContentsItem>?) {
    items?.let {
        view.adapter = BannerViewPagerAdapter(view.context, it)
        view.orientation = ViewPager2.ORIENTATION_HORIZONTAL
    }
}

@BindingAdapter(value = ["activity", "original_map", "current_map", "current_list"])
fun setMainContents(
    view: RecyclerView,
    activity: MainActivity,
    originalMap: Map<String, DataItem>?,
    currentMap: Map<String, List<ContentsItem>>?,
    currentList: List<List<ContentsItem>>?
) {
    currentMap?.let {
        originalMap?.let {
            currentList?.let {
                view.adapter?.apply {
                    val adapter = view.adapter as ContentsMainAdapter
                    adapter.currentMap = currentMap
                    adapter.submitList(it)
                } ?: run {
                    val layoutManager =
                        LinearLayoutManager(
                            view.context,
                            LinearLayoutManager.VERTICAL,
                            false
                        )
                    layoutManager.orientation = LinearLayoutManager.VERTICAL
                    view.layoutManager = layoutManager
                    view.adapter =
                        ContentsMainAdapter(
                            activity,
                            originalMap = originalMap,
                            currentMap = currentMap
                        )

                    (view.adapter as ContentsMainAdapter).submitList(currentList)
                }
            }
        }
    }
}

@BindingAdapter(value = ["contents_type", "sub_items"])
fun setSubContents(view: RecyclerView, type: String, item: List<ContentsItem>?) {
    item?.let {
        view.adapter?.apply {
            val adapter = (view.adapter as ContentsSubAdapter)
            adapter.submitList(item)
        } ?: run {
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
                }

                ContentsType.STYLE.name -> {
                    layoutManager = GridLayoutManager(view.context, 2)
                    view.layoutManager = layoutManager
                }
            }

            val adapter = ContentsSubAdapter(view.context, type)

            view.adapter = adapter
            adapter.submitList(item)
        }
    }
}

@BindingAdapter(value = ["price"])
fun setPrice(view: TextView, price: Int?) {
    price?.let {
        view.text = view.context.getString(R.string.price, DecimalFormat("#,###").format(it))
    }
}



