package com.ryan.agriaid.data.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ryan.agriaid.data.local.weather.Weather
import com.ryan.agriaid.data.remote.model.ListItem
import com.ryan.agriaid.data.remote.model.WeatherForecastResponse
import com.ryan.agriaid.utility.toWeatherList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import retrofit2.Response

class WeatherViewModel(private val weatherRepository: WeatherRepository) : ViewModel() {

    private val _weatherForecast = MutableLiveData<WeatherForecastResponse>()
    val weatherForecast: LiveData<WeatherForecastResponse> = _weatherForecast

    fun getWeatherForecast(lat: String, lon: String, lang: String) {
        viewModelScope.launch {
            val response: Response<WeatherForecastResponse> = weatherRepository.getWeatherForecast(lat, lon, lang)
            if (response.isSuccessful) {
                val weatherResponse: WeatherForecastResponse? = response.body()
                weatherResponse?.let {
                    _weatherForecast.postValue(it)
                    saveWeatherData(it)
                }
            }
        }
    }

    fun getAverageTempAndHumidity(weatherList: List<ListItem>): Pair<Double, Double> {
        return weatherRepository.calculateAverageTempAndHumidity(weatherList)
    }

    fun getRainfallData(weatherList: List<ListItem>): Double {
        return weatherRepository.calculateRainfall(weatherList)
    }

    private fun saveWeatherData(weatherResponse: WeatherForecastResponse) {
        Log.e("test", "weather data mentah: ${weatherResponse.list?.size}")
        val weatherList = weatherResponse.toWeatherList()
        val cityName = weatherResponse.city?.name ?: ""
        val weatherEntities = weatherList.map {
            Weather(
                code = it.code,
                temp = it.temp,
                humidity = it.humidity,
                feelsLike = it.feelsLike,
                city = cityName,
                datetime = it.datetime
            )
        }
        Log.e("test", "weather data conversion: ${weatherEntities.size}")
        viewModelScope.launch {
            weatherRepository.deleteAllSavedWeather()
            weatherRepository.saveWeatherData(weatherEntities)
        }
    }

    fun getAllSavedWeather(): Flow<List<Weather>> {
        return weatherRepository.getAllSavedWeather()
    }
}
