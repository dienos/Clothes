package com.musinsa.jth.domain.repository.local

import com.musinsa.jth.domain.model.local.LocalSampleRepo

interface LocalSampleRepository {
    suspend fun getLocalSample() : List<LocalSampleRepo>
}