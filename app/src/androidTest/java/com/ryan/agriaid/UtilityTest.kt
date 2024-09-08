package com.ryan.agriaid

import androidx.test.core.app.ApplicationProvider
import com.ryan.agriaid.data.local.plants.PlantRepository
import com.ryan.agriaid.data.local.plants.PlantViewModel
import com.ryan.agriaid.data.local.plants.Plants
import kotlinx.coroutines.flow.MutableStateFlow

class FakePlantRepository : PlantRepository(context = ApplicationProvider.getApplicationContext()) {
    private val plantsData = listOf(
        Plants(1 ,"padi", "Padi description", 21),
        Plants(2,"jagung", "Jagung description", 22),
        Plants(3,"buncis", "Buncis description", 23),
        Plants(4, "delima", "Delima description", 24)
    )

    override suspend fun getPlantsByName(names: List<String>): List<Plants> {
        return plantsData.filter { it.name in names }
    }
}

class FakePlantViewModel : PlantViewModel(plantRepository = FakePlantRepository()) {
    private val _plantsData = MutableStateFlow<List<Plants>?>(null)

    init {
        _plantsData.value = listOf(
            Plants(1 ,"padi", "Padi description", 21),
            Plants(2,"jagung", "Jagung description", 22),
            Plants(3,"buncis", "Buncis description", 23),
            Plants(4, "delima", "Delima description", 24)
        )
    }

    override suspend fun getPlantsByName(names: List<String>): List<Plants> {
        return _plantsData.value?.filter { it.name in names } ?: emptyList()
    }
}