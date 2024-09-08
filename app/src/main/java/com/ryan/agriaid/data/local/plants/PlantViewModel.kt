package com.ryan.agriaid.data.local.plants

import androidx.lifecycle.ViewModel

open class PlantViewModel(private val plantRepository: PlantRepository) : ViewModel() {

    suspend fun getPlantDetails(plantName: String): PlantDetails {
        return plantRepository.getPlantDetailsByName(plantName)
    }

    open suspend fun getPlantsByName(names: List<String>): List<Plants>  {
        return plantRepository.getPlantsByName(names)
    }

    suspend fun getAllPlants(): List<Plants> {
        return plantRepository.getAllPlants()
    }
}