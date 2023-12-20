package com.jonathan.kawanuaapp.ui.detailspesies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jonathan.kawanuaapp.data.retrofit.response.Data

class DetailSpesiesViewModel : ViewModel() {
    private val _selectedNews = MutableLiveData<Data>()
    val selectedNews: LiveData<Data>
        get() = _selectedNews

    fun setSelectedNews(news: Data) {
        _selectedNews.value = news
    }
}