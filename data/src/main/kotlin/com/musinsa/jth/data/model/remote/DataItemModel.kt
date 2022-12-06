package com.musinsa.jth.data.model.remote

import com.google.gson.annotations.SerializedName
import com.musinsa.jth.domain.model.remote.Contents
import com.musinsa.jth.domain.model.remote.DataItem
import com.musinsa.jth.domain.model.remote.Footer
import com.musinsa.jth.domain.model.remote.Header

data class DataItemModel(
    @SerializedName("header")
    val _header: HeaderModel?,

    @SerializedName("contents")
    val _contents: ContentsModel,

    @SerializedName("footer")
    val _footer: FooterModel?,
) : DataItem {
    override val contents: Contents
        get() = _contents
    override val footer: Footer?
        get() = _footer
    override val header: Header?
        get() = _header
}