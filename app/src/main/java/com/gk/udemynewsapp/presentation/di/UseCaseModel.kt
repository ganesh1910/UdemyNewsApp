package com.gk.udemynewsapp.presentation.di

import com.gk.udemynewsapp.domain.repository.NewsRepository
import com.gk.udemynewsapp.domain.usecase.DeleteSavedNewsUseCase
import com.gk.udemynewsapp.domain.usecase.GetNewsHeadlineUseCase
import com.gk.udemynewsapp.domain.usecase.GetSavedNewsUseCase
import com.gk.udemynewsapp.domain.usecase.GetSearchedNewsUseCase
import com.gk.udemynewsapp.domain.usecase.SaveNewsUseCase
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

    @Singleton
    @Provides
    fun provideGetSearchNewsHeadlineUseCase(
        newsRepository: NewsRepository
    ): GetSearchedNewsUseCase {
        return GetSearchedNewsUseCase(newsRepository = newsRepository)
    }

    @Singleton
    @Provides
    fun provideSaveNewsUseCase(
        newsRepository: NewsRepository
    ): SaveNewsUseCase {
        return SaveNewsUseCase(repository = newsRepository)
    }

    @Singleton
    @Provides
    fun provideGetSavedNewsUseCase(
        newsRepository: NewsRepository
    ): GetSavedNewsUseCase {
        return GetSavedNewsUseCase(repository = newsRepository)
    }

    @Singleton
    @Provides
    fun provideDeleteNewUseCase(
        newsRepository: NewsRepository
    ): DeleteSavedNewsUseCase {
        return DeleteSavedNewsUseCase(repository = newsRepository)
    }
}