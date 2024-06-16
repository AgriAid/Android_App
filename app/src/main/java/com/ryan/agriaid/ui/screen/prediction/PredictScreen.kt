package com.ryan.agriaid.ui.screen.prediction

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.ryan.agriaid.ui.screen.prediction.camera.CameraPermission
import com.ryan.agriaid.ui.screen.prediction.camera.CameraPreviewScreen

@Composable
fun PredictScreen() {

    var hasPermission by remember { mutableStateOf(false) }

    if (hasPermission) {
        CameraPreviewScreen()
    } else {
        CameraPermission(onPermissionGranted = { hasPermission = true })
    }
}

