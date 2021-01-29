package com.michs.weatherapp.net

import com.michs.weatherapp.net.dto.CurrentWeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("weather?")
    suspend fun getCurrentWeather(@Query("q") cityName: String): Response<CurrentWeatherResponse>

}