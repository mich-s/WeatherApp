package com.michs.weatherapp.repository

import com.michs.weatherapp.locationSearch.ISearchParams
import com.michs.weatherapp.net.CallResult
import com.michs.weatherapp.net.CurrentWeatherCache
import com.michs.weatherapp.net.WeatherService
import com.michs.weatherapp.net.dto.CurrentWeatherNet
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepository @Inject constructor(private val service: WeatherService, private val cache: CurrentWeatherCache) {

    private lateinit var callResult: CallResult<CurrentWeatherNet>

    suspend fun getCurrentWeather(searchParams: ISearchParams): CallResult<CurrentWeatherNet> {
        if (searchParams.areParamsEmpty())
            return CallResult.error(message = "No search params")

        val weatherFromCache = cache.getBySearchParams(searchParams)
        if (weatherFromCache?.data != null){
            return weatherFromCache
        }
        callResult = getCallResult {service.getCurrentWeather(searchParams.createQueryMap())}
        val d = callResult.data
        if (d != null) {
            val id = d.id
            cache.set(id, callResult)
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