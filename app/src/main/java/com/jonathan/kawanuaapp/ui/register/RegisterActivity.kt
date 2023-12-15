package com.jonathan.kawanuaapp.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.jonathan.kawanuaapp.ViewModelFactory
import com.jonathan.kawanuaapp.databinding.ActivityRegisterBinding
import com.jonathan.kawanuaapp.ui.login.LoginActivity

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

        binding.progressBar.visibility = View.GONE

        binding.tvSudahPunyaAkun.setOnClickListener {
            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
        }

        binding.buttonDaftar.setOnClickListener {
            name = binding.namaPengguna.text.toString()
            email = binding.emailPengguna.text.toString()
            password = binding.passwordPengguna.text.toString()
            confirmPass = binding.konfirmasiPassword.text.toString()

            binding.progressBar.visibility = View.VISIBLE

            viewModel.register(name, email, password, confirmPass)
        }

        viewModel.isLoading.observe(this) {
            showLoading(it)
        }

        viewModel.response.observe(this) {
            showToast(it.toString())
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

}