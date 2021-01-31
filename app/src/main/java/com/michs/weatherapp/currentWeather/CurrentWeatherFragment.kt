package com.michs.weatherapp.currentWeather

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.michs.weatherapp.App
import com.michs.weatherapp.databinding.FragmentCurrentWeatherBinding
import com.michs.weatherapp.net.dto.asDomainModel
import com.michs.weatherapp.repository.WeatherRepository
import com.michs.weatherapp.viewModel.WeatherViewModel
import com.michs.weatherapp.viewModel.WeatherViewModelFactory
import javax.inject.Inject

class CurrentWeatherFragment: Fragment() {

    @Inject
    lateinit var repository: WeatherRepository
    private val viewModel: WeatherViewModel by activityViewModels { WeatherViewModelFactory(repository)}

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
        binding.data = viewModel.currentWeather.value?.data?.asDomainModel()
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }
}