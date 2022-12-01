package com.musinsa.jth.domain.usecase

import com.musinsa.jth.domain.model.remote.Banner
import com.musinsa.jth.domain.model.remote.Data
import com.musinsa.jth.domain.repository.remote.ContentsRepository

class GetBannersUseCase(private val repository: ContentsRepository) {
    operator fun invoke(
        data: Data
    ): List<Banner> = repository.getBanners(data)
}