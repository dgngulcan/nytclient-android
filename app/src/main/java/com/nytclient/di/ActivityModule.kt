package com.nytclient.di

import com.nytclient.module.main.MainActivity
import com.nytclient.module.main.MainActivityModule
import com.nytclient.module.main.MainFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector


/**
 * Created by Dogan Gulcan on 9/17/17.
 */
@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules =
    arrayOf(MainActivityModule::class
            , MainFragmentModule::class))
    abstract fun bindMainActivity(): MainActivity

}