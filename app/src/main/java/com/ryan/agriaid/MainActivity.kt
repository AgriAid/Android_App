package com.ryan.agriaid

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ryan.agriaid.data.ViewModelFactory
import com.ryan.agriaid.data.local.user.User
import com.ryan.agriaid.data.local.user.UserViewModel
import com.ryan.agriaid.navigation.BottomNavigationBar
import com.ryan.agriaid.navigation.NavRoutes
import com.ryan.agriaid.ui.screen.article.ArticleScreen
import com.ryan.agriaid.ui.screen.home.HomeScreen
import com.ryan.agriaid.ui.screen.plantslist.PlantsListScreen
import com.ryan.agriaid.ui.screen.recomendation.PredictScreen
import com.ryan.agriaid.ui.screen.profile.ProfileScreen
import com.ryan.agriaid.ui.screen.result.PlantDetailScreen
import com.ryan.agriaid.ui.screen.result.ResultScreen
import com.ryan.agriaid.ui.screen.termsandconditions.TermsAndConditionsScreen
import com.ryan.agriaid.ui.theme.AgriAidTheme
import com.ryan.agriaid.utility.NetworkUtils
import com.ryan.agriaid.utility.PreferencesHelper
import com.ryan.agriaid.utility.parseResultsString
import com.ryan.agriaid.utility.showToast

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

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val screensWithBottomNav = listOf(NavRoutes.Home, NavRoutes.Profile)

    val context = LocalContext.current
    val viewModelFactory = ViewModelFactory.getInstance(context)
    val userViewModel: UserViewModel = viewModel(factory = viewModelFactory)

    val user by userViewModel.userFlow.collectAsState(initial = null)
    val isLogin = user != null

    val preferencesHelper = PreferencesHelper(context)
    val termsAccepted = preferencesHelper.isTermsAccepted()

    val isOnline = NetworkUtils.isNetworkAvailable(context)

    LaunchedEffect(isOnline) {
        if (!isOnline) {
            showToast(context, "Anda sedang luring, beberapa fungsi tidak tersedia")
        }
    }
    Scaffold(
        topBar = {
            if (currentRoute !in screensWithBottomNav && currentRoute != NavRoutes.TermsAndConditions) {
                TopAppBar(
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.secondary.copy(blue = 0.5f),
                        titleContentColor = MaterialTheme.colorScheme.onPrimary,
                        navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    title = {
                        Text(
                            text = when (currentRoute) {
                                "artikelDetail/{artikelId}" -> "Detail Artikel"
                                NavRoutes.Predict -> "Masukan Data"
                                "${NavRoutes.Result}/{results}" -> "Rekomendasi Tanaman"
                                NavRoutes.PlantsList -> "Daftar Tanaman Tersedia"
                                else -> ""
                            }
                        )
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                navController.navigateUp()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBackIosNew,
                                contentDescription = "Back"
                            )
                        }
                    },
                )
            }
        },
        bottomBar = {
            if (currentRoute in screensWithBottomNav) {
                BottomNavigationBar(navController)
            }
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = if (!termsAccepted) NavRoutes.TermsAndConditions else NavRoutes.Home
        ) {
            composable(NavRoutes.TermsAndConditions) {
                TermsAndConditionsScreen(
                    onAccept = {
                        preferencesHelper.setTermsAccepted(true)
                        navController.navigate(NavRoutes.Home) {
                            popUpTo(NavRoutes.TermsAndConditions) { inclusive = true }
                        }
                    },
                    onDecline = {
                        (context as? Activity)?.finish()
                    }
                )
            }
            composable(NavRoutes.Home) {
                HomeScreen(
                    navController,
                    user = user
                )
            }
            composable(NavRoutes.Predict) {
                PredictScreen(navController)
            }
            composable(NavRoutes.PlantsList) {
                PlantsListScreen(navController = navController)
            }
            composable(NavRoutes.Profile) {
                ProfileScreen(
                    user = user,
                    isLogin = isLogin,
                    navController = navController,
                    onClick = {
                        if (user != null) {
                            userViewModel.clearUser()
                        } else {
                            userViewModel.saveUser(
                                User(
                                    1,
                                    "Riyan",
                                    "Admin",
                                    "https://st3.depositphotos.com/15648834/17930/v/450/depositphotos_179308454-stock-illustration-unknown-person-silhouette-glasses-profile.jpg"
                                )
                            )
                        }
                    }
                )
            }
            composable(
                route = "${NavRoutes.Result}/{results}",
                arguments = listOf(navArgument("results") { type = NavType.StringType })
            ) { backStackEntry ->
                val results = backStackEntry.arguments?.getString("results")
                if (results != null) {
                    val parsedResults = parseResultsString(results)
                    ResultScreen(
                        navController = navController,
                        results = parsedResults,
                    )
                } else {
                    // Handle case where results are not available
                }
            }

            composable("artikelDetail/{artikelId}") { backStackEntry ->
                ArticleScreen(backStackEntry)
            }
            composable(NavRoutes.PlantDetail) { backStackEntry ->
                val plantId = backStackEntry.arguments?.getString("plantId")
                if (plantId != null) {
                    PlantDetailScreen(plantId)
                }
            }
        }
    }
}