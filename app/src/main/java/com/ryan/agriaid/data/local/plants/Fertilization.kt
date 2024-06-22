package com.ryan.agriaid.data.local.plants

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "fertilization",
    foreignKeys = [ForeignKey(
        entity = Plants::class,
        parentColumns = ["id"],
        childColumns = ["plantsId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class Fertilization(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val plantsId: Int,
    val type: String, // "Dasar" atau "Susulan"
    val agePlant: Int,
    val urea: Int,
    val tsp: Int,
    val kcl: Int,
    val organicFertilizer: String?
)