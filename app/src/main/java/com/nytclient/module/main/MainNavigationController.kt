package com.nytclient.module.main

import com.nytclient.R
import com.nytclient.module.news.NewsFragment
import org.jetbrains.anko.toast

/**
 * Controller class to handles navigation in [MainActivity]
 *
 * Created by Dogan Gulcan on 9/17/17.
 */
class MainNavigationController constructor(private val activity: MainActivity) {

    private val fragmentManager = activity.supportFragmentManager
    private val containerId = R.id.fragmentContainer

    private val newsFragment by lazy {
        NewsFragment()
    }

    fun navigateToNewsFragment() {
        fragmentManager.beginTransaction()
                .replace(containerId, newsFragment)
                .commitAllowingStateLoss()
    }

    fun navigateToBooksFragment() {
        activity.toast(R.string.yet_to_implement)
    }

    fun navigateToAboutFragment() {
        activity.toast(R.string.yet_to_implement)
    }

}