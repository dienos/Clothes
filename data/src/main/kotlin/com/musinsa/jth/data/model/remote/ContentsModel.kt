package com.musinsa.jth.data.model.remote

import com.google.gson.annotations.SerializedName
import com.musinsa.jth.domain.model.remote.Banner
import com.musinsa.jth.domain.model.remote.Contents
import com.musinsa.jth.domain.model.remote.Style

data class ContentsModel(
    @SerializedName("type")
    val _type: String,

    @SerializedName("banners")
    val _banners: List<BannerModel>?,

    @SerializedName("goods")
    val _goods: List<GoodsModel>?,

    @SerializedName("styles")
    val _styles: List<StyleModel>?,
) : Contents {
    override val banners: List<Banner>?
        get() = _banners

    override val goods: List<com.musinsa.jth.domain.model.remote.Goods>?
        get() = _goods

    override val styles: List<Style>?
        get() = _styles

    override val type: String
        get() = _type
}