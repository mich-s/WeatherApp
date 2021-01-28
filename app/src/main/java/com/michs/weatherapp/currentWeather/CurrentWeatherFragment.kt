package com.michs.weatherapp.currentWeather

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.michs.weatherapp.App
import com.michs.weatherapp.databinding.FragmentCurrentWeatherBinding

class CurrentWeatherFragment: Fragment() {


    override fun onAttach(context: Context) {
        (requireActivity().application as App).appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCurrentWeatherBinding.inflate(inflater, container, false)


        return binding.root
    }
}