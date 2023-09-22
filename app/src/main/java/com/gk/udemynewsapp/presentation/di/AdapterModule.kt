package com.gk.udemynewsapp.presentation.di

import com.gk.udemynewsapp.presentation.NewsRecyclerAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AdapterModule {

    @Singleton
    @Provides
    fun provideNewsRecyclerAdapter(): NewsRecyclerAdapter {
        return NewsRecyclerAdapter()
    }
}