package com.michs.weatherapp.net

import com.michs.weatherapp.net.dto.CurrentWeatherNet

data class CallResult<CurrentWeatherNet>(val data: CurrentWeatherNet?, val status: ResponseStatus, val message: String? = null) {

    enum class ResponseStatus{
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun success(data: CurrentWeatherNet) = CallResult(data, ResponseStatus.SUCCESS)
        fun error(data: CurrentWeatherNet? = null, message: String?) = CallResult(data, ResponseStatus.ERROR, message)
        fun loading(data: CurrentWeatherNet? = null) = CallResult(data, ResponseStatus.LOADING)
    }
}