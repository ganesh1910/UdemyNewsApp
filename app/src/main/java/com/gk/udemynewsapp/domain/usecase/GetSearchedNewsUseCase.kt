package com.gk.udemynewsapp.domain.usecase

import com.gk.udemynewsapp.data.model.APIResponse
import com.gk.udemynewsapp.data.util.Resource
import com.gk.udemynewsapp.domain.repository.NewsRepository

class GetSearchedNewsUseCase(
    private val newsRepository: NewsRepository
) {
    suspend fun invoke(country:String,searchQuery:String,page:Int):Resource<APIResponse>{
        return newsRepository.getSearchedNews(country = country, searchQuery = searchQuery, page = page)
    }
}