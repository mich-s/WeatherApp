package com.michs.weatherapp.net

import com.michs.weatherapp.locationSearch.ISearchParams
import com.michs.weatherapp.net.dto.CurrentWeatherNet
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrentWeatherCache @Inject constructor(): Cache<Long, CallResult<CurrentWeatherNet>>{

    private val map: MutableMap<Long,CallResult<CurrentWeatherNet>> = mutableMapOf()

    override fun get(key: Long): CallResult<CurrentWeatherNet>? = map[key]
    override fun set(key: Long, value: CallResult<CurrentWeatherNet>) {
        map[key] = value
    }
    override fun clear() {
        map.clear()
    }
    override fun getBySearchParams(iSearchParams: ISearchParams): CallResult<CurrentWeatherNet>? {
        var weatherCache: CallResult<CurrentWeatherNet>? = null
        val filteredMap = iSearchParams.filterMap(map)
        if (filteredMap.isNotEmpty())
            weatherCache = filteredMap.values.elementAt(0)
        return weatherCache
    }
}

interface Cache<K : Any, V : Any> {
    fun get(key: K): V?
    fun set(key: K, value: V)
    fun clear()
    fun getBySearchParams(iSearchParams: ISearchParams): CallResult<CurrentWeatherNet>?
}