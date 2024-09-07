package dev.shrishri1108.jetpackweaterroomassignment.models

data class Daily(
    val clouds: Any,
    val dew_point: Any,
    val dt: Any,
    val feels_like: FeelsLike,
    val humidity: Any,
    val moon_phase: Any,
    val moonrise: Any,
    val moonset: Any,
    val pop: Any,
    val pressure: Any,
    val rain: Any,
    val sunrise: Any,
    val sunset: Any,
    val temp: Temp,
    val uvi: Any,
    val weather: List<Weather>,
    val wind_deg: Any,
    val wind_gust: Any,
    val wind_speed: Any
)