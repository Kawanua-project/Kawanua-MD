package com.jonathan.kawanuaapp.data.retrofit.api

import com.jonathan.kawanuaapp.data.retrofit.response.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("everything")
    suspend fun searchNews(
        @Query("q") query: String,
        @Query("apiKey") apiKey: String
    ): NewsResponse
}