package com.gk.udemynewsapp.presentation.di

import com.gk.udemynewsapp.domain.usecase.DeleteSavedNewsUseCase
import com.gk.udemynewsapp.domain.usecase.GetNewsHeadlineUseCase
import com.gk.udemynewsapp.domain.usecase.GetSavedNewsUseCase
import com.gk.udemynewsapp.domain.usecase.GetSearchedNewsUseCase
import com.gk.udemynewsapp.domain.usecase.SaveNewsUseCase
import com.gk.udemynewsapp.presentation.viewmodel.NewsViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {

    @Singleton
    @Provides
    fun provideNewsViewModelFactory(
        getNewsHeadlineUseCase: GetNewsHeadlineUseCase,
        getSearchedNewsUseCase: GetSearchedNewsUseCase,
        saveNewsUseCase: SaveNewsUseCase,
        getSavedNewsUseCase: GetSavedNewsUseCase,
        deleteSavedNewsUseCase: DeleteSavedNewsUseCase
    ): NewsViewModelFactory {
        return NewsViewModelFactory(
            getNewsHeadlineUseCase = getNewsHeadlineUseCase,
            getSearchedNewsUseCase = getSearchedNewsUseCase,
            saveNewsUseCase = saveNewsUseCase,
            getSavedNewsUseCase = getSavedNewsUseCase,
            deleteSavedNewsUseCase = deleteSavedNewsUseCase
        )
    }
}