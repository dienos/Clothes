package com.musinsa.jth.domain.repository.remote

import com.musinsa.jth.domain.model.remote.Data
import com.musinsa.jth.domain.model.remote.DataItem

interface ContentsRepository {
    suspend fun getContents(): Data
    fun getContentsMap(data: Data): Map<String, DataItem>
}