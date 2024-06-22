package com.ryan.agriaid.data.local.plants

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "nurseries",
    foreignKeys = [ForeignKey(
        entity = Plants::class,
        parentColumns = ["id"],
        childColumns = ["plantsId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class Nurseries(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val plantsId: Int,
    val seedSelection: String,
    val soaking: String,
    val seeding: String
)