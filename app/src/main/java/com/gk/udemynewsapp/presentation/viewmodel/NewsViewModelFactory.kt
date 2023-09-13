package com.gk.udemynewsapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gk.udemynewsapp.domain.usecase.GetNewsHeadlineUseCase

class NewsViewModelFactory(
    val getNewsHeadlineUseCase: GetNewsHeadlineUseCase
):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(
            getNewsHeadlineUseCase
        ) as T
    }
}