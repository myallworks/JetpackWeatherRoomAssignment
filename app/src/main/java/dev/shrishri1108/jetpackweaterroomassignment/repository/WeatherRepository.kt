package dev.shrishri1108.jetpackweaterroomassignment.repository

import dev.shrishri1108.jetpackweaterroomassignment.api.WeatherApiService
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val weatherApiService: WeatherApiService
) {
    suspend fun getWeather(lat: String, lon: String, apiKey: String) =
        weatherApiService.getWeatherData(lat, lon)
}

