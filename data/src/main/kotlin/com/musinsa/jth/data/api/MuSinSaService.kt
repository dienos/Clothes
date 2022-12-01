package com.musinsa.jth.data.api

import com.musinsa.jth.data.api.Const.INTERVIEW_URI
import com.musinsa.jth.data.model.remote.DataModel
import retrofit2.http.GET

interface MuSinSaService {
    @GET(INTERVIEW_URI)
    suspend fun getContents() : DataModel
}