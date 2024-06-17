package com.ryan.agriaid.data.local.weather

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(articles: List<Weather>)

    @Query("SELECT * FROM weather_table")
    fun getAllWeather(): Flow<List<Weather>>

    @Query("DELETE FROM weather_table")
    suspend fun deleteAll()
}