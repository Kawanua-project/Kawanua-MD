package com.jonathan.kawanuaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityOptionsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.recyclerview.widget.LinearLayoutManager
import com.jonathan.kawanuaapp.databinding.ActivityListKonservasiBinding

class ListKonservasiActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListKonservasiBinding
    private val list = ArrayList<Konservasi>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListKonservasiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            searchView.setupWithSearchBar(searchBar)

        }

        list.addAll(getListKonservasi())
        showRecyclerList()

        val bottomNavigationView: BottomNavigationView = binding.bottomNavigation
        bottomNavigationView.selectedItemId = R.id.navigation_contact

        bottomNavigationView.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.navigation_profile -> {
                    startProfileActivity()
                    true
                }
                R.id.navigation_scan -> {
                    startScanActivity()
                    true
                }
                R.id.navigation_home -> {
                    startHomeActivity()
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

    private fun startScanActivity() {
        val intent = Intent(this, ScanActivity::class.java)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this)
        startActivity(intent, options.toBundle())
    }

    private fun startHomeActivity() {
        val intent = Intent(this, MainActivity::class.java)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this)
        startActivity(intent, options.toBundle())
    }

    private fun getListKonservasi(): ArrayList<Konservasi> {
        val dataNama = resources.getStringArray(R.array.data_nama)
        val dataAlamat = resources.getStringArray(R.array.data_alamat)
        val dataNomor = resources.getStringArray(R.array.data_nomor)
        val listKonservasi = ArrayList<Konservasi>()
        for (i in dataNama.indices) {
            val konservasi = Konservasi(dataNama[i], dataAlamat[i], dataNomor[i])
            listKonservasi.add(konservasi)
        }
        return listKonservasi
    }

    private fun showRecyclerList() {
        val listKonservasiAdapter = ListKonservasiAdapter(list)
        binding.rvKsdae.apply {
            layoutManager = LinearLayoutManager(this@ListKonservasiActivity)
            adapter = listKonservasiAdapter
            setHasFixedSize(true)
        }
    }
}