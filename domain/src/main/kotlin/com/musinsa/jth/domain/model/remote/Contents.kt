package com.musinsa.jth.domain.model.remote

interface Contents {
    val type : String
    val banners :  List<Banner>?
    val goods :  List<Goods>?
    val styles : List<Style>?
}