package com.gk.udemynewsapp.data.repositpory.dataSourceImpl

import com.gk.udemynewsapp.data.api.NewsApiService
import com.gk.udemynewsapp.data.model.APIResponse
import com.gk.udemynewsapp.data.repositpory.dataSource.NewsRemoteDataSource
import retrofit2.Response

class NewsRemoteDataSourceImpl(
    private val newsApiService: NewsApiService
) : NewsRemoteDataSource {
    override suspend fun getTopHeadlines(country: String,page: Int): Response<APIResponse> {
        return newsApiService.getTopHeadlines(country, page)
    }
}