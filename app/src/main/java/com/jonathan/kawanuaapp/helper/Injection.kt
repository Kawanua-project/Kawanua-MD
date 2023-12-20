package com.jonathan.kawanuaapp.helper

import android.content.Context
import com.jonathan.kawanuaapp.data.pref.ThemePreference
import com.jonathan.kawanuaapp.data.repository.NewsRepository
import com.jonathan.kawanuaapp.data.repository.UserRepository
import com.jonathan.kawanuaapp.data.pref.UserPreference
import com.jonathan.kawanuaapp.data.pref.themeDataStore
import com.jonathan.kawanuaapp.data.pref.userDataStore
import com.jonathan.kawanuaapp.data.retrofit.api.ApiConfig
import com.jonathan.kawanuaapp.data.retrofit.api.ApiPredictConfig
import com.jonathan.kawanuaapp.data.retrofit.api.NewsApiConfig
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

object Injection {
    fun provideRepository(context: Context): UserRepository {
        val pref = UserPreference.getInstance(context.userDataStore)
        val user = runBlocking { pref.getSession().first() }
        val apiService = ApiConfig.getApiService(user.token)
        val apiPredictService = ApiPredictConfig.getApiService(user.token)
        return UserRepository.getInstance(pref, apiService, apiPredictService)
    }

    fun provideNewsRepository(context: Context): NewsRepository {
        val newsApiService = NewsApiConfig.getApiService()
        return NewsRepository.getInstance(newsApiService)
    }

    fun providePreference(context: Context): UserPreference {
        return UserPreference.getInstance(context.userDataStore)
    }

    fun provideTheme(context: Context): ThemePreference {
        return ThemePreference.getInstance(context.themeDataStore)
    }
}