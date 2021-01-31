package com.michs.weatherapp.repository

import com.michs.weatherapp.domain.Coordinates
import com.michs.weatherapp.domain.asNetworkModel
import com.michs.weatherapp.locationSearch.SearchParams
import com.michs.weatherapp.net.CallResult
import com.michs.weatherapp.net.CurrentWeatherCache
import com.michs.weatherapp.net.WeatherService
import com.michs.weatherapp.net.dto.CoordinatesNet
import com.michs.weatherapp.net.dto.CurrentWeatherNet
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepository @Inject constructor(private val service: WeatherService, private val cache: CurrentWeatherCache) {

    private lateinit var callResult: CallResult<CurrentWeatherNet>

    suspend fun getCurrentWeather(searchParams: SearchParams): CallResult<CurrentWeatherNet> {
        val cityName = searchParams.cityName
        val coords = searchParams.coordinates
        if (cityName.isNullOrEmpty() && coords == null)
            return CallResult.error(message = "No search params")

        var cityId = -1L
        val weatherFromCache = cache.getByValue(cityName, coords?.asNetworkModel())
        if (weatherFromCache?.data != null){
            cityId = weatherFromCache.data.id
        }
        if(cityId > -1){
            val cachedCallResult = cache.get(cityId)
            if(cachedCallResult != null) {
                return cachedCallResult
            }
        }

        when{
            cityName != null -> callResult = getCallResult { service.getCurrentWeatherByCityName(cityName) }
            coords != null -> callResult = getCallResult { service.getCurrentWeatherByCoordinates(coords.latitude.toString(), coords.longitude.toString()) }
        }

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