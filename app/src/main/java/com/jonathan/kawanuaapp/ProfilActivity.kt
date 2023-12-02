package com.jonathan.kawanuaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jonathan.kawanuaapp.databinding.ActivityProfilBinding

class ProfilActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfilBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lottieAnimationView.playAnimation()
    }
}