package com.jonathan.kawanuaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityOptionsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jonathan.kawanuaapp.databinding.ActivityScanBinding

class ScanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScanBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScanBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val bottomNavigationView: BottomNavigationView = binding.bottomNavigation

        bottomNavigationView.selectedItemId = R.id.navigation_scan

        bottomNavigationView.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.navigation_profile -> {
                    startProfileActivity()
                    true
                }
                R.id.navigation_home -> {
                    startHomeActivity()
                    true
                }
                R.id.navigation_contact -> {
                    startContactActivity()
                    true
                }
                else -> false
            }
        }

    }

    private fun startProfileActivity() {
        val intent = Intent(this, ProfileActivity::class.java)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this)
        startActivity(intent, options.toBundle())
    }

    private fun startHomeActivity() {
        val intent = Intent(this, MainActivity::class.java)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this)
        startActivity(intent, options.toBundle())
    }

    private fun startContactActivity() {
        val intent = Intent(this, ListKonservasiActivity::class.java)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this)
        startActivity(intent, options.toBundle())
    }
}