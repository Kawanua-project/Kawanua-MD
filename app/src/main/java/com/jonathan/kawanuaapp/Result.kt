package com.jonathan.kawanuaapp

import com.jonathan.kawanuaapp.data.retrofit.response.Status

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Status?) : Result<Nothing>()
    data object Loading : Result<Nothing>()
}