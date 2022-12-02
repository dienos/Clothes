package com.musinsa.jth.data.datasource

import com.musinsa.jth.data.api.MuSinSaService
import com.musinsa.jth.data.model.local.ContentsType
import com.musinsa.jth.domain.model.remote.ContentsItem
import com.musinsa.jth.domain.model.remote.Data
import javax.inject.Inject

interface ContentsLocalSource {
    fun getContentsMap(data: Data): Map<String, List<ContentsItem>>
}

class ContentsLocalSourceImpl@Inject constructor(
    private val service: MuSinSaService
) : ContentsLocalSource {
    override fun getContentsMap(data: Data): Map<String, List<ContentsItem>> {
        val map = hashMapOf<String, List<ContentsItem>>()

        data.data.forEach {
            when (it.contents.type) {
                ContentsType.BANNER.name -> {
                    it.contents.banners?.let { banner ->
                        map.put(it.contents.type, banner)
                    }
                }

                ContentsType.GRID.name -> {
                    it.contents.goods?.let { grid ->
                        map.put(it.contents.type, grid)
                    }
                }

                ContentsType.SCROLL.name -> {
                    it.contents.goods?.let { goods ->
                        map.put(it.contents.type, goods)
                    }
                }

                ContentsType.STYLE.name -> {
                    it.contents.styles?.let { goods ->
                        map.put(it.contents.type, goods)
                    }
                }
                else -> {
                    it.contents.goods?.let { goods ->
                        map.put(it.contents.type, goods)
                    }
                }
            }
        }

        return map
    }
}