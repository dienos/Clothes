package com.musinsa.jth.presentation.views.main

import android.os.Handler
import android.os.Looper
import androidx.databinding.BindingAdapter
import androidx.viewpager2.widget.ViewPager2
import com.musinsa.jth.domain.model.remote.Banner
import java.util.*

private var currentPage = 0
private var bannerCount = 0

@BindingAdapter(value = ["banner_items"])
fun setBanners(view: ViewPager2, items: List<Banner>?) {
    items?.let {
        bannerCount = items.size
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



