package com.ryan.agriaid.ui.screen.prediction.camera

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import androidx.exifinterface.media.ExifInterface
import android.net.Uri
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.ryan.agriaid.R
import java.io.File

@Composable
fun CameraPreviewScreen() {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val previewView = remember { PreviewView(context) }
    var imageCapture: ImageCapture? by remember { mutableStateOf(null) }
    var capturedImage by remember { mutableStateOf<Bitmap?>(null) }

    DisposableEffect(Unit) {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
        val cameraExecutor = ContextCompat.getMainExecutor(context)

        cameraProviderFuture.addListener({
            val cameraProvider = cameraProviderFuture.get()
            val preview = Preview.Builder().build().also {
                it.setSurfaceProvider(previewView.surfaceProvider)
            }
            imageCapture = ImageCapture.Builder().build()
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    lifecycleOwner,
                    cameraSelector,
                    preview,
                    imageCapture
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }, cameraExecutor)

        onDispose {
            cameraProviderFuture.get().unbindAll()
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    )
    {
        AndroidView(
            factory = { previewView },
            modifier = Modifier.fillMaxSize()
        )
        Box(
            modifier = Modifier
                .fillMaxSize(0.9f)
        ) {
            Icon(
                modifier = Modifier.fillMaxSize(),
                imageVector = ImageVector.vectorResource(R.drawable.area),
                tint = MaterialTheme.colorScheme.secondary.copy(alpha = 0.5f),
                contentDescription = "shoot area"
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 25.dp),
            contentAlignment = Alignment.BottomCenter
        ) {

            Button(
                modifier = Modifier.size(100.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = MaterialTheme.colorScheme.secondary.copy(blue = 0.5f)
                ),
                onClick = {
                    imageCapture?.let { capture ->
                        val photoFile = createTempFile(context)
                        val outputOptions =
                            ImageCapture.OutputFileOptions.Builder(photoFile).build()
                        capture.takePicture(
                            outputOptions,
                            ContextCompat.getMainExecutor(context),
                            object : ImageCapture.OnImageSavedCallback {
                                override fun onError(exception: ImageCaptureException) {
                                    exception.printStackTrace()
                                }

                                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                                    val savedUri =
                                        outputFileResults.savedUri ?: Uri.fromFile(photoFile)
                                    val bitmap = BitmapFactory.decodeFile(photoFile.absolutePath)

                                    val rotationDegrees = savedUri.getBitmapRotation()
                                    val rotatedBitmap = bitmap.rotate(rotationDegrees.toFloat())

                                    val paddingDp = 100.dp
                                    val paddingPx = paddingDp.toPx(context).toInt()
                                    val squareBitmap =
                                        rotatedBitmap.cropToSquareWithRightLeftPadding(paddingPx)

                                    capturedImage = squareBitmap

                                    photoFile.delete()
                                }
                            }
                        )
                    }
                }) {
                Icon(
                    modifier = Modifier.fillMaxSize(),
                    imageVector = ImageVector.vectorResource(R.drawable.camera_shooter),
                    contentDescription = "Shooter Button"
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                color = MaterialTheme.colorScheme.secondary.copy(blue = 0.5f),
                fontWeight = MaterialTheme.typography.bodySmall.fontWeight,
                fontSize = MaterialTheme.typography.bodySmall.fontSize,
                text = "Posisikan ditengah area")
        }
        capturedImage?.let { bitmap ->
            Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
                    .clickable {
                        capturedImage = null
                    } // Click to dismiss the captured image preview
            )
        }
    }
}

fun createTempFile(context: Context): File {
    return File.createTempFile(
        "temp_image",
        ".jpg",
        context.cacheDir
    )
}

fun Uri.getBitmapRotation(): Int {
    val exif = ExifInterface(this.path ?: return 0)
    val rotation = exif.getAttributeInt(
        ExifInterface.TAG_ORIENTATION,
        ExifInterface.ORIENTATION_NORMAL
    )

    return when (rotation) {
        ExifInterface.ORIENTATION_ROTATE_90 -> 90
        ExifInterface.ORIENTATION_ROTATE_180 -> 180
        ExifInterface.ORIENTATION_ROTATE_270 -> 270
        else -> 0
    }
}

fun Bitmap.rotate(degrees: Float): Bitmap {
    val matrix = Matrix().apply { postRotate(degrees) }
    return Bitmap.createBitmap(this, 0, 0, width, height, matrix, true)
}

fun Bitmap.cropToSquareWithRightLeftPadding(paddingPx: Int): Bitmap {
    val sourceWidth = width
    val sourceHeight = height
    val targetSize = sourceWidth - 2 * paddingPx
    if (targetSize <= 0) {
        return this
    }

    val yOffset = (sourceHeight - targetSize) / 2

    // Crop image to square size with padding on right and left sides
    return Bitmap.createBitmap(this, paddingPx, yOffset, targetSize, targetSize)
}

fun Dp.toPx(context: Context): Float {
    return this.value * context.resources.displayMetrics.density
}