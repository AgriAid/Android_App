package com.ryan.agriaid.data.local.plants

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "pests",
    foreignKeys = [ForeignKey(
        entity = Plants::class,
        parentColumns = ["id"],
        childColumns = ["plantsId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class Pests(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val plantsId: Int,
    val name: String,
    val chemicalControl: String,
    val biologicalControl: String?
)