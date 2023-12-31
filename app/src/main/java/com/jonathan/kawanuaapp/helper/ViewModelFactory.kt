package com.jonathan.kawanuaapp.helper

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jonathan.kawanuaapp.data.pref.ThemePreference
import com.jonathan.kawanuaapp.data.repository.NewsRepository
import com.jonathan.kawanuaapp.data.repository.UserRepository
import com.jonathan.kawanuaapp.data.pref.UserPreference
import com.jonathan.kawanuaapp.ui.detailspesies.DetailSpesiesViewModel
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
    private val pref: UserPreference,
    private val theme: ThemePreference
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
                MainViewModel(repository, theme) as T
            }
            modelClass.isAssignableFrom(ListBeritaViewModel::class.java) -> {
                ListBeritaViewModel(newsRepository) as T
            }
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(repository) as T
            }
            modelClass.isAssignableFrom(SplashViewModel::class.java) -> {
                SplashViewModel(repository, theme) as T
            }
            modelClass.isAssignableFrom(ScanViewModel::class.java) -> {
                ScanViewModel(repository) as T
            }
            modelClass.isAssignableFrom(ProfileViewModel::class.java) -> {
                ProfileViewModel(theme, repository) as T
            }
            modelClass.isAssignableFrom(DetailSpesiesViewModel::class.java) -> {
                DetailSpesiesViewModel() as T
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
                    val themePreference = Injection.provideTheme(context)
                    INSTANCE = ViewModelFactory(userRepository, newsRepository, userPreference, themePreference)
                }
            }
            return INSTANCE ?: throw IllegalStateException("ViewModelFactory should not be null")
        }
    }

}