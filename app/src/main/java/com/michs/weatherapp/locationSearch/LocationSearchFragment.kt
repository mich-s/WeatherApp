package com.michs.weatherapp.locationSearch

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.michs.weatherapp.App
import com.michs.weatherapp.databinding.FragmentLocationSearchBinding
import com.michs.weatherapp.repository.WeatherRepository
import javax.inject.Inject

class LocationSearchFragment: Fragment() {

    @Inject lateinit var repository: WeatherRepository

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

        val etCity = binding.etSearchCity
        val searchBtn = binding.btnSearch

        val viewModel = ViewModelProvider(this, LocationViewModelFactory(repository)).get(LocationViewModel::class.java)

        searchBtn.setOnClickListener {
            viewModel.getWeather(etCity.text.toString())
        }

        return binding.root
    }
}