package com.jonathan.kawanuaapp.ui.scan

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jonathan.kawanuaapp.data.repository.UserRepository
import com.jonathan.kawanuaapp.data.retrofit.response.PredictionResponse
import java.io.File

class ScanViewModel(private val userRepository: UserRepository) : ViewModel() {

    fun uploadImage(file: File) = userRepository.uploadImage(file)

    companion object {
        const val TAG = "ScanViewModel"
    }
}