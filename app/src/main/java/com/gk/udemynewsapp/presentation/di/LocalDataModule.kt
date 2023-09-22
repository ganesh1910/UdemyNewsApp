package com.gk.udemynewsapp.presentation.di

import com.gk.udemynewsapp.data.db.ArticlesDAO
import com.gk.udemynewsapp.data.repositpory.dataSource.NewsLocalDataSource
import com.gk.udemynewsapp.data.repositpory.dataSourceImpl.NewsLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {

    @Singleton
    @Provides
    fun provideLocalDataSource(articlesDAO: ArticlesDAO):NewsLocalDataSource{
        return NewsLocalDataSourceImpl(articlesDAO = articlesDAO)
    }
}