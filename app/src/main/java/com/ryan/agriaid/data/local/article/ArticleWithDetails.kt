package com.ryan.agriaid.data.local.article

data class ArticleWithDetails(
    val articleId: Int,
    val articleImg: Int,
    val title: String,
    val author: String,
    val detailId: Int,
    val p1: String,
    val p2: String,
    val p3: String,
    val url : String
)
