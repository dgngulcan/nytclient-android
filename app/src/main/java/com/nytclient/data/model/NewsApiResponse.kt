package com.nytclient.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Dogan Gulcan on 9/12/17.
 */
data class NewsApiResponse(
        @SerializedName("copyright")
        var copyright: String = "",

        @SerializedName("num_results")
        var numResults: Long = 0,

        @SerializedName("results")
        var results: List<NewsItem> = ArrayList(),

        @SerializedName("status")
        var status: String = "")