package com.jonathan.kawanuaapp.ui.register

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jonathan.kawanuaapp.data.repository.UserRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException

class RegisterViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _response = MutableLiveData<String>()
    val response: LiveData<String> = _response

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun register(name: String, email: String, password: String, confPass: String) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val registerResponse = userRepository.register(name, email, password, confPass)
                _response.value = registerResponse.msg.toString()
                Log.d(TAG, registerResponse.msg.toString())
                _isLoading.value = false
            } catch (e: HttpException) {
                _response.value = "Gagal mendaftar"
                Log.d(TAG, e.message.toString())
                _isLoading.value = false
            }
        }
    }

    companion object {
        const val TAG = "SignupViewModel"
    }
}
