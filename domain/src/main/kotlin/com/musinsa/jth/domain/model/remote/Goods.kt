package com.musinsa.jth.domain.model.remote

interface Goods {
    val linkURL : String
    val thumbnailURL : String
    val brandName : String
    val price : Int
    val saleRate : Int
    val hasCoupon : Boolean
}