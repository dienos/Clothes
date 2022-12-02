package com.musinsa.jth.domain.repository.remote

import androidx.paging.PagingData
import com.musinsa.jth.domain.model.remote.ContentsItem
import com.musinsa.jth.domain.model.remote.Data
import com.musinsa.jth.domain.model.remote.DataItem
import kotlinx.coroutines.flow.Flow

interface ContentsRepository {
    suspend fun getContents(): Data
    fun getContentsMap(data: Data): Map<String, DataItem>
    fun getContentsByPaging(type : String): Flow<PagingData<ContentsItem>>
    fun getContentsByIndex(type: String, index: Int): List<ContentsItem>
}