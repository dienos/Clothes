package com.musinsa.jth.data.model.remote

import com.google.gson.annotations.SerializedName
import com.musinsa.jth.domain.model.remote.*

data class ContentsModel(
    @SerializedName("type")
    val _type: String,

    @SerializedName("banners")
    val _banners: List<ContentsItemModel>?,

    @SerializedName("goods")
    val _goods: List<ContentsItemModel>?,

    @SerializedName("styles")
    val _styles: List<ContentsItemModel>?,
) : Contents {
    override val banners: List<ContentsItem>?
        get() = _banners

    override val goods: List<ContentsItem>?
        get() = _goods

    override val styles: List<ContentsItem>?
        get() = _styles

    override val type: String
        get() = _type
}