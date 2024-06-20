package com.ryan.agriaid.navigation

import com.ryan.agriaid.R

sealed class BottomNavItem(val route: String, val icon: Int, val iconActive: Int, val title: String) {
    data object Home : BottomNavItem("home", R.drawable.home, R.drawable.home_active, "Home")
    data object Predict: BottomNavItem("predict", 0, 0, "Predict")
    data object Profile : BottomNavItem("profile", R.drawable.profile, R.drawable.profile_active, "Profile")
}