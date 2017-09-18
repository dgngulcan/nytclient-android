package com.nytclient.data.db

import android.arch.persistence.room.Room
import com.nytclient.NytClientApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Dogan Gulcan on 9/18/17.
 */
@Module
class DataBaseModule {

    @Singleton
    @Provides
    fun providesNewsDatabase(application: NytClientApp): AppDatabase =
            Room.databaseBuilder(application,
                    AppDatabase::class.java,
                    "nyt_client.db").build()

    @Singleton
    @Provides
    fun providesNewsDao(database: AppDatabase) = database.newsDao()
}