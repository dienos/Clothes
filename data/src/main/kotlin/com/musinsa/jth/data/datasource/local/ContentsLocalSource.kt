package com.musinsa.jth.data.datasource.local

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.musinsa.jth.data.api.MuSinSaService
import com.musinsa.jth.data.model.local.ContentsType
import com.musinsa.jth.domain.model.remote.ContentsItem
import com.musinsa.jth.domain.model.remote.Data
import com.musinsa.jth.domain.model.remote.DataItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface ContentsLocalSource {
    fun getContentsMap(data: Data?): Map<String, DataItem>
    fun getContentsByPaging(type: String): Flow<PagingData<ContentsItem>>
    fun getContentsByPageNumber(type: String, pageNumber: Int): List<ContentsItem>
}

class ContentsLocalSourceImpl @Inject constructor(
    private val service: MuSinSaService
) : ContentsLocalSource {
    private val map = hashMapOf<String, DataItem>()

    override fun getContentsMap(data: Data?): Map<String, DataItem> {
        data?.data?.forEach { dataItem ->
            map[dataItem.contents.type] = dataItem
        }

        return map
    }

    override fun getContentsByPaging(type: String): Flow<PagingData<ContentsItem>> {
        when (type) {
            ContentsType.GRID.name -> {
                return Pager(
                    config = PagingConfig(pageSize = 6, enablePlaceholders = false),
                    pagingSourceFactory = {
                        GridContentsPagingSource(ContentsLocalSourceImpl(service))
                    }).flow
            }

            ContentsType.STYLE.name -> {
                return Pager(
                    config = PagingConfig(pageSize = 4, enablePlaceholders = false),
                    pagingSourceFactory = {
                        StyleContentsPagingSource(ContentsLocalSourceImpl(service))
                    }).flow
            }
            else -> {
                return Pager(
                    config = PagingConfig(pageSize = 6, enablePlaceholders = false),
                    pagingSourceFactory = {
                        GridContentsPagingSource(ContentsLocalSourceImpl(service))
                    }).flow
            }
        }
    }

    override fun getContentsByPageNumber(type: String, pageNumber: Int): List<ContentsItem> {
        val list: ArrayList<ContentsItem> = arrayListOf()
        val stylePage = 4
        val gridPage = 6

        when (type) {
            ContentsType.GRID.name -> {
                val contents = map[type]?.contents?.goods
                contents?.let {
                    if (pageNumber == 1) {
                        for (i in 0 until gridPage) {
                            if(i < contents.size) {
                                list.add(contents[i])
                            }

                        }
                    } else {
                        val start = pageNumber.minus(1).times(gridPage)
                        val end = start.plus(gridPage)

                        for (i in start until end) {
                            if(i < contents.size) {
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
                            if(i < contents.size) {
                                list.add(contents[i])
                            }
                        }
                    } else {
                        val start = pageNumber.minus(1).times(stylePage)
                        val end = start.plus(stylePage)

                        for (i in start until end) {
                            if(i < contents.size) {
                                list.add(contents[i])
                            }
                        }
                    }
                }
            }
        }

        return list
    }
}