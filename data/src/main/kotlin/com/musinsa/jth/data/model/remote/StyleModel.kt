package com.musinsa.jth.data.model.remote

import com.google.gson.annotations.SerializedName
import com.musinsa.jth.domain.model.remote.Style

data class StyleModel(
    @SerializedName("linkURL")
    val _linkURL: String,

    @SerializedName("thumbnailURL")
    val _thumbnailURL: String
) : Style {
    override val linkURL: String
        get() = _linkURL

    override val thumbnailURL: String
        get() = _thumbnailURL
}