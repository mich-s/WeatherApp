package com.michs.weatherapp.locationSearch

import com.michs.weatherapp.domain.Coordinates

data class SearchParams (var cityName: String? = null, var coordinates: Coordinates? = null)