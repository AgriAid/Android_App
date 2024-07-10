package com.ryan.agriaid.data.local.plants

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "avgSoil",
    foreignKeys = [ForeignKey(
        entity = Plants::class,
        parentColumns = ["id"],
        childColumns = ["plantsId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class AvgSoil(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val plantsId: Int,
    val n: String,
    val p: String,
    val k: String,
    val temperature: String,
    val humidity: String,
    val ph: String,
    val rainfall: String,
)