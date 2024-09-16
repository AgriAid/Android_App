package com.ryan.agriaid.ui.screen.article

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import coil.compose.AsyncImage
import com.ryan.agriaid.R
import com.ryan.agriaid.data.ViewModelFactory
import com.ryan.agriaid.data.local.article.ArticleViewModel
import com.ryan.agriaid.data.local.article.ArticleWithDetails

@Composable
fun ArticleScreen(navBackStackEntry: NavBackStackEntry) {
    val context = LocalContext.current
    val articleId = navBackStackEntry.arguments?.getString("artikelId")?.toIntOrNull() ?: 0
    val viewModelFactory = ViewModelFactory.getInstance(context)
    val articleViewModel: ArticleViewModel = viewModel(factory = viewModelFactory)
    var articleWithDetails by remember { mutableStateOf<ArticleWithDetails?>(null) }

    LaunchedEffect(articleId) {
        articleWithDetails = articleViewModel.getArticlesWithDetails(articleId)
    }

    if (articleWithDetails != null) {
        ArticleContent(articleWithDetails!!, context)
    } else {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Loading...",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
fun ArticleContent(articleWithDetails: ArticleWithDetails, context: Context) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(50.dp))
            AsyncImage(
                model = articleWithDetails.articleImg,
                contentDescription = "Gambar artikel ${articleWithDetails.title}",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
        }
        item {
            Text(
                modifier = Modifier.padding(horizontal = 20.dp),
                text = articleWithDetails.title,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface,
            )
        }
        item {
            Text(
                modifier = Modifier.padding(horizontal = 20.dp),
                text = "Penulis: ${articleWithDetails.author}",
                style = MaterialTheme.typography.displaySmall.copy(
                    fontSize = MaterialTheme.typography.bodySmall.fontSize,
                    fontWeight = MaterialTheme.typography.bodySmall.fontWeight,
                    fontStyle = FontStyle.Italic
                ),
                color = MaterialTheme.colorScheme.onSurface,
            )
        }
        item {
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = AnnotatedString.Builder().apply {
                    withStyle(style = ParagraphStyle(textIndent = TextIndent(firstLine = 20.sp))) {
                        append(articleWithDetails.p1)
                    }
                }.toAnnotatedString(),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    fontWeight = MaterialTheme.typography.bodyMedium.fontWeight,
                    textAlign = TextAlign.Justify
                ),
                modifier = Modifier.padding(top = 8.dp, start = 20.dp, end = 20.dp, bottom = 0.dp)
            )
        }
        item {
            Text(
                text = AnnotatedString.Builder().apply {
                    withStyle(style = ParagraphStyle(textIndent = TextIndent(firstLine = 20.sp))) {
                        append(articleWithDetails.p2)
                    }
                }.toAnnotatedString(),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    fontWeight = MaterialTheme.typography.bodyMedium.fontWeight,
                    textAlign = TextAlign.Justify
                ),
                modifier = Modifier.padding(top = 8.dp, start = 20.dp, end = 20.dp, bottom = 0.dp)
            )
        }
        item {
            Text(
                text = AnnotatedString.Builder().apply {
                    withStyle(style = ParagraphStyle(textIndent = TextIndent(firstLine = 20.sp))) {
                        append(articleWithDetails.p3)
                    }
                }.toAnnotatedString(),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    fontWeight = MaterialTheme.typography.bodyMedium.fontWeight,
                    textAlign = TextAlign.Justify
                ),
                modifier = Modifier.padding(top = 8.dp, start = 20.dp, end = 20.dp, bottom = 0.dp)
            )
        }
        item {
            Spacer(modifier = Modifier.height(20.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.9f),
                contentAlignment = Alignment.BottomEnd
            ) {
                ElevatedButton(
                    shape = RoundedCornerShape(15.dp),
                    elevation = ButtonDefaults.elevatedButtonElevation(5.dp),
                    onClick = {
                        val rawUrl = articleWithDetails.url
                        val fullUrl = if (rawUrl.startsWith("http://") || rawUrl.startsWith("https://")) {
                            rawUrl
                        } else {
                            "http://$rawUrl"
                        }
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(fullUrl))
                        startActivity(context, intent, null)
                    }
                )
                {
                    Text(text = "Kunjungi Situs")
                    Spacer(modifier = Modifier.width(10.dp))
                    Icon(
                        tint = MaterialTheme.colorScheme.primary,
                        imageVector = ImageVector
                            .vectorResource(id = R.drawable.arrow_right),
                        contentDescription = "arrow"
                    )
                }
            }
            Spacer(modifier = Modifier.height(15.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewArticleScreen() {
    val dummyArticle = ArticleWithDetails(
        articleId = 1,
        articleImg = R.drawable.banner_jagung,
        title = "Sample Article Title",
        author = "John Doe",
        detailId = 1,
        p1 = "This is the first paragraph of the article. It contains an introduction to the topic discussed. lorem ipsum This is the first paragraph of the article. It contains an introduction to the topic discussed. lorem ipsum This is the first paragraph of the article. It contains an introduction to the topic discussed. lorem ipsum This is the first paragraph of the article. It contains an introduction to the topic discussed. lorem ipsum",
        p2 = "This is the first paragraph of the article. It contains an introduction to the topic discussed. lorem ipsum This is the first paragraph of the article. It contains an introduction to the topic discussed. lorem ipsum This is the first paragraph of the article. It contains an introduction to the topic discussed. lorem ipsum This is the first paragraph of the article. It contains an introduction to the topic discussed. lorem ipsum",
        p3 = "This is the first paragraph of the article. It contains an introduction to the topic discussed. lorem ipsum This is the first paragraph of the article. It contains an introduction to the topic discussed. lorem ipsum This is the first paragraph of the article. It contains an introduction to the topic discussed. lorem ipsum This is the first paragraph of the article. It contains an introduction to the topic discussed. lorem ipsum",
        url = "www.google.com"
    )

    ArticleContent(articleWithDetails = dummyArticle, context = LocalContext.current)
}