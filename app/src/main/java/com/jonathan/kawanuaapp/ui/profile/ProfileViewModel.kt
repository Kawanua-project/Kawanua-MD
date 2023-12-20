package com.jonathan.kawanuaapp.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.jonathan.kawanuaapp.data.pref.ThemePreference
import com.jonathan.kawanuaapp.data.repository.UserRepository
import kotlinx.coroutines.launch

class ProfileViewModel(private val pref: ThemePreference, val repository: UserRepository) : ViewModel() {

    fun getThemeSettings(): LiveData<Boolean> {
        return pref.getThemeSetting().asLiveData()
    }

    fun saveThemeSetting(isDarkModeActive: Boolean) {
        viewModelScope.launch {
            pref.saveThemeSetting(isDarkModeActive)
        }
    }
    
    fun logout() {
        viewModelScope.launch {
            repository.logout()
        }
    }
}