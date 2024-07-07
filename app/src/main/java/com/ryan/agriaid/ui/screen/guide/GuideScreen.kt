package com.ryan.agriaid.ui.screen.guide

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GuideScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item { Spacer(modifier = Modifier.height(65.dp)) }

        item {
            ExpandableCard(title = "Daftar Istilah") {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Glossary()
                }
            }
        }

        item {
            ExpandableCard(title = "Panduan Pengukuran visual") {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    MeasurementGuide()
                }
            }
        }

        item {
            ExpandableCard(title = "Panduan Penggunaan aplikasi") {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    AppGuide()
                }
            }
        }
    }
}

@Composable
fun Glossary() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Column {
            Text(
                text = "IKT : ", style = MaterialTheme.typography.titleMedium.copy(
                    color = MaterialTheme.colorScheme.secondary.copy(blue = 0.3f)
                )
            )
            Text(
                text = "Indeks Kecocokan Tanah. Nilai yang menunjukkan seberapa cocok suatu tanah untuk jenis tanaman tertentu. Nilai IKT biasanya berada dalam skala 1 hingga 100, di mana nilai yang lebih tinggi menunjukkan kecocokan yang lebih tinggi."
            )
        }
        Column {
            Text(
                text = "N : ", style = MaterialTheme.typography.titleMedium.copy(
                    color = MaterialTheme.colorScheme.secondary.copy(blue = 0.3f)
                )
            )
            Text(
                text = "Nitrogen. Nutrisi penting untuk pertumbuhan tanaman, berperan dalam pembentukan daun dan batang yang sehat."
            )
        }
        Column {
            Text(
                text = "P : ", style = MaterialTheme.typography.titleMedium.copy(
                    color = MaterialTheme.colorScheme.secondary.copy(blue = 0.3f)
                )
            )
            Text(
                text = "Fosfor. Nutrisi yang mendukung pertumbuhan akar, pembungaan, dan pembuahan tanaman."
            )
        }
        Column {
            Text(
                text = "K : ", style = MaterialTheme.typography.titleMedium.copy(
                    color = MaterialTheme.colorScheme.secondary.copy(blue = 0.3f)
                )
            )
            Text(
                text = "Kalium. Nutrisi yang membantu pengaturan air dalam tanaman, meningkatkan ketahanan terhadap penyakit."
            )
        }
        Column {
            Text(
                text = "pH : ", style = MaterialTheme.typography.titleMedium.copy(
                    color = MaterialTheme.colorScheme.secondary.copy(blue = 0.3f)
                )
            )
            Text(
                text = "Tingkat keasaman atau kebasaan tanah. pH tanah mempengaruhi ketersediaan nutrisi dan kesehatan tanaman."
            )
        }
        Column {
            Text(
                text = "Suhu : ", style = MaterialTheme.typography.titleMedium.copy(
                    color = MaterialTheme.colorScheme.secondary.copy(blue = 0.3f)
                )
            )
            Text(
                text = "Temperatur lingkungan yang mempengaruhi pertumbuhan tanaman, termasuk suhu optimal untuk berbagai jenis tanaman, menggunakan Celcius."
            )
        }
        Column {
            Text(
                text = "Kelembapan : ", style = MaterialTheme.typography.titleMedium.copy(
                    color = MaterialTheme.colorScheme.secondary.copy(blue = 0.3f)
                )
            )
            Text(
                text = "Jumlah uap air di udara yang mempengaruhi proses fotosintesis dan kebutuhan air tanaman."
            )
        }
        Column {
            Text(
                text = "Curah Hujan : ", style = MaterialTheme.typography.titleMedium.copy(
                    color = MaterialTheme.colorScheme.secondary.copy(blue = 0.3f)
                )
            )
            Text(
                text = "Jumlah total air hujan yang diterima oleh tanah, mempengaruhi ketersediaan air untuk tanaman."
            )
        }
    }
}

