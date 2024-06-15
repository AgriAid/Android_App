package com.ryan.agriaid.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AdsClick
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Shop
import androidx.compose.material.icons.filled.Shower
import androidx.compose.ui.graphics.vector.ImageVector
import com.ryan.agriaid.R

sealed class BottomNavItem(val route: String, val icon: Int, val iconActive: Int, val title: String) {
    object Home : BottomNavItem("home", R.drawable.home, R.drawable.home_active, "Home")
    object Predict: BottomNavItem("predict", 0, 0, "Predict")
    object Profile : BottomNavItem("profile", R.drawable.profile, R.drawable.profile_active, "Profile")
}