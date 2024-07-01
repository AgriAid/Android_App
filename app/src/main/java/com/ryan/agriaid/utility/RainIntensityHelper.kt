package com.ryan.agriaid.utility

object RainIntensityHelper {
    // conversi ke mm
    private val rainIntensityMap = mapOf(
        500 to 2.5,
        501 to 30.0,
        502 to 60.0,
        503 to 110.0,
        504 to 160.0,
        520 to 20.5,
        521 to 7.5,
        522 to 50.0,
        531 to 100.0
    )
    fun convertToRainIntensity(code: Int): Double {
        return rainIntensityMap.getOrDefault(code, 0.0)
    }

    fun getTotalRainfall(codes: List<Int>): Double {
        return codes.sumOf { code ->
            convertToRainIntensity(code)
        }
    }

    fun getAverageRainfall(codes: List<Int>): Double {
        if (codes.isEmpty()) {
            return 0.0
        }
        val totalRainfall = getTotalRainfall(codes)
        return totalRainfall / codes.size
    }
}


