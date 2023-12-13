package com.jonathan.kawanuaapp.ui.contact

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jonathan.kawanuaapp.model.Konservasi

class ContactViewModel : ViewModel() {

    private val _listKonservasi = MutableLiveData<ArrayList<Konservasi>>()
    val listKonservasi: LiveData<ArrayList<Konservasi>> = _listKonservasi

    fun search() {

    }

}