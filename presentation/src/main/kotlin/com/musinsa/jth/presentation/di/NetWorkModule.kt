package com.musinsa.jth.presentation.di

import com.musinsa.jth.presentation.utils.NetworkUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class NetWorkModule {

    @Provides
    fun providesMetWorkUtil(): NetworkUtil {
        return NetworkUtil()
    }
}