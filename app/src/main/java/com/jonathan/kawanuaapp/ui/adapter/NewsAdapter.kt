package com.jonathan.kawanuaapp.ui.adapter

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.RecyclerView
import com.jonathan.kawanuaapp.ArticlesItem
import com.jonathan.kawanuaapp.databinding.ItemRowMainBinding
import com.jonathan.kawanuaapp.databinding.ItemRowNewsBinding
import com.jonathan.kawanuaapp.loadImage
import com.jonathan.kawanuaapp.ui.detailnews.DetailNewsFragment
import com.jonathan.kawanuaapp.ui.main.MainActivity

class NewsAdapter(
    private val listNews: List<ArticlesItem>,
    private val viewType: Int,
    private val newsItemClickListener: NewsItemClickListener // Add this parameter
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val HORIZONTAL = 1
        const val VERTICAL = 2
    }

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
                viewHolderOne.bind.root.setOnClickListener {
                    newsItemClickListener.onNewsItemClicked(news)
                }
//                viewHolderOne.bind.root.setOnClickListener { view ->
//                    val intentDetail = Intent(view.context, MainActivity::class.java)
//                    intentDetail.putExtra("news", news)
//
//                    view.context.startActivity(intentDetail)
//                }

            }
            VERTICAL -> {
                val viewHolderTwo = holder as ListViewHolderVertical
                viewHolderTwo.bind.tvTitle.text = news.title
                viewHolderTwo.bind.tvDate.text = news.publishedAt
                viewHolderTwo.bind.imgNews.loadImage(news.urlToImage)
                viewHolderTwo.bind.root.setOnClickListener {
                    newsItemClickListener.onNewsItemClicked(news)
                }
//                viewHolderTwo.bind.root.setOnClickListener { view ->
//                    val intentDetail = Intent(view.context, DetailNewsFragment::class.java)
//                    intentDetail.putExtra("news", news)
//
//                    view.context.startActivity(intentDetail)
//                    val intentDetail = Intent(view.context, DetailNewsFragment::class.java)
//                    val bundle = Bundle()
//                    bundle.putParcelable("news", news)
//                    intentDetail.putExtras(bundle)
//                    view.context.startActivity(intentDetail)
//                }
            }
        }
    }

    override fun getItemCount(): Int = listNews.size

    override fun getItemViewType(position: Int): Int {
        return viewType
    }

    class ListViewHolderHorizontal(val bind: ItemRowMainBinding) : RecyclerView.ViewHolder(bind.root)

    class ListViewHolderVertical(val bind: ItemRowNewsBinding) : RecyclerView.ViewHolder(bind.root)

    interface NewsItemClickListener {
        fun onNewsItemClicked(newsItem: ArticlesItem)
    }

}