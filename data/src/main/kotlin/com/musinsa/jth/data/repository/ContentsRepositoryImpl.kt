package com.musinsa.jth.data.repository

import com.musinsa.jth.data.datasource.ContentsLocalSource
import com.musinsa.jth.data.datasource.ContentsRemoteSource
import com.musinsa.jth.data.model.remote.DataModel
import com.musinsa.jth.domain.model.remote.Banner
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
    override fun getContentsMap(data: Data): Map<String, DataItem> =
        localSource.getContentsMap(data)
}