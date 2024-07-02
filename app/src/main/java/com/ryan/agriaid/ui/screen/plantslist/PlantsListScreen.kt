package com.ryan.agriaid.ui.screen.plantslist

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.navigation.NavController
import com.ryan.agriaid.data.ViewModelFactory
import com.ryan.agriaid.data.local.plants.PlantViewModel
import com.ryan.agriaid.data.local.plants.Plants
import com.ryan.agriaid.navigation.NavRoutes
import com.ryan.agriaid.ui.components.CustomCard

@Composable
fun PlantsListScreen(
    navController: NavController,
) {
    val context = LocalContext.current
    val plantViewModel: PlantViewModel =
        viewModel(factory = ViewModelFactory.getInstance(context))

    // State to hold the list of plants
    var plantsData by remember { mutableStateOf<List<Plants>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        try {
            plantsData = plantViewModel.getAllPlants()
        } catch (e: Exception) {
            Log.e("PlantsListScreen", "Failed to get plants", e)
        } finally {
            isLoading = false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (isLoading) {
            Text(text = "Loading...")
        } else if (plantsData.isEmpty()) {
            Text(text = "No Plants Available")
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                item {
                    Spacer(modifier = Modifier.height(50.dp))
                }
                items(plantsData) { plant ->
                    CustomCard(
                        label = plant.name,
                        description = plant.description,
                        imageUrl = plant.imgUrl,
                        onClick = {
                            navController.navigate(
                                NavRoutes.PlantDetail.replace(
                                    "{plantId}",
                                    plant.name
                                )
                            )
                        }
                    )
                }
            }
        }
    }
}