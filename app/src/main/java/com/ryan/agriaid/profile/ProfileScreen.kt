package com.ryan.agriaid.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ProfileScreen() {

    Box(modifier = Modifier.padding(20.dp)) {
        Text(text = "Profile Screen")
    }
}

@Composable
@Preview(name = "ProfileScreen Preview", showBackground = true)
fun ProfileScreenPreview() {
    ProfileScreen()
}
