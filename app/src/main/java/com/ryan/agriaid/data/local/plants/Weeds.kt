package com.ryan.agriaid.data.local.plants

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "weeds",
    foreignKeys = [ForeignKey(
        entity = Plants::class,
        parentColumns = ["id"],
        childColumns = ["plantsId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class Weeds(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val plantsId: Int,
    val manualWeeding: String,
    val herbicide: String
)