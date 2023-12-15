package com.jonathan.kawanuaapp

import android.content.Context
import com.jonathan.kawanuaapp.data.pref.UserPreference
import com.jonathan.kawanuaapp.data.pref.dataStore
import com.jonathan.kawanuaapp.data.retrofit.api.ApiConfig
import com.jonathan.kawanuaapp.data.retrofit.api.NewsApiConfig
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

object Injection {
    fun provideRepository(context: Context): UserRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        val user = runBlocking { pref.getSession().first() }
        val apiService = ApiConfig.getApiService(user.token)
        return UserRepository.getInstance(pref, apiService)
    }

    fun provideNewsRepository(context: Context): NewsRepository {
        val newsApiService = NewsApiConfig.getApiService()
        return NewsRepository.getInstance(newsApiService)
    }
}