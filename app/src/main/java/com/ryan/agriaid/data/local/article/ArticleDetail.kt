package com.ryan.agriaid.data.local.article

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "article_detail_table")
data class ArticleDetail(
    @PrimaryKey(autoGenerate = false) val id: Int = 0,
    val p1 : String,
    val p2 : String,
    val p3 : String
)