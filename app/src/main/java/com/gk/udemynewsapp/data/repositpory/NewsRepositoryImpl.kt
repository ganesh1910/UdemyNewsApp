package com.gk.udemynewsapp.data.repositpory

import com.gk.udemynewsapp.data.model.APIResponse
import com.gk.udemynewsapp.data.model.Article
import com.gk.udemynewsapp.data.repositpory.dataSource.NewsLocalDataSource
import com.gk.udemynewsapp.data.repositpory.dataSource.NewsRemoteDataSource
import com.gk.udemynewsapp.data.util.Resource
import com.gk.udemynewsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class NewsRepositoryImpl(
    private val newsRemoteDataSource: NewsRemoteDataSource,
    private val newsLocalDataSource: NewsLocalDataSource
) : NewsRepository {
    override suspend fun getNewsHeadlines(country: String, page: Int): Resource<APIResponse> {
        return responseToResource(
            newsRemoteDataSource.getTopHeadlines(
                country = country,
                page = page
            )
        )
    }

    override suspend fun getSearchedNews(
        country: String,
        searchQuery: String,
        page: Int
    ): Resource<APIResponse> {
        return responseToResource(
            newsRemoteDataSource.getSearchedNews(
                country = country,
                searchQuery = searchQuery,
                page = page
            )
        )

    }

    override suspend fun saveNews(article: Article): Long {
        return newsLocalDataSource.saveArticlesDB(article)
    }

    override suspend fun deleteNews(article: Article):Int {
        return newsLocalDataSource.deleteArticle(article = article)
    }

    override fun getSavedNews(): Flow<List<Article>> {
        return newsLocalDataSource.getAllSavedArticles()
    }

    private fun responseToResource(response: Response<APIResponse>): Resource<APIResponse> {
        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }
}