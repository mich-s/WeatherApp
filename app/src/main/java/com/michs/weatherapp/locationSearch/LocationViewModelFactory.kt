package com.michs.weatherapp.locationSearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.michs.weatherapp.net.WeatherService
import java.lang.IllegalArgumentException
import javax.inject.Inject

class LocationViewModelFactory @Inject constructor(private val service: WeatherService): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(LocationViewModel::class.java))
            return LocationViewModel(service) as T
        throw IllegalArgumentException("Unknown view model class")
    }
}