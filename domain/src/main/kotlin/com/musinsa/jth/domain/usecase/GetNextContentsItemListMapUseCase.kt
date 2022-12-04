package com.musinsa.jth.domain.usecase

import com.musinsa.jth.domain.model.remote.ContentsItem
import com.musinsa.jth.domain.model.remote.DataItem
import com.musinsa.jth.domain.repository.remote.ContentsRepository

class GetNextContentsItemListMapUseCase(private val repository: ContentsRepository) {
    operator fun invoke(
        type: String,
        originalMap: Map<String, DataItem>,
        currentMap: Map<String, List<ContentsItem>>
    ): Map<String, List<ContentsItem>> =
        repository.getNextContentsItemListMap(type, originalMap, currentMap)
}