package com.ryan.agriaid.data

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ryan.agriaid.data.local.UserPreferences
import com.ryan.agriaid.data.local.article.ArticleRepository
import com.ryan.agriaid.data.local.article.ArticleViewModel
import com.ryan.agriaid.data.local.user.UserRepository
import com.ryan.agriaid.data.local.user.UserViewModel
import com.ryan.agriaid.data.remote.WeatherRepository
import com.ryan.agriaid.data.remote.WeatherViewModel

class ViewModelFactory(
    private val articleRepository: ArticleRepository,
    private val weatherRepository: WeatherRepository,
    private val userRepository: UserRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ArticleViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ArticleViewModel(articleRepository) as T
        }
        if (modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WeatherViewModel(weatherRepository) as T
        }
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserViewModel(userRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                val userPreferences = UserPreferences(context)
                val userRepository = UserRepository(userPreferences)
                instance ?: ViewModelFactory(
                    ArticleRepository(context),
                    WeatherRepository(),
                    userRepository
                ).also { instance = it }
            }
    }
}