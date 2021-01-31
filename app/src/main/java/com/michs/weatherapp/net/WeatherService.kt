package com.michs.weatherapp.net

import com.michs.weatherapp.net.dto.CurrentWeatherNet
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface WeatherService {

    companion object{
        const val ENDPOINT = "https://api.openweathermap.org/data/2.5/"
        const val UNITS_METRIC = "units=metric"
    }

    @GET("weather?$UNITS_METRIC")
    suspend fun getCurrentWeather(@QueryMap filters: Map<String, String>): Response<CurrentWeatherNet>

}