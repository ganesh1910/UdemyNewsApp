package com.gk.udemynewsapp.data.repositpory

import com.gk.udemynewsapp.data.model.APIResponse
import com.gk.udemynewsapp.data.model.Article
import com.gk.udemynewsapp.data.repositpory.dataSource.NewsRemoteDataSource
import com.gk.udemynewsapp.data.util.Resource
import com.gk.udemynewsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl(
    private val newsRemoteDataSource: NewsRemoteDataSource
) : NewsRepository {
    override suspend fun getNewsHeadlines(country:String, page:Int): Resource<APIResponse> {
        val apiResponse = newsRemoteDataSource.getTopHeadlines(country = country, page = page)
        if (apiResponse.isSuccessful) {
            apiResponse.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(apiResponse.message())
    }

    override suspend fun getSearchedNews(searchQuery: String): Resource<APIResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun saveNews(article: Article) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteNews(article: Article) {
        TODO("Not yet implemented")
    }

    override fun getSavedNews(): Flow<List<Article>> {
        TODO("Not yet implemented")
    }
}