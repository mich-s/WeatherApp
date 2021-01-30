package com.michs.weatherapp.net

import com.michs.weatherapp.net.dto.CurrentWeatherNet
import javax.inject.Inject

class CurrentWeatherCache @Inject constructor(): Cache<Int, CallResult<CurrentWeatherNet>>{

    private val map: MutableMap<Int,CallResult<CurrentWeatherNet>> = mutableMapOf()

    override fun get(key: Int): CallResult<CurrentWeatherNet>? = map[key]
    override fun set(key: Int, value: CallResult<CurrentWeatherNet>) {
        map[key] = value
    }
}

interface Cache<K : Any, V : Any> {
    fun get(key: K): V?
    fun set(key: K, value: V)
}