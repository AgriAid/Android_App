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
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import retrofit2.Response
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class WeatherViewModel(private val weatherRepository: WeatherRepository) : ViewModel() {

    private val _weather = MutableLiveData<Weather>()
    val weatherData: LiveData<Weather> = _weather

    fun getWeatherForecast(lat: String, lon: String, lang: String) {
        viewModelScope.launch {
            val response: Response<WeatherForecastResponse> =
                weatherRepository.getWeatherForecast(lat, lon, lang)
            if (response.isSuccessful) {
                val weatherResponse: WeatherForecastResponse? = response.body()
                weatherResponse?.let {
                    val weatherList = mappingWeatherList(it)
                    saveWeatherData(weatherList)
                    _weather.postValue(weatherList[0])
                }
            }
        }
    }

    fun getAverageTempAndHumidity(weatherList: List<ListItem>): Pair<Double, Double> {
        return weatherRepository.calculateAverageTempAndHumidity(weatherList)
    }

    private fun saveWeatherData(weatherList: List<Weather>) {
        viewModelScope.launch {
            weatherRepository.deleteAllSavedWeather()
            weatherRepository.saveWeatherData(weatherList)
        }
    }

    suspend fun getWeatherByDateTime(dateTime: String) {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val dateTimeToFind = LocalDateTime.parse(dateTime, formatter)

        val allWeatherFlow = weatherRepository.getAllSavedWeather()
        val weatherList = allWeatherFlow.first()

        var previousWeather: Weather? = null

        for (weather in weatherList) {
            val weatherDateTime = LocalDateTime.parse(weather.datetime, formatter)

            if (weatherDateTime.isBefore(dateTimeToFind)) {
                if (previousWeather == null || weatherDateTime.isAfter(LocalDateTime.parse(previousWeather.datetime, formatter))) {
                    previousWeather = weather
                }
            }
        }

        previousWeather?.let {
            Log.e("test", "Data cuaca sebelumnya yang ditemukan: $it")
        } ?: run {
            Log.e("test", "Tidak ada data cuaca sebelumnya yang cocok ditemukan untuk datetime $dateTime")
        }
        _weather.postValue(previousWeather)
    }

    suspend fun calculateTotalRainfall(): Double {
        val allWeatherFlow: Flow<List<Weather>> = weatherRepository.getAllSavedWeather()
        val weatherList = allWeatherFlow.first()

        return weatherRepository.calculateRainfall(weatherList)
    }

    private fun mappingWeatherList(weatherResponse: WeatherForecastResponse): List<Weather> {
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
        return weatherEntities
    }
}
