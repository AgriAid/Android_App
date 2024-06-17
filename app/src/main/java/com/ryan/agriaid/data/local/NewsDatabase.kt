package com.ryan.agriaid.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ryan.agriaid.data.local.article.Article
import com.ryan.agriaid.data.local.article.ArticleDao
import com.ryan.agriaid.data.local.article.ArticleDetail
import com.ryan.agriaid.data.local.weather.Weather
import com.ryan.agriaid.data.local.weather.WeatherDao

@Database(entities = [Article::class, ArticleDetail::class, Weather::class], version = 1, exportSchema = false)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
    abstract fun weatherDao(): WeatherDao

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