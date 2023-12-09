package com.jonathan.kawanuaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.jonathan.kawanuaapp.databinding.ActivityListKonservasiBinding

class ListKonservasiActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListKonservasiBinding
    private val list = ArrayList<Konservasi>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListKonservasiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = ListKonservasiAdapter(list)
        binding.rvKsdae.adapter = adapter
        binding.rvKsdae.layoutManager = LinearLayoutManager(this)

        with(binding) {
            searchView.setupWithSearchBar(searchBar)

        }

        list.addAll(getListKonservasi())
        showRecyclerList()
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