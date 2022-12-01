package com.musinsa.jth.data.model.remote

import com.google.gson.annotations.SerializedName
import com.musinsa.jth.domain.model.remote.Banner

data class BannerModel(
    @SerializedName("linkURL")
    val _linkURL: String,

    @SerializedName("thumbnailURL")
    val _thumbnailURL: String,

    @SerializedName("title")
    val _title: String,

    @SerializedName("description")
    val _description: String,

    @SerializedName("keyword")
    val _keyword: String,
) : Banner {
    override val description: String
        get() = _description

    override val keyword: String
        get() = _keyword

    override val linkURL: String
        get() = _linkURL

    override val thumbnailURL: String
        get() = _thumbnailURL

    override val title: String
        get() = _title
}