package com.nytclient.di

import com.nytclient.NytClientApp
import com.nytclient.data.api.ApiModule
import com.nytclient.data.db.DataBaseModule
import com.nytclient.util.UtilsModule
import dagger.Module


/**
 * Application scope module.
 *
 * Created by Dogan Gulcan on 9/15/17.
 */
@Module(includes = arrayOf(
        UtilsModule::class,
        DataBaseModule::class,
        ApiModule::class)
)
class AppModule(private val application: NytClientApp) {
//
//    @Provides
//    @Singleton
//    fun provideContext(application: NytClientApp): NytClientApp {
//        return application
//    }
}