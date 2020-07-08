package com.example.taki.android_design_components

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.taki.android_design_components.databinding.ActivityMainBinding
import com.google.android.material.appbar.AppBarLayout

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val navController by lazy {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navHostFragment.navController
    }

    private val appBarStateChangedListener = object : AppBarStateChangedListener() {
        override fun onStateChanged(appBarLayout: AppBarLayout, state: AppBarState) {
            when (state) {
                AppBarState.EXPANDED -> {
                    this@MainActivity.supportActionBar?.title = ""
                }
                AppBarState.COLLAPSED -> {
                    this@MainActivity.supportActionBar?.title = "aaaaa"
                }
                else -> Unit
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        navController.setGraph(R.navigation.nav_main)

        val appBarConfiguration = AppBarConfiguration(navController.graph)

        setupActionBarWithNavController(navController, appBarConfiguration)

//        binding.appBar.addOnOffsetChangedListener(appBarStateChangedListener)
    }
}
