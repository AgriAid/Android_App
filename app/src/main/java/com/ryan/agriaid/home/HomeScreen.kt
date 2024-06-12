package com.ryan.agriaid.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddLocation
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.google.accompanist.flowlayout.FlowCrossAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.MainAxisAlignment
import com.ryan.agriaid.R
import com.ryan.agriaid.components.BannerArticle
import com.ryan.agriaid.components.CardInfo
import com.ryan.agriaid.components.CardInfoData
import com.ryan.agriaid.utility.TimeHelper

@Composable
fun HomeScreen(
    navController: NavController,
    imageUrl: String? = null,
    name: String = "Petani",
) {
    val gradientStart = colorResource(R.color.white)
    val gradientEnd = colorResource(R.color.gradientEnd)
    val greeting = TimeHelper.getCurrentGreeting()
    val wheather = "Sunny"
    val artikelId = "12345"

    val cardInfoList = listOf(
        CardInfoData(R.drawable.temp, "Suhu", "28 \u00B0C"),
        CardInfoData(R.drawable.humadity, "Kelambaban", "26%"),
        CardInfoData(R.drawable.rainfall, "Curah Hujan", "12 mm")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(230.dp)
            )
            {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(gradientStart, gradientEnd),
                                start = androidx.compose.ui.geometry.Offset(1000f, 0f),
                                end = androidx.compose.ui.geometry.Offset(0f, 1000f),
                            ),
                            shape = RoundedCornerShape(
                                bottomStart = 15.dp,
                                bottomEnd = 15.dp
                            )
                        )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(bottom = 10.dp),
                        horizontalArrangement = Arrangement.Absolute.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                                color = MaterialTheme.colorScheme.onBackground,
                                text = greeting
                            )
                            Text(
                                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                color = MaterialTheme.colorScheme.onBackground,
                                text = name
                            )
                        }
                        if (imageUrl != null) {
                            Box(
                                modifier = Modifier
                                    .size(80.dp)
                                    .background(
                                        color = Color.Transparent,
                                        shape = CircleShape
                                    )
                            ) {
                                AsyncImage(
                                    model = imageUrl,
                                    contentDescription = "Image Profile",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .size(80.dp)
                                        .clip(CircleShape)
                                )
                            }
                        } else {
                            Box(
                                modifier = Modifier
                                    .size(50.dp)
                                    .background(
                                        color = MaterialTheme.colorScheme.primary,
                                        shape = RoundedCornerShape(50.dp)
                                    )
                            )
                        }
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Surface(
                        modifier = Modifier
                            .height(100.dp)
                            .fillMaxWidth(0.8f),
                        shape = RoundedCornerShape(10.dp),
                        color = Color.White,
                        shadowElevation = 5.dp
                    ) {
                        Row(
                            modifier = Modifier
                                .background(Color.Transparent),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            Column {
                                Row {
                                    Icon(
                                        imageVector = Icons.Filled.AddLocation,
                                        contentDescription = "Favorite Icon",
                                        tint = MaterialTheme.colorScheme.primary
                                    )
                                    Spacer(modifier = Modifier.width(5.dp))
                                    Text(
                                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                        color = MaterialTheme.colorScheme.onBackground,
                                        text = "Bumi Agung"
                                    )
                                }
                                Text(
                                    modifier = Modifier.padding(start = 10.dp),
                                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                                    color = MaterialTheme.colorScheme.onBackground,
                                    text = "24°C"
                                )
                            }
                            AsyncImage(
                                model = R.drawable.rain,
                                contentDescription = "Wheater Image",
                                contentScale = ContentScale.FillWidth,
                                modifier = Modifier
                                    .width(90.dp)
                            )
                        }
                    }
                }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                color = MaterialTheme.colorScheme.onBackground,
                text = "Karakteristik"
            )
            FlowRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                mainAxisSpacing = 8.dp,
                crossAxisSpacing = 8.dp,
                mainAxisAlignment = MainAxisAlignment.SpaceBetween,
                crossAxisAlignment = FlowCrossAxisAlignment.Center
            ) {
                cardInfoList.forEach { cardInfo ->
                    CardInfo(
                        iconResId = cardInfo.icon,
                        infoTitle = cardInfo.infoTitle,
                        infoVale = cardInfo.infoValue
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                color = MaterialTheme.colorScheme.onBackground,
                text = "Tips & Trik  Bertani"
            )
            Spacer(modifier = Modifier.height(20.dp))
            BannerArticle(
                articleImage = R.drawable.banner_jagung,
                onClick = {
                    navController.navigate("artikelDetail/$artikelId")
                }
            )
            Spacer(modifier = Modifier.height(20.dp))
            BannerArticle(
                articleImage = R.drawable.banner_jagung,
                onClick = {
                    // Navigate to ArtikelDetailScreen when BannerArticle is clicked
                    navController.navigate("artikelDetail/$artikelId")
                })
            Spacer(modifier = Modifier.height(20.dp))
            BannerArticle(
                articleImage = R.drawable.banner_jagung,
                onClick = {
                    // Navigate to ArtikelDetailScreen when BannerArticle is clicked
                    navController.navigate("artikelDetail/$artikelId")
                })
            Spacer(modifier = Modifier.height(80.dp))
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val navController = rememberNavController()
    HomeScreen(
        navController = navController,
        imageUrl = "https://marketplace.canva.com/EAFHfL_zPBk/1/0/1600w/canva-yellow-inspiration-modern-instagram-profile-picture-kpZhUIzCx_w.jpg",
        name = "Petani"
    )
}