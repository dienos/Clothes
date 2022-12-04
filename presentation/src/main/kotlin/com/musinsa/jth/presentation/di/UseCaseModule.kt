package com.musinsa.jth.presentation.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import com.musinsa.jth.domain.repository.remote.ContentsRepository
import com.musinsa.jth.domain.usecase.*

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

    @Provides
    fun providesGetContentsMapUseCase(repository: ContentsRepository): GetContentsByPageNumberUseCase {
        return GetContentsByPageNumberUseCase(repository)
    }

    @Provides
    fun providesGetContentsByTypeUseCase(repository: ContentsRepository): GetContentsByTypeUseCase {
        return GetContentsByTypeUseCase(repository)
    }

    @Provides
    fun providesInitContentsMapUseCase(repository: ContentsRepository): GetFirstContentsItemListMapUseCase {
        return GetFirstContentsItemListMapUseCase(repository)
    }

    @Provides
    fun providesGetNextContentsItemListMapUseCase(repository: ContentsRepository): GetNextContentsItemListMapUseCase {
        return GetNextContentsItemListMapUseCase(repository)
    }

    @Provides
    fun providesGetNextContentsItemListUseCase(repository: ContentsRepository): GetNextContentsItemListUseCase {
        return GetNextContentsItemListUseCase(repository)
    }

}