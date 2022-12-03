package com.musinsa.jth.data.datasource.local

import com.musinsa.jth.data.api.MuSinSaService
import com.musinsa.jth.data.repository.local.ContentsType
import com.musinsa.jth.domain.model.remote.Contents
import com.musinsa.jth.domain.model.remote.ContentsItem
import com.musinsa.jth.domain.model.remote.Data
import com.musinsa.jth.domain.model.remote.DataItem
import javax.inject.Inject

interface ContentsLocalSource {
    fun convertContentsMap(data: Data?): Map<String, DataItem>
    fun getFirstContentsItemListMap(data: Data?): Map<String, List<ContentsItem>>
    fun getContentsByPageNumber(
        map: Map<String, DataItem>,
        type: String,
        pageNumber: Int
    ): List<ContentsItem>

    fun getContentsByType(map: Map<String, DataItem>, type: String): DataItem?
}

class ContentsLocalSourceImpl @Inject constructor(
    private val service: MuSinSaService
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

        val stylePage = 4
        val gridPage = 6

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

                        for (i in 0 until gridPage) {
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

                        for (i in 0 until stylePage) {
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

    override fun getContentsByPageNumber(
        map: Map<String, DataItem>,
        type: String,
        pageNumber: Int
    ): List<ContentsItem> {
        val list: ArrayList<ContentsItem> = arrayListOf()
        val stylePage = 4
        val gridPage = 6

        when (type) {
            ContentsType.GRID.name -> {
                val contents = map[type]?.contents?.goods
                contents?.let {
                    if (pageNumber == 1) {
                        for (i in 0 until gridPage) {
                            if (i < contents.size) {
                                list.add(contents[i])
                            }
                        }
                    } else {
                        val start = pageNumber.minus(1).times(gridPage)
                        val end = start.plus(gridPage)

                        for (i in start until end) {
                            if (i < contents.size) {
                                list.add(contents[i])
                            }
                        }
                    }
                }
            }

            ContentsType.STYLE.name -> {
                val contents = map[type]?.contents?.styles
                contents?.let {
                    if (pageNumber == 1) {
                        for (i in 0 until stylePage) {
                            if (i < contents.size) {
                                list.add(contents[i])
                            }
                        }
                    } else {
                        val start = pageNumber.minus(1).times(stylePage)
                        val end = start.plus(stylePage)

                        for (i in start until end) {
                            if (i < contents.size) {
                                list.add(contents[i])
                            }
                        }
                    }
                }
            }

            ContentsType.SCROLL.name -> {
                val contents = map[type]?.contents?.goods

                contents?.let {
                    if (pageNumber >= contents.size) {
                        return list
                    }

                    return contents
                }
            }
        }

        return list
    }

    override fun getContentsByType(map: Map<String, DataItem>, type: String): DataItem? {
        map[type]?.let {
            return it
        }

        return null
    }
}