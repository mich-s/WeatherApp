package com.michs.weatherapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.michs.weatherapp.repository.WeatherRepository
import javax.inject.Inject

class WeatherViewModelFactory @Inject constructor(private val repository: WeatherRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(WeatherViewModel::class.java))
            return WeatherViewModel(repository) as T
        throw IllegalArgumentException("Unknown view model class")
    }
}