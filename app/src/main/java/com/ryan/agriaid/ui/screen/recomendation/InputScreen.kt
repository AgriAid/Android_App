package com.ryan.agriaid.ui.screen.recomendation

import android.widget.Toast
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ryan.agriaid.data.ViewModelFactory
import com.ryan.agriaid.data.remote.WeatherViewModel
import com.ryan.agriaid.navigation.NavRoutes
import com.ryan.agriaid.ui.components.CustomOutlinedTextField
import com.ryan.agriaid.utility.checkForEmptyFields
import com.ryan.agriaid.utility.validateAndResetInputs

@Composable
fun PredictScreen(navController: NavController) {
    var nitrogen by remember { mutableStateOf("") }
    var fosfor by remember { mutableStateOf("") }
    var kalium by remember { mutableStateOf("") }
    var ph by remember { mutableStateOf("") }
    var avgTemp by remember { mutableStateOf("") }
    var avgHumidity by remember { mutableStateOf("") }
    var avgRainfall by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    val rotationAngle by animateFloatAsState(
        targetValue = if (expanded) 90f else 0f,
        animationSpec = tween(durationMillis = 300), label = "animation arrow"
    )

    var showValidation by remember { mutableStateOf(false) }
    var enableEdit by remember { mutableStateOf(true) }
    val focusManager = LocalFocusManager.current

    // get viewmodel
    val context = LocalContext.current
    val weatherViewModel: WeatherViewModel =
        viewModel(factory = ViewModelFactory.getInstance(context))

    val tfliteViewModel: ClassificationViewModel =
        viewModel(factory = ViewModelFactory.getInstance(LocalContext.current))

    LaunchedEffect(enableEdit) {
        if (!enableEdit) {
            avgTemp = ""
            avgHumidity = ""
            avgRainfall = ""
        } else {
            val (temp, humidity, rainfall) = weatherViewModel.getAverageTempAndHumidityRainfall()
            avgTemp = temp.toInt().toString()
            avgHumidity = humidity.toInt().toString()
            avgRainfall = rainfall.toInt().toString()
        }
    }

    LazyColumn(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(65.dp))
            Text(
                text = "Sesuaikan dengan kondisi lahan",
                style = MaterialTheme.typography.displaySmall.copy(
                    fontSize = MaterialTheme.typography.titleSmall.fontSize,
                    fontWeight = MaterialTheme.typography.bodySmall.fontWeight,
                    fontStyle = FontStyle.Italic
                ),
            )
            CustomOutlinedTextField(
                value = nitrogen,
                onValueChange = { nitrogen = it },
                label = "Nitrogen(N)",
                imeAction = ImeAction.Next,
                onImeAction = { focusManager.moveFocus(FocusDirection.Down) },
                borderColor = if (showValidation && nitrogen.isEmpty()) Color.Red else MaterialTheme.colorScheme.primary
            )
            CustomOutlinedTextField(
                value = fosfor,
                onValueChange = { fosfor = it },
                label = "Fosfor(P)",
                imeAction = ImeAction.Next,
                onImeAction = { focusManager.moveFocus(FocusDirection.Down) },
                borderColor = if (showValidation && fosfor.isEmpty()) Color.Red else MaterialTheme.colorScheme.primary
            )
            CustomOutlinedTextField(
                value = kalium,
                onValueChange = { kalium = it },
                label = "Kalium(K)",
                imeAction = ImeAction.Next,
                onImeAction = { focusManager.moveFocus(FocusDirection.Down) },
                borderColor = if (showValidation && kalium.isEmpty()) Color.Red else MaterialTheme.colorScheme.primary
            )
            CustomOutlinedTextField(
                value = ph,
                onValueChange = { ph = it },
                label = "Ph (0-14)",
                imeAction = ImeAction.Done,
                onImeAction = { focusManager.clearFocus() },
                borderColor = if (showValidation && ph.isEmpty()) Color.Red else MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
        item {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = !expanded }
            ) {
                Text(
                    text = "Paramater Lanjutan",
                    modifier = Modifier.weight(1f),
                    fontSize = 16.sp
                )
                Icon(
                    painter = painterResource(id = android.R.drawable.arrow_down_float),
                    contentDescription = "Expand/Collapse Icon",
                    modifier = Modifier.rotate(rotationAngle)
                )
            }
        }
        if (expanded) {
            item {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    ) {
                        Checkbox(
                            checked = enableEdit,
                            onCheckedChange = {
                                enableEdit = it
                            }
                        )
                        Text(text = "Gunakan Parameter Otomatis")
                    }
                    CustomOutlinedTextField(
                        enableEdit = !enableEdit,
                        value = avgTemp,
                        onValueChange = { avgTemp = it },
                        label = "Temperatur",
                        imeAction = ImeAction.Next,
                        onImeAction = { focusManager.moveFocus(FocusDirection.Down) },
                        borderColor = if (showValidation && avgTemp.isEmpty()) Color.Red else MaterialTheme.colorScheme.primary
                    )
                    CustomOutlinedTextField(
                        enableEdit = !enableEdit,
                        value = avgHumidity,
                        onValueChange = { avgHumidity = it },
                        label = "Kelembaban",
                        imeAction = ImeAction.Next,
                        onImeAction = { focusManager.moveFocus(FocusDirection.Down) },
                        borderColor = if (showValidation && avgHumidity.isEmpty()) Color.Red else MaterialTheme.colorScheme.primary
                    )
                    CustomOutlinedTextField(
                        enableEdit = !enableEdit,
                        value = avgRainfall,
                        onValueChange = { avgRainfall = it },
                        label = "Curah Hujan",
                        imeAction = ImeAction.Done,
                        onImeAction = { focusManager.clearFocus() },
                        borderColor = if (showValidation && avgRainfall.isEmpty()) Color.Red else MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(vertical = 8.dp),
                horizontalAlignment = Alignment.End
            ) {
                Button(
                    colors = ButtonDefaults.buttonColors(
                        MaterialTheme.colorScheme.secondary.copy(
                            blue = 0.5f
                        )
                    ),
                    elevation = ButtonDefaults.buttonElevation(3.dp),
                    onClick = {
                        if (checkForEmptyFields(
                                nitrogen = nitrogen,
                                fosfor = fosfor,
                                kalium = kalium,
                                ph = ph,
                                avgTemp = avgTemp,
                                avgHumidity = avgHumidity,
                                avgRainfall = avgRainfall,
                                onEmptyFields = {
                                    showValidation = true
                                    Toast.makeText(
                                        context,
                                        "Please fill in all the fields",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            )
                        ) {
                            val newValues = validateAndResetInputs(
                                nitrogen = nitrogen,
                                fosfor = fosfor,
                                kalium = kalium,
                                ph = ph,
                                avgTemp = avgTemp,
                                avgHumidity = avgHumidity,
                                avgRainfall = avgRainfall,
                                onInvalid = {
                                    showValidation = true
                                    Toast.makeText(
                                        context,
                                        "Some inputs are out of range, please correct them.",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            )

                            nitrogen = newValues[0]
                            fosfor = newValues[1]
                            kalium = newValues[2]
                            ph = newValues[3]
                            avgTemp = newValues[4]
                            avgHumidity = newValues[5]
                            avgRainfall = newValues[6]
                        }
                        try {
                            val inputs = arrayOf(
                                floatArrayOf(
                                    nitrogen.toFloat(),
                                    fosfor.toFloat(),
                                    kalium.toFloat(),
                                    avgTemp.toFloat(),
                                    avgHumidity.toFloat(),
                                    ph.toFloat(),
                                    avgRainfall.toFloat()
                                ),
                            )
                            val results = tfliteViewModel.performInference(inputs)
                            val resultString = results.joinToString(",")
                            navController.navigate("${NavRoutes.Result}/$resultString")
                        } catch (e: NumberFormatException) {
                            Toast.makeText(
                                context,
                                "Please enter valid numbers",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                ) {
                    Text("Rekomendasikan")
                }
            }
        }
    }
}

