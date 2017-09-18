package com.nytclient.module.main

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import com.nytclient.R
import com.nytclient.databinding.ActivityMainBinding
import com.nytclient.ui.common.BaseActivity

class MainActivity : BaseActivity() {

    private val navController = MainNavigationController(this)
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.bottomNavigationBar.setOnNavigationItemSelectedListener(OnNavigationItemSelectedListener)
        if (savedInstanceState == null) {
            binding.bottomNavigationBar.selectedItemId = R.id.nav_news  /* default tab */
        }

        init()
    }

    private fun init() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = getString(R.string.app_name)
    }

    private val OnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.nav_news -> {
                navController.navigateToNewsFragment()
                true
            }
            R.id.nav_books -> {
                navController.navigateToBooksFragment()
                true
            }
            R.id.nav_about -> {
                navController.navigateToAboutFragment()
                true
            }
            else -> false

        }
    }
}
