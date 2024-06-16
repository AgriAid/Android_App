package com.ryan.agriaid.data.local

import com.ryan.agriaid.R
import com.ryan.agriaid.data.local.article.Article
import com.ryan.agriaid.data.local.article.ArticleDetail
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object DatabaseInitializer {
    fun populateInitialData(database: NewsDatabase) {
        val articleDao = database.articleDao()
        CoroutineScope(Dispatchers.IO).launch {
            val articles = listOf(
                Article(title = "Cara Merawat Jagung dengan bahan alami", articleImg = R.drawable.banner_jagung ,author = "riyan"),
                Article(title = "Pnggunaan pupuk kandang pada padi", articleImg = R.drawable.banner_jagung ,author = "riyan"),
                Article(title = "Perawatan Mudah, panen Besar (Singkong Tailand)", articleImg = R.drawable.banner_jagung ,author = "riyan"),
            )
            articleDao.insertAll(articles)

            val details = listOf(
                ArticleDetail(id = 1, p1 = "Para 1.1", p2 = "Para 1.2", p3 = "Para 1.3"),
                ArticleDetail(id = 2, p1 = "Para 2.1", p2 = "Para 2.2", p3 = "Para 2.3"),
                ArticleDetail(id = 2, p1 = "Para 2.1", p2 = "Para 2.2", p3 = "Para 2.3")
            )
            articleDao.insertAllDetails(details)
        }
    }
}