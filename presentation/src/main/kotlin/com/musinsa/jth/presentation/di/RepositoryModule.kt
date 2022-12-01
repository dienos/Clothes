package com.musinsa.jth.presentation.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.musinsa.jth.data.repository.ContentsRepositoryImpl
import com.musinsa.jth.domain.repository.remote.ContentsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindContentsRepository(repository: ContentsRepositoryImpl): ContentsRepository
}