package com.gk.udemynewsapp.domain.usecase

import com.gk.udemynewsapp.data.model.Article
import com.gk.udemynewsapp.domain.repository.NewsRepository

class DeleteSavedNewsUseCase(
   private val repository: NewsRepository
) {
    suspend fun invoke(article: Article) = repository.deleteNews(article = article)
}