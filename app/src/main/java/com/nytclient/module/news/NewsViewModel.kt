package com.nytclient.module.news

import android.arch.lifecycle.*
import android.databinding.ObservableBoolean
import com.nytclient.data.Resource
import com.nytclient.data.Status
import com.nytclient.data.model.NewsApiResponse
import com.nytclient.data.model.NewsItem
import com.nytclient.data.repo.NewsRepo
import com.nytclient.ui.common.BaseViewModel
import com.nytclient.ui.common.SingleLiveEvent


@Suppress("UNCHECKED_CAST")
/**
 * ViewModel for [NewsFragment].
 *
 * Created by Dogan Gulcan on 9/12/17.
 */
class NewsViewModel constructor(newsRepo: NewsRepo) : BaseViewModel() {

    var isLoadingNews = ObservableBoolean(false)
    var loadingFailedevent = SingleLiveEvent<Boolean>()
    var openNewsDetailEvent = SingleLiveEvent<NewsItem>()

    val newsItemClickCallback = object : NewsItemClickCallback {
        override fun onClick(newsItem: NewsItem) {
            openNewsDetailEvent.setValue(newsItem)
        }
    }

    private var newsApiResponse: LiveData<Resource<NewsApiResponse>> = newsRepo.getNews(0) // todo paginate

    var newsListData: LiveData<List<NewsItem>> =
            Transformations.switchMap(newsApiResponse, { response ->
                when (response.status) {
                    Status.LOADING -> isLoadingNews.set(true)
                    Status.SUCCESS -> isLoadingNews.set(false)
                    Status.ERROR -> {
                        isLoadingNews.set(false)
                        loadingFailedevent.setValue(true)
                    }
                }

                val result = MutableLiveData<List<NewsItem>>()
                result.value = (response as Resource<List<NewsItem>>).data
                result
            })

    /**
     * Factory class for [ViewModelProvider]. Used to pass values to constructor of the [ViewModel].
     */
    class Factory(private val newsRepo: NewsRepo) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return NewsViewModel(newsRepo) as T
        }
    }

    interface NewsItemClickCallback {
        fun onClick(newsItem: NewsItem)
    }

}