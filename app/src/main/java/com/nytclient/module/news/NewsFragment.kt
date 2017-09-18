package com.nytclient.module.news

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nytclient.R
import com.nytclient.data.repo.NewsRepo
import com.nytclient.databinding.FragmentNewsBinding
import com.nytclient.ui.common.BaseFragment
import com.nytclient.util.openUrlInCustomTab
import org.jetbrains.anko.support.v4.toast
import javax.inject.Inject

/**
 * Fragment to show list of news.
 *
 * Created by Dogan Gulcan on 9/13/17.
 */
class NewsFragment : BaseFragment() {

    private lateinit var viewModel: NewsViewModel
    private lateinit var binding: FragmentNewsBinding
    @Inject lateinit var newsRepo: NewsRepo

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val factory = NewsViewModel.Factory(newsRepo)
        viewModel = ViewModelProviders.of(this, factory).get(NewsViewModel::class.java)

        binding.viewModel = viewModel

        init()
        initEventObservations()
    }

    private fun init() {
        val newsRecyclerAdapter = NewsRecyclerAdapter(activity, viewModel.newsItemClickCallback)
        binding.newsRecyclerView.layoutManager = LinearLayoutManager(activity)
        binding.newsRecyclerView.adapter = newsRecyclerAdapter

        viewModel.newsListData.observe(this, Observer {
            newsRecyclerAdapter.addItems(it)
        })
    }

    private fun initEventObservations() {

        viewModel.openNewsDetailEvent.observe(this, Observer {
            it?.url?.let { newsItem -> activity.openUrlInCustomTab(newsItem) }
        })

        viewModel.loadingFailedevent.observe(this, Observer {
            toast(getString(R.string.fail_news_load))
        })

    }

}