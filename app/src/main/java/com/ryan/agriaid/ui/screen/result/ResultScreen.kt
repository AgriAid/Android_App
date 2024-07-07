package com.ryan.agriaid.ui.screen.result

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ryan.agriaid.data.ViewModelFactory
import com.ryan.agriaid.data.local.plants.PlantViewModel
import com.ryan.agriaid.data.local.plants.Plants
import com.ryan.agriaid.navigation.NavRoutes
import com.ryan.agriaid.ui.components.CustomCard

@Composable
fun ResultScreen(
    navController: NavController,
    results: List<Pair<String, Double>>,
) {
    val context = LocalContext.current
    val plantViewModel: PlantViewModel =
        viewModel(factory = ViewModelFactory.getInstance(context))

    var plantsData by remember { mutableStateOf<List<Plants>?>(null) }

    LaunchedEffect(results) {
        plantsData = plantViewModel.getPlantsByName(results.map { it.first })
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        plantsData?.let { plants ->
            val highAccuracyResults = results.filter { it.second >= 0.7 }
            val lowAccuracyResults = results.filter { it.second < 0.7 && it.second >= 0.05 }

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                item {
                    Spacer(modifier = Modifier.height(60.dp))
                }
                if (highAccuracyResults.isNotEmpty()) {
                    item {
                        Text(
                            text = "Tanaman paling sesuai dengan IKT",
                            style = MaterialTheme.typography.titleMedium.copy(
                                color = MaterialTheme.colorScheme.secondary.copy(blue = 0.3f)
                            )
                        )
                    }
                    items(highAccuracyResults) { (name, value) ->
                        plants.find { it.name == name }?.let { plant ->
                            val iktInt = (value * 100).toInt()
                            CustomCard(
                                label = plant.name,
                                description = plant.description,
                                imageUrl = plant.imgUrl,
                                ikt = iktInt
                            ) {
                                navController.navigate(
                                    NavRoutes.PlantDetail.replace(
                                        "{plantId}",
                                        plant.name
                                    )
                                )
                            }
                        }
                    }
                } else {
                    item {
                        Text(
                            text = "Kondisi tanah saat ini perlu dilakukan pengolahan lanjutan",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                color = MaterialTheme.colorScheme.secondary.copy(blue = 0.3f),
                                textAlign = TextAlign.Center
                            )
                        )
                    }
                }
                if (lowAccuracyResults.isNotEmpty()) {
                    item {
                        Spacer(modifier = Modifier.height(25.dp))
                        Text(
                            text = "Opsi lain dengan pengolahan tanah lanjutan",
                            style = MaterialTheme.typography.titleSmall.copy(
                                color = MaterialTheme.colorScheme.secondary.copy(blue = 0.3f)
                            )
                        )
                    }
                    items(lowAccuracyResults) { (name, value) ->
                        plants.find { it.name == name }?.let { plant ->
                            val iktInt = (value * 100).toInt()
                            CustomCard(
                                label = plant.name,
                                description = plant.description,
                                imageUrl = plant.imgUrl,
                                ikt = iktInt
                            ) {
                                navController.navigate(
                                    NavRoutes.PlantDetail.replace(
                                        "{plantId}",
                                        plant.name
                                    )
                                )
                            }
                        }
                    }
                }
            }
        } ?: run {
            Text("Loading...")
            results.forEach { (name, value) ->
                Text(text = "Plant: $name, Score: $value")
            }
        }
    }
}