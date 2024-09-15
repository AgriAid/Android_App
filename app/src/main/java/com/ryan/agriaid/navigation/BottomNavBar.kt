package com.ryan.agriaid.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ryan.agriaid.R

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Predict,
        BottomNavItem.Profile
    )
    Box(
        contentAlignment = Alignment.BottomCenter
    ) {
        BottomAppBar(
            containerColor = Color.Transparent,
            contentColor = Color.White,
            modifier = Modifier
                .height(50.dp)
                .paint(
                    painter = painterResource(R.drawable.bottom_nav_bg),
                    contentScale = ContentScale.FillWidth,
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.secondary.copy(blue = 0.5f))
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.Bottom
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                items.forEach { item ->
                    val isSelected = currentRoute == item.route
                    if (item == BottomNavItem.Predict) {
                        Spacer(modifier = Modifier.width(100.dp))
                    } else {
                        NavigationBarItem(
                            icon = {
                                Icon(
                                    modifier = Modifier
                                        .size(if (isSelected) 28.dp else 25.dp),
                                    painter = painterResource(if (isSelected) item.iconActive else item.icon),
                                    contentDescription = item.title
                                )
                            },
                            alwaysShowLabel = false,
                            selected = isSelected,
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = Color.White,
                                unselectedIconColor = MaterialTheme.colorScheme.onPrimary,
                                indicatorColor = MaterialTheme.colorScheme.secondary.copy(blue = 0.5f)
                            ),
                            onClick = {
                                navController.navigate(item.route) {
                                    navController.graph.startDestinationRoute?.let { route ->
                                        popUpTo(route) {
                                            saveState = true
                                        }
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        }
        Box(
            modifier = Modifier.height(90.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(
                        color = MaterialTheme.colorScheme.secondary.copy(blue = 0.5f),
                        shape = RoundedCornerShape(60.dp)
                    )
                    .clickable {
                        navController.navigate(BottomNavItem.Predict.route) {
                            navController.graph.startDestinationRoute?.let { route ->
                                popUpTo(route) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 12.dp),
                    painter = painterResource(R.drawable.scan),
                    contentDescription = "Predict",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BottomNavBarPreview() {
    BottomNavigationBar(navController = NavController(LocalContext.current))
}