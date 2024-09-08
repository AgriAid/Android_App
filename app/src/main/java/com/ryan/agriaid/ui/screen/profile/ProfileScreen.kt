package com.ryan.agriaid.ui.screen.profile

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.ryan.agriaid.R
import com.ryan.agriaid.data.local.user.User
import com.ryan.agriaid.navigation.NavRoutes
import com.ryan.agriaid.ui.components.MenuItem

@Composable
fun ProfileScreen(
    navController: NavController,
    user: User?,
    isLogin: Boolean,
) {
    val dataMenuItems = listOf(
        MenuData(
            icon = R.drawable.guide,
            title = "Panduan",
            onClick = {
                navController.navigate(NavRoutes.Guide)
            }),
        MenuData(
            icon = R.drawable.plant,
            title = "Daftar Tanaman",
            onClick = {
                navController.navigate(NavRoutes.PlantsList)
            }),
        MenuData(
            icon = R.drawable.shot,
            title = "Pindai penyakit",
            onClick = { context ->
                Toast.makeText(context, "Sedang dalam pengembangan", Toast.LENGTH_SHORT).show()
            }),
        MenuData(
            icon = R.drawable.notification,
            title = "Pemberitahuan",
            onClick = { context ->
                Toast.makeText(context, "Sedang dalam pengembangan", Toast.LENGTH_SHORT).show()
            }),
        MenuData(
            icon = R.drawable.conversation,
            title = "Konsultasi",
            onClick = { context ->
                Toast.makeText(context, "Sedang dalam pengembangan", Toast.LENGTH_SHORT).show()
            }),
        MenuData(
            icon = R.drawable.support,
            title = "Bantuan",
            onClick = { context ->
                Toast.makeText(context, "Sedang dalam pengembangan", Toast.LENGTH_SHORT).show()
            }),
    )

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(90.dp)
                    .background(Color.Transparent, CircleShape)
            ) {
                AsyncImage(
                    model = user?.imageUrl ?: R.drawable.agri_aid_ico,
                    contentDescription = "Image Profile",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape)
                )
            }
            Column(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth(0.7f)
            ) {
                Text(
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    fontWeight = MaterialTheme.typography.titleLarge.fontWeight,
                    text = user?.username ?: "Petani Milenial",
                )
                Text(
                    fontSize = MaterialTheme.typography.bodySmall.fontSize,
                    fontWeight = MaterialTheme.typography.bodySmall.fontWeight,
                    text = user?.role ?: "User"
                )
            }
        }
        Spacer(modifier = Modifier.height(30.dp))
        MenuList(dataMenuItems = dataMenuItems, isLogin)
    }
}

@Composable
fun MenuList(
    dataMenuItems: List<MenuData>,
    isLogin: Boolean
) {
    val context = LocalContext.current
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp),
        contentPadding = PaddingValues(20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(dataMenuItems) { menuItem ->
            MenuItem(
                icon = menuItem.icon,
                title = menuItem.title,
                isDevelop = menuItem.develop,
                onClick = menuItem.onClick
            )
        }
        item {
            Spacer(modifier = Modifier.height(30.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Center
            ) {
                ElevatedButton(
                    shape = RoundedCornerShape(15.dp),
                    elevation = ButtonDefaults.elevatedButtonElevation(5.dp),
                    onClick = {
                        Toast.makeText(context, "Sedang dalam pengembangan", Toast.LENGTH_SHORT)
                            .show()
                    }
                )
                {
                    Text(text = if (isLogin) "Keluar" else "Masuk")
                    Spacer(modifier = Modifier.width(10.dp))
                    Icon(
                        tint = MaterialTheme.colorScheme.primary,
                        imageVector = ImageVector
                            .vectorResource(
                                if (isLogin) R.drawable.logout else R.drawable.login
                            ), contentDescription = "icon"
                    )
                }
            }
            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}
