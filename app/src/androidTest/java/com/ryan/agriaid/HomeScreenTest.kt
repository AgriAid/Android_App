package com.ryan.agriaid

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.printToLog
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.GrantPermissionRule
import com.ryan.agriaid.data.local.user.User
import com.ryan.agriaid.navigation.NavRoutes
import com.ryan.agriaid.ui.screen.article.ArticleScreen
import com.ryan.agriaid.ui.screen.home.HomeScreen
import junit.framework.TestCase
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import android.Manifest
import com.ryan.agriaid.utility.TimeHelper

@RunWith(AndroidJUnit4::class)
class HomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @get:Rule
    val permissionRule: GrantPermissionRule = GrantPermissionRule.grant(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)

    @Test
    fun homeScreen_displaysGreeting() {
        val user = User(1, "Riyan", "Petani", "https://example.com/riyan.png")

        composeTestRule.setContent {
            val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
            HomeScreen(navController = navController, user = user)
        }

        val currentGreeting = TimeHelper.getCurrentGreeting()

        composeTestRule.onNodeWithText(currentGreeting).assertExists()
        composeTestRule.onNodeWithText("Riyan").assertExists()
    }

    @Test
    fun bannerSection_clickArticle_navigatesToDetail() {
        var navController: NavHostController? = null

        composeTestRule.setContent {
            navController = rememberNavController()
            NavHost(navController = navController!!, startDestination = NavRoutes.Home) {
                composable(NavRoutes.Home) {
                    HomeScreen(navController = navController!!, user = null)
                }
                composable("artikelDetail/{artikelId}") { backStackEntry ->
                    ArticleScreen(backStackEntry)
                }
            }
        }
        composeTestRule.onNodeWithText("Budidaya Jagung").performClick()


        TestCase.assertEquals(
            navController!!.currentDestination?.route, "artikelDetail/{artikelId}"
        )
    }
}