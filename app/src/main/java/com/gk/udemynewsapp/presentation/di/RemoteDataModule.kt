package com.gk.udemynewsapp.presentation.di

import com.gk.udemynewsapp.data.api.NewsApiService
import com.gk.udemynewsapp.data.repositpory.dataSource.NewsRemoteDataSource
import com.gk.udemynewsapp.data.repositpory.dataSourceImpl.NewsRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataModule {

    @Singleton
    @Provides
    fun provideNewsRemoteDataSource(newsApiService: NewsApiService): NewsRemoteDataSource {
        return NewsRemoteDataSourceImpl(newsApiService = newsApiService)
    }
}