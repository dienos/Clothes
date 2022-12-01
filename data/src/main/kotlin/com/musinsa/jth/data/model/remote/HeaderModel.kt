package com.musinsa.jth.data.model.remote

import com.google.gson.annotations.SerializedName
import com.musinsa.jth.domain.model.remote.Header

data class HeaderModel (
    @SerializedName("title")
    val _title : String,

    @SerializedName("iconURL")
    val _iconURL : String?,

    @SerializedName("linkURL")
    val _linkURL : String?,
) : Header {
    override val title: String
        get() = _title

    override val iconURL: String?
        get() = _iconURL

    override val linkURL: String?
        get() = _linkURL
}