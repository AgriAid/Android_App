package com.ryan.agriaid.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AdsClick
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Shop
import androidx.compose.material.icons.filled.Shower
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val icon: ImageVector, val iconActive: ImageVector, val title: String) {
    object Home : BottomNavItem("home", Icons.Default.Home, Icons.Filled.Shower, "Home")
    object Predict: BottomNavItem("predict", Icons.Default.AdsClick,Icons.Default.Shop, "Predict")
    object Profile : BottomNavItem("profile", Icons.Default.Person,Icons.Default.Shop, "Profile")
}