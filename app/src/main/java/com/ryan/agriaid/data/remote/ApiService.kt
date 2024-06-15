package com.ryan.agriaid.data.remote

import com.ryan.agriaid.data.remote.model.WeatherForecastResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("forecast")
    suspend fun getWeatherForecast(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("appid") appId: String,
        @Query("lang") lang: String,
        @Query("units") units: String = "metric"
    ): Response<WeatherForecastResponse>
}