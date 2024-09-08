package com.ryan.agriaid.utility

fun checkForEmptyFields(
    nitrogen: String,
    fosfor: String,
    kalium: String,
    ph: String,
    avgTemp: String,
    avgHumidity: String,
    avgRainfall: String,
    onEmptyFields: () -> Unit
): Boolean {
    return if (nitrogen.isEmpty() || fosfor.isEmpty() || kalium.isEmpty() || ph.isEmpty() || avgTemp.isEmpty() || avgHumidity.isEmpty() || avgRainfall.isEmpty()) {
        onEmptyFields()
        false
    } else {
        true
    }
}

fun validateAndResetInputs(
    nitrogen: String,
    fosfor: String,
    kalium: String,
    ph: String,
    avgTemp: String,
    avgHumidity: String,
    avgRainfall: String,
    onInvalid: () -> Unit
): List<String> {
    var isValid = true

    var nitrogenValue = nitrogen.toFloatOrNull()
    var fosforValue = fosfor.toFloatOrNull()
    var kaliumValue = kalium.toFloatOrNull()
    var phValue = ph.toFloatOrNull()
    var avgTempValue = avgTemp.toFloatOrNull()
    var avgHumidityValue = avgHumidity.toFloatOrNull()
    var avgRainfallValue = avgRainfall.toFloatOrNull()

    if (nitrogenValue == null || nitrogenValue < 0 || nitrogenValue > 140) {
        nitrogenValue = null
        isValid = false
    }
    if (fosforValue == null || fosforValue < 5 || fosforValue > 145) {
        fosforValue = null
        isValid = false
    }
    if (kaliumValue == null || kaliumValue < 5 || kaliumValue > 205) {
        kaliumValue = null
        isValid = false
    }
    if (phValue == null || phValue < 1 || phValue > 14) {
        phValue = null
        isValid = false
    }
    if (avgTempValue == null || avgTempValue < 8 || avgTempValue > 43) {
        avgTempValue = null
        isValid = false
    }
    if (avgHumidityValue == null || avgHumidityValue < 14 || avgHumidityValue > 99) {
        avgHumidityValue = null
        isValid = false
    }
    if (avgRainfallValue == null || avgRainfallValue < 20 || avgRainfallValue > 400) {
        avgRainfallValue = null
        isValid = false
    }

    if (!isValid) {
        onInvalid()
    }

    fun formatValue(value: Float?): String {
        return value?.let {
            "%.2f".format(it)
        } ?: ""
    }

    return listOf(
        formatValue(nitrogenValue),
        formatValue(fosforValue),
        formatValue(kaliumValue),
        formatValue(phValue),
        formatValue(avgTempValue),
        formatValue(avgHumidityValue),
        formatValue(avgRainfallValue)
    )
}