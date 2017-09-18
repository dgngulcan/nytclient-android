package com.nytclient.data.repo

import android.arch.lifecycle.LiveData
import com.nytclient.NytClientApp
import com.nytclient.data.NetworkBoundResource
import com.nytclient.data.Resource
import com.nytclient.data.api.ApiResponse
import com.nytclient.data.api.service.NYTService
import com.nytclient.data.db.NewsDao
import com.nytclient.data.model.NewsApiResponse
import com.nytclient.data.model.NewsItem
import com.nytclient.util.DateTimeUtils
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Created by Dogan Gulcan on 9/13/17.
 */
@Singleton
class NewsRepo @Inject constructor(val appContext: NytClientApp,
                                   val nytService: NYTService,
                                   val newsDao: NewsDao) {

    private val ITEM_PER_PAGE = 20

    fun getNews(pageIndex: Int): LiveData<Resource<NewsApiResponse>> {
        return object : NetworkBoundResource<List<NewsItem>, NewsApiResponse>(appContext) {

            override fun loadFromDb(): LiveData<List<NewsItem>> {
                return newsDao.getAllNews()
            }

            override fun createCall(): LiveData<ApiResponse<NewsApiResponse>> {
                return nytService.getNews(ITEM_PER_PAGE, pageIndex)
            }

            override fun saveCallResult(item: NewsApiResponse) {
                newsDao.insertNews(item.results)
            }

            override fun shouldFetch(data: List<NewsItem>): Boolean {
                val latestNewsCreationDate = if (data.isNotEmpty()) {
                    data.first().createdDate
                } else {
                    ""
                }
                return if (latestNewsCreationDate.isNotBlank()) {
                    latestNewsCreationDate.let {
                        DateTimeUtils.getInstance().getTimeStampFromDate(it)?.
                                let {
                                    val currentMillis = System.currentTimeMillis()
                                    val timeDifference = currentMillis - it
                                    TimeUnit.MILLISECONDS.toMinutes(timeDifference) > 2
                                            || timeDifference < 0
                                }
                    } ?: true
                } else {
                    true
                }

            }

            override fun onFetchFailed() {

            }

            override fun processResponse(response: ApiResponse<NewsApiResponse>): NewsApiResponse? {
                return response.body
            }


        }.asLiveData()
    }

}
