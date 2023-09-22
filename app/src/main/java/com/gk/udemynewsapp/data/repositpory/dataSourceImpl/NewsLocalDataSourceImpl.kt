package com.gk.udemynewsapp.data.repositpory.dataSourceImpl

import com.gk.udemynewsapp.data.db.ArticlesDAO
import com.gk.udemynewsapp.data.model.Article
import com.gk.udemynewsapp.data.repositpory.dataSource.NewsLocalDataSource
import kotlinx.coroutines.flow.Flow

class NewsLocalDataSourceImpl(
    private val articlesDAO: ArticlesDAO
) : NewsLocalDataSource {
    override suspend fun saveArticlesDB(article: Article): Long {
        return articlesDAO.insertArticles(article)
    }

    override suspend fun deleteArticle(article: Article): Int {
        return articlesDAO.deleteArticle(article = article)
    }

    override fun getAllSavedArticles(): Flow<List<Article>> {
        return articlesDAO.getAllSavedArticles()
    }
}