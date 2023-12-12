package com.jonathan.kawanuaapp.ui.contact

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jonathan.kawanuaapp.model.Konservasi
import com.jonathan.kawanuaapp.ui.adapter.ListKonservasiAdapter
import com.jonathan.kawanuaapp.R
import com.jonathan.kawanuaapp.databinding.FragmentContactBinding

class ContactFragment : Fragment() {

    private var _binding: FragmentContactBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val list = ArrayList<Konservasi>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val contactViewViewModel =
            ViewModelProvider(this).get(ContactViewModel::class.java)

        _binding = FragmentContactBinding.inflate(inflater, container, false)
        val root: View = binding.root

        list.addAll(getListKonservasi())
        showRecyclerList()


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showRecyclerList() {
        val listKonservasiAdapter = ListKonservasiAdapter(list)
        binding.rvKsdae.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = listKonservasiAdapter
            setHasFixedSize(true)
        }
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
}