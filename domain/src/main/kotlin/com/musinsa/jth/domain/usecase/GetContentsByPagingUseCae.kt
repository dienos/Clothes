package com.musinsa.jth.domain.usecase

import com.musinsa.jth.domain.model.remote.ContentsItem
import com.musinsa.jth.domain.repository.remote.ContentsRepository

class GetContentsByPageNumberUseCase(private val repository: ContentsRepository) {
    suspend operator fun invoke(type: String, pageNumber: Int): List<ContentsItem> {
        val map = repository.getContentsMap()
        return repository.getContentsByPageNumber(map, type, pageNumber)
    }
}