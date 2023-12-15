package com.jonathan.kawanuaapp.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.activity.viewModels
import com.jonathan.kawanuaapp.R
import com.jonathan.kawanuaapp.ViewModelFactory
import com.jonathan.kawanuaapp.ui.login.LoginActivity
import com.jonathan.kawanuaapp.ui.main.MainActivity
import com.jonathan.kawanuaapp.ui.register.RegisterActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private val viewModel by viewModels<SplashViewModel> {
        ViewModelFactory.getInstance(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        viewModel.getSession().observe(this@SplashActivity) { user ->
            if (!user.isLogin) {
                startActivity(Intent(this, RegisterActivity::class.java))
                finish()
            } else {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }

        Handler().postDelayed({
            // Start the main activity or any other desired activity
        }, SPLASH_DELAY)
    }

    companion object {
        private const val SPLASH_DELAY = 1500L
    }
}