package com.jonathan.kawanuaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityOptionsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jonathan.kawanuaapp.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lottieAnimationView.playAnimation()

        val bottomNavigationView: BottomNavigationView = binding.bottomNavigation

        bottomNavigationView.selectedItemId = R.id.navigation_profile

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home-> {
                    startHomeActivity()
                    true
                }

                R.id.navigation_scan -> {
                    startScanActivity()
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

    private fun startHomeActivity() {
        val intent = Intent(this, MainActivity::class.java)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this)
        startActivity(intent, options.toBundle())
    }

    private fun startScanActivity() {
        val intent = Intent(this, ScanActivity::class.java)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this)
        startActivity(intent, options.toBundle())
    }

    private fun startContactActivity() {
        val intent = Intent(this, ListKonservasiActivity::class.java)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this)
        startActivity(intent, options.toBundle())
    }

}