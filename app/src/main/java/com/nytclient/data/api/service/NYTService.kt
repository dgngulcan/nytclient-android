package com.nytclient.data.api.service

import android.arch.lifecycle.LiveData
import com.nytclient.data.api.ApiResponse
import com.nytclient.data.model.NewsApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * NYT API service class.
 *
 * @link https://developer.nytimes.com/
 *
 * Created by Dogan Gulcan on 9/12/17.
 */
interface NYTService {

    @GET("svc/news/v3/content/nyt/all")
    fun getNews(@Query("limit") itemCount: Int,
                @Query("offset") pageIndex: Int): LiveData<ApiResponse<NewsApiResponse>>

}