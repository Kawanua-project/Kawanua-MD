package com.jonathan.kawanuaapp.data.repository

import androidx.lifecycle.liveData
import com.google.gson.Gson
import com.jonathan.kawanuaapp.data.model.UserLogin
import com.jonathan.kawanuaapp.data.model.UserRegister
import com.jonathan.kawanuaapp.data.pref.UserModel
import com.jonathan.kawanuaapp.data.pref.UserPreference
import com.jonathan.kawanuaapp.data.retrofit.api.ApiPredictService
import com.jonathan.kawanuaapp.data.retrofit.api.ApiService
import com.jonathan.kawanuaapp.data.retrofit.response.LoginResponse
import com.jonathan.kawanuaapp.data.retrofit.response.RegisterResponse
import com.jonathan.kawanuaapp.data.retrofit.response.Status
import com.jonathan.kawanuaapp.helper.Result
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
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

    fun uploadImage(imageFile: File) = liveData {
        emit(Result.Loading)
        val requestImageFile = imageFile.asRequestBody("image/jpeg".toMediaType())
        val multipartBody = MultipartBody.Part.createFormData(
            "image",
            imageFile.name,
            requestImageFile
        )
        try {
            val successResponse = apiPredictService.uploadImage(multipartBody)
            emit(Result.Success(successResponse))
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, Status::class.java)
            emit(Result.Error(errorResponse?.message.toString()))
        }

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