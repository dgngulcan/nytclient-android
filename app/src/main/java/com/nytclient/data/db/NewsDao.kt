package com.nytclient.data.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.nytclient.data.model.NewsItem

/**
 * Created by Dogan Gulcan on 9/13/17.
 */
@Dao
interface NewsDao {

    /**
     * @Insert a list of NewsItems to database. If the news already exists, replaces it.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNews(news: List<NewsItem>)


    /**
     * @return all news in the database
     */
    @Query("SELECT * FROM news")
    fun getAllNews(): LiveData<List<NewsItem>>


}
