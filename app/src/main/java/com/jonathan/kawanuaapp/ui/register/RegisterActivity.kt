package com.jonathan.kawanuaapp.ui.register

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.jonathan.kawanuaapp.helper.ViewModelFactory
import com.jonathan.kawanuaapp.databinding.ActivityRegisterBinding
import com.jonathan.kawanuaapp.ui.login.LoginActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var email: String
    private lateinit var name: String
    private lateinit var password: String
    private lateinit var confirmPass: String

    private val viewModel by viewModels<RegisterViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAction()
        playAnimation()

        binding.progressBar.visibility = View.GONE

        viewModel.isLoading.observe(this) {
            showLoading(it)
        }

        viewModel.response.observe(this) {
            showToast(it.toString())
            runBlocking {
                delay(1000)
            }
            if(it == "Register berhasil") {
                startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this@RegisterActivity, message, Toast.LENGTH_SHORT).show()
    }

    private fun setupAction() {

        binding.buttonDaftar.setOnClickListener {
            name = binding.namaPengguna.text.toString()
            email = binding.emailPengguna.text.toString()
            password = binding.passwordPengguna.text.toString()
            confirmPass = binding.konfirmasiPassword.text.toString()

            binding.progressBar.visibility = View.VISIBLE

            viewModel.register(name, email, password, confirmPass)
        }

        binding.tvSudahPunyaAkun.setOnClickListener {
            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
        }
    }

    private fun playAnimation() {
        ObjectAnimator.ofFloat(binding.logoKawanua, View.TRANSLATION_X, -60f, 60f).apply {
            duration = 6000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()

        val emailEditTextLayout =
            ObjectAnimator.ofFloat(binding.textFieldEmail, View.ALPHA, 1f).setDuration(100)

        val passwordEditTextLayout =
            ObjectAnimator.ofFloat(binding.textFieldPassword, View.ALPHA, 1f).setDuration(100)

        val usernameEditTextLayout =
            ObjectAnimator.ofFloat(binding.textFieldKonfirmasiPassword, View.ALPHA, 1f).setDuration(100)

        val registerButton =
            ObjectAnimator.ofFloat(binding.buttonDaftar, View.ALPHA, 1f).setDuration(100)

        AnimatorSet().apply {
            playSequentially(
                emailEditTextLayout,
                passwordEditTextLayout,
                usernameEditTextLayout,
                registerButton
            )
            startDelay = 100
        }.start()
    }

}