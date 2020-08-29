package com.example.taki.android_design_components

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLiveBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        keyboardHeightCalculator.setKeyboardHeightChangedListener {
            // EditTextをズラしてる
            ConstraintSet().run {
                clone(binding.container)
                setMargin(R.id.edit, ConstraintSet.BOTTOM, it)
                applyTo(binding.container)
            }
        }
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