package com.example.taki.android_design_components.ext

import android.app.Activity
import android.view.View

fun Activity.resetSystemUI() {
    this.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
}

fun Activity.showSystemUI() {
    this.window.decorView.systemUiVisibility =
        (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
}

fun Activity.hideSystemUI() {
    this.window.decorView.systemUiVisibility =
            // Enables regular sticky immersive mode.
        (View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                // Set the content to appear under the system bars so that the
                // content doesn't resize when the system bars hide and show.
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                // Hide the nav bar and status bar
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
}