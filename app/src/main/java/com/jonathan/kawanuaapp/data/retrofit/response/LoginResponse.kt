package com.jonathan.kawanuaapp.data.retrofit.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(

    @field:SerializedName("msg")
    val message: String? = null,

    @field:SerializedName("accessToken")
    val token: String? = null
)