package com.jonathan.kawanuaapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jonathan.kawanuaapp.databinding.ItemRowMainBinding

class NewsAdapter(private val listNews: List<ArticlesItem>) :
    RecyclerView.Adapter<NewsAdapter.ListViewHolder>() {
    class ListViewHolder(var bind: ItemRowMainBinding) : RecyclerView.ViewHolder(bind.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val bind = ItemRowMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(bind)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val news = listNews[position]
        with(holder.bind) {
            tvTitle.text = news.title
            imgNews.loadImage(news.urlToImage)
        }
    }

    override fun getItemCount(): Int = listNews.size
}