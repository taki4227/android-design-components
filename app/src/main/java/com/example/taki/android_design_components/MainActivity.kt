package com.example.taki.android_design_components

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.appbar.AppBarLayout

class MainActivity : AppCompatActivity() {

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
        setContentView(R.layout.activity_main)

//        binding.appBar.addOnOffsetChangedListener(appBarStateChangedListener)
    }
}
