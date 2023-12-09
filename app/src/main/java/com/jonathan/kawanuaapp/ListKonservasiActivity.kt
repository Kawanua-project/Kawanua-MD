package com.jonathan.kawanuaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jonathan.kawanuaapp.databinding.ActivityListKonservasiBinding

class ListKonservasiActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListKonservasiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListKonservasiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchBar
    }
}