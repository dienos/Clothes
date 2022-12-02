package com.musinsa.jth.presentation.di

import com.musinsa.jth.data.datasource.local.ContentsLocalSource
import com.musinsa.jth.data.datasource.local.ContentsLocalSourceImpl
import com.musinsa.jth.data.datasource.remote.ContentsRemoteSource
import com.musinsa.jth.data.datasource.remote.ContentsRemoteSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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