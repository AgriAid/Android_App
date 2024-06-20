package com.ryan.agriaid.ui.screen.home

import android.util.Log
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.google.accompanist.flowlayout.FlowCrossAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.MainAxisAlignment
import com.ryan.agriaid.R
import com.ryan.agriaid.data.ViewModelFactory
import com.ryan.agriaid.data.local.article.Article
import com.ryan.agriaid.data.local.article.ArticleViewModel
import com.ryan.agriaid.data.local.user.User
import com.ryan.agriaid.data.local.weather.Weather
import com.ryan.agriaid.data.remote.WeatherViewModel
import com.ryan.agriaid.data.remote.location.getCurrentLocation
import com.ryan.agriaid.ui.components.BannerArticle
import com.ryan.agriaid.ui.components.CardInfo
import com.ryan.agriaid.ui.components.CardInfoData
import com.ryan.agriaid.utility.NetworkUtils
import com.ryan.agriaid.utility.TimeHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@Composable
fun HomeScreen(
    navController: NavController,
    user: User?,
) {
    val greeting = TimeHelper.getCurrentGreeting()

    // article
    val context = LocalContext.current
    val viewModel: ArticleViewModel = viewModel(factory = ViewModelFactory.getInstance(context))
    val articles by viewModel.getAllArticles().collectAsState(initial = emptyList())

    var location by remember { mutableStateOf<Pair<Double, Double>?>(null) }
    var isPermissionGranted by remember { mutableStateOf(false) }

    RequestPermission { granted ->
        if (granted) {
            isPermissionGranted = true
            getCurrentLocation(context) { latitude, longitude ->
                location = Pair(latitude, longitude)
            }
        } else {
            isPermissionGranted = false
        }
    }

    Log.e("test", "dengan lokasi1 $location")

    // weather
    val weatherViewModel: WeatherViewModel =
        viewModel(factory = ViewModelFactory.getInstance(context))

    var weatherData by remember { mutableStateOf<Weather?>(null) }
    var rainfallData by remember { mutableStateOf(0.0) }

    LaunchedEffect(location, isPermissionGranted) {
        val isConnected = NetworkUtils.isNetworkAvailable(context)
        Log.e("test", "anda sedang online? $isConnected")
        Log.e("test", "dengan lokasi2 $location")

        if (isConnected && isPermissionGranted && location != null) {
            location?.let { loc ->
                weatherViewModel.getWeatherForecast(
                    loc.first.toString(),
                    loc.second.toString(),
                    "ID"
                )
            }
        } else {
            Log.e("test", "anda sedang offline")
            val dateTimeNow = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            val formattedDateTime = dateTimeNow.format(formatter)
            weatherViewModel.getWeatherByDateTime(formattedDateTime)
        }

        rainfallData = withContext(Dispatchers.IO) {
            weatherViewModel.calculateTotalRainfall()
        }
    }

    weatherViewModel.weatherData.observeAsState().value?.let {
        weatherData = it
    }

    val imageUrl = R.drawable.agri_aid_ico

    val characteristicList = listOf(
        CardInfoData(
            R.drawable.temp,
            "Terasa Seperti",
            "${(weatherData?.feelsLike)?.toInt() ?: 0.0}\u00B0C"
        ),
        CardInfoData(
            R.drawable.humadity,
            "Kelambaban",
            "${(weatherData?.humidity) ?: 0.0}%"
        ),
        CardInfoData(R.drawable.rainfall, "Curah Hujan", "${rainfallData.toInt()} mm")
    )

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        GreetingSection(
            greeting = greeting,
            name = user?.username ?: "Petani",
            imageUrl = user?.imageUrl ?: imageUrl,
            cityName = weatherData?.city ?: "tidak ada data",
            temp = weatherData?.temp ?: 0.0
        )
        BannerSection(
            navController = navController,
            characteristicsData = characteristicList,
            articles = articles
        )
    }
}

@Composable
fun GreetingSection(
    greeting: String,
    name: String,
    imageUrl: Comparable<*>,
    cityName: String = "tidak ada data",
    temp: Double = 0.0,
) {
    val gradientStart = colorResource(R.color.white)
    val gradientEnd = colorResource(R.color.gradientEnd)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(230.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(gradientStart, gradientEnd),
                        start = Offset(1000f, 0f),
                        end = Offset(0f, 1000f),
                    ),
                    shape = RoundedCornerShape(bottomStart = 15.dp, bottomEnd = 15.dp)
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
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .background(Color.Transparent, CircleShape)
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
            }
        }
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Surface(
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth(0.8f),
                shape = RoundedCornerShape(10.dp),
                color = Color.White,
                shadowElevation = 5.dp,
            ) {
                Row(
                    modifier = Modifier.background(Color.Transparent),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Column {
                        Row {
                            Icon(
                                imageVector = ImageVector.vectorResource(R.drawable.location),
                                contentDescription = "Favorite Icon",
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(
                                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                color = MaterialTheme.colorScheme.onBackground,
                                text = cityName
                            )
                        }
                        Text(
                            modifier = Modifier.padding(start = 10.dp),
                            fontSize = MaterialTheme.typography.titleLarge.fontSize,
                            color = MaterialTheme.colorScheme.onBackground,
                            text = "${temp.toInt()}°C"
                        )
                    }
                    AsyncImage(
                        model = R.drawable.rain,
                        contentDescription = "Weather Image",
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier.width(90.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun BannerSection(
    navController: NavController,
    characteristicsData: List<CardInfoData>,
    articles: List<Article>,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        item {
            Text(
                modifier = Modifier.padding(vertical = 10.dp),
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                fontWeight = FontWeight.Bold,
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

                characteristicsData.forEach { cardInfo ->
                    CardInfo(
                        iconResId = cardInfo.icon,
                        infoTitle = cardInfo.infoTitle,
                        infoValue = cardInfo.infoValue
                    )
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground,
                text = "Tips & Trik"
            )
        }

        items(articles) { article ->
            BannerArticle(
                title = article.title,
                articleImage = article.articleImg,
                author = article.author,
                onClick = {
                    navController.navigate("artikelDetail/${article.id}")
                }
            )
        }

        item {
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
        user = User(1, "JohnDoe", "Admin", "https://example.com/johndoe.png"),
    )
}