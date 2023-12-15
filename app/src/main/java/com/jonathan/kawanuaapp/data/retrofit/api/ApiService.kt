package com.jonathan.kawanuaapp.data.retrofit.api

import com.jonathan.kawanuaapp.data.model.UserRegister
import com.jonathan.kawanuaapp.data.retrofit.response.RegisterResponse
import com.jonathan.kawanuaapp.data.retrofit.response.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {

    @Headers("Content-Type: application/json")
    @POST("register")
    suspend fun register(
        @Body request: UserRegister
    ): RegisterResponse


}