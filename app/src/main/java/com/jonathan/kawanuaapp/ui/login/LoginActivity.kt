package com.jonathan.kawanuaapp.ui.login

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import com.jonathan.kawanuaapp.ui.main.MainActivity
import com.jonathan.kawanuaapp.R
import com.jonathan.kawanuaapp.helper.ViewModelFactory
import com.jonathan.kawanuaapp.data.pref.UserModel
import com.jonathan.kawanuaapp.databinding.ActivityLoginBinding
import com.jonathan.kawanuaapp.ui.register.RegisterActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var email: String
    private lateinit var password: String
    private val viewModel by viewModels<LoginViewModel> {
        ViewModelFactory.getInstance(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAction()
        playAnimation()

        binding.progressBar.visibility = View.GONE

        viewModel.isLoading.observe(this) {
            showLoading(it)
        }


        viewModel.token.observe(this) {
            if(it.token != null) {
                showToast("Login berhasil")
            }
            runBlocking {
                delay(1000)
            }
            if(it != null) {
                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                val token = it.token
                token?.let { it1 -> UserModel(email, it1) }
                    ?.let { it2 -> viewModel.saveSession(it2) }
            }
        }

        binding.instagram.setOnClickListener {
            openInstagram()
        }

        binding.linkedin.setOnClickListener {
            openLinkedin()
        }

        binding.tvDaftar.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }

        playAnimation()
    }



    companion object {
        const val TAG = "Login Activity"
    }

    private fun openInstagram() {
        val instagramPackage = "com.instagram.android"
        val uri = Uri.parse("https://www.instagram.com/kawanua.app/")

        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = uri
        intent.setPackage(instagramPackage)

        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            val webIntent =
                Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/kawanua.app/"))
            startActivity(webIntent)
        }
    }

    private fun openLinkedin() {
        val linkedInProfileUrl = "https://www.linkedin.com/company/kawanuntukalam"

        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(linkedInProfileUrl))

        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(linkedInProfileUrl))
            startActivity(webIntent)
        }
    }

    private fun playAnimation() {
        ObjectAnimator.ofFloat(binding.logoKawanua, View.TRANSLATION_X, -60f, 60f).apply {
            duration = 6000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()

        val emailEditTextLayout =
            ObjectAnimator.ofFloat(binding.emailEditTextLayout, View.ALPHA, 1f).setDuration(1000)

        val passwordEditTextLayout =
            ObjectAnimator.ofFloat(binding.passwordEditTextLayout, View.ALPHA, 1f).setDuration(1000)

        AnimatorSet().apply {
            playSequentially(
                emailEditTextLayout,
                passwordEditTextLayout,
            )
            startDelay = 100
        }.start()
    }

    private fun showToast(message: String) {
        Toast.makeText(this@LoginActivity, message, Toast.LENGTH_SHORT).show()
    }

    private fun setupAction() {

        binding.buttonMasuk.setOnClickListener {
            email = binding.emailPengguna.text.toString()
            password = binding.passwordPengguna.text.toString()

            binding.progressBar.visibility = View.VISIBLE

            viewModel.login(email, password)
        }

        binding.tvDaftar.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}