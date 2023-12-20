package com.jonathan.kawanuaapp.data.repository

import com.jonathan.kawanuaapp.BuildConfig
import com.jonathan.kawanuaapp.data.retrofit.api.NewsApiService
import com.jonathan.kawanuaapp.data.retrofit.response.ArticlesItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NewsRepository (
    private val apiService: NewsApiService
) {

    suspend fun getNews(): List<ArticlesItem> {
        return withContext(Dispatchers.IO) {
            val response = apiService.searchNews("wildlife", BuildConfig.API_KEY)
            response.articles
        }
    }

    companion object {
        @Volatile
        private var instance: NewsRepository? = null
        fun getInstance(
            apiService: NewsApiService
        ): NewsRepository =
            instance ?: synchronized(this) {
                instance ?: NewsRepository(apiService)
            }.also { instance = it }
    }
}