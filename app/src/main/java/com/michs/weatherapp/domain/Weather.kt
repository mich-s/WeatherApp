package com.michs.weatherapp.domain

data class CurrentWeather(
    val cod: Int,
    val coordinates: Coordinates,
    val weather: List<Weather>,
    val base: String?,
    val main: Main?,
    val visibility: String?,
    val wind: Wind,
    val clouds: Clouds,
    val dt: Long,
    val sys: Sys,
    val timezone: Int,
    val id: Long,
    val name: String
)

data class Coordinates(
    val longitude: Double?,
    val latitude: Double?
)

data class Weather(
    val id: Long?,
    val main: String?,
    val description: String?,
    val icon: String?
)

data class Main(
    val temperature: Double?,
    val feelsLike: Double?,
    val tempMin: Double?,
    val tempMax: Double?,
    val pressure: Int?,
    val humidity: Int?
)

data class Wind(
    val speed: Double?,
    val deg: Int?
)

data class Clouds(
    val all: Int?
)

data class Sys(
    val type: Int?,
    val id: Long?,
    val country: String?,
    val sunrise: Long?,
    val sunset: Long?
)