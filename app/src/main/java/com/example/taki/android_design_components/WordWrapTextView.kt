package com.example.taki.android_design_components

import android.content.Context
import android.os.Build
import android.text.Layout
import android.util.AttributeSet
import com.google.android.material.textview.MaterialTextView

/*
 * OSによって、テキストの折り返し位置や行間が異なるケースがあるのを直す
 */
class WordWrapTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : MaterialTextView(context, attrs, defStyleAttr) {

    init {
        initWordWrapSettings()
    }

    private fun initWordWrapSettings() {

        // Android8.0の場合にはBreakStrategyをsimpleに設定する
        // See: https://issuetracker.google.com/issues/64418117
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.O) {
            this.breakStrategy = Layout.BREAK_STRATEGY_SIMPLE
        }

        // Android9.0以降ではFallbackLineSpacingをfalseに設定する
        // https://issuetracker.google.com/issues/122554779
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            this.isFallbackLineSpacing = false
        }
    }
}