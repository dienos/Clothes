package com.musinsa.jth.domain.usecase

import com.musinsa.jth.domain.model.remote.ContentsItem
import com.musinsa.jth.domain.repository.remote.ContentsRepository

class GetRandomContentsItemListUseCase(private val repository: ContentsRepository) {
    operator fun invoke(
        type: String,
        currentMap: Map<String, List<ContentsItem>>
    ): Map<String, List<ContentsItem>> =
        repository.getRandomContentsItemListMap(type, currentMap)
}