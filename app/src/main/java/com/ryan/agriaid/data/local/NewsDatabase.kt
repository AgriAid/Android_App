package com.ryan.agriaid.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ryan.agriaid.data.local.article.Article
import com.ryan.agriaid.data.local.article.ArticleDao
import com.ryan.agriaid.data.local.article.ArticleDetail
import com.ryan.agriaid.data.local.plants.AvgSoil
import com.ryan.agriaid.data.local.plants.Diseases
import com.ryan.agriaid.data.local.plants.Fertilization
import com.ryan.agriaid.data.local.plants.LandPreparations
import com.ryan.agriaid.data.local.plants.Nurseries
import com.ryan.agriaid.data.local.plants.Pests
import com.ryan.agriaid.data.local.plants.PlantDao
import com.ryan.agriaid.data.local.plants.Plantings
import com.ryan.agriaid.data.local.plants.Plants
import com.ryan.agriaid.data.local.plants.Varieties
import com.ryan.agriaid.data.local.plants.Weeds
import com.ryan.agriaid.data.local.weather.Weather
import com.ryan.agriaid.data.local.weather.WeatherDao

@Database(
    entities = [
        Article::class, ArticleDetail::class, Weather::class, Diseases::class, Fertilization::class,
        LandPreparations::class, Nurseries::class, Pests::class, Plantings::class, Plants::class,
        Varieties::class, Weeds::class, AvgSoil::class],
    version = 1,
    exportSchema = false
)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
    abstract fun weatherDao(): WeatherDao
    abstract fun plantDao(): PlantDao

    companion object {
        @Volatile
        private var instance: NewsDatabase? = null

        fun getInstance(context: Context): NewsDatabase =
            instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    NewsDatabase::class.java, "AgriAid_db"
                )
                    .addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            instance?.let { database ->
                                DatabaseInitializer.populateInitialData(database)
                            }
                        }
                    })
                    .build().also { instance = it }
            }
    }
}