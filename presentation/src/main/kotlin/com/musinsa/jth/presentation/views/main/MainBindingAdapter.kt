package com.musinsa.jth.presentation.views.main

import androidx.databinding.BindingAdapter
import androidx.viewpager2.widget.ViewPager2
import com.musinsa.jth.domain.model.remote.Banner

@BindingAdapter(value = ["banner_items"])
fun setBanners(view: ViewPager2, items: List<Banner>?) {
    items?.let {
        view.adapter = BannerViewPagerAdapter(it)
        view.orientation = ViewPager2.ORIENTATION_HORIZONTAL
    }
}




