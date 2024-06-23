package com.ryan.agriaid.data.local

import com.ryan.agriaid.R
import com.ryan.agriaid.data.local.article.Article
import com.ryan.agriaid.data.local.article.ArticleDetail
import com.ryan.agriaid.data.local.plants.Diseases
import com.ryan.agriaid.data.local.plants.Fertilization
import com.ryan.agriaid.data.local.plants.LandPreparations
import com.ryan.agriaid.data.local.plants.Nurseries
import com.ryan.agriaid.data.local.plants.Pests
import com.ryan.agriaid.data.local.plants.Plantings
import com.ryan.agriaid.data.local.plants.Plants
import com.ryan.agriaid.data.local.plants.Varieties
import com.ryan.agriaid.data.local.plants.Weeds
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object DatabaseInitializer {
    fun populateInitialData(database: NewsDatabase) {
        val articleDao = database.articleDao()
        val plantDao = database.plantDao()
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

            val plantsData = listOf(
                Plants(
                    id = 1,
                    name = "padi",
                    description = "Padi adalah tanaman pangan yang berasal dari tanaman padi. Padi biasa ditemukan di daerah tropis dan subtropis.",
                    imgUrl = R.drawable.padi
                ), Plants(
                    id = 2,
                    name = "jagung",
                    description = "Jagung adalah tanaman biji-bijian yang memiliki banyak manfaat sebagai bahan pangan dan pakan ternak.",
                    imgUrl = R.drawable.jagung
                ), Plants(
                    id = 3,
                    name = "buncis",
                    description = "Buncis adalah tanaman sayuran yang biasa dibudidayakan untuk dikonsumsi sebagai sayuran segar.",
                    imgUrl = R.drawable.buncis
                ), Plants(
                    id = 4,
                    name = "kacang_merah",
                    description = "Kacang merah adalah tanaman biji-bijian yang kaya akan protein dan serat.",
                    imgUrl = R.drawable.kacang_merah
                ), Plants(
                    id = 5,
                    name = "kacang_gude",
                    description = "Kacang pigeon digunakan dalam berbagai hidangan tradisional untuk nilai gizinya, sebagai pupuk hijau dan pakan ternak dalam pertanian, serta untuk manfaat kesehatan seperti meningkatkan pencernaan dan mengontrol kadar gula darah.",
                    imgUrl = R.drawable.kacang_gude
                ), Plants(
                    id = 6,
                    name = "kacang_matik",
                    description = "Mothbean digunakan dalam masakan tradisional untuk nilai gizinya yang tinggi, serta memberikan manfaat kesehatan seperti meningkatkan pencernaan dan mengatur kadar gula darah, sambil mendukung pertanian berkelanjutan melalui fiksasi nitrogen.",
                    imgUrl = R.drawable.kuda
                ), Plants(
                    id = 7,
                    name = "kacang_hijau",
                    description = "Kacang hijau adalah tanaman biji-bijian yang cepat tumbuh dan memiliki kandungan gizi yang baik.",
                    imgUrl = R.drawable.kacang_hijau
                ), Plants(
                    id = 8,
                    name = "kacang_hitam",
                    description = "Kacang hitam adalah tanaman biji-bijian yang kaya akan serat dan antioksidan.",
                    imgUrl = R.drawable.kacang_hitam
                ), Plants(
                    id = 9,
                    name = "kacang_lentis",
                    description = "Kacang lentil digunakan dalam berbagai masakan untuk kandungan proteinnya yang tinggi, serta memberikan manfaat kesehatan seperti meningkatkan pencernaan, dan mendukung kesehatan jantung.",
                    imgUrl = R.drawable.lentis
                ), Plants(
                    id = 10,
                    name = "delima",
                    description = "Delima adalah buah yang kaya akan vitamin C dan antioksidan, sering dibudidayakan untuk dijual segar atau diolah menjadi minuman.",
                    imgUrl = R.drawable.delima
                ), Plants(
                    id = 11,
                    name = "pisang",
                    description = "Pisang adalah buah-buahan tropis yang dikenal dengan rasa manisnya dan memiliki nilai gizi yang tinggi.",
                    imgUrl = R.drawable.pisang
                ), Plants(
                    id = 12,
                    name = "mangga",
                    description = "Mangga adalah buah tropis yang memiliki rasa manis dan sering dibudidayakan untuk dikonsumsi segar atau diolah menjadi berbagai produk.",
                    imgUrl = R.drawable.mangga
                ), Plants(
                    id = 13,
                    name = "anggur",
                    description = "Anggur adalah buah beri yang tumbuh di pohon anggur dan digunakan untuk membuat anggur atau dimakan langsung.",
                    imgUrl = R.drawable.anggur
                ), Plants(
                    id = 14,
                    name = "semangka",
                    description = "Semangka adalah buah berair yang sering dikonsumsi sebagai buah segar atau dijadikan minuman.",
                    imgUrl = R.drawable.semangka
                ), Plants(
                    id = 15,
                    name = "melon",
                    description = "Melon adalah buah berair dan manis yang sering dikonsumsi sebagai buah segar atau dijadikan campuran dalam salad.",
                    imgUrl = R.drawable.melon
                ), Plants(
                    id = 16,
                    name = "apel",
                    description = "Apel adalah buah yang kaya akan serat dan vitamin, sering dikonsumsi segar atau diolah menjadi berbagai produk olahan.",
                    imgUrl = R.drawable.apel
                ), Plants(
                    id = 17,
                    name = "jeruk",
                    description = "Jeruk adalah buah berair yang kaya akan vitamin C dan sering dikonsumsi segar atau diolah menjadi jus.",
                    imgUrl = R.drawable.jeruk
                ), Plants(
                    id = 18,
                    name = "pepaya",
                    description = "Pepaya adalah buah tropis yang memiliki rasa manis dan kaya akan vitamin C serta enzim pencernaan.",
                    imgUrl = R.drawable.pepaya
                ), Plants(
                    id = 19,
                    name = "kelapa",
                    description = "Kelapa adalah buah tropis yang memiliki banyak manfaat dari air kelapa hingga dagingnya yang dapat dimakan atau diolah menjadi minyak kelapa.",
                    imgUrl = R.drawable.kelapa
                ), Plants(
                    id = 20,
                    name = "kapas",
                    description = "Kapas adalah tanaman serat yang penting dalam industri tekstil untuk membuat benang dan kain.",
                    imgUrl = R.drawable.kapas
                ), Plants(
                    id = 21,
                    name = "rami",
                    description = "Rami adalah tanaman serat yang juga digunakan dalam industri tekstil untuk membuat kain dan produk lainnya.",
                    imgUrl = R.drawable.rami
                ), Plants(
                    id = 22,
                    name = "kopi",
                    description = "Kopi adalah biji-bijian yang digunakan untuk membuat minuman kopi yang disukai oleh banyak orang di seluruh dunia.",
                    imgUrl = R.drawable.kopi
                )
            )

            plantDao.insertAllPlants(plantsData)

            // Data untuk Budidaya Padi
            val padiLandPreparations = LandPreparations(
                plantsId = 1, // Sesuaikan dengan id tanaman padi di tabel Plants
                processing = "Lahan dibajak dan digaru untuk menghancurkan sisa tanaman dan menggemburkan tanah.",
                irrigation = "Lahan diisi air setinggi 5-10 cm dan dibiarkan selama 2 minggu untuk membunuh hama dan gulma."
            )

            plantDao.insertLandPreparations(padiLandPreparations)

            val padiNurseries = Nurseries(
                plantsId = 1, // Sesuaikan dengan id tanaman padi di tabel Plants
                seedSelection = "Gunakan benih varietas unggul yang bersertifikat.",
                soaking = "Benih direndam dalam air selama 24-36 jam, kemudian diperam selama 48 jam hingga berkecambah.",
                seeding = "Sebar benih di persemaian yang telah disiapkan dengan media tanah yang subur dan cukup air."
            )

            plantDao.insertNurseries(padiNurseries)

            // Data untuk Pemupukan Padi
            val padiFertilization = listOf(
                Fertilization(
                    plantsId = 1, // Sesuaikan dengan id tanaman padi di tabel Plants
                    type = "Dasar",
                    agePlant = 0,
                    urea = 100,
                    tsp = 50,
                    kcl = 50,
                    organicFertilizer = null
                ),
                Fertilization(
                    plantsId = 1, // Sesuaikan dengan id tanaman padi di tabel Plants
                    type = "Susulan",
                    agePlant = 20,
                    urea = 50,
                    tsp = 0,
                    kcl = 25,
                    organicFertilizer = null
                ),
                // tambahkan data untuk tahap-tahap pemupukan lainnya jika ada
            )

            // Masukkan data ke dalam tabel Fertilizations
            plantDao.insertFertilization(padiFertilization)

            // Data untuk Varietas Unggul Padi
            val padiVarieties = listOf(
                Varieties(
                    plantsId = 1, // Sesuaikan dengan id tanaman padi di tabel Plants
                    name = "IR64",
                    superiority = "Tahan terhadap beberapa hama dan penyakit, hasil panen tinggi, dan kualitas beras baik.",
                    lack = "Kurang tahan terhadap wereng coklat dan beberapa penyakit daun."
                ), Varieties(
                    plantsId = 1, // Sesuaikan dengan id tanaman padi di tabel Plants
                    name = "Ciherang",
                    superiority = "Tahan terhadap hama wereng coklat, hasil panen tinggi, dan kualitas beras medium.",
                    lack = "Rentan terhadap penyakit hawar daun bakteri."
                ), Varieties(
                    plantsId = 1, // Sesuaikan dengan id tanaman padi di tabel Plants
                    name = "Inpari 32",
                    superiority = "Tahan terhadap beberapa jenis hama dan penyakit, adaptasi baik di berbagai kondisi lahan.",
                    lack = "Kualitas beras medium, kurang tahan terhadap kondisi kekeringan."
                )
                // tambahkan data untuk varietas lainnya jika ada
            )

            // Masukkan data ke dalam tabel Variety
            plantDao.insertVarieties(padiVarieties)

            // Data untuk Penanganan Hama dan Penyakit Padi
            val padiPests = listOf(
                Pests(
                    plantsId = 1, // Sesuaikan dengan id tanaman padi di tabel Plants
                    name = "Wereng Coklat",
                    chemicalControl = "Gunakan insektisida seperti imidakloprid atau fipronil, dan tanam varietas yang tahan wereng.",
                    biologicalControl = "Penggunaan pengendalian biologis dengan memasang lampu perangkap."
                ), Pests(
                    plantsId = 1, // Sesuaikan dengan id tanaman padi di tabel Plants
                    name = "Penggerek Batang",
                    chemicalControl = "Gunakan pestisida seperti karbofuran, atau lakukan pengendalian biologis dengan memasang lampu perangkap.",
                    biologicalControl = null
                )
                // tambahkan data untuk hama dan penyakit lainnya jika ada
            )

            // Masukkan data ke dalam tabel Pests
            plantDao.insertPests(padiPests)

            // Data untuk Penanganan Gulma Padi
            val padiWeeds = Weeds(
                plantsId = 1, // Sesuaikan dengan id tanaman padi di tabel Plants
                manualWeeding = "Lakukan penyiangan 2-3 kali selama masa pertumbuhan padi, terutama pada minggu ke-3 dan ke-6 setelah tanam.",
                herbicide = "Gunakan herbisida pra-tumbuh seperti butaklor atau herbisida pasca-tumbuh seperti 2,4-D sesuai kebutuhan."
            )

            // Masukkan data ke dalam tabel Weeds
            plantDao.insertWeeds(padiWeeds)

            // Insert plantings for specific plants (example for "Padi")
            val padiPlantings = listOf(
                Plantings(
                    plantsId = 1,  // Assuming the ID of "Padi" plant in Plants table
                    preparing = "Pengolahan Tanah: Lahan dibajak dan digaru untuk menghancurkan sisa tanaman...",
                    planting = "Penyiapan Tanaman: Bibit berumur 20-25 hari siap dipindahkan ke lahan..."
                )
            )
            plantDao.insertPlantings(padiPlantings)

            // Insert diseases for specific plants (example for "Padi")
            val padiDiseases = listOf(
                Diseases(
                    plantsId = 1,  // Assuming the ID of "Padi" plant in Plants table
                    name = "Wereng Coklat",
                    control = "Gunakan insektisida seperti imidakloprid atau fipronil, dan tanam varietas yang tahan wereng."
                ), Diseases(
                    plantsId = 1,  // Assuming the ID of "Padi" plant in Plants table
                    name = "Hawar Daun Bakteri",
                    control = "Gunakan varietas tahan penyakit, jaga sanitasi lahan, dan aplikasi bakterisida seperti tembaga oksiklorida."
                )
            )
            plantDao.insertDiseases(padiDiseases)

            // Data untuk Budidaya Jagung
            val jagungLandPreparations = LandPreparations(
                plantsId = 2,
                processing = "Lahan dibajak dan digaru untuk meratakan dan menggemburkan tanah.",
                irrigation = "Lahan dibiarkan hingga mencapai kelembapan optimal sebelum penanaman."
            )

            plantDao.insertLandPreparations(jagungLandPreparations)

            val jagungNurseries = Nurseries(
                plantsId = 2,
                seedSelection = "Gunakan benih jagung varietas unggul yang bersertifikat.",
                soaking = "Benih direndam dalam air selama 12-24 jam, kemudian diperam selama 24 jam hingga berkecambah.",
                seeding = "Sebar benih di persemaian yang telah disiapkan dengan media tanah yang subur dan cukup air."
            )

            plantDao.insertNurseries(jagungNurseries)

            val jagungFertilization = listOf(
                Fertilization(
                    plantsId = 2,
                    type = "Dasar",
                    agePlant = 0,
                    urea = 150,
                    tsp = 75,
                    kcl = 75,
                    organicFertilizer = "Sebaiknya menggunakan pupuk kandang atau kompos sebanyak 5-10 ton/ha sebelum pengolahan tanah terakhir."
                ), Fertilization(
                    plantsId = 2,
                    type = "Susulan",
                    agePlant = 20,
                    urea = 50,
                    tsp = 0,
                    kcl = 25,
                    organicFertilizer = null
                ), Fertilization(
                    plantsId = 2,
                    type = "Susulan",
                    agePlant = 35,
                    urea = 50,
                    tsp = 0,
                    kcl = 25,
                    organicFertilizer = null
                ), Fertilization(
                    plantsId = 2,
                    type = "Susulan",
                    agePlant = 50,
                    urea = 50,
                    tsp = 0,
                    kcl = 25,
                    organicFertilizer = null
                )
            )

            plantDao.insertFertilization(jagungFertilization)

            val jagungVarieties = listOf(
                Varieties(
                    plantsId = 2,
                    name = "BISI 18",
                    superiority = "Produktivitas tinggi, adaptasi baik di berbagai kondisi lahan.",
                    lack = "Memerlukan pemupukan dan pengairan yang tepat untuk hasil optimal."
                ), Varieties(
                    plantsId = 2,
                    name = "NK 22",
                    superiority = "Tahan terhadap hama dan penyakit umum, hasil panen stabil.",
                    lack = "Memerlukan manajemen air yang baik untuk menghindari kekeringan."
                ), Varieties(
                    plantsId = 2,
                    name = "Pionir 28",
                    superiority = "Cepat berbunga dan matang, cocok untuk daerah dengan musim tanam pendek.",
                    lack = "Rentan terhadap serangan hama penggerek batang."
                )
            )

            plantDao.insertVarieties(jagungVarieties)

            val jagungPests = listOf(
                Pests(
                    plantsId = 2,
                    name = "Penggerek Batang",
                    chemicalControl = "Gunakan pestisida seperti karbofuran, atau lakukan pengendalian biologis dengan memasang perangkap feromon.",
                    biologicalControl = null
                ), Pests(
                    plantsId = 2,
                    name = "Ulat Grayak",
                    chemicalControl = "Gunakan insektisida yang direkomendasikan, terutama saat tanaman masih muda.",
                    biologicalControl = null
                )
            )

            plantDao.insertPests(jagungPests)

            val jagungDiseases = listOf(
                Diseases(
                    plantsId = 2,
                    name = "Busuk Pangkal Batang",
                    control = "Jaga sanitasi lahan, hindari genangan air, dan gunakan varietas tahan penyakit."
                ), Diseases(
                    plantsId = 2,
                    name = "Bercak Daun Bipolaris",
                    control = "Gunakan fungisida yang tepat, terutama saat fase pertumbuhan tanaman."
                )
            )

            plantDao.insertDiseases(jagungDiseases)

            val jagungWeeds = Weeds(
                plantsId = 2,
                manualWeeding = "Lakukan penyiangan 2-3 kali selama masa pertumbuhan jagung, terutama pada minggu ke-3 dan ke-6 setelah tanam.",
                herbicide = "Gunakan herbisida sesuai anjuran petani untuk mengendalikan gulma yang sulit dikontrol secara manual."
            )

            plantDao.insertWeeds(jagungWeeds)

            val jagungPlantings = listOf(
                Plantings(
                    plantsId = 2,
                    preparing = "Pengolahan Tanah: Lahan dibajak dan digaru untuk meratakan dan menggemburkan tanah...",
                    planting = "Penyiapan Tanaman: Bibit berumur 20-25 hari siap dipindahkan ke lahan..."
                )
            )

            plantDao.insertPlantings(jagungPlantings)

            // Data untuk Budidaya Buncis
            val buncisLandPreparations = LandPreparations(
                plantsId = 3,
                processing = "Lahan dibajak dan diratakan untuk mempersiapkan tanah yang gembur.",
                irrigation = "Pastikan lahan memiliki kelembapan yang cukup sebelum penanaman."
            )

            plantDao.insertLandPreparations(buncisLandPreparations)

            val buncisNurseries = Nurseries(
                plantsId = 3,
                seedSelection = "Gunakan benih buncis varietas unggul yang bersertifikat.",
                soaking = "Benih direndam dalam air selama 8-12 jam, kemudian diperam selama 12-24 jam hingga berkecambah.",
                seeding = "Sebar benih di persemaian yang telah disiapkan dengan media tanah yang subur dan cukup air."
            )

            plantDao.insertNurseries(buncisNurseries)

            val buncisFertilization = listOf(
                Fertilization(
                    plantsId = 3,
                    type = "Dasar",
                    agePlant = 0,
                    urea = 50,
                    tsp = 25,
                    kcl = 25,
                    organicFertilizer = "Gunakan pupuk kandang atau kompos sebanyak 5-10 ton/ha sebelum penanaman."
                ),
                Fertilization(
                    plantsId = 3,
                    type = "Susulan",
                    agePlant = 3,
                    urea = 20,
                    tsp = 0,
                    kcl = 0,
                    organicFertilizer = null
                ),
                Fertilization(
                    plantsId = 3,
                    type = "Susulan",
                    agePlant = 6,
                    urea = 20,
                    tsp = 0,
                    kcl = 10,
                    organicFertilizer = null
                )
            )

            plantDao.insertFertilization(buncisFertilization)

            val buncisVarieties = listOf(
                Varieties(
                    plantsId = 3,
                    name = "Varietas Lokal",
                    superiority = "Adaptasi baik di berbagai kondisi lahan lokal, produktivitas tinggi.",
                    lack = "Rentan terhadap beberapa hama dan penyakit tertentu."
                ),
                Varieties(
                    plantsId = 3,
                    name = "Varietas Import",
                    superiority = "Kualitas dan hasil panen yang lebih konsisten.",
                    lack = "Memerlukan perawatan lebih intensif dan pemantauan terhadap kondisi tumbuhnya."
                )
            )

            plantDao.insertVarieties(buncisVarieties)

            val buncisPests = listOf(
                Pests(
                    plantsId = 3,
                    name = "Thrips",
                    chemicalControl = "Gunakan insektisida yang direkomendasikan, terutama saat tanaman muda.",
                    biologicalControl = null
                ),
                Pests(
                    plantsId = 3,
                    name = "Ulat Grayak",
                    chemicalControl = "Kontrol dengan aplikasi insektisida secara tepat waktu.",
                    biologicalControl = null
                )
            )

            plantDao.insertPests(buncisPests)

            val buncisDiseases = listOf(
                Diseases(
                    plantsId = 3,
                    name = "Busuk Akar Fusarium",
                    control = "Gunakan fungisida yang efektif untuk pengendalian penyakit ini."
                ),
                Diseases(
                    plantsId = 3,
                    name = "Karat Daun",
                    control = "Jaga sanitasi lahan dan aplikasikan fungisida sesuai anjuran."
                )
            )

            plantDao.insertDiseases(buncisDiseases)

            val buncisWeeds = Weeds(
                plantsId = 3,
                manualWeeding = "Lakukan secara rutin untuk mengendalikan pertumbuhan gulma di sekitar tanaman.",
                herbicide = "Gunakan herbisida sesuai petunjuk label untuk mengurangi persaingan gulma dengan tanaman buncis."
            )

            plantDao.insertWeeds(buncisWeeds)

            val buncisPlantings = listOf(
                Plantings(
                    plantsId = 3,
                    preparing = "Pengolahan Tanah: Lahan dibajak dan diratakan untuk mempersiapkan tanah yang gembur...",
                    planting = "Penyiapan Tanaman: Bibit berumur 15-20 hari siap dipindahkan ke lahan..."
                )
            )

            plantDao.insertPlantings(buncisPlantings)

            // Data untuk Budidaya Kacang Merah
            val kacangMerahLandPreparations = LandPreparations(
                plantsId = 4,
                processing = "Lahan dibajak dan diratakan untuk mempersiapkan tanah yang gembur.",
                irrigation = "Pastikan lahan memiliki kelembapan yang cukup sebelum penanaman."
            )

            plantDao.insertLandPreparations(kacangMerahLandPreparations)

            val kacangMerahNurseries = Nurseries(
                plantsId = 4,
                seedSelection = "Gunakan benih kacang merah varietas unggul yang bersertifikat.",
                soaking = "Benih direndam dalam air selama 10-14 jam, kemudian diperam selama 24 jam hingga berkecambah.",
                seeding = "Sebar benih di persemaian yang telah disiapkan dengan media tanah yang subur dan cukup air."
            )

            plantDao.insertNurseries(kacangMerahNurseries)

            val kacangMerahFertilization = listOf(
                Fertilization(
                    plantsId = 4,
                    type = "Dasar",
                    agePlant = 0,
                    urea = 75,
                    tsp = 40,
                    kcl = 40,
                    organicFertilizer = "Gunakan pupuk kandang atau kompos sebanyak 7-12 ton/ha sebelum penanaman."
                ),
                Fertilization(
                    plantsId = 4,
                    type = "Susulan",
                    agePlant = 3,
                    urea = 30,
                    tsp = 0,
                    kcl = 0,
                    organicFertilizer = null
                ),
                Fertilization(
                    plantsId = 4,
                    type = "Susulan",
                    agePlant = 6,
                    urea = 30,
                    tsp = 0,
                    kcl = 15,
                    organicFertilizer = null
                )
            )

            plantDao.insertFertilization(kacangMerahFertilization)

            val kacangMerahVarieties = listOf(
                Varieties(
                    plantsId = 4,
                    name = "Varietas Red Kidney",
                    superiority = "Hasil panen tinggi, kualitas biji baik untuk pasar.",
                    lack = "Rentan terhadap kondisi tanah yang terlalu lembab."
                ),
                Varieties(
                    plantsId = 4,
                    name = "Varietas Black Turtle",
                    superiority = "Tahan terhadap beberapa hama dan penyakit, adaptasi baik di berbagai kondisi lahan.",
                    lack = "Memerlukan manajemen air yang baik untuk menghindari kelembaban berlebih."
                )
            )

            plantDao.insertVarieties(kacangMerahVarieties)

            val kacangMerahPests = listOf(
                Pests(
                    plantsId = 4,
                    name = "Ulat Grayak",
                    chemicalControl = "Kontrol dengan aplikasi insektisida secara tepat waktu.",
                    biologicalControl = null
                ),
                Pests(
                    plantsId = 4,
                    name = "Thrips",
                    chemicalControl = "Gunakan insektisida yang direkomendasikan, terutama saat tanaman muda.",
                    biologicalControl = null
                )
            )

            plantDao.insertPests(kacangMerahPests)

            val kacangMerahDiseases = listOf(
                Diseases(
                    plantsId = 4,
                    name = "Busuk Akar Fusarium",
                    control = "Gunakan fungisida yang efektif untuk pengendalian penyakit ini."
                ),
                Diseases(
                    plantsId = 4,
                    name = "Penyakit Layu Bercak Daun",
                    control = "Jaga sanitasi lahan dan aplikasikan fungisida sesuai anjuran."
                )
            )

            plantDao.insertDiseases(kacangMerahDiseases)

            val kacangMerahWeeds = Weeds(
                plantsId = 4,
                manualWeeding = "Lakukan secara rutin untuk mengendalikan pertumbuhan gulma di sekitar tanaman.",
                herbicide = "Gunakan herbisida sesuai petunjuk label untuk mengurangi persaingan gulma dengan tanaman kacang merah."
            )

            plantDao.insertWeeds(kacangMerahWeeds)

            val kacangMerahPlantings = listOf(
                Plantings(
                    plantsId = 4,
                    preparing = "Pengolahan Tanah: Lahan dibajak dan diratakan untuk mempersiapkan tanah yang gembur...",
                    planting = "Penyiapan Tanaman: Bibit berumur 20-25 hari siap dipindahkan ke lahan..."
                )
            )

            plantDao.insertPlantings(kacangMerahPlantings)

            val kacangGudeLandPreparations = LandPreparations(
                plantsId = 5,
                processing = "Lahan dibajak dan diratakan untuk mempersiapkan tanah yang gembur.",
                irrigation = "Pastikan lahan memiliki kelembapan yang cukup sebelum penanaman."
            )

            plantDao.insertLandPreparations(kacangGudeLandPreparations)

            val kacangGudeNurseries = Nurseries(
                plantsId = 5,
                seedSelection = "Gunakan benih kacang gude varietas unggul yang bersertifikat.",
                soaking = "Benih direndam dalam air selama 12-18 jam, kemudian diperam selama 24 jam hingga berkecambah.",
                seeding = "Sebar benih di persemaian yang telah disiapkan dengan media tanah yang subur dan cukup air."
            )

            plantDao.insertNurseries(kacangGudeNurseries)

            val kacangGudeFertilization = listOf(
                Fertilization(
                    plantsId = 5,
                    type = "Dasar",
                    agePlant = 0,
                    urea = 80,
                    tsp = 50,
                    kcl = 50,
                    organicFertilizer = "Gunakan pupuk kandang atau kompos sebanyak 8-12 ton/ha sebelum penanaman."
                ),
                Fertilization(
                    plantsId = 5,
                    type = "Susulan",
                    agePlant = 3,
                    urea = 40,
                    tsp = 0,
                    kcl = 0,
                    organicFertilizer = null
                ),
                Fertilization(
                    plantsId = 5,
                    type = "Susulan",
                    agePlant = 6,
                    urea = 40,
                    tsp = 0,
                    kcl = 20,
                    organicFertilizer = null
                )
            )

            plantDao.insertFertilization(kacangGudeFertilization)

            val kacangGudeVarieties = listOf(
                Varieties(
                    plantsId = 5,
                    name = "Varietas Gude Unggul",
                    superiority = "Hasil panen tinggi, kualitas biji baik untuk konsumsi.",
                    lack = "Memerlukan perhatian ekstra terhadap hama dan penyakit."
                ),
                Varieties(
                    plantsId = 5,
                    name = "Varietas Gude Lokal",
                    superiority = "Adaptasi baik di berbagai kondisi lahan lokal, produktivitas stabil.",
                    lack = "Rentan terhadap kondisi tanah yang tidak subur."
                )
            )

            plantDao.insertVarieties(kacangGudeVarieties)

            val kacangGudePests = listOf(
                Pests(
                    plantsId = 5,
                    name = "Kumbang Penggerek Batang",
                    chemicalControl = "Kontrol dengan aplikasi insektisida yang direkomendasikan.",
                    biologicalControl = null
                ),
                Pests(
                    plantsId = 5,
                    name = "Ulat Grayak",
                    chemicalControl = "Gunakan metode pengendalian biologis atau insektisida sesuai kebutuhan.",
                    biologicalControl = null
                )
            )

            plantDao.insertPests(kacangGudePests)

            val kacangGudeDiseases = listOf(
                Diseases(
                    plantsId = 5,
                    name = "Antraknosa",
                    control = "Gunakan fungisida yang efektif untuk mengendalikan penyakit ini."
                ),
                Diseases(
                    plantsId = 5,
                    name = "Busuk Batang Fusarium",
                    control = "Terapkan tindakan sanitasi dan penggunaan fungisida secara tepat."
                )
            )

            plantDao.insertDiseases(kacangGudeDiseases)

            val kacangGudeWeeds = Weeds(
                plantsId = 5,
                manualWeeding = "Lakukan secara berkala untuk mengontrol pertumbuhan gulma di sekitar tanaman.",
                herbicide = "Gunakan herbisida yang sesuai untuk mengurangi persaingan gulma dengan tanaman kacang gude."
            )

            plantDao.insertWeeds(kacangGudeWeeds)

            val kacangGudePlantings = listOf(
                Plantings(
                    plantsId = 5,
                    preparing = "Pengolahan Tanah: Lahan dibajak dan diratakan untuk mempersiapkan tanah yang gembur...",
                    planting = "Penyiapan Tanaman: Bibit berumur 15-20 hari siap dipindahkan ke lahan..."
                )
            )

            plantDao.insertPlantings(kacangGudePlantings)

            val kacangKudaLandPreparations = LandPreparations(
                plantsId = 6,
                processing = "Lahan dibajak dan diratakan untuk mempersiapkan tanah yang gembur.",
                irrigation = "Pastikan lahan memiliki kelembapan yang cukup sebelum penanaman."
            )

            plantDao.insertLandPreparations(kacangKudaLandPreparations)

            val kacangKudaNurseries = Nurseries(
                plantsId = 6,
                seedSelection = "Gunakan benih kacang kuda varietas unggul yang bersertifikat.",
                soaking = "Benih direndam dalam air selama 8-12 jam, kemudian diperam selama 12-24 jam hingga berkecambah.",
                seeding = "Sebar benih di persemaian yang telah disiapkan dengan media tanah yang subur dan cukup air."
            )

            plantDao.insertNurseries(kacangKudaNurseries)

            val kacangKudaFertilization = listOf(
                Fertilization(
                    plantsId = 6,
                    type = "Dasar",
                    agePlant = 0,
                    urea = 70,
                    tsp = 40,
                    kcl = 40,
                    organicFertilizer = "Gunakan pupuk kandang atau kompos sebanyak 8-12 ton/ha sebelum penanaman."
                ),
                Fertilization(
                    plantsId = 6,
                    type = "Susulan",
                    agePlant = 3,
                    urea = 30,
                    tsp = 0,
                    kcl = 0,
                    organicFertilizer = null
                ),
                Fertilization(
                    plantsId = 6,
                    type = "Susulan",
                    agePlant = 6,
                    urea = 30,
                    tsp = 0,
                    kcl = 20,
                    organicFertilizer = null
                )
            )

            plantDao.insertFertilization(kacangKudaFertilization)

            val kacangKudaVarieties = listOf(
                Varieties(
                    plantsId = 6,
                    name = "Varietas Kacang Kuda Hijau",
                    superiority = "Produktivitas tinggi, adaptasi baik di daerah tropis.",
                    lack = "Memerlukan perawatan air yang cukup selama musim kering."
                ),
                Varieties(
                    plantsId = 6,
                    name = "Varietas Kacang Kuda Hitam",
                    superiority = "Kualitas biji yang baik, tahan terhadap hama tertentu.",
                    lack = "Rentan terhadap penyakit pada kondisi cuaca lembap."
                )
            )

            plantDao.insertVarieties(kacangKudaVarieties)

            val kacangKudaPests = listOf(
                Pests(
                    plantsId = 6,
                    name = "Kumbang Penggerek Batang",
                    chemicalControl = "Kontrol dengan aplikasi insektisida yang direkomendasikan.",
                    biologicalControl = null
                ),
                Pests(
                    plantsId = 6,
                    name = "Ulat Grayak",
                    chemicalControl = "Gunakan metode pengendalian biologis atau insektisida sesuai kebutuhan.",
                    biologicalControl = null
                )
            )

            plantDao.insertPests(kacangKudaPests)

            val kacangKudaDiseases = listOf(
                Diseases(
                    plantsId = 6,
                    name = "Antraknosa",
                    control = "Gunakan fungisida yang efektif untuk mengendalikan penyakit ini."
                ),
                Diseases(
                    plantsId = 6,
                    name = "Busuk Batang Fusarium",
                    control = "Terapkan tindakan sanitasi dan penggunaan fungisida secara tepat."
                )
            )

            plantDao.insertDiseases(kacangKudaDiseases)

            val kacangKudaWeeds = Weeds(
                plantsId = 6,
                manualWeeding = "Lakukan secara berkala untuk mengontrol pertumbuhan gulma di sekitar tanaman.",
                herbicide = "Gunakan herbisida yang sesuai untuk mengurangi persaingan gulma dengan tanaman kacang kuda."
            )

            plantDao.insertWeeds(kacangKudaWeeds)

            val kacangKudaPlantings = listOf(
                Plantings(
                    plantsId = 6,
                    preparing = "Pengolahan Tanah: Lahan dibajak dan diratakan untuk mempersiapkan tanah yang gembur...",
                    planting = "Penyiapan Tanaman: Bibit berumur 15-20 hari siap dipindahkan ke lahan..."
                )
            )

            plantDao.insertPlantings(kacangKudaPlantings)

            val kacangHijauLandPreparations = LandPreparations(
                plantsId = 7,
                processing = "Lahan dibajak dan diratakan untuk mempersiapkan tanah yang gembur.",
                irrigation = "Pastikan lahan memiliki kelembapan yang cukup sebelum penanaman."
            )

            plantDao.insertLandPreparations(kacangHijauLandPreparations)

            val kacangHijauNurseries = Nurseries(
                plantsId = 7,
                seedSelection = "Gunakan benih kacang hijau varietas unggul yang bersertifikat.",
                soaking = "Benih direndam dalam air selama 6-10 jam, kemudian diperam selama 12-18 jam hingga berkecambah.",
                seeding = "Sebar benih di persemaian yang telah disiapkan dengan media tanah yang subur dan cukup air."
            )

            plantDao.insertNurseries(kacangHijauNurseries)

            val kacangHijauFertilization = listOf(
                Fertilization(
                    plantsId = 7,
                    type = "Dasar",
                    agePlant = 0,
                    urea = 50,
                    tsp = 30,
                    kcl = 30,
                    organicFertilizer = "Gunakan pupuk kandang atau kompos sebanyak 5-10 ton/ha sebelum penanaman."
                ),
                Fertilization(
                    plantsId = 7,
                    type = "Susulan",
                    agePlant = 3,
                    urea = 20,
                    tsp = 0,
                    kcl = 0,
                    organicFertilizer = null
                ),
                Fertilization(
                    plantsId = 7,
                    type = "Susulan",
                    agePlant = 6,
                    urea = 20,
                    tsp = 0,
                    kcl = 15,
                    organicFertilizer = null
                )
            )

            plantDao.insertFertilization(kacangHijauFertilization)

            val kacangHijauVarieties = listOf(
                Varieties(
                    plantsId = 7,
                    name = "Varietas Kacang Hijau Biasa",
                    superiority = "Produktivitas tinggi, cocok untuk pengolahan makanan dan pangan.",
                    lack = "Rentan terhadap serangan hama dan penyakit tertentu."
                ),
                Varieties(
                    plantsId = 7,
                    name = "Varietas Kacang Hijau Super",
                    superiority = "Resistensi lebih baik terhadap hama dan penyakit, hasil panen stabil.",
                    lack = "Memerlukan manajemen air yang baik untuk hasil optimal."
                )
            )

            plantDao.insertVarieties(kacangHijauVarieties)

            val kacangHijauPests = listOf(
                Pests(
                    plantsId = 7,
                    name = "Kumbang Penggerek Batang",
                    chemicalControl = "Kontrol dengan aplikasi insektisida yang direkomendasikan.",
                    biologicalControl = null
                ),
                Pests(
                    plantsId = 7,
                    name = "Ulat Grayak",
                    chemicalControl = "Gunakan metode pengendalian biologis atau insektisida sesuai kebutuhan.",
                    biologicalControl = null
                )
            )

            plantDao.insertPests(kacangHijauPests)

            val kacangHijauDiseases = listOf(
                Diseases(
                    plantsId = 7,
                    name = "Busuk Pangkal Batang",
                    control = "Gunakan fungisida yang efektif untuk pengendalian penyakit ini."
                ),
                Diseases(
                    plantsId = 7,
                    name = "Antraknosa",
                    control = "Terapkan tindakan sanitasi dan penggunaan fungisida secara tepat."
                )
            )

            plantDao.insertDiseases(kacangHijauDiseases)

            val kacangHijauWeeds = Weeds(
                plantsId = 7,
                manualWeeding = "Lakukan secara berkala untuk mengontrol pertumbuhan gulma di sekitar tanaman.",
                herbicide = "Gunakan herbisida yang sesuai untuk mengurangi persaingan gulma dengan tanaman kacang hijau."
            )

            plantDao.insertWeeds(kacangHijauWeeds)

            val kacangHijauPlantings = listOf(
                Plantings(
                    plantsId = 7,
                    preparing = "Pengolahan Tanah: Lahan dibajak dan diratakan untuk mempersiapkan tanah yang gembur...",
                    planting = "Penyiapan Tanaman: Bibit berumur 15-20 hari siap dipindahkan ke lahan..."
                )
            )

            plantDao.insertPlantings(kacangHijauPlantings)

            val kacangHitamLandPreparations = LandPreparations(
                plantsId = 8,
                processing = "Lahan dibajak dan diratakan untuk mempersiapkan tanah yang gembur.",
                irrigation = "Pastikan lahan memiliki kelembapan yang cukup sebelum penanaman."
            )

            plantDao.insertLandPreparations(kacangHitamLandPreparations)

            val kacangHitamNurseries = Nurseries(
                plantsId = 8,
                seedSelection = "Gunakan benih kacang hitam varietas unggul yang bersertifikat.",
                soaking = "Benih direndam dalam air selama 8-12 jam, kemudian diperam selama 18-24 jam hingga berkecambah.",
                seeding = "Sebar benih di persemaian yang telah disiapkan dengan media tanah yang subur dan cukup air."
            )

            plantDao.insertNurseries(kacangHitamNurseries)

            val kacangHitamFertilization = listOf(
                Fertilization(
                    plantsId = 8,
                    type = "Dasar",
                    agePlant = 0,
                    urea = 60,
                    tsp = 35,
                    kcl = 35,
                    organicFertilizer = "Gunakan pupuk kandang atau kompos sebanyak 6-10 ton/ha sebelum penanaman."
                ),
                Fertilization(
                    plantsId = 8,
                    type = "Susulan",
                    agePlant = 3,
                    urea = 25,
                    tsp = 0,
                    kcl = 0,
                    organicFertilizer = null
                ),
                Fertilization(
                    plantsId = 8,
                    type = "Susulan",
                    agePlant = 6,
                    urea = 25,
                    tsp = 0,
                    kcl = 15,
                    organicFertilizer = null
                )
            )

            plantDao.insertFertilization(kacangHitamFertilization)

            val kacangHitamVarieties = listOf(
                Varieties(
                    plantsId = 8,
                    name = "Varietas Kacang Hitam Lokal",
                    superiority = "Adaptasi baik di berbagai kondisi lahan lokal, hasil panen stabil.",
                    lack = "Rentan terhadap serangan hama dan penyakit tertentu."
                ),
                Varieties(
                    plantsId = 8,
                    name = "Varietas Kacang Hitam Super",
                    superiority = "Toleransi yang baik terhadap kondisi cuaca ekstrem, kualitas biji yang baik.",
                    lack = "Memerlukan perawatan ekstra terhadap nutrisi tanah."
                )
            )

            plantDao.insertVarieties(kacangHitamVarieties)

            val kacangHitamPests = listOf(
                Pests(
                    plantsId = 8,
                    name = "Kumbang Penggerek Batang",
                    chemicalControl = "Kontrol dengan aplikasi insektisida yang direkomendasikan.",
                    biologicalControl = null
                ),
                Pests(
                    plantsId = 8,
                    name = "Ulat Grayak",
                    chemicalControl = "Gunakan metode pengendalian biologis atau insektisida sesuai kebutuhan.",
                    biologicalControl = null
                )
            )

            plantDao.insertPests(kacangHitamPests)

            val kacangHitamDiseases = listOf(
                Diseases(
                    plantsId = 8,
                    name = "Busuk Pangkal Batang",
                    control = "Gunakan fungisida yang efektif untuk pengendalian penyakit ini."
                ),
                Diseases(
                    plantsId = 8,
                    name = "Antraknosa",
                    control = "Terapkan tindakan sanitasi dan penggunaan fungisida secara tepat."
                )
            )

            plantDao.insertDiseases(kacangHitamDiseases)

            val kacangHitamWeeds = Weeds(
                plantsId = 8,
                manualWeeding = "Lakukan secara berkala untuk mengontrol pertumbuhan gulma di sekitar tanaman.",
                herbicide = "Gunakan herbisida yang sesuai untuk mengurangi persaingan gulma dengan tanaman kacang hitam."
            )

            plantDao.insertWeeds(kacangHitamWeeds)

            val kacangHitamPlantings = listOf(
                Plantings(
                    plantsId = 8,
                    preparing = "Pengolahan Tanah: Lahan dibajak dan diratakan untuk mempersiapkan tanah yang gembur...",
                    planting = "Penyiapan Tanaman: Bibit berumur 15-20 hari siap dipindahkan ke lahan..."
                )
            )

            plantDao.insertPlantings(kacangHitamPlantings)

            val lentisLandPreparations = LandPreparations(
                plantsId = 9,
                processing = "Lahan dibajak dan digaru untuk mempersiapkan tanah yang gembur dan bersih dari sisa tanaman sebelumnya.",
                irrigation = "Lahan diirigasi untuk memastikan kelembapan optimal sebelum penanaman."
            )

            plantDao.insertLandPreparations(lentisLandPreparations)

            val lentisNurseries = Nurseries(
                plantsId = 9,
                seedSelection = "Gunakan benih varietas lentis unggul yang telah bersertifikat.",
                soaking = "Benih direndam dalam air selama 8-12 jam, kemudian diperam selama 24 jam hingga berkecambah.",
                seeding = "Sebar benih di persemaian yang telah disiapkan dengan media tanah yang subur dan cukup air."
            )

            plantDao.insertNurseries(lentisNurseries)

            val lentisFertilization = listOf(
                Fertilization(
                    plantsId = 9,
                    type = "Dasar",
                    agePlant = 0,
                    urea = 50,
                    tsp = 30,
                    kcl = 30,
                    organicFertilizer = "Gunakan pupuk kandang atau kompos sebanyak 5-8 ton/ha sebelum penanaman."
                ),
                Fertilization(
                    plantsId = 9,
                    type = "Susulan",
                    agePlant = 3,
                    urea = 20,
                    tsp = 0,
                    kcl = 0,
                    organicFertilizer = null
                ),
                Fertilization(
                    plantsId = 9,
                    type = "Susulan",
                    agePlant = 6,
                    urea = 20,
                    tsp = 0,
                    kcl = 15,
                    organicFertilizer = null
                )
            )

            plantDao.insertFertilization(lentisFertilization)

            val lentisVarieties = listOf(
                Varieties(
                    plantsId = 9,
                    name = "Varietas Lentis Hijau",
                    superiority = "Produktivitas tinggi, adaptasi baik di daerah dengan musim tanam yang pendek.",
                    lack = "Rentan terhadap serangan hama seperti ulat grayak."
                ),
                Varieties(
                    plantsId = 9,
                    name = "Varietas Lentis Merah",
                    superiority = "Kualitas biji yang baik, tahan terhadap beberapa jenis hama dan penyakit.",
                    lack = "Memerlukan manajemen air yang baik untuk hasil optimal."
                )
            )

            plantDao.insertVarieties(lentisVarieties)

            val lentisPests = listOf(
                Pests(
                    plantsId = 9,
                    name = "Ulat Grayak",
                    chemicalControl = "Gunakan insektisida yang direkomendasikan untuk pengendalian ulat grayak.",
                    biologicalControl = null
                ),
                Pests(
                    plantsId = 9,
                    name = "Kutu Daun",
                    chemicalControl = "Gunakan pestisida yang efektif untuk mengurangi populasi kutu daun.",
                    biologicalControl = null
                )
            )

            plantDao.insertPests(lentisPests)

            val lentisDiseases = listOf(
                Diseases(
                    plantsId = 9,
                    name = "Busuk Pangkal Batang",
                    control = "Terapkan fungisida yang tepat untuk mengendalikan penyakit ini."
                ),
                Diseases(
                    plantsId = 9,
                    name = "Fusarium Wilt",
                    control = "Jaga sanitasi lahan dan gunakan varietas tahan terhadap penyakit ini."
                )
            )

            plantDao.insertDiseases(lentisDiseases)

            val lentisWeeds = Weeds(
                plantsId = 9,
                manualWeeding = "Lakukan secara teratur untuk mengontrol pertumbuhan gulma di sekitar tanaman lentis.",
                herbicide = "Gunakan herbisida sesuai kebutuhan untuk mengurangi persaingan gulma dengan tanaman lentis."
            )

            plantDao.insertWeeds(lentisWeeds)

            val lentisPlantings = listOf(
                Plantings(
                    plantsId = 9,
                    preparing = "Pengolahan Tanah: Lahan dibajak dan digaru untuk mempersiapkan tanah yang gembur dan bersih dari sisa tanaman sebelumnya...",
                    planting = "Penyiapan Tanaman: Bibit berumur 20-25 hari siap dipindahkan ke lahan..."
                )
            )

            plantDao.insertPlantings(lentisPlantings)

            val pomegranateLandPreparations = LandPreparations(
                plantsId = 10,
                processing = "Lahan dibajak dan diratakan untuk mempersiapkan tanah yang gembur dan bersih dari sisa tanaman sebelumnya.",
                irrigation = "Pastikan lahan memiliki drainase yang baik dan kelembapan optimal sebelum penanaman."
            )

            plantDao.insertLandPreparations(pomegranateLandPreparations)

            val pomegranateNurseries = Nurseries(
                plantsId = 10,
                seedSelection = "Gunakan bibit delima dari tanaman induk yang sehat dan berkualitas.",
                soaking = "Benih direndam dalam air selama 24 jam untuk meningkatkan tingkat perkecambahan.",
                seeding = "Tanam bibit dalam pot atau bedengan yang telah diisi dengan media tanah yang subur dan cukup air."
            )

            plantDao.insertNurseries(pomegranateNurseries)

            val pomegranateFertilization = listOf(
                Fertilization(
                    plantsId = 10,
                    type = "Dasar",
                    agePlant = 0,
                    urea = 0,
                    tsp = 0,
                    kcl = 0,
                    organicFertilizer = "Berikan pupuk kompos atau pupuk kandang dalam jumlah yang cukup untuk memperbaiki struktur tanah dan memberikan nutrisi tambahan."
                ),
                Fertilization(
                    plantsId = 10,
                    type = "Dasar",
                    agePlant = 0,
                    urea = 0,
                    tsp = 0,
                    kcl = 0,
                    organicFertilizer = null
                ),
                Fertilization(
                    plantsId = 10,
                    type = "Susulan",
                    agePlant = 2,
                    urea = 0,
                    tsp = 0,
                    kcl = 0,
                    organicFertilizer = null
                ),
                Fertilization(
                    plantsId = 10,
                    type = "Susulan",
                    agePlant = 5,
                    urea = 0,
                    tsp = 0,
                    kcl = 0,
                    organicFertilizer = null
                )
            )

            plantDao.insertFertilization(pomegranateFertilization)

            val pomegranateVarieties = listOf(
                Varieties(
                    plantsId = 10,
                    name = "Varietas Delima Ruby",
                    superiority = "Buah besar dengan kulit merah cerah, rasa manis, dan kaya akan antioksidan.",
                    lack = "Memerlukan perawatan ekstra terutama dalam pemangkasan dan pengendalian hama."
                ),
                Varieties(
                    plantsId = 10,
                    name = "Varietas Delima Wonderful",
                    superiority = "Produktivitas tinggi, buah besar dengan biji sedikit, cocok untuk pasar ekspor.",
                    lack = "Rentan terhadap penyakit seperti busuk akar dan layu bakteri."
                )
            )

            plantDao.insertVarieties(pomegranateVarieties)

            val pomegranatePests = listOf(
                Pests(
                    plantsId = 10,
                    name = "Kutu Daun",
                    chemicalControl = "Gunakan insektisida yang tepat untuk mengendalikan populasi kutu daun.",
                    biologicalControl = null
                ),
                Pests(
                    plantsId = 10,
                    name = "Ulat Buah",
                    chemicalControl = "Pantau secara teratur dan lakukan tindakan kontrol fisik atau kimiawi jika diperlukan.",
                    biologicalControl = null
                )
            )

            plantDao.insertPests(pomegranatePests)

            val pomegranateDiseases = listOf(
                Diseases(
                    plantsId = 10,
                    name = "Busuk Akar",
                    control = "Terapkan fungisida sistemik untuk mengendalikan penyakit ini."
                ),
                Diseases(
                    plantsId = 10,
                    name = "Layu Bakteri",
                    control = "Lakukan sanitasi lahan dan tanaman serta gunakan varietas yang tahan terhadap penyakit ini."
                )
            )

            plantDao.insertDiseases(pomegranateDiseases)

            val pomegranateWeeds = Weeds(
                plantsId = 10,
                manualWeeding = "Lakukan secara berkala untuk mengontrol pertumbuhan gulma di sekitar tanaman delima.",
                herbicide = "Gunakan herbisida selektif untuk mengurangi persaingan gulma tanpa merusak tanaman delima."
            )

            plantDao.insertWeeds(pomegranateWeeds)

            val pomegranatePlantings = listOf(
                Plantings(
                    plantsId = 10,
                    preparing = "Pengolahan Tanah: Lahan dibajak dan diratakan untuk mempersiapkan tanah yang gembur dan bersih dari sisa tanaman sebelumnya...",
                    planting = "Penyiapan Tanaman: Bibit siap dipindahkan setelah berumur 6-8 minggu atau memiliki tinggi sekitar 20-30 cm..."
                )
            )

            plantDao.insertPlantings(pomegranatePlantings)

            val bananaLandPreparations = LandPreparations(
                plantsId = 11,
                processing = "Lahan dibajak dan diratakan untuk mempersiapkan tanah yang gembur dan bersih dari sisa tanaman sebelumnya.",
                irrigation = "Pastikan lahan memiliki drainase yang baik dan kelembapan optimal sebelum penanaman."
            )

            plantDao.insertLandPreparations(bananaLandPreparations)

            val bananaNurseries = Nurseries(
                plantsId = 11,
                seedSelection = "Gunakan rimpang atau bibit pisang yang sehat dari varietas unggul.",
                soaking = "Rimpang direndam dalam air selama 8-12 jam sebelum penanaman untuk mempersiapkan tanaman dengan lebih baik.",
                seeding = ""
            )

            plantDao.insertNurseries(bananaNurseries)

            val bananaPlantings = listOf(
                Plantings(
                    plantsId = 11,
                    preparing = "Penyiapan Tanaman: Bibit siap dipindahkan setelah berumur 9-12 bulan atau memiliki tinggi sekitar 1-1,5 meter...",
                    planting = "Penanaman: Tanam bibit dengan jarak tanam sekitar 2-3 meter tergantung pada varietasnya, dan pastikan akar tertanam dengan baik."
                )
            )

            plantDao.insertPlantings(bananaPlantings)

            val bananaFertilization = listOf(
                Fertilization(
                    plantsId = 11,
                    type = "Dasar",
                    agePlant = 0,
                    urea = 0,
                    tsp = 0,
                    kcl = 0,
                    organicFertilizer = "Berikan pupuk kandang atau kompos dalam jumlah yang cukup untuk meningkatkan kesuburan tanah."
                ),
                Fertilization(
                    plantsId = 11,
                    type = "Dasar",
                    agePlant = 0,
                    urea = 0,
                    tsp = 0,
                    kcl = 0,
                    organicFertilizer = null
                ),
                Fertilization(
                    plantsId = 11,
                    type = "Susulan",
                    agePlant = 3,
                    urea = 0,
                    tsp = 0,
                    kcl = 0,
                    organicFertilizer = null
                ),
                Fertilization(
                    plantsId = 11,
                    type = "Susulan",
                    agePlant = 6,
                    urea = 0,
                    tsp = 0,
                    kcl = 0,
                    organicFertilizer = null
                )
            )

            plantDao.insertFertilization(bananaFertilization)

            val bananaVarieties = listOf(
                Varieties(
                    plantsId = 11,
                    name = "Varietas Pisang Cavendish",
                    superiority = "Buah besar, tahan terhadap penyakit, dan memiliki daya simpan yang baik.",
                    lack = "Rentan terhadap penyakit layu fusarium."
                ),
                Varieties(
                    plantsId = 11,
                    name = "Varietas Pisang Raja Bulu",
                    superiority = "Rasa buah yang manis, adaptasi baik di daerah tropis.",
                    lack = "Memerlukan perawatan ekstra terutama dalam pengendalian hama."
                )
            )

            plantDao.insertVarieties(bananaVarieties)

            val bananaPests = listOf(
                Pests(
                    plantsId = 11,
                    name = "Kutu Daun Pisang",
                    chemicalControl = "Gunakan insektisida yang direkomendasikan untuk mengendalikan populasi kutu daun.",
                    biologicalControl = null
                ),
                Pests(
                    plantsId = 11,
                    name = "Penggerek Batang",
                    chemicalControl = "Pantau secara teratur dan lakukan tindakan kontrol fisik atau kimiawi jika diperlukan.",
                    biologicalControl = null
                )
            )

            plantDao.insertPests(bananaPests)

            val bananaDiseases = listOf(
                Diseases(
                    plantsId = 11,
                    name = "Penyakit Layu Fusarium",
                    control = "Terapkan fungisida yang tepat untuk mengendalikan penyakit ini."
                ),
                Diseases(
                    plantsId = 11,
                    name = "Busuk Akar",
                    control = "Lakukan sanitasi lahan dan penggunaan varietas tahan."
                )
            )

            plantDao.insertDiseases(bananaDiseases)

            val bananaWeeds = Weeds(
                plantsId = 11,
                manualWeeding = "Lakukan secara berkala untuk mengontrol pertumbuhan gulma di sekitar tanaman pisang.",
                herbicide = "Gunakan herbisida selektif untuk mengurangi persaingan gulma tanpa merusak tanaman pisang."
            )

            plantDao.insertWeeds(bananaWeeds)

            val mangoLandPreparations = LandPreparations(
                plantsId = 12,
                processing = "Lahan dibajak dan digaru untuk menghancurkan sisa tanaman dan menggemburkan tanah.",
                irrigation = "Lahan disiapkan dengan irigasi yang cukup untuk memastikan kelembapan tanah optimal sebelum penanaman."
            )

            plantDao.insertLandPreparations(mangoLandPreparations)

            val mangoNurseries = Nurseries(
                plantsId = 12,
                seedSelection = "Gunakan biji mangga varietas unggul yang telah disertifikasi.",
                soaking = "Benih direndam dalam air selama 24-36 jam, kemudian diperam selama 48 jam untuk memfasilitasi perkecambahan.",
                seeding = ""
            )

            plantDao.insertNurseries(mangoNurseries)

            val mangoPlantings = listOf(
                Plantings(
                    plantsId = 12,
                    preparing = "Penyiapan Tanaman: Bibit mangga berumur sekitar 20-25 hari siap untuk dipindahkan ke lahan.",
                    planting = "Penanaman: Tanam bibit pada lahan yang sudah dipersiapkan dengan jarak tanam yang optimal, biasanya antara 6-8 meter tergantung pada varietasnya."
                )
            )

            plantDao.insertPlantings(mangoPlantings)

            val mangoFertilization = listOf(
                Fertilization(
                    plantsId = 12,
                    type = "Dasar",
                    agePlant = 0,
                    urea = 100,
                    tsp = 50,
                    kcl = 50,
                    organicFertilizer = "Gunakan pupuk kandang atau kompos dengan dosis 10-15 ton/ha sebelum pengolahan tanah terakhir."
                ),
                Fertilization(
                    plantsId = 12,
                    type = "Susulan",
                    agePlant = 20,
                    urea = 50,
                    tsp = 0,
                    kcl = 0,
                    organicFertilizer = null
                ),
                Fertilization(
                    plantsId = 12,
                    type = "Susulan",
                    agePlant = 35,
                    urea = 50,
                    tsp = 25,
                    kcl = 0,
                    organicFertilizer = null
                ),
                Fertilization(
                    plantsId = 12,
                    type = "Susulan",
                    agePlant = 50,
                    urea = 50,
                    tsp = 0,
                    kcl = 0,
                    organicFertilizer = null
                )
            )

            plantDao.insertFertilization(mangoFertilization)

            val mangoVarieties = listOf(
                Varieties(
                    plantsId = 12,
                    name = "Varietas Harum Manis",
                    superiority = "Buah besar, rasa manis, dan produktivitas tinggi.",
                    lack = "Rentan terhadap penyakit antraknosa di daerah yang lembap."
                ),
                Varieties(
                    plantsId = 12,
                    name = "Varietas Gedong Gincu",
                    superiority = "Tahan terhadap beberapa penyakit dan memiliki kualitas buah yang baik.",
                    lack = "Memerlukan perawatan ekstra untuk pengendalian hama dan penyakit."
                ),
                Varieties(
                    plantsId = 12,
                    name = "Varietas Arumanis",
                    superiority = "Buah berukuran sedang, memiliki aroma harum, dan adaptasi baik di berbagai kondisi lahan.",
                    lack = "Kualitas buah bisa berkurang dalam kondisi tanah yang kurang subur."
                )
            )

            plantDao.insertVarieties(mangoVarieties)

            val mangoPests = listOf(
                Pests(
                    plantsId = 12,
                    name = "Lalat Buah Mangga",
                    chemicalControl = "Gunakan insektisida sesuai dengan petunjuk untuk mengendalikan populasi lalat buah.",
                    biologicalControl = null
                ),
                Pests(
                    plantsId = 12,
                    name = "Ulat Grayak",
                    chemicalControl = "Pantau secara rutin dan lakukan pengendalian mekanis atau kimia bila diperlukan.",
                    biologicalControl = null
                )
            )

            plantDao.insertPests(mangoPests)

            val mangoDiseases = listOf(
                Diseases(
                    plantsId = 12,
                    name = "Antraknosa",
                    control = "Gunakan fungisida seperti mankozeb atau tembaga oksiklorida untuk mengendalikan penyakit ini."
                ),
                Diseases(
                    plantsId = 12,
                    name = "Busuk Buah",
                    control = "Jaga sanitasi kebersihan lahan dan gunakan varietas yang tahan terhadap penyakit."
                )
            )

            plantDao.insertDiseases(mangoDiseases)

            val mangoWeeds = Weeds(
                plantsId = 12,
                manualWeeding = "Lakukan secara berkala untuk mengurangi persaingan gulma di sekitar tanaman mangga.",
                herbicide = "Gunakan herbisida sesuai kebutuhan untuk mengendalikan gulma tanpa merusak tanaman mangga."
            )

            plantDao.insertWeeds(mangoWeeds)

            val grapeLandPreparations = LandPreparations(
                plantsId = 13,
                processing = "Lahan disiapkan dengan mencangkul dan menggemburkan tanah untuk memastikan drainase yang baik.",
                irrigation = "Irigasi diperlukan untuk menjaga kelembapan tanah yang optimal sebelum penanaman."
            )

            plantDao.insertLandPreparations(grapeLandPreparations)

            val grapeNurseries = Nurseries(
                plantsId = 13,
                seedSelection = "Gunakan bibit anggur dari varietas unggul yang telah disertifikasi.",
                soaking = "Benih direndam dalam air selama 24 jam untuk memfasilitasi perkecambahan sebelum disemai.",
                seeding = ""
            )

            plantDao.insertNurseries(grapeNurseries)

            val grapePlantings = listOf(
                Plantings(
                    plantsId = 13,
                    preparing = "Penyiapan Tanaman: Bibit anggur siap dipindahkan setelah mencapai umur 1 tahun atau memiliki ketinggian sekitar 30-50 cm.",
                    planting = "Penanaman: Tanam bibit pada jarak yang disarankan antara 1-2 meter tergantung pada sistem pergola atau penyangga yang digunakan."
                )
            )

            plantDao.insertPlantings(grapePlantings)

            val grapeFertilization = listOf(
                Fertilization(
                    plantsId = 13,
                    type = "Dasar",
                    agePlant = 0,
                    urea = 0,
                    tsp = 0,
                    kcl = 0,
                    organicFertilizer = "Gunakan pupuk kandang atau kompos untuk meningkatkan kesuburan tanah sebelum penanaman."
                ),
                Fertilization(
                    plantsId = 13,
                    type = "Dasar",
                    agePlant = 0,
                    urea = 0,
                    tsp = 0,
                    kcl = 0,
                    organicFertilizer = null
                ),
                Fertilization(
                    plantsId = 13,
                    type = "Susulan",
                    agePlant = 20,
                    urea = 0,
                    tsp = 0,
                    kcl = 25,
                    organicFertilizer = null
                ),
                Fertilization(
                    plantsId = 13,
                    type = "Susulan",
                    agePlant = 0,
                    urea = 0,
                    tsp = 0,
                    kcl = 0,
                    organicFertilizer = null
                )
            )

            plantDao.insertFertilization(grapeFertilization)

            val grapeVarieties = listOf(
                Varieties(
                    plantsId = 13,
                    name = "Merlot",
                    superiority = "Buah dengan kualitas anggur merah yang baik, tahan terhadap penyakit, dan adaptasi baik di berbagai iklim.",
                    lack = "Memerlukan perhatian ekstra dalam pengendalian gulma dan penyakit."
                ),
                Varieties(
                    plantsId = 13,
                    name = "Chardonnay",
                    superiority = "Buah berwarna putih dengan rasa yang lembut dan aroma harum, cocok untuk dijadikan anggur putih.",
                    lack = "Rentan terhadap cuaca yang ekstrem dan memerlukan manajemen air yang baik."
                ),
                Varieties(
                    plantsId = 13,
                    name = "Muscat",
                    superiority = "Buah dengan rasa manis dan aroma harum khas, cocok untuk dijadikan anggur meja.",
                    lack = "Rentan terhadap serangan jamur dan hama pada kondisi kelembaban tinggi."
                )
            )

            plantDao.insertVarieties(grapeVarieties)

            val grapePests = listOf(
                Pests(
                    plantsId = 13,
                    name = "Kutu Daun Anggur",
                    chemicalControl = "Gunakan insektisida yang direkomendasikan untuk mengendalikan populasi kutu daun.",
                    biologicalControl = null
                ),
                Pests(
                    plantsId = 13,
                    name = "Ulat Grayak",
                    chemicalControl = "Pantau secara teratur dan terapkan pengendalian mekanis atau kimia jika diperlukan.",
                    biologicalControl = null
                )
            )

            plantDao.insertPests(grapePests)

            val grapeDiseases = listOf(
                Diseases(
                    plantsId = 13,
                    name = "Penyakit Bulai Anggur",
                    control = "Gunakan fungisida seperti sulfur atau tembaga oksiklorida untuk mengendalikan penyakit ini."
                ),
                Diseases(
                    plantsId = 13,
                    name = "Penyakit Busuk Buah Anggur",
                    control = "Jaga sanitasi lahan dan pilih varietas yang tahan terhadap penyakit."
                )
            )

            plantDao.insertDiseases(grapeDiseases)

            val grapeWeeds = Weeds(
                plantsId = 13,
                manualWeeding = "Lakukan secara teratur untuk mengontrol pertumbuhan gulma di sekitar tanaman anggur.",
                herbicide = "Gunakan herbisida selektif jika diperlukan untuk mengendalikan gulma tanpa merusak tanaman anggur."
            )

            plantDao.insertWeeds(grapeWeeds)

            val watermelonLandPreparations = LandPreparations(
                plantsId = 14,
                processing = "Lahan dibajak dan digaru untuk menghancurkan sisa tanaman sebelumnya dan menggemburkan tanah.",
                irrigation = "Lahan diisi air setinggi 5-10 cm dan dibiarkan selama beberapa hari untuk mempersiapkan kondisi tanah yang optimal."
            )

            val watermelonNurseries = Nurseries(
                plantsId = 14,
                seedSelection = "Gunakan benih varietas unggul semangka yang telah disertifikasi.",
                soaking = "Benih direndam dalam air selama 24 jam untuk memfasilitasi perkecambahan sebelum disemai.",
                seeding = ""
            )

            val watermelonPlantings = listOf(
                Plantings(
                    plantsId = 14,
                    preparing = "Penyiapan Tanaman: Bibit semangka siap dipindahkan setelah berumur sekitar 3-4 minggu atau memiliki daun berjumlah cukup.",
                    planting = "Penanaman: Tanam bibit pada jarak tanam yang disarankan, biasanya sekitar 1-2 meter tergantung pada varietas dan sistem penanaman."
                )
            )

            val watermelonFertilization = listOf(
                Fertilization(
                    plantsId = 14,
                    type = "Dasar",
                    agePlant = 0,
                    urea = 0,
                    tsp = 0,
                    kcl = 0,
                    organicFertilizer = "Berikan pupuk kandang atau kompos sebelum penanaman untuk meningkatkan kesuburan tanah."
                ),
                Fertilization(
                    plantsId = 14,
                    type = "Susulan",
                    agePlant = 21,
                    urea = 0,
                    tsp = 0,
                    kcl = 0,
                    organicFertilizer = null
                ),
                Fertilization(
                    plantsId = 14,
                    type = "Susulan",
                    agePlant = 42,
                    urea = 0,
                    tsp = 0,
                    kcl = 0,
                    organicFertilizer = null
                )
            )

            val watermelonVarieties = listOf(
                Varieties(
                    plantsId = 14,
                    name = "Madu",
                    superiority = "Buah dengan rasa manis dan kadar air yang tinggi, cocok untuk konsumsi segar.",
                    lack = "Rentan terhadap penyakit layu bakteri pada kondisi tanah yang lembab."
                ),
                Varieties(
                    plantsId = 14,
                    name = "Crimson Sweet",
                    superiority = "Buah besar dengan daging merah, tahan terhadap beberapa penyakit, dan memiliki umur simpan yang baik.",
                    lack = "Memerlukan pemupukan dan penyiraman yang konsisten untuk hasil terbaik."
                ),
                Varieties(
                    plantsId = 14,
                    name = "Sugar Baby",
                    superiority = "Buah kecil dengan daging merah gelap dan rasa yang manis, cocok untuk ukuran semangka mini.",
                    lack = "Rentan terhadap serangan hama dan penyakit jika tidak dikelola dengan baik."
                )
            )

            val watermelonPests = listOf(
                Pests(
                    plantsId = 14,
                    name = "Kutu Daun Semangka",
                    chemicalControl = "Gunakan insektisida yang direkomendasikan untuk mengendalikan populasi kutu daun.",
                    biologicalControl = null
                ),
                Pests(
                    plantsId = 14,
                    name = "Penggerek Batang",
                    chemicalControl = "Pantau secara teratur dan terapkan pengendalian mekanis atau kimia jika diperlukan.",
                    biologicalControl = null
                )
            )

            val watermelonDiseases = listOf(
                Diseases(
                    plantsId = 14,
                    name = "Layu Fusarium",
                    control = "Gunakan varietas tahan dan praktik sanitasi yang baik untuk mengendalikan penyakit ini."
                ),
                Diseases(
                    plantsId = 14,
                    name = "Antraknosa",
                    control = "Aplikasikan fungisida sesuai kebutuhan untuk mengurangi dampak penyakit pada tanaman."
                )
            )

            val watermelonWeeds = Weeds(
                plantsId = 14,
                manualWeeding = "Lakukan secara teratur untuk mengontrol pertumbuhan gulma di sekitar tanaman semangka.",
                herbicide = "Gunakan herbisida selektif jika diperlukan, dengan memperhatikan dosis dan aplikasi yang tepat untuk menghindari kerusakan pada tanaman semangka."
            )

            plantDao.insertLandPreparations(watermelonLandPreparations)
            plantDao.insertNurseries(watermelonNurseries)
            plantDao.insertPlantings(watermelonPlantings)
            plantDao.insertFertilization(watermelonFertilization)
            plantDao.insertVarieties(watermelonVarieties)
            plantDao.insertPests(watermelonPests)
            plantDao.insertDiseases(watermelonDiseases)
            plantDao.insertWeeds(watermelonWeeds)

            // Data untuk Persiapan Lahan Melon
            val melonLandPreparations = LandPreparations(
                plantsId = 15,
                processing = "Lahan dibajak dan digaru untuk mempersiapkan tanah dengan menghancurkan sisa tanaman sebelumnya dan menggemburkan tanah.",
                irrigation = "Pastikan lahan memiliki drainase yang baik dan kelembapan optimal sebelum penanaman."
            )

            val melonNurseries = Nurseries(
                plantsId = 15,
                seedSelection = "Gunakan benih melon varietas unggul yang telah disertifikasi.",
                soaking = "Benih direndam dalam air selama 24-36 jam untuk memfasilitasi perkecambahan sebelum disemai.",
                seeding = ""
            )

            val melonPlantings = listOf(
                Plantings(
                    plantsId = 15,
                    preparing = "Penyiapan Tanaman: Bibit melon siap dipindahkan setelah berumur sekitar 3-4 minggu atau memiliki daun yang cukup.",
                    planting = "Penanaman: Tanam bibit dengan jarak tanam yang disarankan, biasanya sekitar 1 meter tergantung pada varietas dan sistem penanaman."
                )
            )

            val melonFertilization = listOf(
                Fertilization(
                    plantsId = 15,
                    type = "Dasar",
                    agePlant = 0,
                    urea = 0,
                    tsp = 0,
                    kcl = 0,
                    organicFertilizer = "Berikan pupuk kandang atau kompos sebelum penanaman untuk meningkatkan kesuburan tanah."
                ),
                Fertilization(
                    plantsId = 15,
                    type = "Susulan",
                    agePlant = 21,
                    urea = 0,
                    tsp = 0,
                    kcl = 0,
                    organicFertilizer = null
                ),
                Fertilization(
                    plantsId = 15,
                    type = "Susulan",
                    agePlant = 42,
                    urea = 0,
                    tsp = 0,
                    kcl = 0,
                    organicFertilizer = null
                )
            )

            val melonVarieties = listOf(
                Varieties(
                    plantsId = 15,
                    name = "Honeydew",
                    superiority = "Buah besar dengan daging putih kehijauan, manis, dan memiliki kandungan air tinggi.",
                    lack = "Membutuhkan kondisi tanah yang subur dan perlindungan ekstra terhadap hama seperti thrips."
                ),
                Varieties(
                    plantsId = 15,
                    name = "Cantaloupe",
                    superiority = "Buah berdaging oranye dengan rasa manis yang khas, tahan terhadap penyakit tertentu.",
                    lack = "Rentan terhadap kondisi lingkungan yang terlalu lembab yang dapat menyebabkan masalah penyakit."
                ),
                Varieties(
                    plantsId = 15,
                    name = "Galia",
                    superiority = "Buah berkulit hijau kekuningan dengan daging jingga dan rasa manis yang segar.",
                    lack = "Memerlukan perawatan yang teliti terutama dalam pengendalian hama dan penyakit."
                )
            )

            val melonPests = listOf(
                Pests(
                    plantsId = 15,
                    name = "Thrips",
                    chemicalControl = "Gunakan insektisida yang direkomendasikan untuk mengendalikan populasi thrips pada tanaman melon.",
                    biologicalControl = null
                ),
                Pests(
                    plantsId = 15,
                    name = "Lalat Buah",
                    chemicalControl = "Pantau secara teratur dan terapkan pengendalian mekanis atau kimia jika diperlukan.",
                    biologicalControl = null
                )
            )

            val melonDiseases = listOf(
                Diseases(
                    plantsId = 15,
                    name = "Penyakit Layu Fusarium",
                    control = "Gunakan varietas yang tahan atau toleran terhadap penyakit ini dan praktik sanitasi yang baik."
                ),
                Diseases(
                    plantsId = 15,
                    name = "Penyakit Bulai Daun",
                    control = "Aplikasikan fungisida secara preventif dan pertahankan sanitasi lahan untuk mengurangi risiko infeksi."
                )
            )

            val melonWeeds = Weeds(
                plantsId = 15,
                manualWeeding = "Lakukan secara teratur untuk mengontrol pertumbuhan gulma di sekitar tanaman melon.",
                herbicide = "Gunakan herbisida selektif jika diperlukan, dengan memperhatikan dosis dan aplikasi yang tepat untuk menghindari kerusakan pada tanaman melon."
            )

            plantDao.insertLandPreparations(melonLandPreparations)
            plantDao.insertNurseries(melonNurseries)
            plantDao.insertPlantings(melonPlantings)
            plantDao.insertFertilization(melonFertilization)
            plantDao.insertVarieties(melonVarieties)
            plantDao.insertPests(melonPests)
            plantDao.insertDiseases(melonDiseases)
            plantDao.insertWeeds(melonWeeds)

            val appleLandPreparations = LandPreparations(
                plantsId = 16,
                processing = "Lahan dibajak dan digaru untuk mempersiapkan tanah dengan menghancurkan sisa tanaman sebelumnya dan menggemburkan tanah.",
                irrigation = "Pastikan lahan memiliki drainase yang baik dan kelembapan optimal sebelum penanaman."
            )

            val appleNurseries = Nurseries(
                plantsId = 16,
                seedSelection = "Gunakan bibit apel yang berasal dari varietas unggul dan berkualitas.",
                soaking = "Rimpang atau bibit direndam dalam air sebelum penanaman untuk memfasilitasi perkecambahan yang baik.",
                seeding = ""
            )

            val applePlantings = listOf(
                Plantings(
                    plantsId = 16,
                    preparing = "Penyiapan Tanaman: Bibit apel siap dipindahkan setelah berumur sekitar 1-2 tahun atau sesuai dengan ukuran yang disarankan.",
                    planting = "Penanaman: Tanam bibit apel dengan jarak yang cukup, biasanya sekitar 4-6 meter tergantung pada varietas dan sistem penanaman yang dipilih."
                )
            )

            val appleFertilization = listOf(
                Fertilization(
                    plantsId = 16,
                    type = "Dasar",
                    agePlant = 0,
                    urea = 0,
                    tsp = 0,
                    kcl = 0,
                    organicFertilizer = "Berikan pupuk kandang atau kompos sebelum penanaman untuk memperbaiki kesuburan tanah."
                ),
                Fertilization(
                    plantsId = 16,
                    type = "Susulan",
                    agePlant = 90,
                    urea = 0,
                    tsp = 0,
                    kcl = 0,
                    organicFertilizer = null
                ),
                Fertilization(
                    plantsId = 16,
                    type = "Susulan",
                    agePlant = 150,
                    urea = 0,
                    tsp = 0,
                    kcl = 0,
                    organicFertilizer = null
                )
            )

            val appleVarieties = listOf(
                Varieties(
                    plantsId = 16,
                    name = "Fuji",
                    superiority = "Buah besar dengan rasa manis dan renyah, cocok untuk berbagai kondisi iklim.",
                    lack = "Memerlukan perlindungan ekstra terhadap penyakit seperti busuk buah."
                ),
                Varieties(
                    plantsId = 16,
                    name = "Gala",
                    superiority = "Buah berwarna merah dan kuning dengan rasa manis dan asam yang seimbang.",
                    lack = "Rentan terhadap penyakit seperti noda daun dan busuk akar."
                ),
                Varieties(
                    plantsId = 16,
                    name = "Granny Smith",
                    superiority = "Buah hijau dengan rasa asam yang segar, tahan terhadap penyakit dan hama.",
                    lack = "Memerlukan cuaca yang lebih dingin untuk menghasilkan buah dengan warna yang terbaik."
                )
            )

            val applePests = listOf(
                Pests(
                    plantsId = 16,
                    name = "Ulat Buah Apel",
                    chemicalControl = "Gunakan insektisida yang direkomendasikan untuk mengendalikan populasi ulat buah pada tanaman apel.",
                    biologicalControl = null
                ),
                Pests(
                    plantsId = 16,
                    name = "Lalat Apel",
                    chemicalControl = "Pantau secara teratur dan terapkan pengendalian fisik atau kimia jika diperlukan.",
                    biologicalControl = null
                )
            )

            val appleDiseases = listOf(
                Diseases(
                    plantsId = 16,
                    name = "Penyakit Embun Tepung",
                    control = "Terapkan fungisida yang efektif dan pilih varietas yang tahan terhadap penyakit ini."
                ),
                Diseases(
                    plantsId = 16,
                    name = "Busuk Buah Apel",
                    control = "Jaga kebersihan lingkungan dan gunakan fungisida secara preventif untuk mengurangi risiko infeksi."
                )
            )

            val appleWeeds = Weeds(
                plantsId = 16,
                manualWeeding = "Lakukan secara rutin untuk mengontrol pertumbuhan gulma di sekitar tanaman apel.",
                herbicide = "Gunakan herbisida selektif dengan hati-hati untuk mengurangi persaingan gulma tanpa merusak tanaman apel."
            )

            plantDao.insertLandPreparations(appleLandPreparations)
            plantDao.insertNurseries(appleNurseries)
            plantDao.insertPlantings(applePlantings)
            plantDao.insertFertilization(appleFertilization)
            plantDao.insertVarieties(appleVarieties)
            plantDao.insertPests(applePests)
            plantDao.insertDiseases(appleDiseases)
            plantDao.insertWeeds(appleWeeds)

            val orangeLandPreparations = LandPreparations(
                plantsId = 17,
                processing = "Lahan dibajak dan digaru untuk menghancurkan sisa tanaman dan mempersiapkan tanah yang gembur.",
                irrigation = "Pastikan sistem drainase yang baik dan kelembapan optimal sebelum penanaman."
            )

            val orangeNurseries = Nurseries(
                plantsId = 17,
                seedSelection = "Gunakan bibit jeruk yang berasal dari varietas unggul dan berkualitas.",
                soaking = "Benih direndam dalam air selama beberapa jam sebelum penanaman untuk memfasilitasi perkecambahan yang baik.",
                seeding = ""
            )

            val orangePlantings = listOf(
                Plantings(
                    plantsId = 17,
                    preparing = "Penyiapan Tanaman: Bibit jeruk siap dipindahkan setelah berumur sekitar 1-2 tahun atau memiliki tinggi sekitar 50-80 cm.",
                    planting = "Penanaman: Tanam bibit jeruk dengan jarak yang cukup, biasanya sekitar 5-8 meter tergantung pada varietas dan sistem penanaman yang dipilih."
                )
            )

            val orangeFertilization = listOf(
                Fertilization(
                    plantsId = 17,
                    type = "Dasar",
                    agePlant = 0,
                    urea = 0,
                    tsp = 0,
                    kcl = 0,
                    organicFertilizer = "Berikan pupuk kandang atau kompos sebelum penanaman untuk meningkatkan kesuburan tanah."
                ),
                Fertilization(
                    plantsId = 17,
                    type = "Susulan",
                    agePlant = 90,
                    urea = 0,
                    tsp = 0,
                    kcl = 0,
                    organicFertilizer = null
                ),
                Fertilization(
                    plantsId = 17,
                    type = "Susulan",
                    agePlant = 150,
                    urea = 0,
                    tsp = 0,
                    kcl = 0,
                    organicFertilizer = null
                )
            )

            val orangeVarieties = listOf(
                Varieties(
                    plantsId = 17,
                    name = "Jeruk Siam",
                    superiority = "Buah besar dengan kulit tebal, rasanya manis dan asam seimbang.",
                    lack = "Rentan terhadap serangan hama kutu daun dan penyakit seperti antraknosa."
                ),
                Varieties(
                    plantsId = 17,
                    name = "Jeruk Pontianak",
                    superiority = "Buah berukuran sedang dengan kulit tipis, rasa manis dengan aroma harum.",
                    lack = "Memerlukan perawatan ekstra terhadap penyakit layu bakteri dan penyakit busuk akar."
                ),
                Varieties(
                    plantsId = 17,
                    name = "Jeruk Keprok",
                    superiority = "Buah berwarna kuning cerah, rasanya manis dengan sedikit rasa asam.",
                    lack = "Rentan terhadap hama penghisap tanaman dan memerlukan pola irigasi yang baik."
                )
            )

            val orangePests = listOf(
                Pests(
                    plantsId = 17,
                    name = "Kutu Daun Jeruk",
                    chemicalControl = "Gunakan insektisida yang direkomendasikan untuk mengendalikan populasi kutu daun pada tanaman jeruk.",
                    biologicalControl = null
                ),
                Pests(
                    plantsId = 17,
                    name = "Lalat Buah Jeruk",
                    chemicalControl = "Pantau secara teratur dan terapkan tindakan pengendalian fisik atau kimia jika diperlukan.",
                    biologicalControl = null
                )
            )

            val orangeDiseases = listOf(
                Diseases(
                    plantsId = 17,
                    name = "Antraknosa",
                    control = "Terapkan fungisida yang tepat untuk mengontrol infeksi antraknosa pada jeruk."
                ),
                Diseases(
                    plantsId = 17,
                    name = "Penyakit Layu Bakteri",
                    control = "Gunakan varietas yang tahan jika tersedia dan praktikkan sanitasi yang baik."
                )
            )

            val orangeWeeds = Weeds(
                plantsId = 17,
                manualWeeding = "Lakukan secara berkala untuk mengendalikan gulma di sekitar tanaman jeruk.",
                herbicide = "Gunakan herbisida yang tepat jika diperlukan untuk mengurangi persaingan gulma tanpa merusak tanaman jeruk."
            )

            plantDao.insertLandPreparations(orangeLandPreparations)
            plantDao.insertNurseries(orangeNurseries)
            plantDao.insertPlantings(orangePlantings)
            plantDao.insertFertilization(orangeFertilization)
            plantDao.insertVarieties(orangeVarieties)
            plantDao.insertPests(orangePests)
            plantDao.insertDiseases(orangeDiseases)
            plantDao.insertWeeds(orangeWeeds)

            val papayaLandPreparations = LandPreparations(
                plantsId = 18,
                processing = "Lahan dibajak dan digaru untuk menghancurkan sisa tanaman sebelumnya dan memastikan tanah gembur.",
                irrigation = "Pastikan sistem drainase yang baik dan tanah memiliki kelembapan yang cukup sebelum penanaman."
            )

            val papayaNurseries = Nurseries(
                plantsId = 18,
                seedSelection = "Gunakan biji pepaya yang berasal dari varietas unggul dan berkualitas baik.",
                soaking = "Benih direndam dalam air selama 24-36 jam untuk mempercepat perkecambahan sebelum ditanam.",
                seeding = ""
            )

            val papayaPlantings = listOf(
                Plantings(
                    plantsId = 18,
                    preparing = "Penyiapan Tanaman: Bibit pepaya siap dipindahkan setelah berumur sekitar 6-9 bulan atau memiliki tinggi sekitar 60-80 cm.",
                    planting = "Penanaman: Tanam bibit pepaya dengan jarak yang cukup antar tanaman, sekitar 2-3 meter tergantung pada varietas dan kondisi lahan."
                )
            )

            val papayaFertilization = listOf(
                Fertilization(
                    plantsId = 18,
                    type = "Dasar",
                    agePlant = 0,
                    urea = 0,
                    tsp = 0,
                    kcl = 0,
                    organicFertilizer = "Berikan pupuk kandang atau kompos sebelum penanaman untuk memperbaiki kesuburan tanah."
                ),
                Fertilization(
                    plantsId = 18,
                    type = "Susulan",
                    agePlant = 120,
                    urea = 0,
                    tsp = 0,
                    kcl = 0,
                    organicFertilizer = null
                ),
                Fertilization(
                    plantsId = 18,
                    type = "Susulan",
                    agePlant = 180,
                    urea = 0,
                    tsp = 0,
                    kcl = 0,
                    organicFertilizer = null
                )
            )

            val papayaVarieties = listOf(
                Varieties(
                    plantsId = 18,
                    name = "Pepaya California",
                    superiority = "Buah besar dengan daging buah yang tebal, rasanya manis dan sedikit asam.",
                    lack = "Rentan terhadap serangan hama kutu putih dan penyakit seperti busuk buah."
                ),
                Varieties(
                    plantsId = 18,
                    name = "Pepaya Red Lady",
                    superiority = "Buah berukuran sedang dengan kulit tipis, daging buah oranye, rasanya manis dan aromatik.",
                    lack = "Memerlukan perawatan ekstra terhadap hama penghisap daun dan penyakit seperti antraknosa."
                ),
                Varieties(
                    plantsId = 18,
                    name = "Pepaya Thailand",
                    superiority = "Buah berukuran besar dengan kulit hijau kekuningan, daging buah jingga, rasanya manis dengan aroma harum.",
                    lack = "Rentan terhadap hama penggerek batang dan memerlukan pemeliharaan pola irigasi yang baik."
                )
            )

            val papayaPests = listOf(
                Pests(
                    plantsId = 18,
                    name = "Kutu Putih Pepaya",
                    chemicalControl = "Gunakan insektisida yang direkomendasikan untuk mengendalikan populasi kutu putih pada tanaman pepaya.",
                    biologicalControl = null
                ),
                Pests(
                    plantsId = 18,
                    name = "Penggerek Batang",
                    chemicalControl = "Pantau secara teratur dan terapkan tindakan pengendalian fisik atau kimia jika diperlukan.",
                    biologicalControl = null
                )
            )

            val papayaDiseases = listOf(
                Diseases(
                    plantsId = 18,
                    name = "Busuk Buah",
                    control = "Terapkan fungisida yang tepat untuk mengontrol infeksi busuk buah pada pepaya."
                ),
                Diseases(
                    plantsId = 18,
                    name = "Antraknosa",
                    control = "Gunakan varietas yang tahan jika tersedia dan praktikkan sanitasi yang baik."
                )
            )

            val papayaWeeds = Weeds(
                plantsId = 18,
                manualWeeding = "Lakukan secara berkala untuk mengendalikan gulma di sekitar tanaman pepaya.",
                herbicide = "Gunakan herbisida selektif jika diperlukan untuk mengurangi persaingan gulma tanpa merusak tanaman pepaya."
            )

            plantDao.insertLandPreparations(papayaLandPreparations)
            plantDao.insertNurseries(papayaNurseries)
            plantDao.insertPlantings(papayaPlantings)
            plantDao.insertFertilization(papayaFertilization)
            plantDao.insertVarieties(papayaVarieties)
            plantDao.insertPests(papayaPests)
            plantDao.insertDiseases(papayaDiseases)
            plantDao.insertWeeds(papayaWeeds)

            // Data untuk Persiapan Lahan Kelapa
            val coconutLandPreparations = LandPreparations(
                plantsId = 19,
                processing = "Lahan disiangi dan dikeruk untuk membersihkan sisa-sisa tanaman sebelumnya dan mempersiapkan tanah yang gembur.",
                irrigation = "Pastikan lahan memiliki drainase yang baik untuk menghindari genangan air yang berlebihan."
            )

            val coconutNurseries = Nurseries(
                plantsId = 19,
                seedSelection = "Gunakan bibit kelapa yang berasal dari buah yang sehat dan berkualitas baik.",
                soaking = "Buah kelapa direndam dalam air selama 2-3 hari untuk mempercepat perkecambahan.",
                seeding = "Tunggu 5-7 hari setelah rendam buah kelapa untuk menyiapkan bibit kelapa yang berasal dari buah yang sehat dan berkualitas baik."
            )

            val coconutPlantings = listOf(
                Plantings(
                    plantsId = 19,
                    preparing = "Penyiapan Tanaman: Bibit kelapa siap dipindahkan ke lahan setelah berusia sekitar 9-12 bulan.",
                    planting = "Penanaman: Tanam bibit kelapa dengan jarak tanam 9 meter x 9 meter atau sesuai anjuran teknis, pastikan akar tertanam dengan baik."
                )
            )

            val coconutFertilization = listOf(
                Fertilization(
                    plantsId = 19,
                    type = "Dasar",
                    agePlant = 0,
                    urea = 0,
                    tsp = 0,
                    kcl = 0,
                    organicFertilizer = "Berikan pupuk kandang atau kompos sebanyak 30-40 kg per lubang saat penanaman."
                ),
                Fertilization(
                    plantsId = 19,
                    type = "Dasar",
                    agePlant = 0,
                    urea = 0,
                    tsp = 0,
                    kcl = 0,
                    organicFertilizer = null
                ),
                Fertilization(
                    plantsId = 19,
                    type = "Susulan",
                    agePlant = 120,
                    urea = 0,
                    tsp = 0,
                    kcl = 0,
                    organicFertilizer = null
                ),
                Fertilization(
                    plantsId = 19,
                    type = "Susulan",
                    agePlant = 150,
                    urea = 0,
                    tsp = 0,
                    kcl = 0,
                    organicFertilizer = null
                )
            )

            val coconutVarieties = listOf(
                Varieties(
                    plantsId = 19,
                    name = "Varietas Tegal",
                    superiority = "Hasil buah kelapa cukup tinggi, tahan terhadap hama penyakit tertentu.",
                    lack = "Memerlukan perawatan ekstra terutama dalam pemupukan dan pengairan."
                ),
                Varieties(
                    plantsId = 19,
                    name = "Varietas Malayan Dwarf",
                    superiority = "Buah kelapa berukuran kecil, cepat berbuah, cocok untuk lahan terbatas.",
                    lack = "Memerlukan perlindungan ekstra terhadap hama dan penyakit."
                ),
                Varieties(
                    plantsId = 19,
                    name = "Varietas Sri Lanka Green Dwarf",
                    superiority = "Tahan terhadap hama dan penyakit, adaptsi baik di berbagai kondisi lahan.",
                    lack = "Pertumbuhan awal yang lambat, membutuhkan perawatan lebih intensif."
                )
            )

            val coconutPests = listOf(
                Pests(
                    plantsId = 19,
                    name = "Kumbang Kelapa",
                    chemicalControl = "Gunakan insektisida yang direkomendasikan untuk mengontrol populasi kumbang kelapa.",
                    biologicalControl = null
                ),
                Pests(
                    plantsId = 19,
                    name = "Penggerek Batang",
                    chemicalControl = "Pantau secara rutin dan lakukan pengendalian dengan cara fisik atau menggunakan pestisida yang sesuai.",
                    biologicalControl = null
                )
            )

            val coconutDiseases = listOf(
                Diseases(
                    plantsId = 19,
                    name = "Penyakit Busuk Pangkal Batang",
                    control = "Terapkan fungisida yang tepat untuk mengendalikan penyakit ini."
                ),
                Diseases(
                    plantsId = 19,
                    name = "Penyakit Tandan Rontok",
                    control = "Pastikan sanitasi lahan dan pilih varietas yang tahan terhadap penyakit ini."
                )
            )

            val coconutWeeds = Weeds(
                plantsId = 19,
                manualWeeding = "Lakukan secara berkala untuk mengontrol pertumbuhan gulma di sekitar tanaman kelapa.",
                herbicide = "Gunakan herbisida selektif yang aman untuk kelapa sesuai kebutuhan untuk mengurangi persaingan gulma."
            )

            plantDao.insertLandPreparations(coconutLandPreparations)
            plantDao.insertNurseries(coconutNurseries)
            plantDao.insertPlantings(coconutPlantings)
            plantDao.insertFertilization(coconutFertilization)
            plantDao.insertVarieties(coconutVarieties)
            plantDao.insertPests(coconutPests)
            plantDao.insertDiseases(coconutDiseases)
            plantDao.insertWeeds(coconutWeeds)

            val cottonLandPreparations = LandPreparations(
                plantsId = 20,
                processing = "Lahan dibajak dan digaru untuk menghancurkan sisa tanaman sebelumnya dan menggemburkan tanah.",
                irrigation = "Pastikan lahan memiliki drainase yang baik untuk menghindari genangan air."
            )

            val cottonNurseries = Nurseries(
                plantsId = 20,
                seedSelection = "Gunakan benih kapas varietas unggul yang telah bersertifikat.",
                soaking = "Rendam benih kapas dalam air selama 12-16 jam untuk mempercepat perkecambahan sebelum penanaman.",
                seeding = "Tanam benih kapas pada lahan yang sudah disiapkan."
            )

            val cottonPlantings = listOf(
                Plantings(
                    plantsId = 20,
                    preparing = "Benih kapas biasanya siap untuk dipindahkan ke lahan utama setelah berumur 20-25 hari dan memiliki 3-4 daun yang sehat.",
                    planting = "Tanam benih kapas pada lahan yang sudah disiapkan dengan jarak tanam yang sesuai dengan jenis varietasnya, dan pastikan penanaman dilakukan dengan kedalaman yang tepat."
                )
            )

            val cottonFertilization = listOf(
                Fertilization(
                    plantsId = 20,
                    type = "Dasar",
                    agePlant = 0,
                    urea = 0,
                    tsp = 0,
                    kcl = 0,
                    organicFertilizer = "Sebaiknya aplikasikan pupuk kandang atau kompos untuk meningkatkan kesuburan tanah sebelum tanam."
                ),
                Fertilization(
                    plantsId = 20,
                    type = "Dasar",
                    agePlant = 0,
                    urea = 0,
                    tsp = 0,
                    kcl = 0,
                    organicFertilizer = null
                ),
                Fertilization(
                    plantsId = 20,
                    type = "Susulan",
                    agePlant = 20,
                    urea = 0,
                    tsp = 0,
                    kcl = 0,
                    organicFertilizer = null
                ),
                Fertilization(
                    plantsId = 20,
                    type = "Susulan",
                    agePlant = 30,
                    urea = 0,
                    tsp = 0,
                    kcl = 0,
                    organicFertilizer = null
                )
            )

            val cottonVarieties = listOf(
                Varieties(
                    plantsId = 20,
                    name = "Varietas Bt Cotton",
                    superiority = "Tahan terhadap serangan hama ulat kapas, hasil panen tinggi, dan penggunaan pestisida yang lebih sedikit.",
                    lack = "Memerlukan manajemen yang baik terhadap resistensi hama."
                ),
                Varieties(
                    plantsId = 20,
                    name = "Varietas Lembaga",
                    superiority = "Tahan terhadap cuaca ekstrim, tingkat hasil yang tinggi.",
                    lack = "Rentan terhadap serangan hama."
                )
            )

            val cottonPests = listOf(
                Pests(
                    plantsId = 20,
                    name = "Ulat Kapas",
                    chemicalControl = "Gunakan pestisida sesuai anjuran yang tertera pada kemasan produk.",
                    biologicalControl = null
                ),
                Pests(
                    plantsId = 20,
                    name = "Penggerek Batang",
                    chemicalControl = "Terapkan pengendalian mekanis seperti pemangkasan bagian tanaman yang terinfeksi.",
                    biologicalControl = null
                )
            )

            val cottonDiseases = listOf(
                Diseases(
                    plantsId = 20,
                    name = "Busuk Pangkal Batang",
                    control = "Terapkan fungisida seperti tembaga oksiklorida untuk mengendalikan penyakit ini."
                ),
                Diseases(
                    plantsId = 20,
                    name = "Penyakit Layu Fusarium",
                    control = "Gunakan varietas kapas yang tahan terhadap penyakit ini."
                )
            )

            val cottonWeeds = Weeds(
                plantsId = 20,
                manualWeeding = "Lakukan secara berkala untuk mengurangi kompetisi nutrisi dan air bagi tanaman kapas.",
                herbicide = "Gunakan herbisida selektif yang direkomendasikan untuk mengendalikan gulma tanpa merusak tanaman kapas."
            )

            plantDao.insertLandPreparations(cottonLandPreparations)
            plantDao.insertNurseries(cottonNurseries)
            plantDao.insertPlantings(cottonPlantings)
            plantDao.insertFertilization(cottonFertilization)
            plantDao.insertVarieties(cottonVarieties)
            plantDao.insertPests(cottonPests)
            plantDao.insertDiseases(cottonDiseases)
            plantDao.insertWeeds(cottonWeeds)

            val ramieLandPreparations = LandPreparations(
                plantsId = 21,
                processing = "Lahan dibajak dan digaru untuk menghancurkan sisa-sisa tanaman sebelumnya serta menggemburkan tanah.",
                irrigation = "Lahan disiram air hingga merata untuk menjaga kelembaban tanah sebelum penanaman."
            )

            val ramieNurseries = Nurseries(
                plantsId = 21,
                seedSelection = "Gunakan benih rami yang berasal dari varietas unggul dan berkualitas.",
                soaking = "Benih direndam dalam air selama 24-36 jam, kemudian direndam dalam larutan air hangat selama 48 jam untuk mempercepat perkecambahan.",
                seeding = ""
            )

            val ramiePlantings = listOf(
                Plantings(
                    plantsId = 21,
                    preparing = "Bibit rami siap dipindahkan setelah mencapai umur 20-25 hari atau memiliki 3-4 daun yang berkembang baik.",
                    planting = "Tanam bibit dengan jarak tanam sekitar 20x20 cm atau sesuai rekomendasi lokal, dengan kedalaman tanam sekitar 2-3 cm."
                )
            )

            val ramieFertilization = listOf(
                Fertilization(
                    plantsId = 21,
                    type = "Dasar",
                    agePlant = 0,
                    urea = 0,
                    tsp = 0,
                    kcl = 0,
                    organicFertilizer = "Berikan pupuk kandang atau kompos secara merata sebelum tanah terakhir kali digarap."
                ),
                Fertilization(
                    plantsId = 21,
                    type = "Dasar",
                    agePlant = 0,
                    urea = 0,
                    tsp = 0,
                    kcl = 0,
                    organicFertilizer = null
                ),
                Fertilization(
                    plantsId = 21,
                    type = "Susulan",
                    agePlant = 20,
                    urea = 0,
                    tsp = 0,
                    kcl = 0,
                    organicFertilizer = null
                ),
                Fertilization(
                    plantsId = 21,
                    type = "Susulan",
                    agePlant = 30,
                    urea = 0,
                    tsp = 0,
                    kcl = 0,
                    organicFertilizer = null
                )
            )

            val ramieVarieties = listOf(
                Varieties(
                    plantsId = 21,
                    name = "Varietas Rami ABC",
                    superiority = "Tinggi hasil panen serat, adaptasi baik di berbagai jenis tanah.",
                    lack = "Rentan terhadap serangan hama tikus."
                ),
                Varieties(
                    plantsId = 21,
                    name = "Varietas Rami XYZ",
                    superiority = "Tahan terhadap penyakit dan hama umum, cocok untuk lahan dengan drainase baik.",
                    lack = "Memerlukan manajemen air yang hati-hati."
                )
            )

            val ramiePests = listOf(
                Pests(
                    plantsId = 21,
                    name = "Tikus",
                    chemicalControl = "Gunakan perangkap tikus dan lakukan pengendalian populasi secara teratur.",
                    biologicalControl = null
                ),
                Pests(
                    plantsId = 21,
                    name = "Ulat Grayak",
                    chemicalControl = "Terapkan insektisida yang direkomendasikan saat populasi ulat melebihi ambang ekonomis.",
                    biologicalControl = null
                )
            )

            val ramieDiseases = listOf(
                Diseases(
                    plantsId = 21,
                    name = "Penyakit Layu Rami",
                    control = "Pilih varietas yang tahan terhadap layu rami dan terapkan pengelolaan rotasi tanaman."
                ),
                Diseases(
                    plantsId = 21,
                    name = "Jamur Daun",
                    control = "Gunakan fungisida sistemik untuk mengontrol infeksi jamur pada daun rami."
                )
            )

            val ramieWeeds = Weeds(
                plantsId = 21,
                manualWeeding = "Lakukan penyiangan secara rutin untuk mengendalikan pertumbuhan gulma yang dapat bersaing dengan tanaman rami.",
                herbicide = "Gunakan herbisida selektif untuk mengurangi kompetisi gulma tanpa merusak tanaman rami."
            )

            plantDao.insertLandPreparations(ramieLandPreparations)
            plantDao.insertNurseries(ramieNurseries)
            plantDao.insertPlantings(ramiePlantings)
            plantDao.insertFertilization(ramieFertilization)
            plantDao.insertVarieties(ramieVarieties)
            plantDao.insertPests(ramiePests)
            plantDao.insertDiseases(ramieDiseases)
            plantDao.insertWeeds(ramieWeeds)

            val coffeeLandPreparations = LandPreparations(
                plantsId = 22,
                processing = "Lahan dicangkul dan digemburkan untuk meratakan serta mempersiapkan tanah.",
                irrigation = "Lahan disiram secara merata untuk menyiapkan kondisi tanah yang optimal sebelum penanaman."
            )

            val coffeeNurseries = Nurseries(
                plantsId = 22,
                seedSelection = "Gunakan biji kopi yang berasal dari varietas unggul dan berkualitas.",
                soaking = "Benih direndam dalam air selama 24-36 jam, kemudian ditempatkan dalam lingkungan lembab untuk memacu perkecambahan.",
                seeding = ""
            )

            val coffeePlantings = listOf(
                Plantings(
                    plantsId = 22,
                    preparing = "Bibit kopi yang telah mencapai umur sekitar 12-18 bulan siap dipindahkan ke lahan.",
                    planting = "Tanam bibit kopi pada lahan yang telah disiapkan dengan jarak tanam sekitar 2-3 meter, dengan kedalaman tanam sekitar 5-10 cm."
                )
            )

            val coffeeFertilization = listOf(
                Fertilization(
                    plantsId = 22,
                    type = "Dasar",
                    agePlant = 0,
                    urea = 0,
                    tsp = 0,
                    kcl = 0,
                    organicFertilizer = "Berikan pupuk kandang atau kompos sebanyak 20-30 ton/ha sebelum tanam untuk meningkatkan kesuburan tanah."
                ),
                Fertilization(
                    plantsId = 22,
                    type = "Dasar",
                    agePlant = 0,
                    urea = 0,
                    tsp = 0,
                    kcl = 0,
                    organicFertilizer = null
                ),
                Fertilization(
                    plantsId = 22,
                    type = "Susulan",
                    agePlant = 2,
                    urea = 0,
                    tsp = 0,
                    kcl = 0,
                    organicFertilizer = null
                ),
                Fertilization(
                    plantsId = 22,
                    type = "Susulan",
                    agePlant = 3,
                    urea = 0,
                    tsp = 0,
                    kcl = 0,
                    organicFertilizer = null
                )
            )

            val coffeeVarieties = listOf(
                Varieties(
                    plantsId = 22,
                    name = "Varietas Arabika",
                    superiority = "Memiliki cita rasa yang superior, cocok untuk kopi spesialti.",
                    lack = "Rentan terhadap penyakit karat daun."
                ),
                Varieties(
                    plantsId = 22,
                    name = "Varietas Robusta",
                    superiority = "Tahan terhadap hama dan penyakit, hasil panen tinggi.",
                    lack = "Kualitas rasa yang biasa dibandingkan dengan Arabika."
                ),
                Varieties(
                    plantsId = 22,
                    name = "Varietas Liberika",
                    superiority = "Tahan terhadap kondisi lingkungan yang ekstrem.",
                    lack = "Kurang populer dibandingkan dengan Arabika dan Robusta."
                )
            )

            val coffeePests = listOf(
                Pests(
                    plantsId = 22,
                    name = "Penggerek Buah",
                    chemicalControl = "Gunakan pestisida yang direkomendasikan untuk mengendalikan populasi penggerek buah.",
                    biologicalControl = null
                ),
                Pests(
                    plantsId = 22,
                    name = "Ulat Bawang",
                    chemicalControl = "Terapkan larutan insektisida secara merata untuk mencegah kerusakan oleh ulat bawang.",
                    biologicalControl = null
                )
            )

            val coffeeDiseases = listOf(
                Diseases(
                    plantsId = 22,
                    name = "Karat Daun Kopi",
                    control = "Gunakan fungisida yang direkomendasikan dan praktik sanitasi untuk mencegah penyebaran penyakit."
                ),
                Diseases(
                    plantsId = 22,
                    name = "Busuk Buah",
                    control = "Pilih varietas tahan penyakit dan pastikan sanitasi lahan yang baik untuk menghindari busuk buah."
                )
            )

            val coffeeWeeds = Weeds(
                plantsId = 22,
                manualWeeding = "Lakukan penyiangan secara berkala untuk mengendalikan gulma yang dapat bersaing dengan tanaman kopi.",
                herbicide = "Gunakan herbisida selektif jika diperlukan untuk mengurangi tekanan gulma pada tanaman kopi."
            )

            plantDao.insertLandPreparations(coffeeLandPreparations)
            plantDao.insertNurseries(coffeeNurseries)
            plantDao.insertPlantings(coffeePlantings)
            plantDao.insertFertilization(coffeeFertilization)
            plantDao.insertVarieties(coffeeVarieties)
            plantDao.insertPests(coffeePests)
            plantDao.insertDiseases(coffeeDiseases)
            plantDao.insertWeeds(coffeeWeeds)
        }
    }
}