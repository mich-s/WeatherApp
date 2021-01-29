package com.michs.weatherapp.net.dto

import com.google.gson.annotations.SerializedName

data class CurrentWeatherResponse(
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
    @SerializedName("lon") val longitude: Double?,
    @SerializedName("lat") val latitude: Double?
)

data class Weather(
    val id: Long?,
    val main: String?,
    val description: String?,
    val icon: String?
)

data class Main(
    @SerializedName("temp") val temperature: Double?,
    @SerializedName("feels_like") val feelsLike: Double?,
    @SerializedName("temp_min") val tempMin: Double?,
    @SerializedName("temp_max") val tempMax: Double?,
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