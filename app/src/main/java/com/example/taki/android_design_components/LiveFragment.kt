package com.example.taki.android_design_components

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import com.example.taki.android_design_components.databinding.FragmentLiveBinding


class LiveFragment : Fragment() {

    private lateinit var binding: FragmentLiveBinding

    private val keyboardHeightCalculator: KeyboardHeightCalculator by lazy {
        KeyboardHeightCalculator(requireActivity())
    }

    private var keyboardState = KeyboardState.COLLAPSED

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLiveBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        keyboardHeightCalculator.setKeyboardEventListener(object :
            KeyboardHeightCalculator.KeyboardEventListener {
            override fun onKeyboardHeightChanged(keyboardHeight: Int) {
                ConstraintSet().run {
                    clone(binding.container)
                    setMargin(R.id.comment_container, ConstraintSet.BOTTOM, keyboardHeight)
                    applyTo(binding.container)
                }
            }

            override fun onKeyboardStateChanged(state: KeyboardState) {
                if (keyboardState == state) return
                keyboardState = state

                val colorAnimation: ValueAnimator
                when (state) {
                    KeyboardState.COLLAPSED -> {
                        binding.commentField.clearFocus()
                        colorAnimation =
                            ValueAnimator.ofObject(ArgbEvaluator(), Color.LTGRAY, Color.TRANSPARENT)
                    }
                    KeyboardState.EXPANDED -> {
                        binding.commentField.requestFocus()
                        colorAnimation =
                            ValueAnimator.ofObject(ArgbEvaluator(), Color.TRANSPARENT, Color.LTGRAY)
                    }
                }

                colorAnimation.duration = 200
                colorAnimation.addUpdateListener { animator ->
                    binding.commentContainer.setBackgroundColor(
                        animator.animatedValue as Int
                    )
                }
                colorAnimation.start()
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