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

    private val _responseDescription = MutableLiveData<String>()
    val responseDescription: LiveData<String> = _responseDescription

    val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun uploadFile(image: File) {
        viewModelScope.launch {
            try {
                val response = userRepository.uploadImage(image)
                if(response.status?.code == 200) {
                    _responseDescription.value = response.data?.result?.deskripsi ?: ""
                    Log.d(TAG, "uploadFile: $_responseDescription")
                }
            } catch (e: Exception) {
                Log.d(TAG, "uploadFile: ${e.message}")
            }
        }
    }
    companion object{
        private const val TAG = "ScanViewModel"
    }
}