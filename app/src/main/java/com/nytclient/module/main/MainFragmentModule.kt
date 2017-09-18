package com.nytclient.module.main

import com.nytclient.module.news.NewsFragment
import com.nytclient.module.news.NewsFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Dogan Gulcan on 9/17/17.
 */
@Module
abstract class MainFragmentModule {

    @ContributesAndroidInjector(modules = arrayOf(NewsFragmentModule::class))
    abstract fun provideNewsFragment(): NewsFragment

}