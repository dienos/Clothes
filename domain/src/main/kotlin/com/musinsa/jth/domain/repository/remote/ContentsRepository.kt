package com.musinsa.jth.domain.repository.remote

import androidx.paging.PagingData
import com.musinsa.jth.domain.model.remote.ContentsItem
import com.musinsa.jth.domain.model.remote.Data
import com.musinsa.jth.domain.model.remote.DataItem
import kotlinx.coroutines.flow.Flow

interface ContentsRepository {
    suspend fun getContents(): Data
    suspend fun getContentsMap(): Map<String, DataItem>
    fun convertContentsMap(data: Data): Map<String, DataItem>
    fun getContentsByPaging(type: String): Flow<PagingData<ContentsItem>>
    fun getContentsByPageNumber(map: Map<String, DataItem>, type: String, index: Int): List<ContentsItem>
}