package com.ryan.agriaid.utility

object RainIntensityHelper {
    // conversi ke mm
    private val rainIntensityMap = mapOf(
        500 to 2.5,
        501 to 7.5,
        502 to 20.0,
        503 to 50.0,
        504 to 100.0,
        520 to 2.5,
        521 to 7.5,
        522 to 20.0,
        531 to 50.0
    )
    private fun convertToRainIntensity(code: Int): Double {
        return rainIntensityMap.getOrDefault(code, 0.0)
    }

    fun getTotalRainfall(codes: List<Int>): Double {
        return codes.sumOf { code ->
            convertToRainIntensity(code)
        }
    }
}


