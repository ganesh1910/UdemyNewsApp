package com.gk.udemynewsapp.presentation.di

import com.gk.udemynewsapp.data.repositpory.NewsRepositoryImpl
import com.gk.udemynewsapp.data.repositpory.dataSource.NewsRemoteDataSource
import com.gk.udemynewsapp.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideNewsRepository(newsRemoteDataSource: NewsRemoteDataSource):NewsRepository{
        return NewsRepositoryImpl(newsRemoteDataSource = newsRemoteDataSource)
    }
}
