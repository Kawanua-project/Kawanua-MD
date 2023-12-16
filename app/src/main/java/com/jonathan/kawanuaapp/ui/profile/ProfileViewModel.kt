package com.jonathan.kawanuaapp.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jonathan.kawanuaapp.UserRepository
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: UserRepository) : ViewModel() {
    fun logout() {
        viewModelScope.launch {
            repository.logout()
        }
    }
}

