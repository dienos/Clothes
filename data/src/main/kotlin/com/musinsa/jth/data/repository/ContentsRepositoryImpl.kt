package com.musinsa.jth.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.musinsa.jth.data.datasource.local.ContentsLocalSource
import com.musinsa.jth.data.datasource.remote.ContentsRemoteSource
import com.musinsa.jth.data.model.remote.DataModel
import com.musinsa.jth.data.repository.local.ContentsType
import com.musinsa.jth.data.repository.local.GridContentsPagingSource
import com.musinsa.jth.data.repository.local.ScrollContentsPagingSource
import com.musinsa.jth.data.repository.local.StyleContentsPagingRepository
import com.musinsa.jth.domain.model.remote.ContentsItem
import com.musinsa.jth.domain.model.remote.Data
import com.musinsa.jth.domain.model.remote.DataItem
import com.musinsa.jth.domain.repository.remote.ContentsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ContentsRepositoryImpl @Inject constructor(
    private val remoteSource: ContentsRemoteSource,
    private val localSource: ContentsLocalSource,
) : ContentsRepository {
    override suspend fun getContents(): DataModel = remoteSource.getContents()
    override fun convertContentsMap(data: Data): Map<String, DataItem> =
        localSource.convertContentsMap(data)

    override suspend fun getContentsMap(): Map<String, DataItem> {
        val result = remoteSource.getContents()
        return localSource.convertContentsMap(result)
    }

    override fun getContentsByPaging(type: String): Flow<PagingData<ContentsItem>> {
        when (type) {
            ContentsType.GRID.name -> {
                return Pager(
                    config = PagingConfig(pageSize = 6, enablePlaceholders = false),
                    pagingSourceFactory = {
                        GridContentsPagingSource(this)
                    }).flow
            }

            ContentsType.STYLE.name -> {
                return Pager(
                    config = PagingConfig(pageSize = 4, enablePlaceholders = false),
                    pagingSourceFactory = {
                        StyleContentsPagingRepository(this)
                    }).flow
            }

            ContentsType.SCROLL.name -> {
                return Pager(
                    config = PagingConfig(pageSize = 10, enablePlaceholders = false),
                    pagingSourceFactory = {
                        ScrollContentsPagingSource(this)
                    }).flow
            }

            else -> {
                return Pager(
                    config = PagingConfig(pageSize = 10, enablePlaceholders = false),
                    pagingSourceFactory = {
                        ScrollContentsPagingSource(this)
                    }).flow
            }
        }
    }

    override fun getContentsByPageNumber(
        map: Map<String, DataItem>,
        type: String,
        index: Int
    ): List<ContentsItem>
       = localSource.getContentsByPageNumber(map, type, index)
}