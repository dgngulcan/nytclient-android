package com.nytclient.util

import android.text.format.DateUtils
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Application scope util module.
 *
 * Created by Dogan Gulcan on 9/16/17.
 */
@Module
internal class UtilsModule {

    @Singleton
    @Provides
    fun provideApplicationContext(): DateUtils {
        return DateUtils()
    }

}