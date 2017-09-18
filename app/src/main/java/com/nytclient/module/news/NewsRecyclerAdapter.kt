package com.nytclient.module.news

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.nytclient.data.model.NewsItem
import com.nytclient.databinding.ListItemNewsBinding

/**
 * Basic RecyclerViewAdapter for News.
 *
 * Created by Dogan Gulcan on 9/16/17.
 */
class NewsRecyclerAdapter(context: Activity, private val newsItemClickCallback: NewsViewModel.NewsItemClickCallback) : RecyclerView.Adapter<NewsViewHolder>() {

    private var newsItems: MutableList<NewsItem> = ArrayList()
    private var layoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): NewsViewHolder {
        val binding = ListItemNewsBinding.inflate(layoutInflater, parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder?, position: Int) {
        holder?.bind(newsItems[position], newsItemClickCallback)
    }

    override fun getItemCount(): Int {
        return newsItems.size
    }

    fun addItems(newsItems: List<NewsItem>?) {
        newsItems?.let {
            this.newsItems.addAll(it)
            val distinctList = this.newsItems.distinct()

            this.newsItems.clear().also {
                this.newsItems.addAll(distinctList)
            }
        }
        notifyDataSetChanged()
    }

}