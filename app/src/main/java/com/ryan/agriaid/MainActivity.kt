package com.ryan.agriaid

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ryan.agriaid.ui.screen.article.ArticleScreen
import com.ryan.agriaid.ui.screen.home.HomeScreen
import com.ryan.agriaid.navigation.BottomNavigationBar
import com.ryan.agriaid.navigation.NavRoutes
import com.ryan.agriaid.ui.screen.prediction.PredictScreen
import com.ryan.agriaid.ui.screen.profile.ProfileScreen
import com.ryan.agriaid.ui.theme.AgriAidTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AgriAidTheme {
                MainScreen()
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val screensWithBottomNav = listOf(NavRoutes.Home, NavRoutes.Profile)

    Scaffold(
        bottomBar = {
            if (currentRoute in screensWithBottomNav) {
                BottomNavigationBar(navController)
            }
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = NavRoutes.Home
        ) {
            composable(NavRoutes.Home) {
                HomeScreen(
                    navController,
                    name = "Petani"
                )
            }
            composable(NavRoutes.Predict) {
                PredictScreen()
            }
            composable(NavRoutes.Profile) {
                ProfileScreen(
                    imageUrl = null
                )
            }
            composable("artikelDetail/{artikelId}") { backStackEntry ->
                ArticleScreen(backStackEntry)
            }
        }
    }
}