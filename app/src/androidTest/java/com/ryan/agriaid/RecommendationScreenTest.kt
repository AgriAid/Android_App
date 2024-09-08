package com.ryan.agriaid

import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.printToLog
import androidx.compose.ui.text.AnnotatedString
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ryan.agriaid.navigation.NavRoutes
import com.ryan.agriaid.ui.screen.recomendation.InputScreen
import com.ryan.agriaid.ui.screen.result.ResultScreen
import com.ryan.agriaid.utility.parseResultsString
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RecommendationScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testPredictScreenElements() {

        var navController: NavHostController?

        composeTestRule.setContent {
            navController = rememberNavController()
            NavHost(navController = navController!!, startDestination = NavRoutes.Home) {
                composable(NavRoutes.Home) {
                    InputScreen(navController = navController!!)
                }
                composable(
                    route = "${NavRoutes.Result}/{results}",
                    arguments = listOf(navArgument("results") { type = NavType.StringType })
                ) { backStackEntry ->
                    val results = backStackEntry.arguments?.getString("results")
                    if (results != null) {
                        val parsedResults = parseResultsString(results)
                        ResultScreen(
                            navController = navController!!,
                            results = parsedResults,
                        )
                    }
                }
            }
        }

        composeTestRule.onNodeWithText("Sesuaikan dengan kondisi lahan")
            .assertIsDisplayed()

        composeTestRule.onNodeWithText("Nitrogen(N)")
            .assertIsDisplayed()
            .performTextInput("10")

        composeTestRule.onNodeWithText("Fosfor(P)")
            .assertIsDisplayed()
            .performTextInput("5")

        composeTestRule.onNodeWithText("Kalium(K)")
            .assertIsDisplayed()
            .performTextInput("8")

        composeTestRule.onNodeWithText("Ph (0-14)")
            .assertIsDisplayed()
            .performTextInput("7")

        // Expand the advanced parameters section
        composeTestRule.onNodeWithText("Paramater Lanjutan")
            .assertIsDisplayed()
            .performClick()

        composeTestRule.onNodeWithTag("autoParamsCheckbox")
            .assertIsDisplayed()
            .performClick()

        composeTestRule.onNodeWithText("Temperatur")
            .assertIsDisplayed()
            .performTextInput("25")

        composeTestRule.onNodeWithText("Kelembaban")
            .assertIsDisplayed()
            .performTextInput("60")

        composeTestRule.onNodeWithText("Curah Hujan")
            .assertIsDisplayed()
            .performTextInput("100")

        composeTestRule.onNodeWithText("Rekomendasikan")
            .assertIsDisplayed()
            .performClick()
    }

    @Test
    fun testValidationAndResetInputs() {

        composeTestRule.setContent {
            val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
            InputScreen(navController = navController)
        }

        composeTestRule.onNodeWithText("Nitrogen(N)")
            .assertIsDisplayed()
            .performTextInput("150") // Invalid input, should be reset to ""

        composeTestRule.onNodeWithText("Fosfor(P)")
            .assertIsDisplayed()
            .performTextInput("4") // Invalid input, should be reset to ""

        composeTestRule.onNodeWithText("Kalium(K)")
            .assertIsDisplayed()
            .performTextInput("210") // Invalid input, should be reset to ""

        composeTestRule.onNodeWithText("Ph (0-14)")
            .assertIsDisplayed()
            .performTextInput("7") // Valid input should not be reset

        composeTestRule.onNodeWithText("Rekomendasikan")
            .performClick()

        composeTestRule.onRoot().printToLog("Recommendation")

        composeTestRule.onNode(hasText("Nitrogen(N)").and(hasEditableText("")))
            .assertExists()

        composeTestRule.onNode(hasText("Fosfor(P)").and(hasEditableText("")))
            .assertExists()

        composeTestRule.onNode(hasText("Kalium(K)").and(hasEditableText("")))
            .assertExists()

        composeTestRule.onNode(hasText("Ph (0-14)").and(hasEditableText("7")))
            .assertExists()
    }

    private fun hasEditableText(expectedText: String): SemanticsMatcher {
        return SemanticsMatcher.expectValue(SemanticsProperties.EditableText, AnnotatedString(expectedText))
    }
}