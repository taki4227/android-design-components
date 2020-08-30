package com.example.taki.android_design_components

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.taki.android_design_components.widget.KeyboardState

class LiveViewModel : ViewModel() {
    private val _keyboardState = MutableLiveData(KeyboardState.COLLAPSED)
    val keyboardState: LiveData<KeyboardState> = _keyboardState

    fun changeKeyboardState(state: KeyboardState) {
        _keyboardState.value = state
    }
}