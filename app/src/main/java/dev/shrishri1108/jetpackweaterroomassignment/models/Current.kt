package dev.shrishri1108.jetpackweaterroomassignment.models

data class Current(
    val clouds: Any,
    val dew_point: Any,
    val dt: Any,
    val feels_like: Any,
    val humidity: Any,
    val pressure: Any,
    val sunrise: Any,
    val sunset: Any,
    val temp: Any,
    val uvi: Any,
    val visibility: Any,
    val weather: List<Weather>,
    val wind_deg: Any,
    val wind_speed: Any
)