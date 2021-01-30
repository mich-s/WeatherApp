package com.michs.weatherapp.net

import com.michs.weatherapp.net.dto.CurrentWeatherNet
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    companion object{
        const val ENDPOINT = "https://api.openweathermap.org/data/2.5/"
    }

    @GET("weather?")
    suspend fun getCurrentWeather(@Query("q") cityName: String): Response<CurrentWeatherNet>

}