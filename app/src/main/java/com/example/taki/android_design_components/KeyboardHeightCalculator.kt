package com.example.taki.android_design_components

import android.app.Activity
import android.graphics.Point
import android.graphics.Rect
import android.view.*
import android.widget.PopupWindow

typealias KeyboardHeightChangedListener = (keyboardHeight: Int) -> Unit

class KeyboardHeightCalculator(private val activity: Activity) : PopupWindow() {

    private val measurementView: View by lazy {
        LayoutInflater.from(activity).inflate(R.layout.view_mesurement, null, false)
    }
    private val parentView: View by lazy {
        activity.findViewById(android.R.id.content)
    }

    private var listener: KeyboardHeightChangedListener? = null

    private val globalLayoutListener = ViewTreeObserver.OnGlobalLayoutListener {
        calcHeight()
    }

    init {
        softInputMode = WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE or
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE
        inputMethodMode = INPUT_METHOD_NEEDED
        contentView = measurementView
        width = 0
        height = WindowManager.LayoutParams.MATCH_PARENT
        measurementView.viewTreeObserver.addOnGlobalLayoutListener(globalLayoutListener)
    }

    fun setKeyboardHeightChangedListener(listener: KeyboardHeightChangedListener) {
        this.listener = listener
    }

    fun start() {
        if (!isShowing && parentView.windowToken != null) {
            showAtLocation(parentView, Gravity.NO_GRAVITY, 0, 0)
        }
    }

    fun stop() {
        dismiss()
    }

    fun release() {
        measurementView.viewTreeObserver.removeOnGlobalLayoutListener(globalLayoutListener)
        listener = null
    }

    private fun calcHeight() {
        val point = Point()
        activity.windowManager.defaultDisplay.getSize(point)
        val rect = Rect()
        measurementView.getWindowVisibleDisplayFrame(rect)
        listener?.invoke(point.y - rect.bottom)
    }

}