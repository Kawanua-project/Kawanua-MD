package com.jonathan.kawanuaapp.ui.scan

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jonathan.kawanuaapp.UserRepository
import com.jonathan.kawanuaapp.data.retrofit.response.PredictionResponse
import kotlinx.coroutines.launch
import java.io.File

class ScanViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _predictionResponse = MutableLiveData<PredictionResponse>()
    val predictionResponse: LiveData<PredictionResponse>
        get() = _predictionResponse

    val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading


    fun uploadImage(file: File) = userRepository.uploadImage(file)

    /*fun uploadFile(image: File) {
        viewModelScope.launch {
            try {
                val response = userRepository.uploadImage(image)
                _predictionResponse.value = response
                Log.d(TAG, "uploadFile: ${response.status?.message}")
            } catch(e: Exception) {
                Log.d(TAG, "uploadFileError: ${e.message}")
            }
        }
    }*/

    companion object {
        const val TAG = "ScanViewModel"
    }
}