package com.ryan.agriaid.ui.screen.result

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ryan.agriaid.data.ViewModelFactory
import com.ryan.agriaid.data.local.plants.PlantViewModel
import com.ryan.agriaid.data.local.plants.Plants
import com.ryan.agriaid.ui.components.CustomCard

@Composable
fun ResultScreen(results: List<String>, navigateToPlantDetail: (plantId: String) -> Unit) {

    val context = LocalContext.current
    val plantViewModel: PlantViewModel =
        viewModel(factory = ViewModelFactory.getInstance(context))

    var plantsData by remember { mutableStateOf<List<Plants>?>(null) }

    LaunchedEffect(results) {
        plantsData = plantViewModel.getPlantsByName(results)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            plantsData?.let { plants ->
                plants.forEach { plant ->
                    CustomCard(
                        label = plant.name,
                        description = plant.description,
                        imageUrl = plant.imgUrl,
                        onClick = {
                            navigateToPlantDetail(plant.name)
                        }
                    )
                }
            } ?: run {
                Text("Loading...")
            }
        }
    }
}