package com.ryan.agriaid.data.local.article

import android.content.Context
import androidx.room.Query
import androidx.room.Transaction
import com.ryan.agriaid.data.local.NewsDatabase
import kotlinx.coroutines.flow.Flow


 class ArticleRepository(context: Context) {
    private val articleDao = NewsDatabase.getInstance(context).articleDao()

    suspend fun insert(article: Article) {
        articleDao.insert(article)
    }

    fun getAllArticles(): Flow<List<Article>> {
        return articleDao.getAllArticles()
    }

    suspend fun getArticlesWithDetails(articleId: Int): ArticleWithDetails? {
        return articleDao.getArticlesWithDetails(articleId)
    }
}