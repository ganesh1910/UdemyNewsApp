package com.gk.udemynewsapp.domain.usecase

import com.gk.udemynewsapp.data.model.Article
import com.gk.udemynewsapp.domain.repository.NewsRepository

class SaveNewsUseCase(
    private val repository: NewsRepository
) {
    suspend fun invoke(article: Article):Long = repository.saveNews(article = article)
}