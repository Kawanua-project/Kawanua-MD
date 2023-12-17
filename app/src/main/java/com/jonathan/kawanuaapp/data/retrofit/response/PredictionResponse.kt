package com.jonathan.kawanuaapp.data.retrofit.response

import com.google.gson.annotations.SerializedName

data class PredictionResponse(

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("status")
	val status: Status? = null
)

data class Data(

	@field:SerializedName("result")
	val result: Result? = null,

	@field:SerializedName("endangered_prediction")
	val endangeredPrediction: String? = null,

	@field:SerializedName("image_url")
	val imageUrl: String? = null,

	@field:SerializedName("confidence")
	val confidence: Any? = null
)

data class Result(

	@field:SerializedName("habitat")
	val habitat: String? = null,

	@field:SerializedName("phylum")
	val phylum: String? = null,

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("species")
	val species: String? = null,

	@field:SerializedName("deskripsi")
	val deskripsi: String? = null,

	@field:SerializedName("tingkat_kelangkaan")
	val tingkatKelangkaan: String? = null,

	@field:SerializedName("family")
	val family: String? = null,

	@field:SerializedName("class")
	val jsonMemberClass: String? = null,

	@field:SerializedName("kingdom")
	val kingdom: String? = null,

	@field:SerializedName("order")
	val order: String? = null
)

data class Status(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("message")
	val message: String? = null
)
