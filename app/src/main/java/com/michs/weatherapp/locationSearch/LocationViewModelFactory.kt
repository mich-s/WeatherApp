package com.michs.weatherapp.locationSearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.michs.weatherapp.repository.WeatherRepository
import javax.inject.Inject

class LocationViewModelFactory @Inject constructor(private val repository: WeatherRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(LocationViewModel::class.java))
            return LocationViewModel(repository) as T
        throw IllegalArgumentException("Unknown view model class")
    }
}