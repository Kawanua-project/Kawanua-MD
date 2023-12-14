package com.jonathan.kawanuaapp.ui.listnews

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jonathan.kawanuaapp.ArticlesItem
import com.jonathan.kawanuaapp.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListBeritaViewModel(private val repository: NewsRepository) : ViewModel() {
    private val _news = MutableLiveData<List<ArticlesItem>>()
    val news: MutableLiveData<List<ArticlesItem>> = _news

    fun getNews() {
        GlobalScope.launch(Dispatchers.IO) {
            val result = repository.getNews()
            withContext(Dispatchers.Main) {
                _news.value = result
            }
        }
    }
}