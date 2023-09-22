package com.gk.udemynewsapp.data.repositpory.dataSource

import com.gk.udemynewsapp.data.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsLocalDataSource {
    suspend fun saveArticlesDB(article: Article): Long

    suspend fun deleteArticle(article: Article): Int
    fun getAllSavedArticles(): Flow<List<Article>>
}