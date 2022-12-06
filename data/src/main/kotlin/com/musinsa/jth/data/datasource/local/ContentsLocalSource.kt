package com.musinsa.jth.data.datasource.local

import com.musinsa.jth.data.api.MuSinSaService
import com.musinsa.jth.data.model.local.PageSize
import com.musinsa.jth.data.model.local.ContentsType
import com.musinsa.jth.domain.model.remote.ContentsItem
import com.musinsa.jth.domain.model.remote.Data
import com.musinsa.jth.domain.model.remote.DataItem
import javax.inject.Inject
import kotlin.collections.ArrayList
import kotlin.collections.LinkedHashMap

interface ContentsLocalSource {
    fun convertContentsMap(data: Data?): Map<String, DataItem>
    fun getFirstContentsItemListMap(data: Data?): Map<String, List<ContentsItem>>
    fun getNextContentsItemListMap(
        type: String,
        originalMap: Map<String, DataItem>,
        currentMap: Map<String, List<ContentsItem>>
    ): Map<String, List<ContentsItem>>

    fun getRandomContentsItemListMap(
        type: String,
        currentMap: Map<String, List<ContentsItem>>
    ): Map<String, List<ContentsItem>>

    fun getNextContentsItemList(
        currentMap: Map<String, List<ContentsItem>>
    ): List<List<ContentsItem>>
}

class ContentsLocalSourceImpl @Inject constructor(
    private val service: MuSinSaService //do nothing
) : ContentsLocalSource {
    override fun convertContentsMap(data: Data?): Map<String, DataItem> {
        val map = LinkedHashMap<String, DataItem>()

        data?.data?.forEach { dataItem ->
            map[dataItem.contents.type] = dataItem
        }

        return map
    }

    override fun getFirstContentsItemListMap(data: Data?): Map<String, List<ContentsItem>> {
        val map = LinkedHashMap<String, List<ContentsItem>>()

        data?.data?.forEach {
            when (it.contents.type) {
                ContentsType.BANNER.name -> {
                    it.contents.banners?.let { banners ->
                        val list: ArrayList<ContentsItem> = arrayListOf()
                        list.addAll(banners)
                        map[it.contents.type] = list
                    }
                }

                ContentsType.GRID.name -> {
                    it.contents.goods?.let { goods ->
                        val list: ArrayList<ContentsItem> = arrayListOf()

                        for (i in 0 until PageSize.GRID.size) {
                            if (i < goods.size) {
                                list.add(goods[i])
                            }
                        }

                        map[it.contents.type] = list
                    }
                }

                ContentsType.STYLE.name -> {
                    it.contents.styles?.let { styles ->
                        val list: ArrayList<ContentsItem> = arrayListOf()

                        for (i in 0 until PageSize.STYLE.size) {
                            if (i < styles.size) {
                                list.add(styles[i])
                            }
                        }

                        map[it.contents.type] = list
                    }
                }

                ContentsType.SCROLL.name -> {
                    it.contents.goods?.let { goods ->
                        val list: ArrayList<ContentsItem> = arrayListOf()
                        list.addAll(goods)
                        map[it.contents.type] = list
                    }
                }
            }
        }

        return map
    }

    override fun getNextContentsItemListMap(
        type: String,
        originalMap: Map<String, DataItem>,
        currentMap: Map<String, List<ContentsItem>>
    ): Map<String, List<ContentsItem>> {
        var pageCount = 0
        val originalContents: ArrayList<ContentsItem> = arrayListOf()

        val map = LinkedHashMap<String, List<ContentsItem>>(currentMap)
        val currentContents = map[type]

        when (type) {
            ContentsType.STYLE.name -> {
                pageCount = PageSize.STYLE.size

                originalMap[type]?.contents?.styles?.let { styles ->
                    originalContents.addAll(styles)
                }
            }

            ContentsType.GRID.name -> {
                pageCount = PageSize.GRID.size

                originalMap[type]?.contents?.goods?.let { goods ->
                    originalContents.addAll(goods)
                }
            }
        }

        currentContents?.let { contents ->
            if (originalContents.size != contents.size) {
                for (i in contents.size until pageCount + contents.size) {
                    if (i < originalContents.size) {
                        (currentContents as ArrayList<ContentsItem>).add(originalContents[i])
                    }
                }

                map[type] = currentContents
            }
        }

        return map
    }

    override fun getRandomContentsItemListMap(
        type: String,
        currentMap: Map<String, List<ContentsItem>>
    ): Map<String, List<ContentsItem>> {
        val map = LinkedHashMap<String, List<ContentsItem>>(currentMap)
        val currentList = map[type]
        val changedList = currentList?.shuffled()

        changedList?.toMutableList()?.let {
            map[type] = it
        }

        return map
    }

    override fun getNextContentsItemList(currentMap: Map<String, List<ContentsItem>>): List<List<ContentsItem>> {
        val resultList = arrayListOf<List<ContentsItem>>()
        val contentsKeys: List<String> = currentMap.keys.toList()

        contentsKeys.forEach {
            currentMap[it]?.let { item ->
                resultList.add(item.toMutableList())
            }
        }

        return resultList
    }
}