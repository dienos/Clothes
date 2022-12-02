package com.musinsa.jth.data.datasource

import com.musinsa.jth.data.api.MuSinSaService
import com.musinsa.jth.domain.model.remote.Data
import com.musinsa.jth.domain.model.remote.DataItem
import javax.inject.Inject

interface ContentsLocalSource {
    fun getContentsMap(data: Data?): Map<String, DataItem>
}

class ContentsLocalSourceImpl @Inject constructor(
    private val service: MuSinSaService
) : ContentsLocalSource {
    override fun getContentsMap(data: Data?): Map<String, DataItem> {
        val map = hashMapOf<String, DataItem>()

        data?.let {
            data.data.forEach { dataItem ->
                map[dataItem.contents.type] = dataItem
            }
        }?: run {
            //service.getContents()
        }

        return map
    }
}