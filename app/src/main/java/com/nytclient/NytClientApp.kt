package com.nytclient

import android.app.Activity
import android.app.Application
import com.nytclient.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * Created by Dogan Gulcan on 9/13/17.
 */
class NytClientApp : Application(), HasActivityInjector {

    @Suppress("MemberVisibilityCanPrivate")
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        initDagger()
    }

    private fun initDagger() {
        DaggerAppComponent
                .builder()
                .application(this)
                .build()
                .inject(this)

    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingAndroidInjector
    }


}