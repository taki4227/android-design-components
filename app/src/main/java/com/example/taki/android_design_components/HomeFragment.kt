package com.example.taki.android_design_components

import android.os.Bundle
import android.view.*
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.taki.android_design_components.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private var showBadge = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.liveButton.setOnClickListener {
            LiveActivity.start(requireActivity())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_home, menu)

        val item = menu.findItem(R.id.announcement)
        item.actionView.setOnClickListener {
            showBadge = showBadge.not()
            requireActivity().invalidateOptionsMenu()
        }

        val badge = item.actionView.findViewById<ImageView>(R.id.badge)
        badge.isVisible = showBadge

        super.onCreateOptionsMenu(menu, inflater)
    }
}