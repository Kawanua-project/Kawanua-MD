package com.jonathan.kawanuaapp.data.retrofit.api

import com.jonathan.kawanuaapp.data.model.UserLogin
import com.jonathan.kawanuaapp.data.model.UserRegister
import com.jonathan.kawanuaapp.data.retrofit.response.LoginResponse
import com.jonathan.kawanuaapp.data.retrofit.response.RegisterResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {

    @Headers("Content-Type: application/json")
    @POST("register")
    suspend fun register(
        @Body request: UserRegister
    ): RegisterResponse

    @Headers("Content-Type: application/json")
    @POST("login")
    suspend fun login(
        @Body request: UserLogin
    ): LoginResponse


}