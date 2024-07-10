package com.ryan.agriaid.data.local.plants

import androidx.room.Embedded
import androidx.room.Relation

data class PlantDetails(
    @Embedded val plant: Plants,
    @Relation(parentColumn = "id", entityColumn = "plantsId")
    val landPreparations: List<LandPreparations>,
    @Relation(parentColumn = "id", entityColumn = "plantsId")
    val nurseries: List<Nurseries>,
    @Relation(parentColumn = "id", entityColumn = "plantsId")
    val plantings: List<Plantings>,
    @Relation(parentColumn = "id", entityColumn = "plantsId")
    val fertilization: List<Fertilization>,
    @Relation(parentColumn = "id", entityColumn = "plantsId")
    val varieties: List<Varieties>,
    @Relation(parentColumn = "id", entityColumn = "plantsId")
    val pests: List<Pests>,
    @Relation(parentColumn = "id", entityColumn = "plantsId")
    val diseases: List<Diseases>,
    @Relation(parentColumn = "id", entityColumn = "plantsId")
    val weeds: List<Weeds>,
    @Relation(parentColumn = "id", entityColumn = "plantsId")
    val avgSoil: List<AvgSoil>
)