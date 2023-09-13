package com.gk.udemynewsapp.domain.usecase

import com.gk.udemynewsapp.data.model.Article
import com.gk.udemynewsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetSavedNewsUseCase(
    private val repository: NewsRepository
) {
    fun invoke(): Flow<List<Article>> {
        return repository.getSavedNews()
    }
}