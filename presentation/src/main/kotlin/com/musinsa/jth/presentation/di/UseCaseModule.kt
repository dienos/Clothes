package com.musinsa.jth.presentation.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import com.musinsa.jth.domain.repository.remote.ContentsRepository
import com.musinsa.jth.domain.usecase.ConvertContentsListToMapUseCase
import com.musinsa.jth.domain.usecase.GetContentsUseCase

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun providesGetContentsUseCase(repository: ContentsRepository): GetContentsUseCase {
        return GetContentsUseCase(repository)
    }

    @Provides
    fun providesConvertContentsListToMapUseCase(repository: ContentsRepository): ConvertContentsListToMapUseCase {
        return ConvertContentsListToMapUseCase(repository)
    }
}