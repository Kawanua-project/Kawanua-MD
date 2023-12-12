package com.jonathan.kawanuaapp

import com.jonathan.kawanuaapp.pref.UserModel
import com.jonathan.kawanuaapp.pref.UserPreference
import com.jonathan.kawanuaapp.retrofit.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserRepository private constructor(
    private val userPreference: UserPreference,
    private val apiService: ApiService
) {
    suspend fun saveSession(user: UserModel) {
        userPreference.saveSession(user)
    }

    fun getSession(): Flow<UserModel> {
        return userPreference.getSession()
    }

    suspend fun logout() {
        userPreference.logout()
    }

    suspend fun register(name: String, email: String, password: String): Flow<Result<Response>> = flow {
        emit(Result.Loading)
        try {
            val response = apiService.register(email, password)
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }

//    suspend fun login(email: String, password: String): Flow<Result<LoginResponse>> = flow {
//        emit(Result.Loading)
//        try {
//            val response = apiService.login(email, password)
//            emit(Result.Success(response))
//        } catch (e: Exception) {
//            emit(Result.Error(e))
//        }
//    }

    companion object {
        @Volatile
        private var instance: UserRepository? = null
        fun getInstance(
            userPreference: UserPreference,
            apiService: ApiService
        ): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(userPreference, apiService)
            }.also { instance = it }
    }
}