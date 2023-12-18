package com.jonathan.kawanuaapp

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jonathan.kawanuaapp.data.pref.UserPreference
import com.jonathan.kawanuaapp.ui.detailnews.DetailNewsViewModel
import com.jonathan.kawanuaapp.ui.home.HomeViewModel
import com.jonathan.kawanuaapp.ui.listnews.ListBeritaViewModel
import com.jonathan.kawanuaapp.ui.login.LoginViewModel
import com.jonathan.kawanuaapp.ui.main.MainViewModel
import com.jonathan.kawanuaapp.ui.profile.ProfileViewModel
import com.jonathan.kawanuaapp.ui.register.RegisterViewModel
import com.jonathan.kawanuaapp.ui.scan.ScanViewModel
import com.jonathan.kawanuaapp.ui.splash.SplashViewModel


class ViewModelFactory(
    private val repository: UserRepository,
    private val newsRepository: NewsRepository,
    private val pref: UserPreference
) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> {
                RegisterViewModel(repository) as T
            }

            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(newsRepository) as T
            }
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(repository) as T
            }
            modelClass.isAssignableFrom(ListBeritaViewModel::class.java) -> {
                ListBeritaViewModel(newsRepository) as T
            }

            modelClass.isAssignableFrom(DetailNewsViewModel::class.java) -> {
                DetailNewsViewModel() as T
            }

            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(repository) as T
            }

            modelClass.isAssignableFrom(SplashViewModel::class.java) -> {
                SplashViewModel(repository) as T
            }
            modelClass.isAssignableFrom(ProfileViewModel::class.java) -> {
                ProfileViewModel(repository) as T
            }
            modelClass.isAssignableFrom(ScanViewModel::class.java) -> {
                ScanViewModel(repository) as T
            }

            modelClass.isAssignableFrom(ProfileViewModel::class.java) -> {
                ProfileViewModel(pref) as T
            }

            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        @JvmStatic
        fun getInstance(context: Context): ViewModelFactory {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    val userRepository = Injection.provideRepository(context)
                    val newsRepository = Injection.provideNewsRepository(context)
                    val userPreference = Injection.providePreference(context)
                    INSTANCE = ViewModelFactory(userRepository, newsRepository, userPreference)
                }
            }
            return INSTANCE ?: throw IllegalStateException("ViewModelFactory should not be null")
        }
    }

}