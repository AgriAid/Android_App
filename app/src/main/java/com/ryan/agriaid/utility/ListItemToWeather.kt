package com.ryan.agriaid.utility

import com.ryan.agriaid.data.local.weather.Weather
import com.ryan.agriaid.data.remote.model.ListItem
import com.ryan.agriaid.data.remote.model.WeatherForecastResponse

fun ListItem.toWeather(cityName: String): Weather {
    val weatherItem = this.weather?.getOrNull(0)
    val temp = this.main?.temp ?: 0.0
    val humidity = this.main?.humidity ?: 0
    val feelsLike = this.main?.feelsLike ?: 0.0
    val datetime = this.dtTxt ?: ""
    val code = weatherItem?.id ?: 0

    return Weather(
        code = code,
        temp = temp,
        humidity = humidity,
        feelsLike = feelsLike,
        city = cityName,
        datetime = datetime
    )
}

fun WeatherForecastResponse.toWeatherList(): List<Weather> {
    val cityName = this.city?.name ?: ""
    return this.list?.mapNotNull { it?.toWeather(cityName) } ?: emptyList()
}