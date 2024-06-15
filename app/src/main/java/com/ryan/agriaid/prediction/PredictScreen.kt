package com.ryan.agriaid.prediction

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ryan.agriaid.data.ViewModelFactory
import com.ryan.agriaid.data.remote.WeatherViewModel
import com.ryan.agriaid.data.remote.model.WeatherForecastResponse

@Composable
fun PredictScreen() {
    val context = LocalContext.current
    val weatherViewModel: WeatherViewModel =
        viewModel(factory = ViewModelFactory.getInstance(context))

    // Mengambil data cuaca
    weatherViewModel.getWeatherForecast("-5.130885107175405", "105.55069853093704", "ID")

    // Mengamati data cuaca
    val weatherData: WeatherForecastResponse? by weatherViewModel.weatherForecast.observeAsState()

    // State untuk menyimpan rata-rata suhu dan kelembaban
    var averageTempAndHumidity by remember { mutableStateOf<Pair<Double, Double>?>(null) }

    // Menghitung rata-rata suhu dan kelembaban ketika weatherData diperbarui
    LaunchedEffect(weatherData) {
        weatherData?.list?.let { list ->
            val nonNullList = list.filterNotNull()  // Pastikan tidak ada elemen null
            averageTempAndHumidity = weatherViewModel.getAverageTempAndHumidity(nonNullList)
        }
    }

    Log.e("test", "averageTempAndHumidity: $averageTempAndHumidity")

    // Menampilkan data cuaca dan rata-rata suhu dan kelembaban
    Column {
        Text(text = "Test")
        averageTempAndHumidity?.let {
            Text(text = "Rata-rata Suhu: ${it.first}")
            Text(text = "Rata-rata Kelembaban: ${it.second}")
        }
        weatherData?.let {
            Text(text = it.toString())
        }


    }
}
