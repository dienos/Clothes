package com.musinsa.jth.presentation.views.main

import android.os.Handler
import android.os.Looper
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.musinsa.jth.domain.model.remote.Banner
import com.musinsa.jth.domain.model.remote.ContentsItem
import com.musinsa.jth.domain.model.remote.DataItem
import java.util.*

private var currentPage = 0
private var bannerCount = 0

@BindingAdapter(value = ["banner_items"])
fun setBanners(view: ViewPager2, items: DataItem?) {
    items?.contents?.banners?.let {
        bannerCount = it.size
        view.adapter = BannerViewPagerAdapter(it)
        view.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        view.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                currentPage = position
            }
        })
        
        val handler = Handler(Looper.getMainLooper())
        val update = Runnable {
            if (currentPage == bannerCount.minus(1)) {
                currentPage = 0
            }

            view.setCurrentItem(currentPage++, true)
        }

        val timer = Timer()

        timer.schedule(object : TimerTask() {
            override fun run() {
                handler.post(update)
            }
        }, 500, 3000)
    }
}

@BindingAdapter(value = ["items"])
fun setMainContents(view: RecyclerView, map : Map<String, DataItem>?) {
    map?.let {
        val layoutManager = LinearLayoutManager(view.context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        view.layoutManager =layoutManager
        view.adapter = ContentsMainAdapter(map)
    }
}

@BindingAdapter(value = ["sub_items"])
fun setSubContents(view: RecyclerView, item : DataItem?) {
    item?.let {
        val layoutManager = LinearLayoutManager(view.context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        view.layoutManager =layoutManager
        view.adapter = ContentsSubAdapter(item)
    }
}



