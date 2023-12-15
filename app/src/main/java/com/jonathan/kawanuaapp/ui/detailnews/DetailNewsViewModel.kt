package com.jonathan.kawanuaapp.ui.detailnews

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jonathan.kawanuaapp.ArticlesItem

class DetailNewsViewModel : ViewModel() {
    private val _selectedNews = MutableLiveData<ArticlesItem>()
    val selectedNews: LiveData<ArticlesItem>
        get() = _selectedNews

    fun setSelectedNews(news: ArticlesItem) {
        _selectedNews.value = news
    }
}