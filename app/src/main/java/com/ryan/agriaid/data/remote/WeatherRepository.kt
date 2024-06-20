package com.ryan.agriaid.data.remote

import android.content.Context
import com.ryan.agriaid.BuildConfig
import com.ryan.agriaid.data.local.NewsDatabase
import com.ryan.agriaid.data.local.weather.Weather
import com.ryan.agriaid.data.local.weather.WeatherDao
import com.ryan.agriaid.data.remote.model.WeatherForecastResponse
import com.ryan.agriaid.utility.RainIntensityHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import retrofit2.Response

class WeatherRepository(context: Context) {
   private val weatherDao: WeatherDao = NewsDatabase.getInstance(context).weatherDao()

    private val apiKey = BuildConfig.API_KEY
    private val apiService = ApiConfig.getApiServiceWithAuth(apiKey)

    suspend fun getWeatherForecast(lat: String, lon: String, lang: String): Response<WeatherForecastResponse> {
        return apiService.getWeatherForecast(lat, lon, apiKey, lang, "metric")
    }

    suspend fun avgTempAndHumidityRainfall(): Triple<Double, Double, Double> {
        val weatherList = getAllSavedWeather().first()
        var totalTemp = 0.0
        var totalHumidity = 0.0
        val rainFall = calculateRainfall(weatherList)

        for (weatherItem in weatherList) {
            weatherItem.temp.let { totalTemp += it }
            weatherItem.humidity.let { totalHumidity += it }
        }

        val averageTemp = if (weatherList.isNotEmpty()) totalTemp / weatherList.size else 0.0
        val averageHumidity = if (weatherList.isNotEmpty()) totalHumidity / weatherList.size else 0.0

        return Triple(averageTemp, averageHumidity, rainFall)
    }

    fun calculateRainfall(weatherList: List<Weather>): Double {
        var totalRainfall = 0.0
        for (weatherItem in weatherList) {
            val codes = weatherList.map { it.code }
            val rainfallForItem = RainIntensityHelper.getTotalRainfall(codes)
            totalRainfall += rainfallForItem
        }
        return totalRainfall
    }

    suspend fun saveWeatherData(weatherData: List<Weather>) {
        weatherDao.insertAll(weatherData)
    }

    fun getAllSavedWeather(): Flow<List<Weather>> {
        return weatherDao.getAllWeather()
    }

    suspend fun deleteAllSavedWeather() {
        weatherDao.deleteAll()
    }
}