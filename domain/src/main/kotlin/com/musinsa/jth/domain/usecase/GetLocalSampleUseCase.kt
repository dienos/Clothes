package com.musinsa.jth.domain.usecase

import com.musinsa.jth.domain.model.local.LocalSampleRepo
import com.musinsa.jth.domain.repository.local.LocalSampleRepository

class GetLocalSampleUseCase(private val repository: LocalSampleRepository) {
    suspend operator fun invoke(
    ): List<LocalSampleRepo> {
        return repository.getLocalSample()
    }
}