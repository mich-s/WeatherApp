package com.michs.weatherapp.viewModel

import androidx.lifecycle.*
import com.michs.weatherapp.locationSearch.SearchParams
import com.michs.weatherapp.net.CallResult
import com.michs.weatherapp.net.dto.CurrentWeatherNet
import com.michs.weatherapp.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Singleton
class WeatherViewModel (private val repository: WeatherRepository): ViewModel() {

    val searchParams = MutableLiveData<SearchParams>()

    val currentWeather: LiveData<CallResult<CurrentWeatherNet>>
    get() = _currentWeather

    private val _currentWeather =  searchParams.switchMap {
        return@switchMap getCurrentWeather(it)
    }

    private fun getCurrentWeather(searchParams: SearchParams) =
        liveData(Dispatchers.IO) {
            emit(CallResult.loading())
            val currentWeather = repository.getCurrentWeather(searchParams)
            emit(currentWeather)
        }

}