package com.jonathan.kawanuaapp

import com.jonathan.kawanuaapp.data.model.UserLogin
import com.jonathan.kawanuaapp.data.model.UserRegister
import com.jonathan.kawanuaapp.data.pref.UserModel
import com.jonathan.kawanuaapp.data.pref.UserPreference
import com.jonathan.kawanuaapp.data.retrofit.api.ApiPredictService
import com.jonathan.kawanuaapp.data.retrofit.api.ApiService
import com.jonathan.kawanuaapp.data.retrofit.response.LoginResponse
import com.jonathan.kawanuaapp.data.retrofit.response.PredictionResponse
import com.jonathan.kawanuaapp.data.retrofit.response.RegisterResponse
import com.jonathan.kawanuaapp.data.retrofit.response.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class UserRepository private constructor(
    private val userPreference: UserPreference,
    private val apiService: ApiService,
    private val apiPredictService: ApiPredictService
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

    suspend fun register(
        name: String,
        email: String,
        password: String,
        confPass: String
    ): RegisterResponse {
        val userRegister = UserRegister(name, email, password, confPass)
        return apiService.register(userRegister)
    }

    suspend fun login(email: String, password: String): LoginResponse {
        val userLogin = UserLogin(email, password)
        return apiService.login(userLogin)
    }

    suspend fun uploadImage(imageFile: File): PredictionResponse {
        val user = runBlocking { userPreference.getUser().first() }
        val token = user.token

        val requestImageFile = imageFile.asRequestBody("image/jpeg".toMediaType())
        val multipartBody = MultipartBody.Part.createFormData(
            "photo",
            imageFile.name,
            requestImageFile
        )
        return apiPredictService.uploadImage("Bearer $token", multipartBody)
    }

    companion object {
        @Volatile
        private var instance: UserRepository? = null
        fun getInstance(
            userPreference: UserPreference,
            apiService: ApiService,
            apiPredictService: ApiPredictService
        ): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(userPreference, apiService, apiPredictService)
            }.also { instance = it }
    }
}