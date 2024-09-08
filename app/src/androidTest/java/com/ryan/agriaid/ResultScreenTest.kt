package com.ryan.agriaid

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ryan.agriaid.ui.screen.result.ResultScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ResultScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testResultScreenDisplaysAllResults() {
        // Arrange: Set up the necessary data
        val results = listOf(
            "padi" to 0.8,
            "jagung" to 0.6,
            "buncis" to 0.7,
            "delima" to 0.4
        )

        composeTestRule.setContent {
            val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
            val fakePlantViewModel = FakePlantViewModel()
            ResultScreen(navController = navController, results = results, plantViewModel = fakePlantViewModel)
        }

        composeTestRule.onNodeWithText("Tanaman paling sesuai dengan IKT")
            .assertIsDisplayed()

        composeTestRule.onNodeWithText("Opsi lain dengan pengolahan tanah lanjutan")
            .assertIsDisplayed()
    }

    @Test
    fun testResultScreenDisplaysOnlyHeightIKT() {
        // Arrange: Set up the necessary data
        val results = listOf(
            "padi" to 0.9,
            "jagung" to 0.8,
            "buncis" to 0.7,
            "delima" to 0.7
        )

        composeTestRule.setContent {
            val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
            val fakePlantViewModel = FakePlantViewModel()
            ResultScreen(navController = navController, results = results, plantViewModel = fakePlantViewModel)
        }

        composeTestRule.onNodeWithText("Tanaman paling sesuai dengan IKT")
            .assertIsDisplayed()
    }

    @Test
    fun testResultScreenDisplaysLowestIKT() {
        // Arrange: Set up the necessary data
        val results = listOf(
            "padi" to 0.4,
            "jagung" to 0.3,
            "buncis" to 0.2,
            "delima" to 0.2
        )

        composeTestRule.setContent {
            val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
            val fakePlantViewModel = FakePlantViewModel()
            ResultScreen(navController = navController, results = results, plantViewModel = fakePlantViewModel)
        }

        composeTestRule.onNodeWithText("Kondisi tanah saat ini perlu dilakukan pengolahan lanjutan")
            .assertIsDisplayed()

        composeTestRule.onNodeWithText("Opsi lain dengan pengolahan tanah lanjutan")
            .assertIsDisplayed()
    }
}