package com.nytclient.module.news

import android.support.v7.widget.RecyclerView
import com.nytclient.data.model.NewsItem
import com.nytclient.databinding.ListItemNewsBinding

/**
 * ViewHolder class for News item.
 * @see [NewsRecyclerAdapter].
 *
 * Created by Dogan Gulcan on 9/18/17.
 */
class NewsViewHolder(private val binding: ListItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(news: NewsItem, newsItemClickCallback: NewsViewModel.NewsItemClickCallback) {
        binding.newsItem = news
        binding.newsCard.setOnClickListener({ newsItemClickCallback.onClick(news) })
        binding.executePendingBindings()
    }
}