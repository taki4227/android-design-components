package com.example.taki.android_design_components

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.taki.android_design_components.databinding.FragmentLiveBinding
import com.example.taki.android_design_components.widget.KeyboardHeightCalculator
import com.example.taki.android_design_components.widget.KeyboardState

class LiveFragment : Fragment() {

    private lateinit var binding: FragmentLiveBinding

    private val keyboardHeightCalculator: KeyboardHeightCalculator by lazy {
        KeyboardHeightCalculator(requireActivity())
    }

    private val liveViewModel by viewModels<LiveViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLiveBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = liveViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        keyboardHeightCalculator.setKeyboardEventListener(object :
            KeyboardHeightCalculator.KeyboardEventListener {
            override fun onKeyboardHeightChanged(keyboardHeight: Int) {
                ConstraintSet().run {
                    clone(binding.container)
                    setMargin(binding.commentContainer.id, ConstraintSet.BOTTOM, keyboardHeight)
                    applyTo(binding.container)
                }
            }

            override fun onKeyboardStateChanged(state: KeyboardState) {
                liveViewModel.changeKeyboardState(state)

                when (state) {
                    KeyboardState.COLLAPSED -> {
                        binding.commentField.clearFocus()
                    }
                    KeyboardState.EXPANDED -> {
                        binding.commentField.requestFocus()
                    }
                }
            }
        })
    }

    override fun onResume() {
        super.onResume()

        binding.container.post {
            keyboardHeightCalculator.start()
        }
    }

    override fun onPause() {
        keyboardHeightCalculator.stop()
        super.onPause()
    }

    override fun onDestroyView() {
        keyboardHeightCalculator.release()
        super.onDestroyView()
    }
}