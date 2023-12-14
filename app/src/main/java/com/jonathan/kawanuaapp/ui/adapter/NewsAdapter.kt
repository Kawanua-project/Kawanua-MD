package com.jonathan.kawanuaapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jonathan.kawanuaapp.ArticlesItem
import com.jonathan.kawanuaapp.databinding.ItemRowMainBinding
import com.jonathan.kawanuaapp.databinding.ItemRowNewsBinding
import com.jonathan.kawanuaapp.loadImage

class NewsAdapter(
    private val listNews: List<ArticlesItem>,
    private val viewType: Int
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val HORIZONTAL = 1
        private const val VERTICAL = 2
    }
//    class ListViewHolder(var bind: ItemRowMainBinding) : RecyclerView.ViewHolder(bind.root)

//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
//        val bind = ItemRowMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return ListViewHolder(bind)
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            HORIZONTAL -> {
                val bindingOne = ItemRowMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ListViewHolderHorizontal(bindingOne)
            }
            VERTICAL -> {
                val bindingTwo = ItemRowNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ListViewHolderVertical(bindingTwo)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val news = listNews[position]
        when (holder.itemViewType) {
            HORIZONTAL -> {
                val viewHolderOne = holder as ListViewHolderHorizontal
                viewHolderOne.bind.tvTitle.text = news.title
                viewHolderOne.bind.imgNews.loadImage(news.urlToImage)
            }
            VERTICAL -> {
                val viewHolderTwo = holder as ListViewHolderVertical
                viewHolderTwo.bind.tvTitle.text = news.title
                viewHolderTwo.bind.tvDate.text = news.publishedAt
                viewHolderTwo.bind.imgNews.loadImage(news.urlToImage)
            }
        }
    }

//    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
//        val news = listNews[position]
//        with(holder.bind) {
//            tvTitle.text = news.title
//            imgNews.loadImage(news.urlToImage)
//        }
//    }

    override fun getItemCount(): Int = listNews.size

    override fun getItemViewType(position: Int): Int {
        return viewType
    }

    class ListViewHolderHorizontal(val bind: ItemRowMainBinding) : RecyclerView.ViewHolder(bind.root)

    class ListViewHolderVertical(val bind: ItemRowNewsBinding) : RecyclerView.ViewHolder(bind.root)

}