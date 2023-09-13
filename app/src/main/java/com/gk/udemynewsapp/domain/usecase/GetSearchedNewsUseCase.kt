package com.gk.udemynewsapp.domain.usecase

import com.gk.udemynewsapp.data.model.APIResponse
import com.gk.udemynewsapp.data.util.Resource
import com.gk.udemynewsapp.domain.repository.NewsRepository

class GetSearchedNewsUseCase(
    private val repository: NewsRepository
) {
    suspend fun invoke(searchQuery:String):Resource<APIResponse>{
        return repository.getSearchedNews(searchQuery = searchQuery)
    }
}