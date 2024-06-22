package com.ryan.agriaid.data.local.plants

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "land_preparations",
    foreignKeys = [ForeignKey(
        entity = Plants::class,
        parentColumns = ["id"],
        childColumns = ["plantsId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class LandPreparations(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val plantsId: Int,
    val processing: String,
    val irrigation: String
)
