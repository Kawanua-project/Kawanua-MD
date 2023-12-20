package com.jonathan.kawanuaapp.ui.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jonathan.kawanuaapp.data.retrofit.response.ArticlesItem
import com.jonathan.kawanuaapp.databinding.ItemRowMainBinding
import com.jonathan.kawanuaapp.databinding.ItemRowNewsBinding
import com.jonathan.kawanuaapp.helper.loadImage
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class NewsAdapter(
    private val listNews: List<ArticlesItem>,
    private val viewType: Int,
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
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        val parsedDate = try {
            news.publishedAt?.let { dateFormat.parse(it) }
        } catch (e: ParseException) {
            null
        }
        when (holder.itemViewType) {
            HORIZONTAL -> {
                val viewHolderOne = holder as ListViewHolderHorizontal
                viewHolderOne.bind.tvTitle.text = news.title
                viewHolderOne.bind.imgNews.loadImage(news.urlToImage)

                holder.itemView.setOnClickListener {
                    val newsUrl = news.url
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(newsUrl))
                    holder.itemView.context.startActivity(intent)
                }

            }
            VERTICAL -> {
                val viewHolderTwo = holder as ListViewHolderVertical
                viewHolderTwo.bind.tvTitle.text = news.title
                viewHolderTwo.bind.tvDate.text = parsedDate?.let { formatDate(it) }
                viewHolderTwo.bind.imgNews.loadImage(news.urlToImage)

                holder.itemView.setOnClickListener {
                    val newsUrl = news.url
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(newsUrl))
                    holder.itemView.context.startActivity(intent)
                }
            }
        }
    }

    private fun formatDate(date: Date): String {
        val targetFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        return targetFormat.format(date)
    }

    override fun getItemCount(): Int = listNews.size

    override fun getItemViewType(position: Int): Int {
        return viewType
    }

    class ListViewHolderHorizontal(val bind: ItemRowMainBinding) : RecyclerView.ViewHolder(bind.root)

    class ListViewHolderVertical(val bind: ItemRowNewsBinding) : RecyclerView.ViewHolder(bind.root)

}