package com.jonathan.kawanuaapp.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jonathan.kawanuaapp.data.retrofit.response.Response
import com.jonathan.kawanuaapp.Result
import com.jonathan.kawanuaapp.UserRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class RegisterViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _registrationStatus = MutableLiveData<Result<Response>>()
    val registrationStatus: LiveData<Result<Response>> = _registrationStatus

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun register(name: String, email: String, password: String, confPassword: String) {
        viewModelScope.launch {
            _registrationStatus.value = Result.Loading
            userRepository.register(name, email, password, confPassword)
                .onStart { _isLoading.value = true }
                .catch { e ->
                    _registrationStatus.value = Result.Error(e)
                    _isLoading.value = false
                }
                .collect { responseResult ->
                    _registrationStatus.value = responseResult
                    _isLoading.value = false
                }
        }
    }

}
