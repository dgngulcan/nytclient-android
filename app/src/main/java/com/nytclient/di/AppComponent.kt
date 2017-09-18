package com.nytclient.di

import com.nytclient.NytClientApp
import com.nytclient.module.main.MainActivityModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Created by Dogan Gulcan on 9/14/17.
 */
@Singleton
@Component(modules = arrayOf(
        AndroidInjectionModule::class,
        AppModule::class,
        ActivityModule::class)
)
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: NytClientApp): Builder

        fun build(): AppComponent
    }

    fun inject(nytClientApp: NytClientApp)

}
