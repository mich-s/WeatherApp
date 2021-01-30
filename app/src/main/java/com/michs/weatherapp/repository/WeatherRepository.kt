package com.michs.weatherapp.repository

import com.michs.weatherapp.net.CallResult
import com.michs.weatherapp.net.CurrentWeatherCache
import com.michs.weatherapp.net.WeatherService
import com.michs.weatherapp.net.dto.CurrentWeatherNet
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepository @Inject constructor(private val service: WeatherService, private val cache: CurrentWeatherCache) {

    private var cityId = -1

    suspend fun getCurrentWeather(cityName: String): CallResult<CurrentWeatherNet> {
        val cached = cache.get(cityId)
        if(cached != null) {
            return cached
        }
        val callResult = getCallResult { service.getCurrentWeather(cityName) }
        if (callResult.data != null) {
            val id = callResult.data.id.toInt()
            cache.set(id, callResult)
            cityId = id
        }

        return callResult
    }

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