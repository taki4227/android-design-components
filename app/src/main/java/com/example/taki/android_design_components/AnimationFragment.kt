package com.example.taki.android_design_components

import android.animation.*
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.taki.android_design_components.databinding.FragmentAnimationBinding
import com.example.taki.android_design_components.ext.dpToPx

class AnimationFragment : Fragment() {

    private lateinit var binding: FragmentAnimationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAnimationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fadeInButton.setOnClickListener {
            val objectAnimator = ObjectAnimator.ofFloat(binding.view, "alpha", 0F, 1.0F)
            objectAnimator.duration = ANIMATION_DURATION
            objectAnimator.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    binding.view.isVisible = true
                }
            })
            objectAnimator.start()
        }

        binding.fadeOutButton.setOnClickListener {
            val objectAnimator = ObjectAnimator.ofFloat(binding.view, "alpha", 1.0F, 0F)
            objectAnimator.duration = ANIMATION_DURATION
            objectAnimator.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    binding.view.isVisible = false
                }
            })
            objectAnimator.start()
        }

        binding.changeStartColorButton.setOnClickListener {
            val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), Color.BLUE, Color.RED)
            colorAnimation.duration = ANIMATION_DURATION
            colorAnimation.addUpdateListener { animator ->
                binding.view.setBackgroundColor(
                    animator.animatedValue as Int
                )
            }
            colorAnimation.start()
        }

        binding.changeEndColorButton.setOnClickListener {
            val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), Color.RED, Color.BLUE)
            colorAnimation.duration = ANIMATION_DURATION
            colorAnimation.addUpdateListener { animator ->
                binding.view.setBackgroundColor(
                    animator.animatedValue as Int
                )
            }
            colorAnimation.start()
        }

        binding.expandViewButton.setOnClickListener {
            val width = binding.view.width
            val widthDiff = 200.dpToPx() - width

            val floatAnimation = ValueAnimator.ofFloat(0F, 1.0F)
            floatAnimation.duration = ANIMATION_DURATION
            floatAnimation.addUpdateListener { animator ->
                val value = animator.animatedValue as Float

                binding.view.run {
                    layoutParams.width = width + (widthDiff * value).toInt()
                    requestLayout()
                }
            }
            floatAnimation.start()
        }

        binding.collapseViewButton.setOnClickListener {
            val width = binding.view.width
            val widthDiff = 100.dpToPx() - width

            val floatAnimation = ValueAnimator.ofFloat(0F, 1.0F)
            floatAnimation.duration = ANIMATION_DURATION
            floatAnimation.addUpdateListener { animator ->
                val value = animator.animatedValue as Float

                binding.view.run {
                    layoutParams.width = width + (widthDiff * value).toInt()
                    requestLayout()
                }
            }
            floatAnimation.start()
        }
    }

    companion object {
        private const val ANIMATION_DURATION = 200L
    }
}