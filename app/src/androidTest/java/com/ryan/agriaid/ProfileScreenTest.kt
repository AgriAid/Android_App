package com.ryan.agriaid

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ryan.agriaid.data.local.user.User
import com.ryan.agriaid.navigation.NavRoutes
import com.ryan.agriaid.ui.screen.guide.GuideScreen
import com.ryan.agriaid.ui.screen.profile.ProfileScreen
import junit.framework.TestCase.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ProfileScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun profileScreen_DisplaysUserInfo() {
        val testUser = User(1, "Riyan", "Petani", "https://example.com/riyan.png")

        composeTestRule.setContent {
            ProfileScreen(
                navController = TestNavHostController(ApplicationProvider.getApplicationContext()),
                user = testUser,
                isLogin = true,
                onClick = {}
            )
        }

        composeTestRule.onNodeWithText("Riyan").assertExists()
        composeTestRule.onNodeWithText("Petani").assertExists()
    }

    @Test
    fun profileScreen_NavigateToGuide() {
        var navController: NavHostController? = null

        composeTestRule.setContent {
            navController = rememberNavController()
            NavHost(navController = navController!!, startDestination = "profileScreen") {
                composable("profileScreen") {
                    ProfileScreen(
                        navController = navController!!,
                        user = null,
                        isLogin = true,
                        onClick = {}
                    )
                }
                composable(NavRoutes.Guide) {
                    GuideScreen()
                }
            }
        }

        composeTestRule.onNodeWithText("Panduan").performClick()

        assertEquals(navController!!.currentDestination?.route, NavRoutes.Guide)
    }

    @Test
    fun profileScreen_ShowToastOnDevelopingFeature() {
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())

        composeTestRule.setContent {
            ProfileScreen(
                navController = navController,
                user = null,
                isLogin = true,
                onClick = {}
            )
        }

        composeTestRule.onNodeWithText("Pindai penyakit").performClick()
    }

    @Test
    fun profileScreen_LogoutButtonWorks() {
        composeTestRule.setContent {
            ProfileScreen(
                navController = TestNavHostController(ApplicationProvider.getApplicationContext()),
                user = null,
                isLogin = true,
                onClick = {}
            )
        }

        composeTestRule.onNodeWithText("Keluar").performClick()
    }

    @Test
    fun profileScreen_LoginButtonWorks() {
        composeTestRule.setContent {
            ProfileScreen(
                navController = TestNavHostController(ApplicationProvider.getApplicationContext()),
                user = null,
                isLogin = false,
                onClick = {}
            )
        }

        composeTestRule.onNodeWithText("Masuk").performClick()
    }
}
