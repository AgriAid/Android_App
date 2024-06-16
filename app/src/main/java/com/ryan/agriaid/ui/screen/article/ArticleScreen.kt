package com.ryan.agriaid.ui.screen.article

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import com.ryan.agriaid.data.ViewModelFactory
import com.ryan.agriaid.data.local.article.ArticleViewModel
import com.ryan.agriaid.data.local.article.ArticleWithDetails

@Composable
fun ArticleScreen(navBackStackEntry: NavBackStackEntry) {
    val articleId = navBackStackEntry.arguments?.getString("artikelId")?.toIntOrNull() ?: 0

    val viewModelFactory = ViewModelFactory.getInstance(LocalContext.current)
    val articleViewModel: ArticleViewModel = viewModel(factory = viewModelFactory)

    var articleWithDetails by remember { mutableStateOf<ArticleWithDetails?>(null) }

    LaunchedEffect(articleId) {
        articleWithDetails = articleViewModel.getArticlesWithDetails(articleId)
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (articleWithDetails != null) {
            Text(
                text = "Article Title: ${articleWithDetails!!.title}",
                style = typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        } else {
            Text(
                text = "Loading...",
                style = typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}