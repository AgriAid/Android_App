package com.ryan.agriaid.data.local.weather

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather_table")
data class Weather(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val code: Int,
    val temp: Double,
    val humidity: Int,
    val feelsLike: Double,
    val city: String,
    val datetime: String
)
