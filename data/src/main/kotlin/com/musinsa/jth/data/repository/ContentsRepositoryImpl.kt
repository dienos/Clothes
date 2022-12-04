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

    override fun getNextContentsItemListMap(
        type: String,
        originalMap: Map<String, DataItem>,
        currentMap: Map<String, List<ContentsItem>>
    ): Map<String, List<ContentsItem>> =
        localSource.getNextContentsItemListMap(type, originalMap, currentMap)

    override fun getRandomContentsItemListMap(
        type: String,
        currentMap: Map<String, List<ContentsItem>>
    ): Map<String, List<ContentsItem>> {
       return localSource.getRandomContentsItemListMap(type, currentMap)
    }

    override fun getNextContentsItemList(currentMap: Map<String, List<ContentsItem>>): List<List<ContentsItem>> {
        return localSource.getNextContentsItemList(currentMap)
    }
}