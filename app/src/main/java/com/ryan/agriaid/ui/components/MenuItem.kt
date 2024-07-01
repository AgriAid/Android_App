package com.ryan.agriaid.ui.components

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ryan.agriaid.R

@Composable
fun MenuItem(
    icon: Int,
    title: String,
    onClick: (context: Context) -> Unit,
) {
    val context = LocalContext.current
    ElevatedButton(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),
        elevation = ButtonDefaults.elevatedButtonElevation(5.dp),
        onClick = { onClick(context) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Icon(
                tint = MaterialTheme.colorScheme.primary,
                imageVector = ImageVector
                    .vectorResource(id = icon), contentDescription = "icon"
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth(0.7f),
                fontWeight = MaterialTheme.typography.bodyLarge.fontWeight,
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                color = MaterialTheme.colorScheme.primary,
                text = title
            )
            Icon(
                tint = MaterialTheme.colorScheme.primary,
                imageVector = ImageVector
                    .vectorResource(id = R.drawable.arrow_right), contentDescription = "arrow"
            )
        }
    }
}

@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true)
@Composable
fun MenuItemPreview() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        MenuItem(icon = R.drawable.profile, title = "Pemberitahuan") {}
        MenuItem(icon = R.drawable.profile, title = "Login Ke akun") {}
        MenuItem(icon = R.drawable.profile, title = "Rekomendasi tanaman") {}
    }
}