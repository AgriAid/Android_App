package com.ryan.agriaid.ui.screen.result

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.ryan.agriaid.data.ViewModelFactory
import com.ryan.agriaid.data.local.plants.PlantDetails
import com.ryan.agriaid.data.local.plants.PlantViewModel
import com.ryan.agriaid.utility.Capitalize.toTitleCase

@Composable
fun PlantDetailScreen(plantName: String) {
    val context = LocalContext.current
    val plantViewModel: PlantViewModel = viewModel(factory = ViewModelFactory.getInstance(context))
    var plantDetails by remember { mutableStateOf<PlantDetails?>(null) }

    LaunchedEffect(plantName) {
        plantDetails = plantViewModel.getPlantDetails(plantName)
    }

    plantDetails?.let { details ->
        PlantDetailContent(plantDetails = details)
    } ?: run {
        Text(text = "Loading...")
    }
}

@Composable
fun PlantDetailContent(plantDetails: PlantDetails) {
    LazyColumn(
        modifier = Modifier.padding(16.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(70.dp))
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                AsyncImage(
                    model = plantDetails.plant.imgUrl,
                    contentDescription = "Image Plant",
                    modifier = Modifier
                        .size(200.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = plantDetails.plant.name.toTitleCase(),
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = MaterialTheme.colorScheme.secondary,
                        fontWeight = MaterialTheme.typography.titleLarge.fontWeight,
                        fontSize = MaterialTheme.typography.titleLarge.fontSize
                    )
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = plantDetails.plant.description,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    fontWeight = MaterialTheme.typography.bodyMedium.fontWeight,
                    textAlign = TextAlign.Justify
                ),
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        item { SectionTitle(title = "Persiapan Lahan") }
        items(plantDetails.landPreparations) { preparation ->
            Column(
                modifier = Modifier
                    .border(1.dp, Color.Black)
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, Color.Black)
                        .padding(4.dp)
                ) {
                    Text(
                        text = "Pemrosesan tanah Pra-Tanam",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                            fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                            textAlign = TextAlign.Justify
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                ) {
                    Text(text = preparation.processing)
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, Color.Black)
                        .padding(4.dp)
                ) {
                    Text(
                        text = "Pemrosesan irigasi Pra-Tanam",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                            fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                            textAlign = TextAlign.Justify
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                ) {
                    Text(text = preparation.irrigation)
                }
            }
        }
        item { Spacer(modifier = Modifier.height(16.dp)) }

        item { SectionTitle(title = "Cara Pembibitan") }
        items(plantDetails.nurseries) { nursery ->
            Column(
                modifier = Modifier
                    .border(1.dp, Color.Black)
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, Color.Black)
                        .padding(4.dp)
                ) {
                    Text(
                        text = "Proses Pemilihan Bibit",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                            fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                            textAlign = TextAlign.Justify
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                ) {
                    Text(text = nursery.seedSelection)
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, Color.Black)
                        .padding(4.dp)
                ) {
                    Text(
                        text = "Pembibitan",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                            fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                            textAlign = TextAlign.Justify
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                ) {
                    Text(text = nursery.soaking)
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, Color.Black)
                        .padding(4.dp)
                ) {
                    Text(
                        text = "Penyemaian",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                            fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                            textAlign = TextAlign.Justify
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                ) {
                    Text(text = nursery.seeding)
                }
            }
        }
        item { Spacer(modifier = Modifier.height(16.dp)) }

        item { SectionTitle(title = "Penanaman") }
        items(plantDetails.plantings) { planting ->

            Column(
                modifier = Modifier
                    .border(1.dp, Color.Black)
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, Color.Black)
                        .padding(4.dp)
                ) {
                    Text(
                        text = "Persiapan Tanam",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                            fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                            textAlign = TextAlign.Justify
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                ) {
                    Text(text = planting.preparing)
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, Color.Black)
                        .padding(4.dp)
                ) {
                    Text(
                        text = "Proses Penaman",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                            fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                            textAlign = TextAlign.Justify
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                ) {
                    Text(text = planting.planting)
                }
            }
        }
        item { Spacer(modifier = Modifier.height(16.dp)) }

        item { SectionTitle(title = "Pemupukan") }
        items(plantDetails.fertilization) { fertilization ->
            Column(
                modifier = Modifier
                    .border(1.dp, Color.Black)
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, Color.Black)
                        .padding(4.dp)
                ) {
                    Text(
                        text = "Pemupukan ${fertilization.type} dihari ke-${fertilization.agePlant}",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                            fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                            textAlign = TextAlign.Justify
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(0.4f)
                            .border(1.dp, Color.Black)
                            .padding(4.dp)
                    ) {
                        Text(text = "Urea: ${fertilization.urea}")
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(0.4f)
                            .border(1.dp, Color.Black)
                            .padding(4.dp)
                    ) {
                        Text(text = "TSP: ${fertilization.tsp}")
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, Color.Black)
                            .padding(4.dp)
                    ) {
                        Text(text = "KCL: ${fertilization.kcl}")
                    }
                }
            }
            fertilization.organicFertilizer?.let {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, Color.Black)
                            .padding(4.dp),
                        text = "Pemupukan organik",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                            textAlign = TextAlign.Justify
                        )
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, Color.Black)
                            .padding(4.dp),
                        text = it
                    )
                }
            }
            Spacer(modifier = Modifier.height(5.dp))
        }
        item { Spacer(modifier = Modifier.height(16.dp)) }

        item { SectionTitle(title = "Pilihan Varietas") }
        items(plantDetails.varieties) { variety ->
            Column(
                modifier = Modifier
                    .border(1.dp, Color.Black)
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(0.3f)
                            .border(1.dp, Color.Black)
                            .padding(4.dp),
                        text = "Nama",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                            fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                            textAlign = TextAlign.Justify
                        )
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, Color.Black)
                            .padding(4.dp),
                        text = variety.name,
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(0.3f)
                            .padding(4.dp),
                        text = "Keunggulan",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                            fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                            textAlign = TextAlign.Justify
                        )
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, Color.Black)
                            .padding(4.dp),
                        text = variety.superiority,
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(0.3f)
                            .padding(4.dp),
                        text = "Kelemahan",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                            fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                            textAlign = TextAlign.Justify
                        )
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, Color.Black)
                            .padding(4.dp),
                        text = variety.weakness,
                    )
                }
            }
            Spacer(modifier = Modifier.height(5.dp))
        }
        item { Spacer(modifier = Modifier.height(16.dp)) }

        item { SectionTitle(title = "Jenis hama yang mungkin menyerang") }
        items(plantDetails.pests) { pest ->
            Column(
                modifier = Modifier
                    .border(1.dp, Color.Black)
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(0.3f)
                            .border(1.dp, Color.Black)
                            .padding(4.dp),
                        text = "Nama",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                            fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                            textAlign = TextAlign.Justify
                        )
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, Color.Black)
                            .padding(4.dp),
                        text = pest.name,
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(0.3f)
                            .padding(4.dp),
                        text = "Obat Kimia",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                            fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                            textAlign = TextAlign.Justify
                        )
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, Color.Black)
                            .padding(4.dp),
                        text = pest.chemicalControl,
                    )
                }
            }
            pest.biologicalControl?.let {
                Column(
                    modifier = Modifier
                        .border(1.dp, Color.Black)
                        .fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth(0.3f)
                                .padding(4.dp),
                            text = "Obat Bahan Alami",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                                textAlign = TextAlign.Justify
                            )
                        )
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(1.dp, Color.Black)
                                .padding(4.dp),
                            text = it,
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(5.dp))
        }
        item { Spacer(modifier = Modifier.height(16.dp)) }

        item { SectionTitle(title = "Penyakit yang biasa dialami") }
        items(plantDetails.diseases) { disease ->
            Column(
                modifier = Modifier
                    .border(1.dp, Color.Black)
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(0.3f)
                            .border(1.dp, Color.Black)
                            .padding(4.dp),
                        text = "Nama",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                            fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                            textAlign = TextAlign.Justify
                        )
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, Color.Black)
                            .padding(4.dp),
                        text = disease.name,
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(0.3f)
                            .padding(4.dp),
                        text = "Pengendalian",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                            fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                            textAlign = TextAlign.Justify
                        )
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, Color.Black)
                            .padding(4.dp),
                        text = disease.control,
                    )
                }
            }
            Spacer(modifier = Modifier.height(5.dp))
        }
        item { Spacer(modifier = Modifier.height(16.dp)) }

        item { SectionTitle(title = "Penanganan Gulma") }
        items(plantDetails.weeds) { weed ->
            Column(
                modifier = Modifier
                    .border(1.dp, Color.Black)
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(0.3f)
                            .padding(4.dp),
                        text = "Manual",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                            fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                            textAlign = TextAlign.Justify
                        )
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, Color.Black)
                            .padding(4.dp),
                        text = weed.manualWeeding,
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(0.3f)
                            .padding(4.dp),
                        text = "Herbisida",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                            fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                            textAlign = TextAlign.Justify
                        )
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, Color.Black)
                            .padding(4.dp),
                        text = weed.herbicide,
                    )
                }
            }
            Spacer(modifier = Modifier.height(5.dp))
        }
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier.padding(vertical = 8.dp)
    )
}
