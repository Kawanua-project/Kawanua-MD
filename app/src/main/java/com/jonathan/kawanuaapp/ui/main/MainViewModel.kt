package com.jonathan.kawanuaapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.jonathan.kawanuaapp.data.repository.UserRepository
import com.jonathan.kawanuaapp.data.pref.UserModel
import com.jonathan.kawanuaapp.data.pref.UserPreference

class MainViewModel(private val userRepository: UserRepository,  val pref: UserPreference): ViewModel() {
    fun getSession(): LiveData<UserModel> {
        return userRepository.getSession().asLiveData()
    }

    fun getThemeSettings(): LiveData<Boolean> {
        return pref.getThemeSetting().asLiveData()
    }
}