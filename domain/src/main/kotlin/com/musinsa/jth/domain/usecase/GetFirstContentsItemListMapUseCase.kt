package com.musinsa.jth.domain.usecase

import com.musinsa.jth.domain.model.remote.ContentsItem
import com.musinsa.jth.domain.model.remote.Data
import com.musinsa.jth.domain.repository.remote.ContentsRepository

class GetFirstContentsItemListMapUseCase(private val repository: ContentsRepository) {
    operator fun invoke(
        data: Data
    ): Map<String, List<ContentsItem>> = repository.getFirstContentsItemListMap(data)
}