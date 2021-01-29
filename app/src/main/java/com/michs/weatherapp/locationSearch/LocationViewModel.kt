package com.michs.weatherapp.locationSearch

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.michs.weatherapp.net.WeatherService
import kotlinx.coroutines.launch

class LocationViewModel (private val service: WeatherService): ViewModel(){

    fun getWeather(cityName: String) {
        viewModelScope.launch {
            var currentWeather = service.getCurrentWeather(cityName)
            Log.d("currentWeather", currentWeather.body()?.toString() + " ")
        }
    }

}