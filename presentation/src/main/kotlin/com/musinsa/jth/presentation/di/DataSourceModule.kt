package com.musinsa.jth.presentation.di

import com.musinsa.jth.data.datasource.ContentsLocalSource
import com.musinsa.jth.data.datasource.ContentsLocalSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.musinsa.jth.data.datasource.ContentsRemoteSource
import com.musinsa.jth.data.datasource.ContentsRemoteSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Singleton
    @Binds
    abstract fun bindContentsRemoteSource(source: ContentsRemoteSourceImpl): ContentsRemoteSource

    @Singleton
    @Binds
    abstract fun bindContentsLocalSource(source: ContentsLocalSourceImpl): ContentsLocalSource
}