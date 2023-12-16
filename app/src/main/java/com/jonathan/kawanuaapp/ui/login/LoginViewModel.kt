package com.jonathan.kawanuaapp.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.jonathan.kawanuaapp.UserRepository
import com.jonathan.kawanuaapp.data.pref.UserModel
import com.jonathan.kawanuaapp.data.retrofit.response.LoginResponse
import kotlinx.coroutines.launch
import retrofit2.HttpException

class LoginViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _response = MutableLiveData<String>()
    val response: LiveData<String> = _response

    private val _token = MutableLiveData<LoginResponse>()
    val token: LiveData<LoginResponse> = _token

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val loginResponse = userRepository.login(email, password)
                _response.value = loginResponse.message.toString()
                _token.postValue(loginResponse)
                Log.d(TAG, loginResponse.message.toString())
                _isLoading.value = false
            } catch (e: HttpException) {
                _response.value = "Gagal mendaftar"
                Log.d(TAG, e.message.toString())
                _isLoading.value = false
            }
        }
    }

    fun saveSession(user: UserModel) {
        viewModelScope.launch {
            userRepository.saveSession(user)
        }
    }

    companion object {
        const val TAG = "LoginViewModel"
    }
}