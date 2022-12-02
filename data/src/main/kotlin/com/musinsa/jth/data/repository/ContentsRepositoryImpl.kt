package com.musinsa.jth.data.repository

import androidx.paging.PagingData
import com.musinsa.jth.data.datasource.local.ContentsLocalSource
import com.musinsa.jth.data.datasource.remote.ContentsRemoteSource
import com.musinsa.jth.data.model.remote.DataModel
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
    override fun getContentsMap(data: Data): Map<String, DataItem> =
        localSource.getContentsMap(data)

    override fun getContentsByPaging(type : String): Flow<PagingData<ContentsItem>> =
        localSource.getContentsByPaging(type)

    override fun getContentsByIndex(type: String, index: Int): List<ContentsItem> = localSource.getContentsByPageNumber(type, index)
}