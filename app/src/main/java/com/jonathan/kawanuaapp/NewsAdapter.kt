package com.jonathan.kawanuaapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jonathan.kawanuaapp.databinding.ItemRowNewsBinding

class NewsAdapter(private val news: List<ArticlesItem>) :
    RecyclerView.Adapter<NewsAdapter.ListViewHolder>() {
    class ListViewHolder(var bind: ItemRowNewsBinding) : RecyclerView.ViewHolder(bind.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val bind = ItemRowNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(bind)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val news = news[position]
        with(holder.bind) {
            tvTitle.text = news.title
            tvDate.text = news.publishedAt
            imgNews.loadImage(news.urlToImage)
        }
    }

    override fun getItemCount(): Int {
        return news.size
    }
}