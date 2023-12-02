package com.jonathan.kawanuaapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jonathan.kawanuaapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener {
            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
        }

        binding.scanButton.setOnClickListener {
            startActivity(Intent(this@MainActivity, ScanActivity::class.java))
        }

        binding.registerButton.setOnClickListener {
            startActivity(Intent(this@MainActivity, RegisterActivity::class.java))
        }

        binding.profilButton.setOnClickListener {
            startActivity(Intent(this@MainActivity, ProfilActivity::class.java))
        }
    }
}