@Composable
fun MeasurementGuide() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        MeasurementItem(
            title = "N",
            description = "Nitrogen. Nutrisi penting untuk pertumbuhan tanaman, berperan dalam pembentukan daun dan batang yang sehat.",
            measurementGuide = "Tanah dengan kadar nitrogen yang baik (20-40 ppm) cenderung lebih gelap dan memiliki struktur gembur. Tanaman yang tumbuh di tanah dengan kadar nitrogen cukup akan memiliki daun yang hijau tua dan pertumbuhan yang cepat. Sebaliknya, tanah dengan kadar nitrogen rendah (kurang dari 10 ppm) biasanya lebih terang dan tanaman akan menunjukkan daun yang kekuningan atau pertumbuhan yang terhambat."
        )
        MeasurementItem(
            title = "P",
            description = "Fosfor. Nutrisi yang mendukung pertumbuhan akar, pembungaan, dan pembuahan tanaman.",
            measurementGuide = "Tanah yang kaya fosfor (15-30 ppm) mendukung pembungaan dan pembuahan yang baik, Tanah mungkin terasa lebih lembut dan lebih subur saat dipegang, sering kali memiliki banyak bahan organik. Tanaman di tanah dengan kadar fosfor rendah (kurang dari 5 ppm) mungkin menunjukkan tanda-tanda pertumbuhan akar yang buruk, batang yang lemah, dan produksi bunga atau buah yang rendah."
        )
        MeasurementItem(
            title = "K",
            description = "Kalium. Nutrisi yang membantu pengaturan air dalam tanaman, meningkatkan ketahanan terhadap penyakit.",
            measurementGuide = "Tanah yang kaya kalium (100-200 ppm) mendukung tanaman dengan batang kuat dan daun sehat. Memiliki tekstur yang remah dan tidak mudah menggumpal, biasanya memiliki drainase yang bagus, tidak tergenang air. Tanaman dengan kadar kalium rendah (kurang dari 50 ppm) biasanya menunjukkan daun yang menguning di tepi dan munculnya bercak coklat."
        )
        MeasurementItem(
            title = "pH",
            description = "Tingkat keasaman atau kebasaan tanah. pH tanah mempengaruhi ketersediaan nutrisi dan kesehatan tanaman.",
            measurementGuide = "pH tanah yang baik untuk sebagian besar tanaman berada di kisaran 6,0 hingga 7,0. Tanah asam (pH di bawah 6,0) mungkin terasa kasar dan kering saat disentuh, sementara tanah basa (pH di atas 7,0) cenderung lebih lembut dan licin. Tanaman di tanah yang terlalu asam atau basa dapat menunjukkan pertumbuhan yang buruk dan daun yang berubah warna."
        )
        MeasurementItem(
            title = "Suhu",
            description = "Temperatur lingkungan yang mempengaruhi pertumbuhan tanaman, termasuk suhu optimal untuk berbagai jenis tanaman.",
            measurementGuide = "Suhu tanah yang optimal untuk sebagian besar tanaman berada di kisaran 20-30°C. Tanah yang hangat biasanya nyaman untuk disentuh, sementara tanah yang terlalu panas (>35°C) atau terlalu dingin (<15°C) akan terasa tidak nyaman dan mungkin menunjukkan penurunan aktivitas tanaman."
        )
        MeasurementItem(
            title = "Kelembapan",
            description = "Jumlah uap air di udara yang mempengaruhi proses fotosintesis dan kebutuhan air tanaman.",
            measurementGuide = "Kelembapan tanah yang ideal berada di kisaran 40-60%. Kelembapan tanah dapat dirasakan dengan meremas segenggam tanah. Tanah yang lembab akan membentuk gumpalan yang dapat dipadatkan, sedangkan tanah yang kering akan mudah hancur. Tanah yang terlalu lembab bisa menunjukkan genangan air atau lumut yang tumbuh."
        )
        MeasurementItem(
            title = "Curah Hujan",
            description = "Jumlah total air hujan yang diterima oleh tanah, mempengaruhi ketersediaan air untuk tanaman.",
            measurementGuide = "Curah hujan yang ideal untuk banyak tanaman rata-rata bulanan sebesar 100-150 mm. Tanaman yang tumbuh di area dengan curah hujan yang cukup biasanya tampak segar dan hijau. Kekurangan curah hujan bisa ditandai dengan tanah yang retak dan tanaman yang layu. Sebaliknya, kelebihan curah hujan dapat menyebabkan genangan air dan tanda-tanda pembusukan akar."
        )
    }
}

