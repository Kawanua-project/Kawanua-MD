package com.jonathan.kawanuaapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jonathan.kawanuaapp.ArticlesItem
import com.jonathan.kawanuaapp.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(private val repository: NewsRepository) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    private val _stories = MutableLiveData<List<ArticlesItem>>()
    val stories: LiveData<List<ArticlesItem>> get() = _stories

    fun getNews() {
        GlobalScope.launch(Dispatchers.IO) {
            val result = repository.getNews()
            withContext(Dispatchers.Main) {
                _stories.value = result
            }
        }
    }
}