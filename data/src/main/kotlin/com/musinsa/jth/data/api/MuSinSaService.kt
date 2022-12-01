package com.musinsa.jth.data.api

import com.musinsa.jth.data.model.remote.DataModel
import retrofit2.http.GET

interface MuSinSaService {
    @GET("/interview/list.json")
    suspend fun getContents() : DataModel
}