@Composable
fun AppGuide() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        GuideStep(
            stepNumber = 1,
            title = "Izinkan Lokasi (Opsional)",
            description = "Izinkan akses lokasi (GPS) Anda untuk mendapatkan data cuaca otomatis. Cukup 1 kali untuk data 1 minggu (Daring)."
        )
        GuideStep(
            stepNumber = 2,
            title = "Masuk Ke-Menu Rekomendasi",
            description = "Klik tombol 'rekomendasi' pada bagian tengah layar utama, untuk memasukan data."
        )
        GuideStep(
            stepNumber = 3,
            title = "Input Kondisi Tanah",
            description = "Isi data mengenai kondisi tanah seperti nitrogen (N), fosfor (P), kalium (K), pH. suhu, kelembapan, dan curah hujan (otomatis terisi jika dalam keadaan daring dan mengizinkan akses lokasi). jika anda dalam keadaan luring maka tekan pada bagian 'Parameter Lanjutan', hilangkan centang pada paremeter otomatis untuk mengisi manual."
        )
        GuideStep(
            stepNumber = 4,
            title = "Dapatkan Rekomendasi",
            description = "Klik tombol 'Rekomendasikan' untuk melihat tanaman yang cocok dengan kondisi tanah Anda."
        )
        GuideStep(
            stepNumber = 5,
            title = "Lihat Hasil Rekomendasi",
            description = "Lihat hasil rekomendasi tanaman dengan informasi mengenai kesesuaian tanaman dengan kondisi tanah Anda, dalam persentase IKT."
        )
        GuideStep(
            stepNumber = 6,
            title = "Pilih Tanaman",
            description = "Tekan pada tanaman yang ingin dipilih, untuk melihat detail informasi dari tanaman."
        )
        GuideStep(
            stepNumber = 7,
            title = "Kembali",
            description = "anda bisa kembali dengan menekan tombol kembali atau panah pada bagian atas kiri."
        )
    }
}

@Composable
fun GuideStep(stepNumber: Int, title: String, description: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = "Langkah $stepNumber: $title",
            style = MaterialTheme.typography.bodyLarge.copy(
                color = MaterialTheme.colorScheme.primary
            )
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = description,
            style = MaterialTheme.typography.bodyLarge.copy(
                color = MaterialTheme.colorScheme.onBackground
            )
        )
    }
}


@Composable
fun MeasurementItem(
    title: String,
    description: String,
    measurementGuide: String,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = "$title : ", style = MaterialTheme.typography.titleMedium.copy(
                color = MaterialTheme.colorScheme.secondary.copy(blue = 0.3f)
            )
        )
        Text(
            text = description, style = MaterialTheme.typography.bodyLarge.copy(
                textAlign = TextAlign.Justify
            )
        )
        Text(
            text = "Cara Mengukur:", style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Bold,
            )
        )
        Text(
            text = measurementGuide, style = MaterialTheme.typography.bodyLarge.copy(
                textAlign = TextAlign.Justify
            )
        )
    }
}

@Composable
fun ExpandableCard(
    title: String,
    content: @Composable () -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    val rotationAngle by animateFloatAsState(
        targetValue = if (expanded) 90f else 0f,
        animationSpec = tween(durationMillis = 300),
        label = "animation arrow"
    )
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0.2f)
        ),
    ) {
        Column(
            modifier = Modifier
                .animateContentSize()
                .padding(16.dp)
        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = !expanded }) {
                Text(
                    text = title, modifier = Modifier.weight(1f), fontSize = 16.sp
                )
                Icon(
                    imageVector = Icons.Default.ExpandMore,
                    contentDescription = "Expand/Collapse Icon",
                    modifier = Modifier.rotate(rotationAngle)
                )
            }
            if (expanded) {
                Spacer(modifier = Modifier.height(7.dp))
                content()
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewGuideScreen() {
    GuideScreen()
}