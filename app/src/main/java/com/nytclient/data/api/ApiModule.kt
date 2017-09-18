package com.nytclient.data.api

import com.google.gson.GsonBuilder
import com.nytclient.BuildConfig
import com.nytclient.NytClientApp
import com.nytclient.R
import com.nytclient.data.api.service.NYTService
import com.nytclient.util.LiveDataCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


/**
 * Provider module for APIs.
 * Created by Dogan Gulcan on 9/15/17.
 */
@Module
class ApiModule {

    private val BASE_NYT_URL = "https://api.nytimes.com/"

    @Singleton
    @Provides
    fun provideNytService(nytClientApp: NytClientApp): NYTService {

        val loggingInterceptor = HttpLoggingInterceptor()

        if (BuildConfig.DEBUG) {
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }

        val okHttp = OkHttpClient.Builder()
                .addInterceptor { chain ->
                    var request = chain.request()
                    val url = request.url().newBuilder()
                            .addQueryParameter("api-key", nytClientApp.getString(R.string.nyt_api_key))
//                            .addPathSegment("json")
                            .build()

                    request = request.newBuilder().url(url).build()
                    chain.proceed(request)
                }
                .addInterceptor(loggingInterceptor)
                .build()

        val gsonBuilder = GsonBuilder()
        gsonBuilder.setLenient()
        val gsonFactory = GsonConverterFactory.create(gsonBuilder.create())

        return Retrofit.Builder()
                .baseUrl(BASE_NYT_URL)
                .client(okHttp)
                .addConverterFactory(gsonFactory)
                .addCallAdapterFactory(LiveDataCallAdapterFactory())
                .build()
                .create(NYTService::class.java)
    }

}