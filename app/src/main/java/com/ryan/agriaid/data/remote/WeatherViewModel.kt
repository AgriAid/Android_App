package com.ryan.agriaid.data.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ryan.agriaid.data.remote.model.ListItem
import com.ryan.agriaid.data.remote.model.WeatherForecastResponse
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
}
