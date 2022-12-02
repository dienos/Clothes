package com.musinsa.jth.data.model.remote

import com.google.gson.annotations.SerializedName
import com.musinsa.jth.domain.model.remote.ContentsItem

data class ContentsItemModel (
    @SerializedName("linkURL")
    val _linkURL : String,

    @SerializedName("thumbnailURL")
    val _thumbnailURL : String,

    @SerializedName("brandName")
    val _brandName : String?,

    @SerializedName("price")
    val _price : Int?,

    @SerializedName("saleRate")
    val _saleRate : Int?,

    @SerializedName("hasCoupon")
    val _hasCoupon : Boolean?,

    @SerializedName("title")
    val _title : String?,

    @SerializedName("_description")
    val _description : String?,

    @SerializedName("keyword")
    val _keyword : String?
) : ContentsItem {
    override val brandName: String?
        get() = _brandName
    override val hasCoupon: Boolean?
        get() = _hasCoupon
    override val linkURL: String
        get() =_linkURL
    override val price: Int?
        get() = _price
    override val saleRate: Int?
        get() = _saleRate
    override val thumbnailURL: String
        get() = _thumbnailURL
    override val description: String?
        get() = _description
    override val keyword: String?
        get() = _keyword
    override val title: String?
        get() = _title
}