package com.jonathan.kawanuaapp

import com.jonathan.kawanuaapp.data.retrofit.api.NewsApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NewsRepository (
    private val apiService: NewsApiService
) {

    suspend fun getNews(): List<ArticlesItem> {
        return withContext(Dispatchers.IO) {
            val response = apiService.searchNews("wildlife", "6c3f261ad3cb4746afa01b50fd3e092a")
            response.articles
        }
    }
//    fun getStories(): LiveData<PagingData<ListStoryItem>> {
//        val user = runBlocking { userPreference.getSession().first() }
//        val apiService = ApiConfig.getApiService(user.token)
//
//        return Pager(
//            config = PagingConfig(
//                pageSize = 5
//            ),
//            pagingSourceFactory = {
//                StoryPagingSource(apiService)
//            }
//        ).liveData
//    }

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