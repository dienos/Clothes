package com.musinsa.jth.domain.model.remote

interface DataItem {
    val header: Header?
    val contents: Contents
    val footer: Footer?
}