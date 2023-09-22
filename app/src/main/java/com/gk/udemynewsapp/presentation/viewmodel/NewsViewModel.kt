package com.gk.udemynewsapp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.gk.udemynewsapp.data.model.APIResponse
import com.gk.udemynewsapp.data.model.Article
import com.gk.udemynewsapp.data.util.Resource
import com.gk.udemynewsapp.domain.usecase.DeleteSavedNewsUseCase
import com.gk.udemynewsapp.domain.usecase.GetNewsHeadlineUseCase
import com.gk.udemynewsapp.domain.usecase.GetSavedNewsUseCase
import com.gk.udemynewsapp.domain.usecase.GetSearchedNewsUseCase
import com.gk.udemynewsapp.domain.usecase.SaveNewsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class NewsViewModel(
    private val getNewsHeadlineUseCase: GetNewsHeadlineUseCase,
    private val getSearchedNewsUseCase: GetSearchedNewsUseCase,
    private val savedNewsUseCase: SaveNewsUseCase,
    private val getSaveNewsUseCase: GetSavedNewsUseCase,
    private val deleteSavedNewsUseCase: DeleteSavedNewsUseCase
) : ViewModel() {
    val newsHeadlines: MutableLiveData<Resource<APIResponse>> = MutableLiveData()
    val searchNews: MutableLiveData<Resource<APIResponse>> = MutableLiveData()
    private var _isInserted: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isInserted: StateFlow<Boolean> = _isInserted

    private var _isDeleted: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isDeleted: StateFlow<Boolean> = _isDeleted


    fun getNewsHeadlines(country: String, page: Int) {
        viewModelScope.launch {
            newsHeadlines.postValue(Resource.Loading())
            try {
                val response = getNewsHeadlineUseCase.invoke(country = country, page = page)
                Log.d("Response", response.data.toString())
                newsHeadlines.postValue(response)
            } catch (e: Exception) {
                newsHeadlines.postValue(Resource.Error("Something went wrong...Please try again"))
            }
        }
    }

    fun searchNews(
        country: String,
        searchQuery: String,
        page: Int
    ) = viewModelScope.launch {
        searchNews.postValue(Resource.Loading())
        try {
            val response = getSearchedNewsUseCase.invoke(
                country = country,
                searchQuery = searchQuery,
                page = page
            )
            searchNews.postValue(response)
        } catch (e: Exception) {
            searchNews.postValue(Resource.Error("Something went wrong...Please try again"))
        }
    }

    fun saveArticleInDB(article: Article) {
        viewModelScope.launch {
            val result = savedNewsUseCase.invoke(article)
            Log.d("Result", result.toString())
            if (result > 0) {
                _isInserted.emit(true)
            } else {
                _isInserted.emit(false)
            }
        }
    }

    fun getAllNewsArticles() = liveData {
        getSaveNewsUseCase.invoke().collectLatest {
            emit(it)
        }
    }

    fun deleteNews(article: Article) {
        viewModelScope.launch {
            val count = deleteSavedNewsUseCase.invoke(article = article)
            if (count > 0) {
                _isDeleted.emit(true)
            } else {
                _isDeleted.emit(false)
            }
        }
    }


}