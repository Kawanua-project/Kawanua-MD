package com.jonathan.kawanuaapp.data.retrofit.response

import com.google.gson.annotations.SerializedName

data class Response(

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("token")
	val token: String? = null
)
