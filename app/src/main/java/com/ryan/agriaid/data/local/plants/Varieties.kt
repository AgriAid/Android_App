package com.ryan.agriaid.data.local.plants

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "varieties",
    foreignKeys = [ForeignKey(
        entity = Plants::class,
        parentColumns = ["id"],
        childColumns = ["plantsId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class Varieties(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val plantsId: Int,
    val name: String,
    val superiority: String,
    val weakness: String
)
