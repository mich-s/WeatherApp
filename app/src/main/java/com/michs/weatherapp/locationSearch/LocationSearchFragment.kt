package com.michs.weatherapp.locationSearch

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.michs.weatherapp.App
import com.michs.weatherapp.databinding.FragmentLocationSearchBinding

class LocationSearchFragment: Fragment() {

    override fun onAttach(context: Context) {
        (requireActivity().application as App).appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentLocationSearchBinding.inflate(inflater, container, false)


        return binding.root
    }
}