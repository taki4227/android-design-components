package com.example.taki.android_design_components

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import com.example.taki.android_design_components.databinding.ActivityLiveBinding
import com.example.taki.android_design_components.ext.hideSystemUI
import com.example.taki.android_design_components.ext.showSystemUI

class LiveActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLiveBinding

    private val navController by lazy {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navHostFragment.navController
    }

    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_live)

        showSystemUI()
        handler.postDelayed(Runnable { hideSystemUI() }, 2000L)

        // Prevent to screenshot and screen record
        window.addFlags(WindowManager.LayoutParams.FLAG_SECURE)

        navController.setGraph(R.navigation.nav_live)
    }

    companion object {
        fun start(activity: Activity) {
            activity.startActivity(Intent(activity, LiveActivity::class.java))
        }
    }
}
