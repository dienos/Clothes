package com.musinsa.jth.domain.repository.remote

import com.musinsa.jth.domain.model.remote.Banner
import com.musinsa.jth.domain.model.remote.Data

interface ContentsRepository {
    suspend fun getContents() : Data
    fun getBanners(data : Data) : List<Banner>
}