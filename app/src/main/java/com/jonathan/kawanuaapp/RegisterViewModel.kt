package com.jonathan.kawanuaapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class RegisterViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _registrationStatus = MutableLiveData<Result<Response>>()
    val registrationStatus: LiveData<Result<Response>> = _registrationStatus

    fun register(name: String, email: String, password: String) {
        viewModelScope.launch {
            userRepository.register(name, email, password)
                .onStart { _registrationStatus.value = Result.Loading }
                .catch { e -> _registrationStatus.value = Result.Error(e) }
                .collect { responseResult ->
                    _registrationStatus.value = responseResult
                }
        }
    }
}
