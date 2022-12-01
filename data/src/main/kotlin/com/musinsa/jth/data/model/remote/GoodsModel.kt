package com.musinsa.jth.data.model.remote

import com.google.gson.annotations.SerializedName
import com.musinsa.jth.domain.model.remote.Goods

data class GoodsModel (
    @SerializedName("linkURL")
    val _linkURL : String,

    @SerializedName("thumbnailURL")
    val _thumbnailURL : String,

    @SerializedName("brandName")
    val _brandName : String,

    @SerializedName("price")
    val _price : Int,

    @SerializedName("saleRate")
    val _saleRate : Int,

    @SerializedName("hasCoupon")
    val _hasCoupon : Boolean,
) : Goods {
    override val brandName: String
        get() = _brandName
    override val hasCoupon: Boolean
        get() = _hasCoupon
    override val linkURL: String
        get() =_linkURL
    override val price: Int
        get() = _price
    override val saleRate: Int
        get() = _saleRate
    override val thumbnailURL: String
        get() = _thumbnailURL
}