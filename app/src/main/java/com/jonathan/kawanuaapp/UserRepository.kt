package com.jonathan.kawanuaapp

import com.jonathan.kawanuaapp.data.model.UserLogin
import com.jonathan.kawanuaapp.data.model.UserRegister
import com.jonathan.kawanuaapp.data.pref.UserModel
import com.jonathan.kawanuaapp.data.pref.UserPreference
import com.jonathan.kawanuaapp.data.retrofit.api.ApiService
import com.jonathan.kawanuaapp.data.retrofit.response.LoginResponse
import com.jonathan.kawanuaapp.data.retrofit.response.RegisterResponse
import com.jonathan.kawanuaapp.data.retrofit.response.Response
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

    suspend fun register(name: String, email: String, password: String, confPass: String): RegisterResponse {
        val userRegister = UserRegister(name, email, password, confPass)
        return apiService.register(userRegister)
    }

    suspend fun login(email: String, password: String): LoginResponse {
        val userLogin = UserLogin(email, password)
        return apiService.login(userLogin)
    }
//        emit(Result.Loading)
//        try {
//            val response = apiService.login(email, password)
//            emit(Result.Success(response))
//        } catch (e: Exception) {
//            emit(Result.Error(e))
//        }

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