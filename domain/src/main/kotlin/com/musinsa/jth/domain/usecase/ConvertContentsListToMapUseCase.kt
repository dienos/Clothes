package com.musinsa.jth.domain.usecase

import com.musinsa.jth.domain.model.remote.Data
import com.musinsa.jth.domain.model.remote.DataItem
import com.musinsa.jth.domain.repository.remote.ContentsRepository

class ConvertContentsListToMapUseCase(private val repository: ContentsRepository) {
    operator fun invoke(
        data: Data
    ): Map<String, DataItem> = repository.convertContentsMap(data)
}