package com.michs.weatherapp.locationSearch

import com.michs.weatherapp.domain.Coordinates
import com.michs.weatherapp.domain.asNetworkModel
import com.michs.weatherapp.net.CallResult
import com.michs.weatherapp.net.dto.CurrentWeatherNet

class SearchParams (var cityName: String? = null, var coordinates: Coordinates? = null): ISearchParams{
    override fun filterMap(map: Map<Long, CallResult<CurrentWeatherNet>>): Map<Long, CallResult<CurrentWeatherNet>> {
        return map.filterValues {
            if(!cityName.isNullOrBlank())
                it.data?.name == cityName
            else
                it.data?.coordinates == coordinates?.asNetworkModel()
        }
    }

    override fun areParamsEmpty(): Boolean {
        return cityName.isNullOrEmpty() && coordinates == null
    }

    override fun createQueryMap(): Map<String,String> =
        when {
            !cityName.isNullOrBlank() -> mapOf("q" to cityName!!)
            coordinates != null -> mapOf("lat" to coordinates?.latitude.toString(), "lon" to coordinates?.longitude.toString())
            else -> emptyMap()
        }

}

interface ISearchParams{
    fun filterMap(map: Map<Long, CallResult<CurrentWeatherNet>>): Map<Long, CallResult<CurrentWeatherNet>>
    fun areParamsEmpty(): Boolean
    fun createQueryMap(): Map<String,String>
}