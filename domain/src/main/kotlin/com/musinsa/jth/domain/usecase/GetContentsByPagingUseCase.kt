package com.musinsa.jth.domain.usecase

import androidx.paging.PagingData
import com.musinsa.jth.domain.model.remote.ContentsItem
import com.musinsa.jth.domain.repository.remote.ContentsRepository
import kotlinx.coroutines.flow.Flow

class GetContentsByPagingUseCase(private val repository: ContentsRepository) {
    operator fun invoke(type : String): Flow<PagingData<ContentsItem>> = repository.getContentsByPaging(type)
}