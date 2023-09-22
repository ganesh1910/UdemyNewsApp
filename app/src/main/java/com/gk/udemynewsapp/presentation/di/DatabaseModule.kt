package com.gk.udemynewsapp.presentation.di

import android.content.Context
import androidx.room.Room
import com.gk.udemynewsapp.constant.Constants.DATABASE_NAME
import com.gk.udemynewsapp.data.db.ArticlesDAO
import com.gk.udemynewsapp.data.db.ArticlesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideArticleDatabaseInstance(@ApplicationContext context: Context):ArticlesDatabase{
        return Room.databaseBuilder(context, ArticlesDatabase::class.java,DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideArticleDAO(database: ArticlesDatabase): ArticlesDAO{
        return database.getArticlesDAO()
    }
}