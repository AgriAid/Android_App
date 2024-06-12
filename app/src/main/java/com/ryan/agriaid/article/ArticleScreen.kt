package com.ryan.agriaid.article

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry

@Composable
fun ArticleScreen(navBackStackEntry: NavBackStackEntry) {
    val articleId = navBackStackEntry.arguments?.getString("artikelId")

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Artikel Detail for ID: $articleId",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}