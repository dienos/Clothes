package com.musinsa.jth.data.repository

import com.musinsa.jth.data.datasource.ContentsRemoteSource
import com.musinsa.jth.data.model.remote.DataModel
import com.musinsa.jth.domain.model.remote.Banner
import com.musinsa.jth.domain.model.remote.Data
import com.musinsa.jth.domain.repository.remote.ContentsRepository
import javax.inject.Inject

class ContentsRepositoryImpl @Inject constructor(
    private val remoteSource: ContentsRemoteSource,
) : ContentsRepository {
    override suspend fun getContents(): DataModel = remoteSource.getContents()
    override fun getBanners(data : Data): List<Banner> {
        var result : List<Banner> = arrayListOf()

        data.data.forEach {
           if(it.contents.banners != null) {
               result = it.contents.banners!!
               return@forEach
           }
       }

        return result
    }
}