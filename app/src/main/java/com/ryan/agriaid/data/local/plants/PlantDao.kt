package com.ryan.agriaid.data.local.plants

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface PlantDao {

    @Transaction
    @Query("SELECT * FROM plants WHERE name = :plantName")
    suspend fun getPlantDetailsByName(plantName: String): PlantDetails

    @Query("SELECT * FROM plants WHERE name = :plantName")
    suspend fun getPlantsByName(plantName: String): Plants

    @Query("SELECT * FROM plants")
    suspend fun getAllPlants(): List<Plants>

    @Insert
    suspend fun insertAllPlants(plants: List<Plants>)


    @Insert
    suspend fun insertLandPreparations(landPreparations: LandPreparations)

    @Insert
    suspend fun insertNurseries(nurseries: Nurseries)

    @Insert
    suspend fun insertFertilization(fertilizationList: List<Fertilization>)

    @Insert
    suspend fun insertVarieties(varieties: List<Varieties>)

    @Insert
    suspend fun insertPests(pests: List<Pests>)

    @Insert
    suspend fun insertWeeds(weeds: Weeds)

    @Insert
    suspend fun insertPlantings(plantings: List<Plantings>)

    @Insert
    suspend fun insertDiseases(diseases: List<Diseases>)
}