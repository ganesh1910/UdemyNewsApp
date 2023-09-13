package com.gk.udemynewsapp.presentation.di

import com.gk.udemynewsapp.domain.repository.NewsRepository
import com.gk.udemynewsapp.domain.usecase.GetNewsHeadlineUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModel {

    @Singleton
    @Provides
    fun provideGetNewsHeadlineUseCase(
        newsRepository: NewsRepository
    ): GetNewsHeadlineUseCase {
        return GetNewsHeadlineUseCase(newsRepository = newsRepository)
    }
}