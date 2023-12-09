package com.jonathan.kawanuaapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jonathan.kawanuaapp.databinding.ItemRowContactBinding

class ListKonservasiAdapter(private val originalList: ArrayList<Konservasi>) :
    RecyclerView.Adapter<ListKonservasiAdapter.ListViewHolder>() {
    class ListViewHolder(var bind: ItemRowContactBinding) : RecyclerView.ViewHolder(bind.root)

    private var filteredList: MutableList<Konservasi> = originalList.toMutableList()

    fun filter(text: String) {
        filteredList.clear()
        if (text.isEmpty()) {
            filteredList.addAll(originalList)
        } else {
            val query = text.toLowerCase().trim()
            originalList.forEach { konservasi ->
                if (konservasi.nama.toLowerCase().contains(query) ||
                    konservasi.alamat.toLowerCase().contains(query)
                ) {
                    filteredList.add(konservasi)
                }
            }
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val bind = ItemRowContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(bind)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val konservasi = originalList[position]
        with(holder.bind) {
            tvName.text = konservasi.nama
            tvLocation.text = konservasi.alamat
            tvNumber.text = konservasi.nomor.toString()
        }
    }

    override fun getItemCount(): Int = originalList.size
}