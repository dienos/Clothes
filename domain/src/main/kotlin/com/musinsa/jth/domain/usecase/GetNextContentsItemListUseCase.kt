package com.musinsa.jth.domain.usecase

import com.musinsa.jth.domain.model.remote.ContentsItem
import com.musinsa.jth.domain.repository.remote.ContentsRepository

class GetNextContentsItemListUseCase(private val repository: ContentsRepository) {
    operator fun invoke(
        currentMap: Map<String, List<ContentsItem>>
    ): List<List<ContentsItem>> =
        repository.getNextContentsItemList(currentMap)
}