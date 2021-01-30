package com.michs.weatherapp.repository

import com.michs.weatherapp.domain.CurrentWeather
import com.michs.weatherapp.net.CallResult
import com.michs.weatherapp.net.WeatherService
import com.michs.weatherapp.net.dto.CurrentWeatherNet
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val service: WeatherService) {

    var currentWeather: CurrentWeather? = null

    suspend fun getCurrentWeather(cityName: String) = getCallResult { service.getCurrentWeather(cityName) }

    private suspend fun getCallResult(call: suspend () -> Response<CurrentWeatherNet>): CallResult<CurrentWeatherNet> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return CallResult.success(body)
            }
            return CallResult.error(null, "Response Code: ${response.code()}\nMessage: ${response.message()}")
        }
        catch (ex: Exception){
            return CallResult.error(null, "Network call failed: ${ex.message}")
        }
    }

}