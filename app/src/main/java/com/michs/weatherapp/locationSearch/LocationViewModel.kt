package com.michs.weatherapp.locationSearch

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.michs.weatherapp.repository.WeatherRepository
import kotlinx.coroutines.launch

class LocationViewModel (private val repository: WeatherRepository): ViewModel() {

    //todo add currentWeather field - liveData?

    fun getWeather(cityName: String) {
        viewModelScope.launch {
            var currentWeather = repository.getCurrentWeather(cityName)
            Log.d("currentWeather", currentWeather.data.toString())
            Log.d("currentWeather", currentWeather.message.toString())
            Log.d("currentWeather", currentWeather.status.toString())
        }
    }

}