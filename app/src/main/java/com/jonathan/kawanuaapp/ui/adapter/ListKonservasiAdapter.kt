package com.jonathan.kawanuaapp.ui.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jonathan.kawanuaapp.databinding.ItemRowContactBinding
import com.jonathan.kawanuaapp.data.model.Konservasi

class ListKonservasiAdapter(private val originalList: ArrayList<Konservasi>) :
    RecyclerView.Adapter<ListKonservasiAdapter.ListViewHolder>() {
    class ListViewHolder(var bind: ItemRowContactBinding) : RecyclerView.ViewHolder(bind.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val bind = ItemRowContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(bind)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val konservasi = originalList[position]
        with(holder.bind) {
            tvName.text = konservasi.nama
            tvLocation.text = konservasi.alamat
            tvNumber.text = konservasi.nomor
        }

        holder.itemView.setOnClickListener {
            val phoneNumber = konservasi.nomor
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:$phoneNumber")
            holder.itemView.context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int = originalList.size

}