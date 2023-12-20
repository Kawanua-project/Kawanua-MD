package com.jonathan.kawanuaapp.ui.listnews

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jonathan.kawanuaapp.data.retrofit.response.ArticlesItem
import com.jonathan.kawanuaapp.data.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListBeritaViewModel(private val repository: NewsRepository) : ViewModel() {
    private val _news = MutableLiveData<List<ArticlesItem>>()
    val news: MutableLiveData<List<ArticlesItem>> = _news

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getNews() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _isLoading.postValue(true)

                val result = repository.getNews()
                withContext(Dispatchers.Main) {
                    _news.value = result
                }
            } finally {
                _isLoading.postValue(false)
            }
        }
    }
}