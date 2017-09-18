package com.nytclient.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.nytclient.data.model.NewsItem
import javax.inject.Singleton

/**
 * Created by Dogan Gulcan on 9/13/17.
 */
@Singleton
@Database(entities = arrayOf(NewsItem::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao

}
