package com.musinsa.jth.domain.usecase

import com.musinsa.jth.domain.model.remote.DataItem
import com.musinsa.jth.domain.repository.remote.ContentsRepository

class GetContentsByTypeUseCase(private val repository: ContentsRepository) {
    operator fun invoke(map: Map<String, DataItem>,  type : String):  DataItem? = repository.getContentsByType(map, type)
}