package dev.shrishri1108.jetpackweaterroomassignment.api

import dev.shrishri1108.jetpackweaterroomassignment.models.WeatherResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("onecall")
    suspend fun getWeatherData(
        @Query("lat") lat: String = "12.9082847623315",
        @Query("lon") lon: String = "77.65197822993314",
        @Query("units") units: String = "imperial",
        @Query("appid") appid: String = "b143bb707b2ee117e62649b358207d3e"
    ): WeatherResponse
}

// Retrofit instance
val retrofit = Retrofit.Builder()
    .baseUrl("https://api.openweathermap.org/data/2.5/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val weatherService = retrofit.create(WeatherApiService::class.java)
