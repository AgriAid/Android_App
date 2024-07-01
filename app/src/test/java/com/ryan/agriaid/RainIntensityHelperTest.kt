package com.ryan.agriaid

import com.ryan.agriaid.utility.RainIntensityHelper
import org.junit.Assert.assertEquals
import org.junit.Test

class RainIntensityHelperTest {

    @Test
    fun testConvertToRainIntensity() {
        assertEquals(2.5, RainIntensityHelper.convertToRainIntensity(500), 0.0)
        assertEquals(30.0, RainIntensityHelper.convertToRainIntensity(501), 0.0)
        assertEquals(60.0, RainIntensityHelper.convertToRainIntensity(502), 0.0)
        assertEquals(110.0, RainIntensityHelper.convertToRainIntensity(503), 0.0)
        assertEquals(160.0, RainIntensityHelper.convertToRainIntensity(504), 0.0)
        assertEquals(20.5, RainIntensityHelper.convertToRainIntensity(520), 0.0)
        assertEquals(7.5, RainIntensityHelper.convertToRainIntensity(521), 0.0)
        assertEquals(50.0, RainIntensityHelper.convertToRainIntensity(522), 0.0)
        assertEquals(100.0, RainIntensityHelper.convertToRainIntensity(531), 0.0)
        assertEquals(0.0, RainIntensityHelper.convertToRainIntensity(999), 0.0)
    }

    @Test
    fun testGetTotalRainfall() {
        assertEquals(0.0, RainIntensityHelper.getTotalRainfall(emptyList()), 0.0)
        assertEquals(110.0, RainIntensityHelper.getTotalRainfall(listOf(500, 521, 531)), 0.0)
        assertEquals(270.0, RainIntensityHelper.getTotalRainfall(listOf(502, 504, 522)), 0.0)
    }

    @Test
    fun testGetAverageRainfall() {
        assertEquals(0.0, RainIntensityHelper.getAverageRainfall(emptyList()), 0.0)
        assertEquals(30.833333333333332, RainIntensityHelper.getAverageRainfall(listOf(500, 501, 502)), 0.0001)
        assertEquals(110.0, RainIntensityHelper.getAverageRainfall(listOf(503, 503, 503)), 0.0)
    }
}