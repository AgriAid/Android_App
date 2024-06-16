package com.ryan.agriaid.data.local.article

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {
    @Insert
    suspend fun insert(article: Article)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(articles: List<Article>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllDetails(details: List<ArticleDetail>)

    @Query("SELECT * FROM article_table")
    fun getAllArticles(): Flow<List<Article>>

    @Query(
        """
    SELECT a.id as articleId, a.articleImg, a.title, a.author,
           d.id as detailId, d.p1, d.p2, d.p3
    FROM article_table a
    INNER JOIN article_detail_table d ON a.id = d.id
    WHERE a.id = :articleId
    """
    )
    suspend fun getArticlesWithDetails(articleId: Int): ArticleWithDetails
}