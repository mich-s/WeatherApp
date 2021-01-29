package com.michs.weatherapp.repository

import com.michs.weatherapp.net.WeatherService
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val service: WeatherService) {

    suspend fun getCurrentWeather(cityName: String) = service.getCurrentWeather(cityName)

}