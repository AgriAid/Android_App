package com.ryan.agriaid.data.local.article

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "article_table")
data class Article(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val articleImg: Int,
    val title: String,
    val author: String,
    val describe: String
)
