package com.gk.udemynewsapp.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gk.udemynewsapp.data.model.APIResponse
import com.gk.udemynewsapp.data.util.Resource
import com.gk.udemynewsapp.domain.usecase.GetNewsHeadlineUseCase
import kotlinx.coroutines.launch

class NewsViewModel(
    private val getNewsHeadlineUseCase: GetNewsHeadlineUseCase
): ViewModel() {
    val newsHeadlines: MutableLiveData<Resource<APIResponse>> = MutableLiveData()

    fun getNewsHeadlines(country:String,page:Int){
        viewModelScope.launch {
            newsHeadlines.postValue(Resource.Loading())
            try {
                val response = getNewsHeadlineUseCase.invoke(country = country, page = page)
                newsHeadlines.postValue(response)
            }catch (e:Exception){
                newsHeadlines.postValue(Resource.Error("Something went wrong...Please try again"))
            }
        }
    }
}