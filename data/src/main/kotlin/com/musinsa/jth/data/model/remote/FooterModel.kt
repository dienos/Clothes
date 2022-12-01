package com.musinsa.jth.data.model.remote

import com.google.gson.annotations.SerializedName
import com.musinsa.jth.domain.model.remote.Footer

data class FooterModel (
    @SerializedName("type")
    val _type : String,

    @SerializedName("title")
    val _title : String,

    @SerializedName("iconURL")
    val _iconURL : String?
) : Footer {
    override val iconURL: String?
        get() = _iconURL
    override val title: String
        get() = _title
    override val type: String
        get() = _type
}