package dev.shrishri1108.jetpackweaterroomassignment.models

data class WeatherResponse(
    val current: Current,
    val daily: List<Daily>,
    val hourly: List<Hourly>,
    val lat: Any,
    val lon: Any,
    val minutely: List<Minutely>,
    val timezone: String,
    val timezone_offset: Any
)