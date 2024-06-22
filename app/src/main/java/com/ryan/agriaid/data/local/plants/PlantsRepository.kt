package com.ryan.agriaid.data.local.plants

import android.content.Context
import com.ryan.agriaid.data.local.NewsDatabase

class PlantRepository(context: Context) {

    private val plantDao = NewsDatabase.getInstance(context).plantDao()

    suspend fun getPlantDetailsByName(plantName: String) =
        plantDao.getPlantDetailsByName(plantName)

    suspend fun getPlantsByName(names: List<String>): List<Plants>  {
        val plants = mutableListOf<Plants>()

        for (name in names) {
            val plantsByName = plantDao.getPlantsByName(name)
            plants.addAll(listOf(plantsByName))
        }
        return plants
    }
}