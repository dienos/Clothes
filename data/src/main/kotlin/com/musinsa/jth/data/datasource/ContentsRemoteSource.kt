package com.musinsa.jth.data.datasource

import com.musinsa.jth.data.api.MuSinSaService
import com.musinsa.jth.data.model.remote.DataModel
import javax.inject.Inject

interface ContentsRemoteSource {
    suspend fun getContents(): DataModel
}

class ContentsRemoteSourceImpl @Inject constructor(
    private val service: MuSinSaService
) : ContentsRemoteSource {
    override suspend fun getContents(): DataModel = service.getContents()
}