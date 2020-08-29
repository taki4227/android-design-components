package com.example.taki.android_design_components

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.taki.android_design_components.databinding.FragmentLiveBinding

class LiveFragment : Fragment() {

    private lateinit var binding: FragmentLiveBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLiveBinding.inflate(inflater, container, false)
        return binding.root
    }
}