package com.michs.weatherapp.net

import com.michs.weatherapp.net.dto.CoordinatesNet
import com.michs.weatherapp.net.dto.CurrentWeatherNet
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrentWeatherCache @Inject constructor(): Cache<Long, CallResult<CurrentWeatherNet>>{

    private val map: MutableMap<Long,CallResult<CurrentWeatherNet>> = mutableMapOf()
    lateinit var filteredMap: Map<Long,CallResult<CurrentWeatherNet>>

    override fun get(key: Long): CallResult<CurrentWeatherNet>? = map[key]
    override fun set(key: Long, value: CallResult<CurrentWeatherNet>) {
        map[key] = value
    }
    override fun remove() {
        map.clear()
    }

    override fun getSize() = map.size

    override fun getByValue(
        cityName: String?,
        coords: CoordinatesNet?
    ): CallResult<CurrentWeatherNet>? {
        var weatherCache: CallResult<CurrentWeatherNet>? = null

        when {
            cityName != null -> filteredMap = map.filterValues { it.data?.name == cityName }
            coords != null -> filteredMap = map.filterValues { it.data?.coordinates == coords }
        }
        if (filteredMap.isNotEmpty())
            weatherCache = map.values.elementAt(0)

        return weatherCache
    }
}

interface Cache<K : Any, V : Any> {
    fun get(key: K): V?
    fun set(key: K, value: V)
    fun remove()
    fun getSize(): Int
    fun getByValue(
        cityName: String?,
        coords: CoordinatesNet?
    ): V?
}