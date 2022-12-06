package com.musinsa.jth.domain.model.remote

interface ContentsItem {
    val linkURL: String
    val thumbnailURL: String
    val brandName: String?
    val price: Int?
    val saleRate: Int?
    val hasCoupon: Boolean?
    val title: String?
    val description: String?
    val keyword: String?
}