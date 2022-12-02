package com.musinsa.jth.domain.model.remote

interface Contents {
    val type : String
    val banners :  List<ContentsItem>?
    val goods :  List<ContentsItem>?
    val styles : List<ContentsItem>?
}