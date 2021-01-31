package com.michs.weatherapp.locationSearch

import androidx.lifecycle.*
import com.michs.weatherapp.net.CallResult
import com.michs.weatherapp.net.dto.CurrentWeatherNet
import com.michs.weatherapp.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers

class LocationViewModel (private val repository: WeatherRepository): ViewModel() {

    val cityName = MutableLiveData<String>()
    init {
        cityName.value = ""
    }

    val currentWeather: LiveData<CallResult<CurrentWeatherNet>>
    get() = _currentWeather

    private val _currentWeather =  cityName.switchMap {
        return@switchMap getCurrentWeather(it)
    }

    private fun getCurrentWeather(cityName: String?) =
        liveData(Dispatchers.IO) {
            emit(CallResult.loading())
            val currentWeather = repository.getCurrentWeather(cityName)
            emit(currentWeather)
        }

}