package com.musinsa.jth.data.repository

import com.musinsa.jth.data.datasource.local.ContentsLocalSource
import com.musinsa.jth.data.datasource.remote.ContentsRemoteSource
import com.musinsa.jth.data.model.remote.DataModel
import com.musinsa.jth.domain.model.remote.ContentsItem
import com.musinsa.jth.domain.model.remote.Data
import com.musinsa.jth.domain.model.remote.DataItem
import com.musinsa.jth.domain.repository.remote.ContentsRepository
import javax.inject.Inject

class ContentsRepositoryImpl @Inject constructor(
    private val remoteSource: ContentsRemoteSource,
    private val localSource: ContentsLocalSource,
) : ContentsRepository {
    override suspend fun getContents(): DataModel = remoteSource.getContents()
    override fun convertContentsMap(data: Data): Map<String, DataItem> =
        localSource.convertContentsMap(data)

    override fun getFirstContentsItemListMap(data: Data?): Map<String, List<ContentsItem>> =
        localSource.getFirstContentsItemListMap(data)

    override suspend fun getContentsMap(): Map<String, DataItem> {
        val result = remoteSource.getContents()
        return localSource.convertContentsMap(result)
    }

    override fun getContentsByPageNumber(
        map: Map<String, DataItem>,
        type: String,
        index: Int
    ): List<ContentsItem> = localSource.getContentsByPageNumber(map, type, index)

    override fun getContentsByType(map: Map<String, DataItem>, type: String): DataItem? =
        localSource.getContentsByType(map, type)
}