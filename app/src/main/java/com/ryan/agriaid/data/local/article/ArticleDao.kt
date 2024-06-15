package com.ryan.agriaid.data.local.article

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {
    @Insert
    suspend fun insert(article: Article)

    @Query("SELECT * FROM article_table")
    fun getAllArticles(): Flow<List<Article>>
}