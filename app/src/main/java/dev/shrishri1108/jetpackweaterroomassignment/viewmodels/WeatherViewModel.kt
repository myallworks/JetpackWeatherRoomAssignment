package dev.shrishri1108.jetpackweaterroomassignment.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import dev.shrishri1108.jetpackweaterroomassignment.api.WeatherApiService
import dev.shrishri1108.jetpackweaterroomassignment.models.WeatherResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val weatherService: WeatherApiService
) : ViewModel() {

    private val _weatherData = MutableStateFlow<WeatherResponse?>(null)
    val weatherState: StateFlow<WeatherResponse?> = _weatherData

    fun getWeather() {
        viewModelScope.launch {
            try {
                val response = weatherService.getWeatherData(
                    lat = "12.9082847623315",
                    lon = "77.65197822993314",
                    units = "imperial",
                    appid = "b143bb707b2ee117e62649b358207d3e"
                )
                _weatherData.value = response
            } catch (e: Exception) {
                Log.e("WeatherViewModel", "Error fetching weather data", e)
            }
        }
    }
}

class WeatherViewModelFactory(
    private val weatherService: WeatherApiService
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
            return WeatherViewModel(weatherService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

