package com.ryan.agriaid

import com.ryan.agriaid.data.local.weather.Weather
import com.ryan.agriaid.data.remote.model.ListItem
import com.ryan.agriaid.data.remote.model.Main
import com.ryan.agriaid.data.remote.model.WeatherItem
import com.ryan.agriaid.utility.checkForEmptyFields
import com.ryan.agriaid.utility.parseResultsString
import com.ryan.agriaid.utility.toWeather
import com.ryan.agriaid.utility.validateAndResetInputs
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class UtilityTests {

    @Test
    fun `checkForEmptyFields detects empty fields`() {
        var isCalled = false
        checkForEmptyFields(
            nitrogen = "",
            fosfor = "10",
            kalium = "20",
            ph = "6",
            avgTemp = "30",
            avgHumidity = "50",
            avgRainfall = "100"
        ) {
            isCalled = true
        }
        assertTrue(isCalled)
    }

    @Test
    fun `validateAndResetInputs validates and returns correct values`() {
        val result = validateAndResetInputs(
            nitrogen = "150",
            fosfor = "10",
            kalium = "20",
            ph = "6",
            avgTemp = "30",
            avgHumidity = "50",
            avgRainfall = "100"
        ) {
            // do nothing
        }
        assertEquals("", result[0])
        assertEquals("10", result[1])
        assertEquals("20", result[2])
        assertEquals("6", result[3])
        assertEquals("30", result[4])
        assertEquals("50", result[5])
        assertEquals("100", result[6])
    }

    @Test
    fun `toWeather converts ListItem to Weather correctly`() {
        val listItem = ListItem(
            weather = listOf(WeatherItem(id = 800)),
            main = Main(temp = 25.0, humidity = 60, feelsLike = 23.0),
            dtTxt = "2024-07-10 12:00:00"
        )
        val cityName = "Test City"
        val expected = Weather(
            code = 800,
            temp = 25.0,
            humidity = 60,
            feelsLike = 23.0,
            city = cityName,
            datetime = "2024-07-10 12:00:00"
        )
        assertEquals(expected, listItem.toWeather(cityName))
    }

    @Test
    fun `parseResultsString extracts pairs from string`() {
        val input = "(Nitrogen, 50.0), (Phosphorus, 30.0), (Potassium, 70.0)"
        val expected = listOf(
            "Nitrogen" to 50.0,
            "Phosphorus" to 30.0,
            "Potassium" to 70.0
        )
        assertEquals(expected, parseResultsString(input))
    }
}