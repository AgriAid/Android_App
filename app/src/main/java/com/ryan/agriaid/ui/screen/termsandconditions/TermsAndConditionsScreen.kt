package com.ryan.agriaid.ui.screen.termsandconditions

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ryan.agriaid.ui.components.SectionTitle
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TermsAndConditionsScreen(
    onAccept: () -> Unit,
    onDecline: () -> Unit,
) {
    val terms = listOf(
        "1. Pendahuluan" to "Selamat datang di aplikasi kami. Syarat dan Ketentuan ini menguraikan aturan dan ketentuan untuk penggunaan aplikasi kami yang mengakses internet dan menggunakan GPS. Dengan menggunakan aplikasi ini, Anda setuju untuk terikat oleh Syarat dan Ketentuan ini.",
        "2. Penerimaan Syarat" to "Dengan mengakses atau menggunakan aplikasi kami, Anda setuju untuk terikat oleh Syarat dan Ketentuan ini.",
        "3. Perubahan Syarat" to "Kami berhak untuk memperbarui atau mengubah Syarat dan Ketentuan ini kapan saja tanpa pemberitahuan terlebih dahulu. Perubahan tersebut akan berlaku efektif segera setelah diposting di aplikasi. Penggunaan aplikasi yang terus-menerus oleh Anda setelah perubahan menunjukkan penerimaan Anda atas syarat dan ketentuan yang telah diperbarui.",
        "4. Tanggung Jawab Pengguna" to "Anda bertanggung jawab untuk menjaga kerahasiaan informasi akun Anda, termasuk email yang Anda berikan untuk feedback. Anda juga bertanggung jawab atas semua aktivitas yang terjadi di bawah akun Anda dan memastikan bahwa penggunaan aplikasi Anda sesuai dengan Syarat dan Ketentuan ini.",
        "5. Pengumpulan Data dan Privasi" to "Aplikasi kami hanya mengumpulkan data email Anda yang digunakan untuk mengirimkan feedback atau pertanyaan terkait aplikasi. Kami tidak mengumpulkan data pribadi lainnya dari Anda. Data yang dikumpulkan akan digunakan sesuai dengan Kebijakan Privasi kami dan tidak akan dibagikan kepada pihak ketiga tanpa izin Anda.",
        "6. Penggunaan Internet dan GPS (Opsional)" to "Aplikasi ini memerlukan akses internet untuk berfungsi secara optimal dan menggunakan GPS untuk beberapa fitur. Penggunaan fitur GPS akan mematuhi kebijakan privasi yang berlaku dan Anda akan diminta izin untuk mengakses lokasi Anda. Anda dapat menonaktifkan fitur GPS melalui pengaturan perangkat Anda jika Anda tidak ingin aplikasi mengakses lokasi Anda. Namun jika anda ingin menggunakan tanpa internet dan gps, aplikasi masih bisa berfungsi dengan baik.",
        "7. Penghentian" to "Kami berhak untuk menangguhkan atau menghentikan akses Anda ke aplikasi jika kami mendeteksi pelanggaran terhadap Syarat dan Ketentuan ini atau jika kami menemukan aktivitas yang melanggar hukum atau merugikan aplikasi kami.",
        "8. Hukum yang Berlaku" to "Syarat dan Ketentuan ini diatur oleh dan ditafsirkan sesuai dengan hukum yang berlaku di wilayah hukum tempat perusahaan kami terdaftar. Setiap perselisihan yang timbul dari penggunaan aplikasi ini akan diselesaikan di pengadilan yang berwenang di wilayah tersebut.",
        "9. Informasi Kontak" to "Jika Anda memiliki pertanyaan atau komentar tentang Syarat dan Ketentuan ini, silakan hubungi kami melalui email di riyandotianto2@gmail.com."
    )

    val isLoading = remember { mutableStateOf(false) }

    LaunchedEffect(isLoading.value) {
        if (isLoading.value) {
            delay(1000)
            onAccept()
        }
    }
    Scaffold(
        topBar = {
            if (!isLoading.value) {
                TopAppBar(
                    title = { Text("Syarat dan Ketentuan") }
                )
            }
        }
    ) { paddingValues ->
        if (!isLoading.value) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                LazyColumn(
                    modifier = Modifier.weight(1f)
                ) {
                    items(terms) { (title, content) ->
                        SectionTitle(
                            title = title
                        )
                        Text(
                            text = content,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                if (!isLoading.value) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        ElevatedButton(
                            shape = RoundedCornerShape(15.dp),
                            elevation = ButtonDefaults.elevatedButtonElevation(5.dp),
                            onClick = onDecline
                        ) {
                            Text(
                                text = "Tolak",
                                fontSize = MaterialTheme.typography.bodyMedium.fontSize
                            )
                        }

                        ElevatedButton(
                            colors = ButtonDefaults.elevatedButtonColors(
                                containerColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0.8f)
                            ),
                            shape = RoundedCornerShape(15.dp),
                            elevation = ButtonDefaults.elevatedButtonElevation(5.dp),
                            onClick = {
                                isLoading.value = true
                            }
                        ) {
                            Text(
                                text = "Terima",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            )
                        }
                    }
                }
            }
        }
    }
    if (isLoading.value) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = "Loading...",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TermsAndConditionsScreenPreview() {
    TermsAndConditionsScreen(onAccept = {}, onDecline = {})
}