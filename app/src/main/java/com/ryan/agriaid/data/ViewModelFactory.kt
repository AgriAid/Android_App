package com.ryan.agriaid.data

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ryan.agriaid.data.local.article.ArticleRepository
import com.ryan.agriaid.data.local.article.ArticleViewModel
import com.ryan.agriaid.data.remote.WeatherRepository
import com.ryan.agriaid.data.remote.WeatherViewModel

class ViewModelFactory(
    private val repository: ArticleRepository,
    private val weatherRepository: WeatherRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ArticleViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ArticleViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WeatherViewModel(weatherRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(
                    ArticleRepository(context),
                    WeatherRepository()
                ).also { instance = it }
            }
    }
}
