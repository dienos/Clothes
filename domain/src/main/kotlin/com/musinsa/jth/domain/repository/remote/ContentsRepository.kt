package com.musinsa.jth.domain.repository.remote

import com.musinsa.jth.domain.model.remote.ContentsItem
import com.musinsa.jth.domain.model.remote.Data
import com.musinsa.jth.domain.model.remote.DataItem

interface ContentsRepository {
    suspend fun getContents(): Data
    fun convertContentsMap(data: Data): Map<String, DataItem>
    fun getFirstContentsItemListMap(data: Data?): Map<String, List<ContentsItem>>
    fun getNextContentsItemListMap(
        type: String,
        originalMap: Map<String, DataItem>,
        currentMap: Map<String, List<ContentsItem>>
    ): Map<String, List<ContentsItem>>

    fun getRandomContentsItemListMap(
        type: String,
        currentMap: Map<String, List<ContentsItem>>
    ): Map<String, List<ContentsItem>>

    fun getNextContentsItemList(
        currentMap: Map<String, List<ContentsItem>>
    ): List<List<ContentsItem>>
}