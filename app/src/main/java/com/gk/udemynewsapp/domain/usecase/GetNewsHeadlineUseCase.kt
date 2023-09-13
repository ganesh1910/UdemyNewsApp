package com.gk.udemynewsapp.domain.usecase

import com.gk.udemynewsapp.data.model.APIResponse
import com.gk.udemynewsapp.data.util.Resource
import com.gk.udemynewsapp.domain.repository.NewsRepository

class GetNewsHeadlineUseCase(
     private val newsRepository: NewsRepository
) {
    suspend fun invoke(country: String, page: Int): Resource<APIResponse> {
        return newsRepository.getNewsHeadlines(country = country, page = page)
    }
}