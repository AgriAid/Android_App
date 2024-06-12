package com.ryan.agriaid.components

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CloudQueue
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CardInfo(
    iconResId: Int,
    infoTitle: String,
    infoVale: String,
) {
    Column(
        modifier = Modifier
            .height(100.dp)
            .width(100.dp)
            .background(
                color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.3f),
                shape = RoundedCornerShape(16.dp)
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
    )
    {
        Spacer(modifier = Modifier.height(5.dp))
        Row(
            modifier = Modifier
                .height(50.dp)
                .width(50.dp)
                .background(
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f),
                    shape = RoundedCornerShape(25.dp)
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = iconResId),
                contentDescription = "Favorite Icon",
                tint = MaterialTheme.colorScheme.primary
            )
        }
        Spacer(modifier = Modifier.height(3.dp))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                fontSize = MaterialTheme.typography.bodySmall.fontSize,
                text = infoTitle,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                text = infoVale,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO)
fun CardInfoPreview() {
//
}