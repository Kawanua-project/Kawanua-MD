package com.jonathan.kawanuaapp.ui.main

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.jonathan.kawanuaapp.ArticlesItem
import com.jonathan.kawanuaapp.R
import com.jonathan.kawanuaapp.data.pref.UserPreference
import com.jonathan.kawanuaapp.data.pref.dataStore
import com.jonathan.kawanuaapp.databinding.ActivityMainBinding
import com.jonathan.kawanuaapp.ui.detailnews.DetailNewsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var pref: UserPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        application?.let {
            pref = UserPreference.getInstance(it.dataStore)
        }

//        val news = intent.getParcelableExtra<ArticlesItem>("news")
//        val fragment = DetailNewsFragment()
//        val bundle = Bundle()
//        bundle.putParcelable("news", news)
//        fragment.arguments = bundle
//
//        supportFragmentManager.beginTransaction()
//            .replace(R.id.nav_host_fragment_activity_main, fragment) // Replace 'fragmentContainer' with your actual container ID
//            .commit()

        val navView: BottomNavigationView = binding.navView
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false) // Hide the title
//        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        val navController = navHostFragment.navController
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_scan,
                R.id.navigation_profile,
                R.id.navigation_contact
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.navigation_home) {
                supportActionBar?.hide()
                supportActionBar?.setDisplayShowTitleEnabled(false)
            } else {
                supportActionBar?.show()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}

