package com.ryan.agriaid

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TermsAndConditionsScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun termsAndConditionsScreen_isDisplayed() {

        // Check if the Terms and Conditions text is displayed
        composeTestRule
            .onNodeWithText("Syarat dan Ketentuan")
            .assertIsDisplayed()

        // Check if the Accept button is displayed
        composeTestRule
            .onNodeWithText("Terima")
            .assertIsDisplayed()

        // Check if the Decline button is displayed
        composeTestRule
            .onNodeWithText("Tolak")
            .assertIsDisplayed()
    }
}