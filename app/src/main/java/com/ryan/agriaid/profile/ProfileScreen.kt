package com.ryan.agriaid.profile

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen() {
    var location by remember { mutableStateOf<Pair<Double, Double>?>(null) }
    val context = LocalContext.current

    RequestPermission { granted ->
        if (granted) {
            Log.e("test123", "sudah acc")
            getCurrentLocation(context) { latitude, longitude ->
                location = Pair(latitude, longitude)
            }
        } else {
            Log.e("test123", "tidak acc")
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("GPS Location") })
        }
    ) { padding ->
        location?.let {
            Text(
                text = "Latitude: ${it.first}, Longitude: ${it.second}",
                modifier = Modifier.padding(padding)
            )
        }
    }
}

@Composable
@Preview(name = "ProfileScreen Preview", showBackground = true)
fun ProfileScreenPreview() {
    ProfileScreen()
}
