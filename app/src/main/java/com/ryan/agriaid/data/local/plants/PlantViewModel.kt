package com.ryan.agriaid.data.local.plants

import androidx.lifecycle.ViewModel

class PlantViewModel(private val plantRepository: PlantRepository) : ViewModel() {

    suspend fun getPlantDetails(plantName: String): PlantDetails {
        return plantRepository.getPlantDetailsByName(plantName)
    }

    suspend fun getPlantsByName(names: List<String>): List<Plants>  {
        return plantRepository.getPlantsByName(names)
    }

    suspend fun getAllPlants(): List<Plants> {
        return plantRepository.getAllPlants()
    }
}