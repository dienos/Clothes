package com.musinsa.jth.data.model.remote

import com.google.gson.annotations.SerializedName
import com.musinsa.jth.domain.model.remote.Data
import com.musinsa.jth.domain.model.remote.DataItem

data class DataModel(
    @SerializedName("data")
    val _data: List<DataItemModel>
) : Data {
    override val data: List<DataItem>
        get() = _data
}