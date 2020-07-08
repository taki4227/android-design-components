package com.example.taki.android_design_components

import com.google.android.material.appbar.AppBarLayout
import kotlin.math.abs

abstract class AppBarStateChangedListener : AppBarLayout.OnOffsetChangedListener {
    var currentState = AppBarState.EXPANDED
        private set

    override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
        appBarLayout ?: return

        when {
            verticalOffset == 0 -> {
                setCurrentStateAndNotify(appBarLayout, AppBarState.EXPANDED);
            }
            abs(verticalOffset) >= appBarLayout.totalScrollRange -> {
                setCurrentStateAndNotify(appBarLayout, AppBarState.COLLAPSED);
            }
            else -> {
                setCurrentStateAndNotify(appBarLayout, AppBarState.CHANGING);
            }
        }
    }

    private fun setCurrentStateAndNotify(appBarLayout: AppBarLayout?, state: AppBarState) {
        appBarLayout ?: return

        if (currentState != state) {
            onStateChanged(appBarLayout, state)
        }
        currentState = state
    }

    abstract fun onStateChanged(appBarLayout: AppBarLayout, state: AppBarState)
}