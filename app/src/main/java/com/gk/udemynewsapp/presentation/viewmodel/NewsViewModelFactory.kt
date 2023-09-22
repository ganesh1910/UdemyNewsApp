package com.gk.udemynewsapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gk.udemynewsapp.domain.usecase.DeleteSavedNewsUseCase
import com.gk.udemynewsapp.domain.usecase.GetNewsHeadlineUseCase
import com.gk.udemynewsapp.domain.usecase.GetSavedNewsUseCase
import com.gk.udemynewsapp.domain.usecase.GetSearchedNewsUseCase
import com.gk.udemynewsapp.domain.usecase.SaveNewsUseCase

class NewsViewModelFactory(
    val getNewsHeadlineUseCase: GetNewsHeadlineUseCase,
    val getSearchedNewsUseCase: GetSearchedNewsUseCase,
    private val saveNewsUseCase: SaveNewsUseCase,
    private val getSavedNewsUseCase: GetSavedNewsUseCase,
    private val deleteSavedNewsUseCase: DeleteSavedNewsUseCase
):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(
            getNewsHeadlineUseCase = getNewsHeadlineUseCase,
            getSearchedNewsUseCase = getSearchedNewsUseCase,
            savedNewsUseCase = saveNewsUseCase,
            getSaveNewsUseCase = getSavedNewsUseCase,
            deleteSavedNewsUseCase = deleteSavedNewsUseCase
        ) as T
    }
}