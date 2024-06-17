package com.ryan.agriaid.data.local

import com.ryan.agriaid.R
import com.ryan.agriaid.data.local.article.Article
import com.ryan.agriaid.data.local.article.ArticleDetail
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object DatabaseInitializer {
    fun populateInitialData(database: NewsDatabase) {
        val articleDao = database.articleDao()
        CoroutineScope(Dispatchers.IO).launch {
            val articles = listOf(
                Article(
                    title = "Budidaya Jagung",
                    articleImg = R.drawable.banner_jagung,
                    author = "Kementrian Pertanian"
                ),
                Article(
                    title = "Higdroganik, Soluisi Menanam Padi Di Lahan Terbatas",
                    articleImg = R.drawable.banner_padi,
                    author = "Wahyu Hidayat - Berita Magelang"
                ),
                Article(
                    title = "Budidaya Tanaman Singkong",
                    articleImg = R.drawable.banner_singkong,
                    author = "Dinas Pertanian Kab. Buleleng"
                ),
            )
            articleDao.insertAll(articles)

            val details = listOf(
                ArticleDetail(
                    id = 1,
                    p1 = "Budidaya jagung di Indonesia melibatkan pemilihan varietas yang sesuai dengan kondisi lokal, seperti toleransi terhadap hama, penyakit, dan faktor lingkungan. Tanaman ini tumbuh baik di dataran rendah hingga tinggi, dengan persyaratan tanah yang subur seperti lempung, lempung berpasir, atau tanah vulkanik, yang memiliki drainase baik dan terpapar sinar matahari minimal 8 jam per hari. Waktu tanam sangat penting; disarankan untuk menanam jagung pada musim kemarau untuk memastikan ketersediaan air yang cukup, atau di musim hujan dengan pengaturan drainase yang baik untuk mencegah genangan yang dapat merusak tanaman.",
                    p2 = "Persiapan lahan dapat dilakukan dengan metode Tanpa Olah Tanah (TOT) atau Olah Tanah Sempurna (OTS), tergantung pada kondisi lahan. Pemilihan varietas yang unggul dan berkualitas tinggi menjadi kunci untuk hasil panen yang optimal. Benih yang berkualitas tinggi, dengan daya tumbuh lebih dari 95%, dapat menjamin populasi tanaman yang cukup di lapangan. Penanaman dilakukan dengan jarak yang disarankan dan menggunakan pupuk kandang serta pupuk anorganik seperti urea, SP-36, dan KCL untuk mendukung pertumbuhan tanaman jagung.",
                    p3 = "Pemupukan dilakukan secara bertahap untuk memenuhi kebutuhan nutrisi tanaman pada tahapan awal dan pertumbuhan selanjutnya. Pada lahan kering, penggunaan perangkat uji tanah khusus untuk menentukan dosis pupuk fosfor dan kalium, serta Bagan Warna Daun untuk nitrogen, membantu mengoptimalkan hasil panen. Dengan memperhatikan semua aspek ini, petani dapat meningkatkan produktivitas dan kualitas jagung yang dibudidayakan.",
                    url = "https://pustaka.setjen.pertanian.go.id/info-literasi/budidaya-jagung"
                ),
                ArticleDetail(
                    id = 2,
                    p1 = "Irul, seorang warga di Kabupaten Magelang, berhasil menciptakan metode unik dalam budidaya padi dengan memanfaatkan kolam ikan sebagai media tanam. Melalui teknik hidroganik, ia menggunakan peralon berukuran 6 x 12 meter di atas kolam ikan berukuran 19 x 5 meter. Dengan kombinasi kompos dan sekam bakar sebagai media tanam, Irul mencatat hasil panen padi yang diperkirakan empat kali lipat lebih tinggi daripada cara konvensional di sawah.",
                    p2 = "Keunggulan metode ini terletak pada produktivitas yang lebih tinggi dan kontinuitas tanam yang lebih sering. Irul mampu menanam padi hingga lima kali dalam setahun, berkat proses tanam yang lebih efisien dan tanpa memerlukan pemangkasan lahan atau penggunaan alat berat seperti bajak. Media tanamnya juga berhasil mengendalikan gulma dan hama seperti walang, wereng, dan burung emprit, karena menggunakan campuran kompos dan sekam bakar dalam perbandingan tertentu.",
                    p3 = "Meskipun masih dalam tahap eksperimen, Irul telah menerima pesanan untuk membuat media tanam serupa dari Bogor. Dia menggunakan pompa akuarium untuk memastikan sirkulasi air yang baik, dengan air yang bersih kembali ke kolam setelah melewati peralon. Metode ini menjadi alternatif yang cocok bagi mereka yang memiliki lahan terbatas atau kesulitan akses air, menunjukkan potensi besar dalam mendukung ketahanan pangan keluarga.",
                    url = "https://www.beritamagelang.id/higdroganik-soluisi-menanam-padi-di-lahan-terbatas"
                ),
                ArticleDetail(
                    id = 3,
                    p1 = "Budidaya singkong merupakan kegiatan penting untuk memenuhi kebutuhan industri yang beragam, seperti tepung mocaf, tapioka, bioetanol, dan makanan camilan. Tanaman singkong, atau ketela pohon, memiliki sejarah panjang dan tersebar luas dari Amerika hingga ke Indonesia pada tahun 1852. Di Indonesia, ketela pohon tidak hanya sebagai makanan pokok, tetapi juga memiliki nilai ekonomis yang tinggi sebagai bahan baku industri pangan, pakan, dan obat-obatan.",
                    p2 = "Pertumbuhan ketela pohon memerlukan iklim yang sesuai, seperti curah hujan yang mencukupi, suhu udara minimal, dan kelembaban yang optimal. Tanah yang cocok untuk budidaya ketela pohon adalah yang kaya akan bahan organik dan memiliki pH yang tepat. Proses budidaya dimulai dari pembibitan hingga perawatan tanaman, termasuk teknik pemupukan, penyiraman, dan pengendalian hama serta penyakit yang melibatkan strategi khusus.",
                    p3 = "Panen dilakukan setelah umbi matang, biasanya setelah 6-12 bulan masa tanam tergantung pada varietasnya. Hasil panen kemudian disortir, disimpan, dan dikemas sesuai standar untuk mempertahankan kualitasnya. Dengan pengelolaan budidaya yang baik, ketela pohon dapat menjadi solusi untuk menjaga pasokan bahan baku industri tetap stabil dan harga terjangkau, sehingga mendukung produktivitas industri yang bergantung pada bahan baku tersebut.",
                    url = "https://distan.bulelengkab.go.id/informasi/detail/artikel/budidaya-tanaman-singkong-41"
                ),
            )
            articleDao.insertAllDetails(details)
        }
    }
}