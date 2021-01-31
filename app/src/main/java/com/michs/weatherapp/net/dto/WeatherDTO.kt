package com.michs.weatherapp.net.dto

import com.google.gson.annotations.SerializedName
import com.michs.weatherapp.domain.*

data class CurrentWeatherNet(
    val cod: Int,
    @SerializedName("coord") val coordinates: CoordinatesNet,
    val weather: List<WeatherNet>,
    val base: String?,
    val main: MainNet?,
    val visibility: String?,
    val wind: WindNet,
    val clouds: CloudsNet,
    val dt: Long,
    val sys: SysNet,
    val timezone: Int,
    val id: Long,
    val name: String
)

data class CoordinatesNet(
    @SerializedName("lon") val longitude: Double?,
    @SerializedName("lat") val latitude: Double?
)

data class WeatherNet(
    val id: Long?,
    val main: String?,
    val description: String?,
    val icon: String?
)

data class MainNet(
    @SerializedName("temp") val temperature: Double?,
    @SerializedName("feels_like") val feelsLike: Double?,
    @SerializedName("temp_min") val tempMin: Double?,
    @SerializedName("temp_max") val tempMax: Double?,
    val pressure: Int?,
    val humidity: Int?
)

data class WindNet(
    val speed: Double?,
    val deg: Int?
)

data class CloudsNet(
    val all: Int?
)

data class SysNet(
    val type: Int?,
    val id: Long?,
    val country: String?,
    val sunrise: Long?,
    val sunset: Long?
)

fun CurrentWeatherNet.asDomainModel(): CurrentWeather =
    CurrentWeather(
        cod = cod,
        coordinates = coordinates.asDomainModel(),
        weather= weather.asDomainModel(),
        base =base,
        main = main?.asDomainModel(),
        visibility = visibility,
        wind = wind.asDomainModel(),
        clouds = clouds.asDomainModel(),
        dt = dt,
        sys = sys.asDomainModel(),
        timezone = timezone,
        id = id,
        name = name
    )

fun CoordinatesNet.asDomainModel(): Coordinates =
    Coordinates(
        longitude = longitude,
        latitude = latitude
    )
fun List<WeatherNet>.asDomainModel(): List<Weather> =
    map {
        Weather(
            id = it.id,
            main = it.main,
            description = it.description,
            icon = it.icon
        )
    }
fun MainNet.asDomainModel(): Main =
    Main(
        temperature = temperature,
        feelsLike = feelsLike,
        tempMin = tempMin,
        tempMax = tempMax,
        pressure = pressure,
        humidity = humidity
    )

fun WindNet.asDomainModel(): Wind =
    Wind(
        speed = speed,
        deg = deg
    )
fun CloudsNet.asDomainModel(): Clouds =
    Clouds(
        all = all
    )
fun SysNet.asDomainModel(): Sys =
    Sys(
        type = type,
        id = id,
        country = country,
        sunrise = sunrise,
        sunset = sunset
    )