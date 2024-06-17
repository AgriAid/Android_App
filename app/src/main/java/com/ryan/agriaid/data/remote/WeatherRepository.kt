package com.ryan.agriaid.data.remote

import android.content.Context
import com.ryan.agriaid.BuildConfig
import com.ryan.agriaid.data.local.NewsDatabase
import com.ryan.agriaid.data.local.weather.Weather
import com.ryan.agriaid.data.local.weather.WeatherDao
import com.ryan.agriaid.data.remote.model.ListItem
import com.ryan.agriaid.data.remote.model.WeatherForecastResponse
import com.ryan.agriaid.utility.RainIntensityHelper
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class WeatherRepository(context: Context) {
   private val weatherDao: WeatherDao = NewsDatabase.getInstance(context).weatherDao()

    private val apiKey = BuildConfig.API_KEY
    private val apiService = ApiConfig.getApiServiceWithAuth(apiKey)

    suspend fun getWeatherForecast(lat: String, lon: String, lang: String): Response<WeatherForecastResponse> {
        return apiService.getWeatherForecast(lat, lon, apiKey, lang, "metric")
    }

    fun calculateAverageTempAndHumidity(weatherList: List<ListItem>): Pair<Double, Double> {
        var totalTemp = 0.0
        var totalHumidity = 0.0

        for (weatherItem in weatherList) {
            weatherItem.main?.temp?.let { totalTemp += it }
            weatherItem.main?.humidity?.let { totalHumidity += it }
        }

        val averageTemp = totalTemp / weatherList.size
        val averageHumidity = totalHumidity / weatherList.size

        return Pair(averageTemp, averageHumidity)
    }

    fun calculateRainfall(weatherList: List<ListItem>): Double {
        var totalRainfall = 0.0
        for (weatherItem in weatherList) {
            val weatherCodes = weatherItem.weather?.mapNotNull { it?.id } ?: emptyList()
            val rainfallForItem = RainIntensityHelper.getTotalRainfall(weatherCodes)
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