package com.jonathan.kawanuaapp.ui.contact

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jonathan.kawanuaapp.R
import com.jonathan.kawanuaapp.data.model.Konservasi

class ContactViewModel : ViewModel() {

    private val _listKonservasi = MutableLiveData<ArrayList<Konservasi>>()
    val listKonservasi: LiveData<ArrayList<Konservasi>> = _listKonservasi


    fun setListKonservasi(resources: Resources): ArrayList<Konservasi> {
        val dataNama = resources.getStringArray(R.array.data_nama)
        val dataAlamat = resources.getStringArray(R.array.data_alamat)
        val dataNomor = resources.getStringArray(R.array.data_nomor)
        val listKonservasi = ArrayList<Konservasi>()
        for(i in dataNama.indices) {
            val konservasi = Konservasi(dataNama[i], dataAlamat[i], dataNomor[i])
            listKonservasi.add(konservasi)
        }

        return listKonservasi
    }

    /*private fun getListKonservasi(): ArrayList<Konservasi> {
        val dataNama = resources.getStringArray(R.array.data_nama)
        val dataAlamat = resources.getStringArray(R.array.data_alamat)
        val dataNomor = resources.getStringArray(R.array.data_nomor)
        val listKonservasi = ArrayList<Konservasi>()
        for (i in dataNama.indices) {
            val konservasi = Konservasi(dataNama[i], dataAlamat[i], dataNomor[i])
            listKonservasi.add(konservasi)
        }
        return listKonservasi
    }*/


}