package com.ryan.agriaid.data.local

import com.ryan.agriaid.R
import com.ryan.agriaid.data.local.article.Article
import com.ryan.agriaid.data.local.article.ArticleDetail
import com.ryan.agriaid.data.local.plants.AvgSoil
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
                    description = "Padi adalah tanaman semusim yang tumbuh di sawah atau lahan berair, yang bijinya digunakan sebagai bahan pokok makanan utama di banyak negara, terutama di Asia. Tanaman ini memiliki siklus hidup yang terdiri dari fase tanam, vegetatif, generatif, dan pematangan. Padi menghasilkan butir-butir beras setelah melalui proses penggilingan dan pemolesan, yang kemudian diolah menjadi nasi.",
                    imgUrl = R.drawable.padi
                ), Plants(
                    id = 2,
                    name = "jagung",
                    description = "Jagung adalah tanaman yang mudah tumbuh dan serbaguna, yang bijinya dapat dimanfaatkan sebagai bahan pangan, pakan ternak, dan bahan baku industri. Tanaman ini kaya akan nutrisi seperti karbohidrat, vitamin, dan mineral, sehingga bermanfaat untuk kesehatan. Jagung dapat diolah menjadi berbagai produk seperti tepung, minyak, dan sirup, memberikan nilai tambah bagi petani.",
                    imgUrl = R.drawable.jagung
                ), Plants(
                    id = 3,
                    name = "buncis",
                    description = "Buncis adalah tanaman sayuran yang mudah ditanam dan cepat panen, sering kali dalam waktu kurang dari dua bulan. Tanaman ini tumbuh baik di daerah dengan iklim sejuk hingga sedang dan membutuhkan penyiraman yang cukup. Buncis kaya akan serat, vitamin, dan mineral, menjadikannya pilihan yang baik untuk kesehatan. Selain itu, buncis dapat diolah menjadi berbagai hidangan, menambah nilai ekonomi bagi petani.",
                    imgUrl = R.drawable.buncis
                ), Plants(
                    id = 4,
                    name = "kacang_merah",
                    description = "Kacang merah adalah tanaman leguminosa yang banyak dibudidayakan untuk bijinya yang bergizi tinggi. Biji kacang merah kaya akan protein, serat, dan mineral, serta sering digunakan dalam berbagai hidangan seperti sup dan kari. Tanaman ini tumbuh baik di tanah yang subur dengan sinar matahari yang cukup dan memiliki siklus hidup sekitar 3-4 bulan. ",
                    imgUrl = R.drawable.kacang_merah
                ), Plants(
                    id = 5,
                    name = "kacang_gude",
                    description ="Kacang gude adalah tanaman legum yang sering dibudidayakan untuk bijinya yang kaya protein dan serat. Tanaman ini tumbuh dengan baik di daerah beriklim tropis dan subtropis, serta memerlukan tanah yang gembur dan penyiraman yang cukup. Kacang gude dapat diolah menjadi berbagai hidangan tradisional seperti sup atau campuran makanan, dan juga dapat digunakan sebagai pakan ternak. Selain itu, kacang gude memiliki manfaat untuk meningkatkan kesuburan tanah karena kemampuannya mengikat nitrogen.",
                    imgUrl = R.drawable.kacang_gude
                ), Plants(
                    id = 6,
                    name = "kacang_matik",
                    description = "Kacang matik adalah tanaman legum yang sering dibudidayakan untuk bijinya yang kaya protein dan serat. Tanaman ini tumbuh baik di tanah yang gembur dengan iklim tropis dan curah hujan yang cukup. Kacang matik dapat diolah menjadi berbagai hidangan, seperti lauk pauk dan camilan.",
                    imgUrl = R.drawable.kuda
                ), Plants(
                    id = 7,
                    name = "kacang_hijau",
                    description = "Kacang hijau adalah tanaman legum yang bijinya kaya akan protein, serat, dan vitamin. Tanaman ini tumbuh dengan baik di tanah yang gembur dan membutuhkan iklim tropis dengan curah hujan yang cukup. Kacang hijau sering digunakan dalam berbagai hidangan seperti bubur atau kecap, dan juga membantu memperbaiki kesuburan tanah karena kemampuannya mengikat nitrogen dari udara.",
                    imgUrl = R.drawable.kacang_hijau
                ), Plants(
                    id = 8,
                    name = "kacang_hitam",
                    description = "Kacang hitam adalah tanaman legum yang menghasilkan biji berwarna hitam dengan kandungan protein, serat, dan vitamin yang tinggi. Tanaman ini tumbuh baik di tanah yang gembur dengan iklim tropis atau subtropis dan memerlukan curah hujan yang cukup. Kacang hitam sering digunakan dalam berbagai hidangan seperti sup dan salad.",
                    imgUrl = R.drawable.kacang_hitam
                ), Plants(
                    id = 9,
                    name = "kacang_lentis",
                    description = "Kacang lentil adalah tanaman legum yang menghasilkan biji kecil berbentuk datar dan kaya protein, serat, serta vitamin B. Tanaman ini tumbuh baik di tanah yang gembur dengan iklim sedang hingga subtropis dan memerlukan curah hujan yang cukup. Kacang lentil sering digunakan dalam berbagai hidangan seperti sup dan salad.",
                    imgUrl = R.drawable.lentis
                ), Plants(
                    id = 10,
                    name = "delima",
                    description = "Delima adalah tanaman buah yang menghasilkan buah dengan kulit merah cerah dan biji berwarna merah atau pink yang kaya akan antioksidan, vitamin C, dan serat. Tanaman ini tumbuh baik di tanah yang gembur dan berdrainase baik dengan iklim subtropis hingga tropis. Buah delima sering dikonsumsi segar, dijus, atau digunakan dalam berbagai hidangan kuliner, serta memiliki manfaat kesehatan seperti meningkatkan kesehatan jantung dan sistem pencernaan.",
                    imgUrl = R.drawable.delima
                ), Plants(
                    id = 11,
                    name = "pisang",
                    description = "Pisang adalah tanaman buah yang menghasilkan buah berkulit kuning atau hijau dengan daging yang manis dan kaya nutrisi. Tanaman ini tumbuh baik di tanah yang subur dan berdrainase baik dengan iklim tropis yang hangat dan curah hujan yang merata. Pisang sering dikonsumsi langsung, diolah menjadi makanan seperti kue atau pancake, dan juga memiliki manfaat sebagai sumber energi cepat serta vitamin dan mineral penting.",
                    imgUrl = R.drawable.pisang
                ), Plants(
                    id = 12,
                    name = "mangga",
                    description = "Mangga adalah tanaman buah tropis yang menghasilkan buah dengan kulit berwarna hijau, kuning, atau merah dan daging buah yang manis serta kaya vitamin C, A, dan serat. Tanaman ini tumbuh baik di tanah yang subur dan berdrainase baik dengan iklim tropis atau subtropis dan membutuhkan sinar matahari penuh untuk berkembang. Mangga dapat dikonsumsi langsung, dibuat menjadi jus, atau diolah dalam berbagai hidangan kuliner. Selain itu, pohon mangga juga dapat memberikan naungan dan memperbaiki kualitas tanah.",
                    imgUrl = R.drawable.mangga
                ), Plants(
                    id = 13,
                    name = "anggur",
                    description = "Anggur adalah tanaman buah merambat yang menghasilkan buah kecil berwarna hijau, merah, atau ungu yang kaya akan antioksidan, vitamin C, dan serat. Tanaman ini tumbuh baik di tanah yang subur dan berdrainase baik dengan iklim sedang hingga subtropis serta membutuhkan sinar matahari penuh untuk menghasilkan buah yang optimal. Anggur dapat dikonsumsi langsung, dibuat menjadi jus, atau diolah menjadi produk seperti anggur kering (raisin) dan anggur untuk pembuatan wine. Selain itu, tanaman anggur juga dapat memberikan naungan dan estetika pada taman.",
                    imgUrl = R.drawable.anggur
                ), Plants(
                    id = 14,
                    name = "semangka",
                    description = "Semangka adalah tanaman buah yang menghasilkan buah besar dengan kulit berwarna hijau bergaris dan daging merah atau kuning yang manis serta kaya air. Tanaman ini tumbuh baik di tanah yang subur dan berdrainase baik dengan iklim tropis atau subtropis yang hangat dan sinar matahari penuh. Semangka sering dikonsumsi langsung sebagai camilan segar atau digunakan dalam berbagai hidangan dan minuman. Selain memberikan buah yang menyegarkan, semangka juga dapat meningkatkan keberagaman tanaman di kebun atau lahan pertanian.",
                    imgUrl = R.drawable.semangka
                ), Plants(
                    id = 15,
                    name = "melon",
                    description = "Melon adalah tanaman buah yang menghasilkan buah besar dengan kulit berwarna hijau, kuning, atau oranye dan daging buah yang manis serta kaya air, vitamin C, dan serat. Tanaman ini tumbuh baik di tanah yang subur, berdrainase baik, dan memerlukan iklim tropis atau subtropis dengan sinar matahari penuh dan curah hujan yang merata. Melon sering dikonsumsi langsung sebagai camilan atau digunakan dalam salad dan minuman. ",
                    imgUrl = R.drawable.melon
                ), Plants(
                    id = 16,
                    name = "apel",
                    description = "Apel adalah tanaman buah yang menghasilkan buah bulat dengan kulit berwarna merah, hijau, atau kuning dan daging buah yang renyah serta kaya vitamin C, serat, dan antioksidan. Tanaman ini tumbuh baik di tanah yang subur dan berdrainase baik dengan iklim sedang hingga dingin dan membutuhkan sinar matahari penuh. Apel dapat dikonsumsi langsung, dibuat menjadi jus, atau diolah menjadi produk seperti pie dan selai. Pohon apel juga dapat meningkatkan kesehatan tanah dengan menambah bahan organik dari daun yang gugur.",
                    imgUrl = R.drawable.apel
                ), Plants(
                    id = 17,
                    name = "jeruk",
                    description = "Jeruk adalah tanaman buah yang menghasilkan buah bulat dengan kulit berwarna oranye dan daging buah yang juicy serta kaya vitamin C, serat, dan antioksidan. Tanaman ini tumbuh baik di tanah yang subur dan berdrainase baik dengan iklim tropis hingga subtropis dan membutuhkan sinar matahari penuh. Jeruk dapat dikonsumsi langsung, dibuat menjadi jus, atau diolah menjadi berbagai produk seperti selai dan perasa makanan. Pohon jeruk juga dapat membantu mengurangi erosi tanah dengan sistem akar yang kuat",
                    imgUrl = R.drawable.jeruk
                ), Plants(
                    id = 18,
                    name = "pepaya",
                    description = "Pepaya adalah tanaman buah tropis yang menghasilkan buah besar dengan kulit berwarna hijau, kuning, atau oranye dan daging buah yang manis serta kaya vitamin C, vitamin A, dan enzim papain. Tanaman ini tumbuh baik di tanah yang subur dan berdrainase baik dengan iklim tropis yang hangat dan membutuhkan sinar matahari penuh. Pepaya dapat dikonsumsi langsung, dibuat menjadi jus, atau diolah dalam berbagai hidangan dan salad. Tanaman pepaya juga dapat membantu mengurangi kelembaban tanah berlebih.",
                    imgUrl = R.drawable.pepaya
                ), Plants(
                    id = 19,
                    name = "kelapa",
                    description = "Kelapa adalah tanaman palma yang menghasilkan buah dengan kulit berserat berwarna coklat dan daging buah yang kaya minyak serta vitamin C, vitamin B, dan serat. Tanaman ini tumbuh baik di tanah berpasir yang subur dan berdrainase baik dengan iklim tropis yang hangat dan membutuhkan sinar matahari penuh. Kelapa dapat dikonsumsi langsung, diolah menjadi santan, atau digunakan dalam berbagai produk makanan dan minuman. Tanaman kelapa juga dapat melindungi pantai dari erosi dengan akar yang kuat.",
                    imgUrl = R.drawable.kelapa
                ), Plants(
                    id = 20,
                    name = "kapas",
                    description = "Kapas adalah tanaman serat yang menghasilkan bola kapas dengan serat putih yang lembut dan kaya serat alami. Tanaman ini tumbuh baik di tanah yang subur dan berdrainase baik dengan iklim hangat hingga panas dan membutuhkan sinar matahari penuh. Kapas dapat diproses menjadi kain, benang, atau produk tekstil lainnya. Tanaman kapas juga dapat meningkatkan struktur tanah dengan sistem akar yang dalam.",
                    imgUrl = R.drawable.kapas
                ), Plants(
                    id = 21,
                    name = "rami",
                    description = "Rami adalah tanaman serat yang menghasilkan batang dengan serat panjang dan kuat yang kaya serat alami dan digunakan dalam berbagai produk tekstil. Tanaman ini tumbuh baik di tanah yang subur dan berdrainase baik dengan iklim sedang hingga tropis dan membutuhkan sinar matahari penuh. Rami dapat diolah menjadi benang, kain, atau produk kerajinan tangan. Tanaman rami juga dapat membantu mengurangi pencemaran tanah dengan menyerap logam berat dari tanah.",
                    imgUrl = R.drawable.rami
                ), Plants(
                    id = 22,
                    name = "kopi",
                    description = "Kopi adalah tanaman berkayu yang menghasilkan biji kopi dengan kulit berwarna hijau hingga coklat dan daging biji yang kaya kafein serta antioksidan. Tanaman ini tumbuh baik di tanah yang subur, berdrainase baik, dan memerlukan iklim tropis dengan suhu sejuk hingga hangat dan membutuhkan sinar matahari parsial. Biji kopi dapat dipanggang dan digiling untuk membuat minuman kopi yang populer di seluruh dunia. Tanaman kopi juga dapat memberikan naungan untuk tanaman lain di kebun.",
                    imgUrl = R.drawable.kopi
                )
            )
            plantDao.insertAllPlants(plantsData)

            val avgSoils = listOf(
                AvgSoil(plantsId = 1, n = "80", p = "48", k = "40", temperature = "24", humidity = "82", ph = "6", rainfall = "236"),
                AvgSoil(plantsId = 2, n = "78", p = "48", k = "20", temperature = "22", humidity = "65", ph = "6", rainfall = "85"),
                AvgSoil(plantsId = 3, n = "40", p = "68", k = "80", temperature = "19", humidity = "17", ph = "7", rainfall = "80"),
                AvgSoil(plantsId = 4, n = "21", p = "68", k = "20", temperature = "20", humidity = "22", ph = "6", rainfall = "106"),
                AvgSoil(plantsId = 5, n = "21", p = "68", k = "20", temperature = "28", humidity = "48", ph = "6", rainfall = "149"),
                AvgSoil(plantsId = 6, n = "21", p = "48", k = "20", temperature = "28", humidity = "53", ph = "7", rainfall = "51"),
                AvgSoil(plantsId = 7, n = "21", p = "47", k = "20", temperature = "29", humidity = "85", ph = "7", rainfall = "48"),
                AvgSoil(plantsId = 8, n = "40", p = "67", k = "19", temperature = "30", humidity = "65", ph = "7", rainfall = "68"),
                AvgSoil(plantsId = 9, n = "19", p = "68", k = "19", temperature = "25", humidity = "65", ph = "7", rainfall = "46"),
                AvgSoil(plantsId = 10, n = "19", p = "19", k = "40", temperature = "22", humidity = "90", ph = "6", rainfall = "108"),
                AvgSoil(plantsId = 11, n = "100", p = "82", k = "50", temperature = "27", humidity = "80", ph = "6", rainfall = "105"),
                AvgSoil(plantsId = 12, n = "20", p = "27", k = "30", temperature = "31", humidity = "50", ph = "6", rainfall = "95"),
                AvgSoil(plantsId = 13, n = "23", p = "133", k = "200", temperature = "24", humidity = "82", ph = "6", rainfall = "70"),
                AvgSoil(plantsId = 14, n = "99", p = "17", k = "50", temperature = "26", humidity = "85", ph = "6", rainfall = "51"),
                AvgSoil(plantsId = 15, n = "100", p = "18", k = "50", temperature = "29", humidity = "92", ph = "6", rainfall = "25"),
                AvgSoil(plantsId = 16, n = "21", p = "134", k = "200", temperature = "23", humidity = "92", ph = "6", rainfall = "113"),
                AvgSoil(plantsId = 17, n = "20", p = "17", k = "10", temperature = "23", humidity = "92", ph = "7", rainfall = "110"),
                AvgSoil(plantsId = 18, n = "50", p = "59", k = "50", temperature = "34", humidity = "92", ph = "7", rainfall = "143"),
                AvgSoil(plantsId = 19, n = "22", p = "17", k = "31", temperature = "27", humidity = "95", ph = "6", rainfall = "176"),
                AvgSoil(plantsId = 20, n = "118", p = "46", k = "20", temperature = "24", humidity = "80", ph = "7", rainfall = "80"),
                AvgSoil(plantsId = 21, n = "78", p = "47", k = "40", temperature = "25", humidity = "80", ph = "7", rainfall = "175"),
                AvgSoil(plantsId = 22, n = "101", p = "29", k = "30", temperature = "26", humidity = "59", ph = "7", rainfall = "158"),
            )
            plantDao.insertAvgSoils(avgSoils)

            val padiLandPreparations = LandPreparations(
                plantsId = 1,
                processing = "Pilih lahan dengan pH 5,5-7,0 dan pastikan tanah subur serta memiliki sistem drainase yang baik. Olah tanah dengan membajak atau mencangkul hingga tanah halus. Genangi lahan dengan air setinggi 2-5 cm dan biarkan selama 1-2 minggu untuk membunuh gulma dan merangsang pembusukan sisa tanaman.",
                irrigation = "Pastikan sistem irigasi memadai untuk menjaga tingkat air yang stabil. Gunakan irigasi sistem basah-kering untuk memastikan kebutuhan air yang cukup dan menghindari kerusakan akar."
            )

            plantDao.insertLandPreparations(padiLandPreparations)

            val padiNurseries = Nurseries(
                plantsId = 1,
                seedSelection = "Pilih benih padi varietas unggul yang bersertifikat dengan daya tumbuh tinggi dan bebas dari penyakit. Pilih benih yang bersih dan tidak ada cacat fisik.",
                soaking = "Rendam benih dalam air selama 24 jam untuk mempercepat perkecambahan. Kemudian tiriskan dan biarkan benih selama 1-2 hari hingga tumbuh tunas kecil.",
                seeding = "Sebar benih di persemaian yang telah disiapkan dengan media tanah yang subur dan cukup air."
            )

            plantDao.insertNurseries(padiNurseries)

            val padiFertilization = listOf(
                Fertilization(
                    plantsId = 1,
                    type = "Dasar",
                    agePlant = "7 hari",
                    urea = "50 Ton/Ha",
                    tsp = "50 Ton/Ha",
                    kcl = "50 Ton/Ha",
                    organicFertilizer = "Kompos atau pupuk kandang, 3-5 ton/ha, diberikan pada awal tanam untuk meningkatkan kesuburan tanah."
                ),
                Fertilization(
                    plantsId = 1,
                    type = "Susulan",
                    agePlant = "30-40 hari",
                    urea = "75 Ton/Ha",
                    tsp = "50 Ton/Ha",
                    kcl = "25 Ton/Ha",
                    organicFertilizer = null
                ),
                Fertilization(
                    plantsId = 1,
                    type = "Susulan",
                    agePlant = "50-60 hari (opsional)",
                    urea = "50 Ton/Ha",
                    tsp = "0",
                    kcl = "0",
                    organicFertilizer = "Jika diperlukan, tambahkan kompos atau pupuk kandang untuk meningkatkan kualitas tanah."
                ),
                Fertilization(
                    plantsId = 1,
                    type = "Susulan",
                    agePlant = "60-70 hari (opsional)",
                    urea = "50 Ton/Ha",
                    tsp = "0",
                    kcl = "50 Ton/Ha",
                    organicFertilizer = null
                ),
            )

            plantDao.insertFertilization(padiFertilization)

            val padiVarieties = listOf(
                Varieties(
                    plantsId = 1,
                    name = "IR64",
                    superiority = "Tahan terhadap penyakit, hasil panen tinggi, adaptasi baik pada berbagai kondisi.",
                    weakness = "Sensitif terhadap kekeringan, butuh perawatan intensif."
                ), Varieties(
                    plantsId = 1,
                    name = "Ciherang",
                    superiority = "Tahan terhadap hama wereng coklat, hasil panen tinggi, dan kualitas beras medium.",
                    weakness = "Rentan terhadap penyakit hawar daun bakteri, Kurang tahan terhadap genangan air yang berkepanjangan."
                ), Varieties(
                    plantsId = 1,
                    name = "Inpari 32",
                    superiority = "Tahan terhadap beberapa jenis hama dan penyakit, adaptasi baik di berbagai kondisi lahan.",
                    weakness = "Kualitas beras medium, kurang tahan terhadap kondisi kekeringan."
                )
            )
            plantDao.insertVarieties(padiVarieties)

            val padiPests = listOf(
                Pests(
                    plantsId = 1,
                    name = "Wereng Coklat",
                    chemicalControl = "Gunakan insektisida seperti imidakloprid atau fipronil, dan tanam varietas yang tahan wereng. Piretrum (Kandungan: Pyrethrin) atau insektisida sistemik seperti Dimethoate.",
                    biologicalControl = "Larutan bawang putih atau minyak neem, efektif untuk mengusir hama dengan cara alami."
                ), Pests(
                    plantsId = 1,
                    name = "Ulat Grayak",
                    chemicalControl = "Gunakan pestisida seperti karbofuran atau Chlorpyrifos (Kandungan: Chlorpyrifos)",
                    biologicalControl = " Campuran daun pepaya dan air, diaplikasikan untuk mengurangi jumlah ulat secara organik."
                )
            )
            plantDao.insertPests(padiPests)

            val padiWeeds = Weeds(
                plantsId = 1,
                manualWeeding = "Penyiangan manual dengan mencabut gulma secara rutin setiap 2 minggu untuk menghindari kompetisi dengan padi.",
                herbicide = "Pre-emergence herbisida seperti Butachlor, digunakan sebelum tanaman padi muncul dari tanah untuk membunuh gulma awal."
            )
            plantDao.insertWeeds(padiWeeds)

            val padiPlantings = listOf(
                Plantings(
                    plantsId = 1,
                    preparing = "Pengolahan Tanah: Lahan dibajak dan digaru untuk menghancurkan sisa tanaman.",
                    planting = "Tanam bibit padi pada lahan genangan dengan jarak 25-30 cm antar baris dan 5-10 cm antar tanaman. Pastikan bibit ditanam pada kedalaman yang tepat dan tegak lurus."
                )
            )
            plantDao.insertPlantings(padiPlantings)

            val padiDiseases = listOf(
                Diseases(
                    plantsId = 1,
                    name = "Penyakit Blas",
                    control = "Gunakan varietas tahan blas, aplikasi fungisida seperti Propiconazole, dan rotasi tanaman untuk mengurangi infeksi."
                ), Diseases(
                    plantsId = 1,
                    name = "Hawar Daun Bakteri",
                    control = "Gunakan varietas tahan penyakit. Aplikasikan fungisida seperti Mancozeb, dan pastikan sirkulasi udara baik serta tidak ada genangan air yang lama."
                )
            )
            plantDao.insertDiseases(padiDiseases)

            val jagungLandPreparations = LandPreparations(
                plantsId = 2,
                processing = "Pilih lahan dengan pH 5,8-7,0 dan tanah yang subur, gembur, serta memiliki drainase baik. Olah tanah dengan membajak atau mencangkul hingga kedalaman 20-30 cm. Tanam jagung di lahan yang terkena sinar matahari penuh.",
                irrigation = "Jagung membutuhkan kelembapan tanah yang konsisten. Gunakan sistem irigasi tetes atau siram secara rutin untuk menjaga kelembapan tanah terutama saat masa pertumbuhan dan pembentukan tongkol."
            )

            plantDao.insertLandPreparations(jagungLandPreparations)

            val jagungNurseries = Nurseries(
                plantsId = 2,
                seedSelection = "Pilih benih jagung varietas unggul dengan daya tumbuh tinggi dan bebas dari penyakit. Pilih benih dengan kemasan yang masih baru dan tidak cacat.",
                soaking = "Tidak diperlukan.",
                seeding = "Tidak diperlukan. Benih dapat langsung ditanam pada lahan tanam setelah persiapan tanah."
            )

            plantDao.insertNurseries(jagungNurseries)

            val jagungFertilization = listOf(
                Fertilization(
                    plantsId = 2,
                    type = "Dasar",
                    agePlant = "7 Hari",
                    urea = "75 Ton/Ha",
                    tsp = "50 Ton/Ha",
                    kcl = "50 Ton/Ha",
                    organicFertilizer = "Pupuk kandang, 3-5 ton/ha, digunakan pada awal tanam untuk meningkatkan kesuburan tanah."
                ), Fertilization(
                    plantsId = 2,
                    type = "Susulan",
                    agePlant = "30-40 Hari",
                    urea = "100 Ton/Ha",
                    tsp = "50 Ton/Ha",
                    kcl = "50 Ton/Ha",
                    organicFertilizer = null
                ), Fertilization(
                    plantsId = 2,
                    type = "Susulan",
                    agePlant = "70-80 Hari",
                    urea = "50 Ton/Ha",
                    tsp = "0",
                    kcl = "25 Ton/Ha",
                    organicFertilizer = null
                )
            )

            plantDao.insertFertilization(jagungFertilization)

            val jagungVarieties = listOf(
                Varieties(
                    plantsId = 2,
                    name = "BISI 2",
                    superiority = "Hasil tinggi, tahan terhadap penyakit busuk tongkol, dan adaptasi pada berbagai kondisi tanah.",
                    weakness = "Sensitif terhadap kekeringan ekstrem."
                ),
                Varieties(
                    plantsId = 2,
                    name = "BISI 18",
                    superiority = "Produktivitas tinggi, adaptasi baik di berbagai kondisi lahan.",
                    weakness = "Memerlukan pemupukan dan pengairan yang tepat untuk hasil optimal."
                ),
                Varieties(
                    plantsId = 2,
                    name = "NK 22",
                    superiority = "Tahan terhadap hama dan penyakit umum, hasil panen stabil.",
                    weakness = "Memerlukan manajemen air yang baik untuk menghindari kekeringan."
                ),
                Varieties(
                    plantsId = 2,
                    name = "Pionir 28",
                    superiority = "Cepat berbunga dan matang, cocok untuk daerah dengan musim tanam pendek.",
                    weakness = "Rentan terhadap serangan hama penggerek batang."
                ),
                Varieties(
                    plantsId = 2,
                    name = "Pionir 37",
                    superiority = "Produktivitas tinggi, tahan terhadap penyakit, dan memiliki ketahanan terhadap hama.",
                    weakness = "Memerlukan perawatan lebih intensif dibandingkan varietas lain."
                )
            )

            plantDao.insertVarieties(jagungVarieties)

            val jagungPests = listOf(
                Pests(
                    plantsId = 2,
                    name = "Ulat Penggerek Jagung",
                    chemicalControl = "Gunakan pestisida seperti karbofuran, atau pestisida yang mengandung Cypermethrin",
                    biologicalControl = "Lakukan pengendalian biologis dengan memasang perangkap feromon atau Penggunaan ekstrak bawang putih, karena memiliki sifat pestisida alami yang dapat mengusir hama."
                ),
                Pests(
                    plantsId = 2,
                    name = "Ulat Grayak",
                    chemicalControl = "Gunakan insektisida yang direkomendasikan, terutama saat tanaman masih muda.",
                    biologicalControl = null
                ),
                Pests(
                    plantsId = 2,
                    name = "Kutu Daun",
                    chemicalControl = "Campuran air dengan sabun cuci piring, diaplikasikan pada daun untuk mengatasi kutu daun secara alami.",
                    biologicalControl = null
                )
            )
            plantDao.insertPests(jagungPests)

            val jagungDiseases = listOf(
                Diseases(
                    plantsId = 2,
                    name = "Penyakit Busuk Leher",
                    control = "Gunakan varietas tahan, aplikasikan fungisida seperti Mancozeb, dan pastikan jarak tanam yang baik untuk ventilasi."
                ),
                Diseases(
                    plantsId = 2,
                    name = "Penyakit Layu Fusarium",
                    control = "Gunakan benih sehat, rotasi tanaman, dan aplikasi fungisida berbasis Propiconazole."
                )
            )

            plantDao.insertDiseases(jagungDiseases)

            val jagungWeeds = Weeds(
                plantsId = 2,
                manualWeeding = "Penyiangan manual setiap 2-3 minggu untuk menghindari kompetisi dengan jagung.",
                herbicide = "Gunakan herbisida sesuai anjuran petani untuk mengendalikan gulma yang sulit dikontrol secara manual. Gunakan Glyphosate (digunakan untuk pengendalian gulma pasca-tumbuh, aplikasikan sebelum penanaman jagung)."
            )

            plantDao.insertWeeds(jagungWeeds)

            val jagungPlantings = listOf(
                Plantings(
                    plantsId = 2,
                    preparing = "Siapkan lahan dengan olah tanah yang baik dan buat bedengan atau lahan tanam dengan jarak antar baris 70-80 cm.",
                    planting = "Tanam benih pada kedalaman 2-3 cm dengan jarak antar benih 10-15 cm dalam barisan. Siram dengan cukup air setelah tanam."
                )
            )
            plantDao.insertPlantings(jagungPlantings)

            val buncisLandPreparations = LandPreparations(
                plantsId = 3,
                processing = "Pilih lahan dengan pH tanah 6,0-7,0 dan tanah yang subur, gembur, serta memiliki drainase yang baik. Olah tanah dengan membajak atau mencangkul hingga kedalaman 20-30 cm. Pastikan tanah bebas dari gulma dan sisa tanaman sebelumnya. Lakukan pengolahan tanah beberapa minggu sebelum tanam untuk meningkatkan aerasi tanah.",
                irrigation = "Buncis memerlukan kelembapan tanah yang cukup. Gunakan sistem irigasi tetes atau siram tanah secara rutin setiap 2-3 hari sekali, terutama selama masa pertumbuhan vegetatif. Hindari genangan air yang bisa menyebabkan penyakit akar."
            )
            plantDao.insertLandPreparations(buncisLandPreparations)

            val buncisNurseries = Nurseries(
                plantsId = 3,
                seedSelection = "Pilih benih buncis varietas unggul yang bebas dari penyakit, memiliki daya tumbuh tinggi, dan berasal dari sumber terpercaya. Pilih benih dengan kualitas baik yang belum kadaluarsa.",
                soaking = "Rendam benih dalam air hangat (35°C) selama 2-4 jam sebelum tanam untuk meningkatkan daya germinasi.",
                seeding = "Tanam benih langsung di lahan tanam atau semai di media tanam. Jika disemai, pindahkan bibit ke lahan setelah bibit memiliki 2-3 daun sejati."
            )
            plantDao.insertNurseries(buncisNurseries)

            val buncisFertilization = listOf(
                Fertilization(
                    plantsId = 3,
                    type = "Dasar",
                    agePlant = "0 Hari",
                    urea = "50 Ton/Ha",
                    tsp = "50 Ton/Ha",
                    kcl = "50 Ton/Ha",
                    organicFertilizer = "Pupuk kompos atau pupuk kandang, 2-3 ton/ha, ditambahkan ke tanah sebelum tanam untuk meningkatkan kesuburan tanah."
                ),
                Fertilization(
                    plantsId = 3,
                    type = "Susulan",
                    agePlant = "15-20 Hari",
                    urea = "50 Ton/Ha",
                    tsp = "25 Ton/Ha",
                    kcl = "25 Ton/Ha",
                    organicFertilizer = null
                ),
                Fertilization(
                    plantsId = 3,
                    type = "Susulan",
                    agePlant = "30-35 Hari (opsional)",
                    urea = "25 Ton/Ha",
                    tsp = "0",
                    kcl = "25 Ton/Ha",
                    organicFertilizer = null
                )
            )

            plantDao.insertFertilization(buncisFertilization)

            val buncisVarieties = listOf(
                Varieties(
                    plantsId = 3,
                    name = "Buncis Merah",
                    superiority = "Berbuah lebat, adaptasi pada berbagai kondisi tanah, hasil panen tinggi.",
                    weakness = "Rentan terhadap penyakit jamur pada kondisi lembab."
                ),
                Varieties(
                    plantsId = 3,
                    name = "Buncis Kancing",
                    superiority = "Tahan terhadap hama penggerek, masa panen lebih cepat.",
                    weakness = "Hasil panen mungkin lebih rendah dibandingkan varietas lain."
                ),
                Varieties(
                    plantsId = 3,
                    name = "Buncis Hijau",
                    superiority = "Kualitas biji baik, daya tahan terhadap kekeringan.",
                    weakness = "Memerlukan lebih banyak air dibandingkan varietas lain."
                )
            )

            plantDao.insertVarieties(buncisVarieties)

            val buncisPests = listOf(
                Pests(
                    plantsId = 3,
                    name = "Ulat Penggerek",
                    chemicalControl = "Cypermethrin (Kandungan: Cypermethrin) - Insektisida untuk mengendalikan ulat penggerek dengan cara mengganggu sistem saraf hama.",
                    biologicalControl = "Campuran air dan ekstrak bawang putih - Menjaga hama penggerek dari tanaman."
                ),
                Pests(
                    plantsId = 3,
                    name = "Kutu Daun",
                    chemicalControl = "Imidacloprid (Kandungan: Imidacloprid) - Insektisida sistemik yang mengendalikan kutu daun dengan cara mengganggu fungsi saraf.",
                    biologicalControl = "Campuran air dengan sabun cuci piring - Mengurangi populasi kutu daun dengan mematikan mereka secara fisik."
                ),
                Pests(
                    plantsId = 3,
                    name = "Tungau",
                    chemicalControl = "Acaricide seperti Abamectin (Kandungan: Abamectin) - Untuk mengendalikan tungau",
                    biologicalControl = "Larutan minyak neem dan air - Mengandung azadirachtin yang efektif dalam mengendalikan tungau."
                ),
                Pests(
                    plantsId = 3,
                    name = "Kumbang Daun",
                    chemicalControl = "Metomil (Kandungan: Metomil) - Insektisida yang efektif mengendalikan kumbang daun dengan cara menghambat enzim pencernaan.",
                    biologicalControl = "Ekstrak daun tomat - Menjauhkan kumbang daun dari tanaman."
                )
            )
            plantDao.insertPests(buncisPests)

            val buncisDiseases = listOf(
                Diseases(
                    plantsId = 3,
                    name = "Busuk Akar Fusarium",
                    control = "Gunakan benih sehat, rotasi tanaman, dan aplikasikan fungisida seperti Mancozeb. Pastikan drainase tanah baik."
                ),
                Diseases(
                    plantsId = 3,
                    name = "Karat Daun",
                    control = "Aplikasikan fungisida berbasis Tebuconazole, dan pastikan ventilasi tanaman baik."
                ),
                Diseases(
                    plantsId = 3,
                    name = "Bercak Daun",
                    control = "Gunakan fungisida berbasis Copper Oxychloride dan pastikan sirkulasi udara yang baik serta pengelolaan hama yang efektif."
                )
            )
            plantDao.insertDiseases(buncisDiseases)

            val buncisWeeds = Weeds(
                plantsId = 3,
                manualWeeding = "Penyiangan manual setiap 2 minggu untuk menghindari kompetisi dengan buncis. Cabut gulma dengan tangan atau gunakan alat penyiang.",
                herbicide = "Glyphosate - Digunakan sebelum penanaman untuk mengendalikan gulma awal dengan cara mematikan tanaman gulma."
            )

            plantDao.insertWeeds(buncisWeeds)

            val buncisPlantings = listOf(
                Plantings(
                    plantsId = 3,
                    preparing = "Siapkan lahan dengan pengolahan tanah yang baik, buat bedengan atau lahan tanam dengan jarak antar baris 40-50 cm.",
                    planting = "Tanam benih pada kedalaman 2-3 cm dengan jarak antar benih 10-15 cm dalam barisan. Siram tanah setelah penanaman untuk memastikan benih mendapatkan kelembapan yang cukup."
                )
            )
            plantDao.insertPlantings(buncisPlantings)

            val kacangMerahLandPreparations = LandPreparations(
                plantsId = 4,
                processing = "Pilih lahan dengan pH tanah 5,5-7,5 dan tanah yang subur, gembur, serta memiliki drainase yang baik. Olah tanah dengan membajak atau mencangkul hingga kedalaman 20-30 cm, campurkan pupuk organik seperti kompos atau pupuk kandang untuk meningkatkan kesuburan tanah. Persiapkan bedengan atau lahan tanam dengan jarak antar baris 30-40 cm.",
                irrigation = "Kacang merah memerlukan kelembapan tanah yang konsisten, terutama pada masa awal pertumbuhan. Gunakan sistem irigasi tetes atau siram tanah secara rutin setiap 2-3 hari, terutama pada masa vegetatif. Hindari genangan air di sekitar tanaman."
            )

            plantDao.insertLandPreparations(kacangMerahLandPreparations)

            val kacangMerahNurseries = Nurseries(
                plantsId = 4,
                seedSelection = "Pilih benih kacang merah varietas unggul yang bebas dari penyakit, memiliki daya tumbuh tinggi, dan berasal dari sumber terpercaya. Pilih benih yang berkualitas baik dan masih dalam kemasan yang baru.",
                soaking = "Rendam benih dalam air selama 8-12 jam sebelum tanam untuk meningkatkan daya germinasi.",
                seeding = "Tanam benih langsung di lahan tanam atau semai di media tanam. Jika disemai, pindahkan bibit ke lahan setelah bibit memiliki 2-3 daun sejati."
            )
            plantDao.insertNurseries(kacangMerahNurseries)

            val kacangMerahFertilization = listOf(
                Fertilization(
                    plantsId = 4,
                    type = "Dasar",
                    agePlant = "0 Hari",
                    urea = "50 Ton/Ha",
                    tsp = "50 Ton/Ha",
                    kcl = "50 Ton/Ha",
                    organicFertilizer = "Pupuk kompos atau pupuk kandang, 2-3 ton/ha, ditambahkan ke tanah sebelum tanam untuk meningkatkan kesuburan tanah."
                ),
                Fertilization(
                    plantsId = 4,
                    type = "Susulan",
                    agePlant = "15-20 Hari",
                    urea = "25 Ton/Ha",
                    tsp = "25 Ton/Ha",
                    kcl = "25 Ton/Ha",
                    organicFertilizer = null
                ),
                Fertilization(
                    plantsId = 4,
                    type = "Susulan",
                    agePlant = "30-35 Hari",
                    urea = "25 Ton/Ha",
                    tsp = "0",
                    kcl = "25 Ton/Ha",
                    organicFertilizer = null
                )
            )
            plantDao.insertFertilization(kacangMerahFertilization)

            val kacangMerahVarieties = listOf(
                Varieties(
                    plantsId = 4,
                    name = "Merah Local",
                    superiority = "Adaptasi baik di berbagai kondisi tanah, hasil panen stabil.",
                    weakness = "Rentan terhadap penyakit jamur pada kondisi lembab."
                ),
                Varieties(
                    plantsId = 4,
                    name = "Prima Agrihorti",
                    superiority = "Produktivitas tinggi, tahan terhadap penyakit, masa panen lebih cepat.",
                    weakness = "Memerlukan perhatian ekstra pada kelembapan tanah."
                ),
                Varieties(
                    plantsId = 4,
                    name = "Gajah",
                    superiority = "Kualitas biji sangat baik, toleran terhadap penyakit.",
                    weakness = "Memerlukan kondisi tanah yang sangat subur dan perawatan yang konsisten."
                )
            )
            plantDao.insertVarieties(kacangMerahVarieties)

            val kacangMerahPests = listOf(
                Pests(
                    plantsId = 4,
                    name = "Ulat Kupu-Kupu",
                    chemicalControl = "Cypermethrin (Kandungan: Cypermethrin) - Insektisida untuk mengendalikan ulat dengan cara mengganggu sistem saraf hama.",
                    biologicalControl = "Campuran air dan ekstrak bawang putih - Mengandung senyawa sulfur yang dapat mengusir hama."
                ),
                Pests(
                    plantsId = 4,
                    name = "Kutu Daun",
                    chemicalControl = "Gunakan Imidacloprid (Kandungan: Imidacloprid) - Insektisida sistemik untuk mengendalikan kutu daun.",
                    biologicalControl = "Campuran air dengan sabun cuci piring - Mengurangi populasi kutu daun dengan mematikan mereka secara fisik."
                ),
                Pests(
                    plantsId = 4,
                    name = "Kumbang Daun",
                    chemicalControl = "Gunakan Metomil (Kandungan: Metomil) - Insektisida untuk mengendalikan kumbang daun.",
                    biologicalControl = "Campuran air dengan ekstrak daun tomat - Mengusir kumbang daun secara alami."
                )
            )
            plantDao.insertPests(kacangMerahPests)

            val kacangMerahDiseases = listOf(
                Diseases(
                    plantsId = 4,
                    name = "Busuk Akar",
                    control = "Gunakan benih sehat, rotasi tanaman, dan aplikasikan fungisida berbasis Mancozeb. Pastikan drainase tanah baik untuk mencegah akumulasi air."
                ),
                Diseases(
                    plantsId = 4,
                    name = "Jamur Bercak Daun",
                    control = "Aplikasikan fungisida berbasis Copper Oxychloride dan pastikan ventilasi tanaman baik untuk mencegah penyebaran jamur."
                ),
                Diseases(
                    plantsId = 4,
                    name = "Penyakit Layu Fusarium",
                    control = "Gunakan varietas tahan terhadap Fusarium, rotasi tanaman dengan tanaman non-anggota keluarga kacang-kacangan, dan aplikasikan fungisida berbasis Propiconazole."
                )
            )
            plantDao.insertDiseases(kacangMerahDiseases)

            val kacangMerahWeeds = Weeds(
                plantsId = 4,
                manualWeeding = "Penyiangan manual setiap 2-3 minggu untuk menghindari kompetisi dengan kacang merah. Cabut gulma dengan tangan atau gunakan alat penyiang.",
                herbicide = "Gunakan herbisida Glyphosate - Digunakan sebelum penanaman untuk mengendalikan gulma awal dengan cara mematikan tanaman gulma."
            )
            plantDao.insertWeeds(kacangMerahWeeds)

            val kacangMerahPlantings = listOf(
                Plantings(
                    plantsId = 4,
                    preparing = "Siapkan lahan dengan pengolahan tanah yang baik, buat bedengan atau lahan tanam dengan jarak antar baris 30-40 cm.",
                    planting = "Tanam benih pada kedalaman 2-3 cm dengan jarak antar benih 10-15 cm dalam barisan. Siram tanah setelah penanaman untuk memastikan benih mendapatkan kelembapan yang cukup."
                )
            )
            plantDao.insertPlantings(kacangMerahPlantings)

            val kacangGudeLandPreparations = LandPreparations(
                plantsId = 5,
                processing = "Pilih lahan dengan pH tanah 6,0-7,0 dan tanah yang subur, gembur, serta memiliki drainase yang baik. Olah tanah dengan membajak atau mencangkul hingga kedalaman 20-30 cm, dan campurkan pupuk organik seperti kompos atau pupuk kandang untuk meningkatkan kesuburan tanah. Persiapkan bedengan atau lahan tanam dengan jarak antar baris 30-40 cm.",
                irrigation = "Kacang gude memerlukan kelembapan tanah yang konsisten terutama pada masa awal pertumbuhan. Gunakan sistem irigasi tetes atau siram tanah secara rutin setiap 2-3 hari, terutama pada masa vegetatif. Hindari genangan air yang dapat menyebabkan penyakit akar."
            )
            plantDao.insertLandPreparations(kacangGudeLandPreparations)

            val kacangGudeNurseries = Nurseries(
                plantsId = 5,
                seedSelection = "Pilih benih kacang gude varietas unggul yang bebas dari penyakit, memiliki daya tumbuh tinggi, dan berasal dari sumber terpercaya. Pilih benih yang berkualitas baik dan belum kadaluarsa.",
                soaking = "Rendam benih dalam air selama 8-12 jam sebelum tanam untuk meningkatkan daya germinasi.",
                seeding = "Tanam benih langsung di lahan tanam atau semai di media tanam. Jika disemai, pindahkan bibit ke lahan setelah bibit memiliki 2-3 daun sejati."
            )
            plantDao.insertNurseries(kacangGudeNurseries)

            val kacangGudeFertilization = listOf(
                Fertilization(
                    plantsId = 5,
                    type = "Dasar",
                    agePlant = "0 Hari",
                    urea = "50 Ton/Ha",
                    tsp = "50 Ton/Ha",
                    kcl = "50 Ton/Ha",
                    organicFertilizer = "Pupuk kompos atau pupuk kandang, 2-3 ton/ha, ditambahkan ke tanah sebelum tanam untuk meningkatkan kesuburan tanah."
                ),
                Fertilization(
                    plantsId = 5,
                    type = "Susulan",
                    agePlant = "15-20 Hari",
                    urea = "25 Ton/Ha",
                    tsp = "25 Ton/Ha",
                    kcl = "25 Ton/Ha",
                    organicFertilizer = null
                ),
                Fertilization(
                    plantsId = 5,
                    type = "Susulan",
                    agePlant = "30-35 Hari (opsional)",
                    urea = "25 Ton/Ha",
                    tsp = "0",
                    kcl = "25 Ton/Ha",
                    organicFertilizer = null
                )
            )
            plantDao.insertFertilization(kacangGudeFertilization)

            val kacangGudeVarieties = listOf(
                Varieties(
                    plantsId = 5,
                    name = "Kacang Gude Lokal",
                    superiority = "Adaptasi baik di berbagai kondisi tanah, hasil panen stabil.",
                    weakness = "Rentan terhadap penyakit jamur pada kondisi lembab."
                ),
                Varieties(
                    plantsId = 5,
                    name = "Kacang Gude Super",
                    superiority = "Produktivitas tinggi, hasil panen lebih cepat dibandingkan varietas lokal.",
                    weakness = "Memerlukan perhatian ekstra pada kelembapan tanah dan perawatan intensif."
                ),
                Varieties(
                    plantsId = 5,
                    name = "Kacang Gude Hibrida",
                    superiority = "Hasil panen sangat tinggi, tahan terhadap hama dan penyakit, dan cocok untuk sistem trellis.",
                    weakness = "Memerlukan perawatan intensif dan trellis yang stabil."
                )
            )
            plantDao.insertVarieties(kacangGudeVarieties)

            val kacangGudePests = listOf(
                Pests(
                    plantsId = 5,
                    name = "Ulat Kupu-Kupu",
                    chemicalControl = "Cypermethrin (Kandungan: Cypermethrin) - Insektisida untuk mengendalikan ulat dengan cara mengganggu sistem saraf hama.",
                    biologicalControl = "Campuran air dan ekstrak bawang putih - Mengandung senyawa sulfur yang dapat mengusir hama."
                ),
                Pests(
                    plantsId = 5,
                    name = "Kutu Daun",
                    chemicalControl = "Gunakan Imidacloprid (Kandungan: Imidacloprid) - Insektisida sistemik untuk mengendalikan kutu daun.",
                    biologicalControl = "Campuran air dengan sabun cuci piring - Mengurangi populasi kutu daun dengan mematikan mereka secara fisik."
                ),
                Pests(
                    plantsId = 5,
                    name = "Penggerek Kacang",
                    chemicalControl = "Gunakan Chlorpyrifos (Kandungan: Chlorpyrifos) - Insektisida untuk mengendalikan penggerek dengan menghambat fungsi saraf hama.",
                    biologicalControl = "Larutan minyak neem dan air - Mengandung azadirachtin yang efektif dalam mengendalikan penggerek."
                ),
                Pests(
                    plantsId = 5,
                    name = "Kumbang Daun",
                    chemicalControl = "Metomil (Kandungan: Metomil) - Insektisida untuk mengendalikan kumbang daun.",
                    biologicalControl = "Campuran air dengan ekstrak daun tomat - Mengusir kumbang daun secara alami."
                )
            )
            plantDao.insertPests(kacangGudePests)

            val kacangGudeDiseases = listOf(
                Diseases(
                    plantsId = 5,
                    name = "Penyakit Busuk Akar",
                    control = "Gunakan benih sehat, rotasi tanaman, dan aplikasikan fungisida berbasis Mancozeb. Pastikan drainase tanah baik untuk mencegah akumulasi air."
                ),
                Diseases(
                    plantsId = 5,
                    name = "Penyakit Jamur Bercak Daun",
                    control = "Aplikasikan fungisida berbasis Copper Oxychloride dan pastikan ventilasi tanaman baik untuk mencegah penyebaran jamur."
                ),
                Diseases(
                    plantsId = 5,
                    name = "Layu Fusarium",
                    control = "Gunakan varietas tahan terhadap Fusarium, rotasi tanaman dengan tanaman non-anggota keluarga kacang-kacangan, dan aplikasikan fungisida berbasis Propiconazole."
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
                    preparing = "Penyiangan manual setiap 2-3 minggu untuk menghindari kompetisi dengan kacang gude. Cabut gulma dengan tangan atau gunakan alat penyiang.",
                    planting = "Glyphosate - Digunakan sebelum penanaman untuk mengendalikan gulma awal dengan cara mematikan tanaman gulma."
                )
            )
            plantDao.insertPlantings(kacangGudePlantings)

            val kacangKudaLandPreparations = LandPreparations(
                plantsId = 6,
                processing = "Pilih lahan dengan pH tanah 5,5-7,0 dan tanah yang subur, gembur, serta memiliki drainase yang baik. Olah tanah dengan membajak atau mencangkul hingga kedalaman 20-30 cm, campurkan pupuk organik seperti kompos atau pupuk kandang untuk meningkatkan kesuburan tanah. Persiapkan bedengan atau lahan tanam dengan jarak antar baris 30-40 cm.",
                irrigation = "Kacang matik memerlukan kelembapan tanah yang konsisten, terutama pada masa awal pertumbuhan. Gunakan sistem irigasi tetes atau siram tanah secara rutin setiap 2-3 hari, terutama pada masa vegetatif. Hindari genangan air di sekitar tanaman."
            )
            plantDao.insertLandPreparations(kacangKudaLandPreparations)

            val kacangKudaNurseries = Nurseries(
                plantsId = 6,
                seedSelection = "Pilih benih kacang matik varietas unggul yang bebas dari penyakit, memiliki daya tumbuh tinggi, dan berasal dari sumber terpercaya. Pilih benih yang berkualitas baik dan belum kadaluarsa.",
                soaking = "Rendam benih dalam air selama 8-12 jam sebelum tanam untuk meningkatkan daya germinasi.",
                seeding = "Tanam benih langsung di lahan tanam atau semai di media tanam. Jika disemai, pindahkan bibit ke lahan setelah bibit memiliki 2-3 daun sejati."
            )

            plantDao.insertNurseries(kacangKudaNurseries)

            val kacangKudaFertilization = listOf(
                Fertilization(
                    plantsId = 6,
                    type = "Dasar",
                    agePlant = "0 Hari",
                    urea = "50 Ton/Ha",
                    tsp = "50 Ton/Ha",
                    kcl = "50 Ton/Ha",
                    organicFertilizer = "Pupuk kompos atau pupuk kandang, 2-3 ton/ha, dicampurkan ke tanah sebelum tanam untuk meningkatkan kesuburan tanah."
                ),
                Fertilization(
                    plantsId = 6,
                    type = "Susulan",
                    agePlant = "15-20 Hari",
                    urea = "25 Ton/Ha",
                    tsp = "25  Ton/Ha",
                    kcl = "25 Ton/Ha",
                    organicFertilizer = null
                ),
                Fertilization(
                    plantsId = 6,
                    type = "Susulan",
                    agePlant = "30-35 Hari (opsional)",
                    urea = "25 Ton/Ha",
                    tsp = "0",
                    kcl = "25 Ton/Ha",
                    organicFertilizer = null
                )
            )
            plantDao.insertFertilization(kacangKudaFertilization)

            val kacangKudaVarieties = listOf(
                Varieties(
                    plantsId = 6,
                    name = "Kacang matik Lokal",
                    superiority = "Adaptasi baik di berbagai kondisi tanah, hasil panen stabil.",
                    weakness = "Rentan terhadap penyakit jamur pada kondisi lembab."
                ),
                Varieties(
                    plantsId = 6,
                    name = "Kacang matik Super",
                    superiority = "Produktivitas tinggi, hasil panen lebih cepat dibandingkan varietas lokal.",
                    weakness = "Memerlukan perhatian ekstra pada kelembapan tanah dan perawatan intensif."
                ),
                Varieties(
                    plantsId = 6,
                    name = "Kacang matik Hibrida",
                    superiority = "Hasil panen sangat tinggi, tahan terhadap hama dan penyakit, dan cocok untuk sistem trellis.",
                    weakness = "Memerlukan perawatan intensif dan trellis yang stabil."
                ),
            )
            plantDao.insertVarieties(kacangKudaVarieties)

            val kacangKudaPests = listOf(
                Pests(
                    plantsId = 6,
                    name = "Ulat Kupu-Kupu",
                    chemicalControl = "Cypermethrin (Kandungan: Cypermethrin) - Insektisida untuk mengendalikan ulat dengan cara mengganggu sistem saraf hama.",
                    biologicalControl = "Campuran air dan ekstrak bawang putih - Mengandung senyawa sulfur yang dapat mengusir hama."
                ),
                Pests(
                    plantsId = 6,
                    name = "Kutu Daun",
                    chemicalControl = "Gunakan Imidacloprid (Kandungan: Imidacloprid) - Insektisida sistemik untuk mengendalikan kutu daun.",
                    biologicalControl = "Campuran air dengan sabun cuci piring - Mengurangi populasi kutu daun dengan mematikan mereka secara fisik."
                ),
                Pests(
                    plantsId = 6,
                    name = "Penggerek Kacang",
                    chemicalControl = "Gunakan Chlorpyrifos (Kandungan: Chlorpyrifos) - Insektisida untuk mengendalikan penggerek dengan menghambat fungsi saraf hama.",
                    biologicalControl = "Larutan minyak neem dan air - Mengandung azadirachtin yang efektif dalam mengendalikan penggerek."
                ),
                Pests(
                    plantsId = 6,
                    name = "Kumbang Daun",
                    chemicalControl = "Metomil (Kandungan: Metomil) - Insektisida untuk mengendalikan kumbang daun.",
                    biologicalControl = "Campuran air dengan ekstrak daun tomat - Mengusir kumbang daun secara alami."
                )
            )
            plantDao.insertPests(kacangKudaPests)

            val kacangKudaDiseases = listOf(
                Diseases(
                    plantsId = 6,
                    name = "Antraknosa",
                    control = "Aplikasikan fungisida berbasis Copper Oxychloride dan pastikan ventilasi tanaman baik untuk mencegah penyebaran jamur."
                ),
                Diseases(
                    plantsId = 6,
                    name = "Busuk Batang Fusarium",
                    control = "Gunakan varietas tahan terhadap Fusarium, rotasi tanaman dengan tanaman non-anggota keluarga kacang-kacangan, dan aplikasikan fungisida berbasis Propiconazole."
                )
            )
            plantDao.insertDiseases(kacangKudaDiseases)

            val kacangKudaWeeds = Weeds(
                plantsId = 6,
                manualWeeding = "Penyiangan manual setiap 2-3 minggu untuk menghindari kompetisi dengan kacang matik. Cabut gulma dengan tangan atau gunakan alat penyiang.",
                herbicide = "Glyphosate - Digunakan sebelum penanaman untuk mengendalikan gulma awal dengan cara mematikan tanaman gulma."
            )
            plantDao.insertWeeds(kacangKudaWeeds)

            val kacangKudaPlantings = listOf(
                Plantings(
                    plantsId = 6,
                    preparing = "Siapkan lahan dengan pengolahan tanah yang baik, buat bedengan atau lahan tanam dengan jarak antar baris 30-40 cm..",
                    planting = "Tanam benih pada kedalaman 2-3 cm dengan jarak antar benih 10-15 cm dalam barisan. Siram tanah setelah penanaman untuk memastikan benih mendapatkan kelembapan yang cukup."
                )
            )
            plantDao.insertPlantings(kacangKudaPlantings)

            val kacangHijauLandPreparations = LandPreparations(
                plantsId = 7,
                processing = "Persiapkan tanah dengan membajak atau menggemburkan tanah hingga kedalaman 15–20 cm. Cek pH tanah, idealnya pH tanah untuk kacang hijau adalah 5,5–7. Jika tanah terlalu asam, tambahkan kapur pertanian.",
                irrigation = "Buat bedengan atau alur tanam untuk mengatur saluran air. Kacang hijau memerlukan kelembapan tanah yang cukup, tetapi tidak boleh tergenang air."
            )

            plantDao.insertLandPreparations(kacangHijauLandPreparations)

            val kacangHijauNurseries = Nurseries(
                plantsId = 7,
                seedSelection = "Pilih benih kacang hijau yang bersertifikat dan bebas dari penyakit. Benih harus bersih dan memiliki daya berkecambah yang tinggi.",
                soaking = "Rendam benih dalam air selama 6–12 jam sebelum disemai untuk meningkatkan daya tumbuh.",
                seeding = "Tanam benih langsung di bedengan atau lahan tanam dengan kedalaman 2–3 cm dan jarak tanam 30x10 cm untuk memberikan ruang yang cukup bagi tanaman."
            )

            plantDao.insertNurseries(kacangHijauNurseries)

            val kacangHijauFertilization = listOf(
                Fertilization(
                    plantsId = 7,
                    type = "Dasar",
                    agePlant = "0 Hari",
                    urea = "100 Ton/Ha",
                    tsp = "100 Ton/Ha",
                    kcl = "50 Ton/Ha",
                    organicFertilizer = "Tambahkan kompos atau pupuk kandang matang sebanyak 5–10 ton/ha untuk meningkatkan kesuburan tanah.\n"
                ),
                Fertilization(
                    plantsId = 7,
                    type = "Susulan",
                    agePlant = "15-20 Hari",
                    urea = "25 Ton/Ha",
                    tsp = "25 Ton/Ha",
                    kcl = "25 Ton/Ha",
                    organicFertilizer = null
                ),
                Fertilization(
                    plantsId = 7,
                    type = "Susulan",
                    agePlant = "30-35 Hari (opsional)",
                    urea = "25  Ton/Ha",
                    tsp = "0",
                    kcl = "25  Ton/Ha",
                    organicFertilizer = null
                )
            )

            plantDao.insertFertilization(kacangHijauFertilization)

            val kacangHijauVarieties = listOf(
                Varieties(
                    plantsId = 7,
                    name = "Kencana",
                    superiority = "Produktivitas tinggi, tahan terhadap penyakit utama.",
                    weakness = "Rentan terhadap kekeringan."
                ),
                Varieties(
                    plantsId = 7,
                    name = "Vima 1",
                    superiority = "Tahan terhadap hama dan penyakit, hasil tinggi.",
                    weakness = "Memerlukan perawatan ekstra untuk kelembapan tanah."
                ),
                Varieties(
                    plantsId = 7,
                    name = "Tropical",
                    superiority = "Adaptasi baik di berbagai iklim, hasil tinggi.",
                    weakness = "Memerlukan pemupukan yang lebih sering."
                )
            )
            plantDao.insertVarieties(kacangHijauVarieties)

            val kacangHijauPests = listOf(
                Pests(
                    plantsId = 7,
                    name = "Kutu Daun (Aphid)",
                    chemicalControl = "Insektisida berbahan aktif imidacloprid atau cypermethrin.",
                    biologicalControl = "Semprotkan ekstrak bawang putih atau sabun insektisida untuk mengendalikan kutu daun."
                ),
                Pests(
                    plantsId = 7,
                    name = "Ulat Grayak (Spodoptera litura)",
                    chemicalControl = "Insektisida berbahan aktif chlorantraniliprole atau lambda-cyhalothrin.",
                    biologicalControl = "Gunakan pestisida nabati seperti ekstrak daun mimba."
                ),
                Pests(
                    plantsId = 7,
                    name = "Penggerek Polong (Callosobruchus maculatus)",
                    chemicalControl = "Gunakan insektisida berbahan aktif deltamethrin.",
                    biologicalControl = "Menyimpan benih di tempat yang kering dan dingin."
                )
            )
            plantDao.insertPests(kacangHijauPests)

            val kacangHijauDiseases = listOf(
                Diseases(
                    plantsId = 7,
                    name = "Busuk Batang (Fusarium wilt)",
                    control = "Gunakan benih yang sehat, rotasi tanaman, dan perbaiki drainase tanah."
                ),
                Diseases(
                    plantsId = 7,
                    name = "Jamur Tepung (Powdery Mildew)",
                    control = "Gunakan fungisida berbahan aktif sulfur atau tepung kapur."
                ),
                Diseases(
                    plantsId = 7,
                    name = "Penyakit Kerdil (Bean Pod Mottle Virus)",
                    control = "Hancurkan tanaman yang terinfeksi dan rotasi tanaman dengan tanaman non-kacang hijau."
                )
            )
            plantDao.insertDiseases(kacangHijauDiseases)

            val kacangHijauWeeds = Weeds(
                plantsId = 7,
                manualWeeding = "Lakukan penyiangan secara rutin untuk menghilangkan gulma di sekitar tanaman.",
                herbicide = "Gunakan herbisida berbahan aktif glyphosate untuk gulma sebelum tanam atau di awal pertumbuhan kacang hijau."
            )
            plantDao.insertWeeds(kacangHijauWeeds)

            val kacangHijauPlantings = listOf(
                Plantings(
                    plantsId = 7,
                    preparing = "Siapkan lahan dengan baik, pastikan tanah dalam kondisi gembur dan pH optimal.",
                    planting = "Tanam benih dengan kedalaman 2–3 cm pada jarak tanam 30x10 cm, tutup dengan tanah dan siram untuk menjaga kelembapan."
                )
            )
            plantDao.insertPlantings(kacangHijauPlantings)

            val kacangHitamLandPreparations = LandPreparations(
                plantsId = 8,
                processing = "Persiapkan lahan dengan membersihkan gulma dan sampah. Olah tanah dengan cangkul atau bajak untuk menggemburkan tanah dan membuat bedengan setinggi 20-30 cm.",
                irrigation = "Pastikan sistem drainase baik untuk mencegah genangan air. Berikan irigasi secara berkala untuk menjaga kelembapan tanah, terutama pada fase awal pertumbuhan."
            )
            plantDao.insertLandPreparations(kacangHitamLandPreparations)

            val kacangHitamNurseries = Nurseries(
                plantsId = 8,
                seedSelection = "Pilih benih kacang hitam yang sehat, bebas penyakit, dan memiliki daya berkecambah tinggi.",
                soaking = "Rendam benih dalam air selama 6-8 jam untuk mempercepat perkecambahan.",
                seeding = "Tanam benih dalam polybag atau langsung di bedengan dengan kedalaman 3-5 cm dan jarak tanam 20-30 cm antar tanaman."
            )
            plantDao.insertNurseries(kacangHitamNurseries)

            val kacangHitamFertilization = listOf(
                Fertilization(
                    plantsId = 8,
                    type = "Dasar",
                    agePlant = "0 Hari",
                    urea = "100 Ton/Ha",
                    tsp = "100 Ton/Ha",
                    kcl = "50 Ton/Ha",
                    organicFertilizer = "Gunakan Kompos atau pupuk kandang 5-10 ton/ha dicampurkan dengan tanah."
                ),
                Fertilization(
                    plantsId = 8,
                    type = "Susulan",
                    agePlant = "30 Hari",
                    urea = "50 Ton/Ha",
                    tsp = "0",
                    kcl = "25 Ton/Ha",
                    organicFertilizer = "Pupuk kompos atau pupuk organik cair 2-3 liter/ha."
                ),
            )

            plantDao.insertFertilization(kacangHitamFertilization)

            val kacangHitamVarieties = listOf(
                Varieties(
                    plantsId = 8,
                    name = "Black Turtle",
                    superiority = "Tahan penyakit, hasil tinggi.",
                    weakness = "Memerlukan suhu yang stabil."
                ),
                Varieties(
                    plantsId = 8,
                    name = "Black Kidney",
                    superiority = "Kaya protein, cocok untuk konsumsi.",
                    weakness = "Rentan terhadap serangan hama."
                ),
                Varieties(
                    plantsId = 8,
                    name = "Black Soybean",
                    superiority = "Nutrisi tinggi, tahan kekeringan.",
                    weakness = "Memerlukan perlakuan benih khusus."
                )
            )
            plantDao.insertVarieties(kacangHitamVarieties)

            val kacangHitamPests = listOf(
                Pests(
                    plantsId = 8,
                    name = "Kutu Daun",
                    chemicalControl = "Insektisida yang mengandung imidacloprid.",
                    biologicalControl = "Semprotan campuran air dan sabun cuci piring untuk membasmi kutu."
                ),
                Pests(
                    plantsId = 8,
                    name = "Penggerek Kacang",
                    chemicalControl = "Insektisida yang mengandung cypermethrin.",
                    biologicalControl = "Pemberian nematoda entomopatogen untuk mengendalikan larva penggerek."
                ),
                Pests(
                    plantsId = 8,
                    name = "Lalat Kacang",
                    chemicalControl = "Insektisida dengan bahan aktif diazinon.",
                    biologicalControl = "Feromon pengacau kawanan untuk menjerat lalat dewasa."
                )
            )
            plantDao.insertPests(kacangHitamPests)

            val kacangHitamDiseases = listOf(
                Diseases(
                    plantsId = 8,
                    name = "Penyakit Busuk Akar",
                    control = "Gunakan fungisida berbahan aktif metalaxyl, pastikan drainase lahan baik."
                ),
                Diseases(
                    plantsId = 8,
                    name = "Penyakit Jamur Kacang",
                    control = "Gunakan fungisida berbahan aktif mancozeb atau tembaga. Rotasi tanaman dengan legum lainnya untuk menghindari penularan penyakit."
                )
            )
            plantDao.insertDiseases(kacangHitamDiseases)

            val kacangHitamWeeds = Weeds(
                plantsId = 8,
                manualWeeding = " Penyiangan secara rutin untuk menghilangkan gulma yang bersaing dengan tanaman kacang hitam.",
                herbicide = "Herbisida berbahan aktif glifosat untuk gulma yang telah tumbuh."
            )
            plantDao.insertWeeds(kacangHitamWeeds)

            val kacangHitamPlantings = listOf(
                Plantings(
                    plantsId = 8,
                    preparing = "Olah tanah dengan benar dan pastikan kelembapan tanah terjaga. Siapkan bibit yang sudah direndam.",
                    planting = "Tanam benih pada kedalaman 3-5 cm dengan jarak tanam 20-30 cm. Setelah tanam, siram dengan air secukupnya."
                )
            )
            plantDao.insertPlantings(kacangHitamPlantings)

            val lentisLandPreparations = LandPreparations(
                plantsId = 9,
                processing = "Persiapkan lahan dengan membersihkan gulma dan sampah. Olah tanah dengan cangkul atau bajak untuk menggemburkan tanah dan membuat bedengan setinggi 15-20 cm. Tambahkan bahan organik seperti kompos atau pupuk kandang untuk meningkatkan kesuburan tanah.",
                irrigation = "Pastikan lahan memiliki sistem drainase yang baik. Irigasi secara berkala untuk menjaga kelembapan tanah, terutama selama fase awal pertumbuhan. Hindari genangan air yang bisa menyebabkan penyakit akar."
            )
            plantDao.insertLandPreparations(lentisLandPreparations)

            val lentisNurseries = Nurseries(
                plantsId = 9,
                seedSelection = "Pilih benih kacang lentil yang sehat, bebas penyakit, dan memiliki daya berkecambah tinggi. ",
                soaking = "Rendam benih dalam air selama 6-8 jam untuk mempercepat perkecambahan.",
                seeding = "Tanam benih di bedengan dengan kedalaman 2-3 cm dan jarak tanam 10-15 cm antar tanaman."
            )

            plantDao.insertNurseries(lentisNurseries)

            val lentisFertilization = listOf(
                Fertilization(
                    plantsId = 9,
                    type = "Dasar",
                    agePlant = "0 Hari",
                    urea = "50 Ton/Ha",
                    tsp = "100 Ton/Ha",
                    kcl = "50 Ton/Ha",
                    organicFertilizer = "Gunakan Kompos atau pupuk kandang 5-7 ton/ha dicampurkan dengan tanah."
                ),
                Fertilization(
                    plantsId = 9,
                    type = "Susulan",
                    agePlant = "30 Hari",
                    urea = "50 Ton/Ha",
                    tsp = "0",
                    kcl = "0",
                    organicFertilizer = "Pupuk kompos atau pupuk organik cair 2-3 liter/ha."
                )
            )
            plantDao.insertFertilization(lentisFertilization)

            val lentisVarieties = listOf(
                Varieties(
                    plantsId = 9,
                    name = "Lentil Red Chief",
                    superiority = "Hasil tinggi, cocok untuk kondisi kering.",
                    weakness = "Memerlukan suhu dingin untuk hasil optimal."
                ),
                Varieties(
                    plantsId = 9,
                    name = "Lentil PBA Bolt",
                    superiority = "Tahan penyakit, hasil tinggi.",
                    weakness = "Memerlukan perlakuan tanah yang baik."
                ),
                Varieties(
                    plantsId = 9,
                    name = "Lentil DPL 62",
                    superiority = "Tahan terhadap kekeringan, hasil baik.",
                    weakness = "Rentan terhadap serangan hama."
                )
            )
            plantDao.insertVarieties(lentisVarieties)

            val lentisPests = listOf(
                Pests(
                    plantsId = 9,
                    name = "Kutu Daun",
                    chemicalControl = "Insektisida yang mengandung imidacloprid.",
                    biologicalControl = "Semprotan campuran air dan sabun cuci piring untuk mengendalikan kutu."
                ),
                Pests(
                    plantsId = 9,
                    name = "Penggerek Biji",
                    chemicalControl = "Gunakan Insektisida dengan bahan aktif deltamethrin.",
                    biologicalControl = "Gunakan insektisida alami seperti ekstrak neem untuk mengendalikan penggerek."
                ),
                Pests(
                    plantsId = 9,
                    name = "Kumbang",
                    chemicalControl = "Insektisida dengan bahan aktif chlorpyrifos.",
                    biologicalControl = "Pemberian nematoda entomopatogen untuk mengendalikan kumbang dewasa."
                )
            )
            plantDao.insertPests(lentisPests)

            val lentisDiseases = listOf(
                Diseases(
                    plantsId = 9,
                    name = "Penyakit Busuk Akar",
                    control = "Gunakan fungisida berbahan aktif metalaxyl, pastikan drainase tanah baik."
                ),
                Diseases(
                    plantsId = 9,
                    name = "Penyakit Jamur",
                    control = "Gunakan fungisida berbahan aktif mancozeb atau tembaga. Rotasi tanaman dengan legum lainnya untuk mencegah penyebaran penyakit."
                )
            )
            plantDao.insertDiseases(lentisDiseases)

            val lentisWeeds = Weeds(
                plantsId = 9,
                manualWeeding = "Penyiangan secara rutin untuk menghilangkan gulma yang bersaing dengan tanaman lentil.",
                herbicide = "Herbisida berbahan aktif glifosat untuk gulma yang telah tumbuh, digunakan dengan hati-hati agar tidak merusak tanaman lentil."
            )
            plantDao.insertWeeds(lentisWeeds)

            val lentisPlantings = listOf(
                Plantings(
                    plantsId = 9,
                    preparing = "Olah tanah dengan benar dan pastikan kelembapan tanah terjaga. Siapkan bibit yang sudah direndam.",
                    planting = "Tanam benih pada kedalaman 2-3 cm dengan jarak tanam 10-15 cm. Setelah tanam, siram dengan air secukupnya."
                )
            )
            plantDao.insertPlantings(lentisPlantings)

            val delimaLandPreparations = LandPreparations(
                plantsId = 10,
                processing = "Pilih lokasi dengan sinar matahari penuh dan tanah yang memiliki drainase baik. Gemburkan tanah dengan kedalaman 30-40 cm dan campurkan dengan kompos untuk meningkatkan kesuburan.",
                irrigation = "Lakukan penyiraman rutin, terutama selama musim kemarau. Tanaman delima membutuhkan tanah yang lembab tetapi tidak tergenang air."
            )
            plantDao.insertLandPreparations(delimaLandPreparations)

            val delimaNurseries = Nurseries(
                plantsId = 10,
                seedSelection = "Pilih benih dari varietas delima yang berkualitas, bebas dari penyakit, dan berasal dari sumber yang terpercaya.",
                soaking = "Tidak Wajib, tetapi bisa dilakukan untuk meningkatkan daya berkecambah benih. Jika dilakukan: Rendam benih dalam air hangat selama 24 jam untuk mempercepat proses perkecambahan.",
                seeding = "Tidak Wajib, tetapi membantu benih tumbuh dengan baik. Jika dilakukan: Semai benih di media tanam yang gembur dan berdrainase baik, seperti campuran tanah dan kompos dengan perbandingan 1:1. Jaga kelembaban media tanam hingga benih berkecambah."
            )
            plantDao.insertNurseries(delimaNurseries)

            val delimaFertilization = listOf(
                Fertilization(
                    plantsId = 10,
                    type = "Dasar",
                    agePlant = "30 hari",
                    urea = "10  Ton/Ha",
                    tsp = "10 Ton/Ha",
                    kcl = "5 Ton/Ha",
                    organicFertilizer = "Kompos atau pupuk kandang (beri 1-2 cm di sekitar pangkal tanaman)"
                ),
                Fertilization(
                    plantsId = 10,
                    type = "Susulan",
                    agePlant = "60 hari",
                    urea = "15 Ton/Ha",
                    tsp = "15 Ton/Ha",
                    kcl = "10 Ton/Ha",
                    organicFertilizer = null
                ),
                Fertilization(
                    plantsId = 10,
                    type = "Susulan",
                    agePlant = "90 hari dan seterusnya (opsional)",
                    urea = "10 Ton/Ha",
                    tsp = "10 Ton/Ha",
                    kcl = "5 Ton/Ha",
                    organicFertilizer = "Kompos (beri 1-2 cm di sekitar pangkal tanaman)"
                )
            )
            plantDao.insertFertilization(delimaFertilization)

            val delimaVarieties = listOf(
                Varieties(
                    plantsId = 10,
                    name = "Wonderful",
                    superiority = "Buah besar, rasa manis, produktivitas tinggi.",
                    weakness = "Memerlukan perawatan intensif."
                ),
                Varieties(
                    plantsId = 10,
                    name = "Angel Red",
                    superiority = "Buah manis, warna buah cerah.",
                    weakness = "Rentan terhadap penyakit jamur."
                ),
                Varieties(
                    plantsId = 10,
                    name = "Bale Fruit",
                    superiority = "Buah sangat manis, tahan terhadap berbagai kondisi cuaca.",
                    weakness = "Tanaman membutuhkan perlindungan dari angin kencang."
                )
            )
            plantDao.insertVarieties(delimaVarieties)

            val delimaPests = listOf(
                Pests(
                    plantsId = 10,
                    name = "Kutu Daun",
                    chemicalControl = "Insektisida berbahan aktif Imidacloprid",
                    biologicalControl = "Semprot dengan campuran sabun cuci dan air (sabun membantu menempel pada hama dan membunuh kutu daun)."
                ),
                Pests(
                    plantsId = 10,
                    name = "Ulat Penggerek Buah",
                    chemicalControl = "Insektisida berbahan aktif Chlorantraniliprole",
                    biologicalControl = "Gunakan neem oil (minyak nimba) untuk mencegah serangan ulat."
                ),
                Pests(
                    plantsId = 10,
                    name = "Penggerek Batang",
                    chemicalControl = "Insektisida berbahan aktif Cypermethrin",
                    biologicalControl = "Gunakan ekstrak bawang putih atau cabai (memiliki efek repellent terhadap penggerek batang)."
                )
            )
            plantDao.insertPests(delimaPests)

            val delimaDiseases = listOf(
                Diseases(
                    plantsId = 10,
                    name = "Jamur Akar Putih",
                    control = "Gunakan fungisida berbahan aktif Metalaxyl atau Fosetyl-Al. Jaga kelembaban tanah dan pastikan drainase baik."
                ),
                Diseases(
                    plantsId = 10,
                    name = "Penyakit Bercak Daun",
                    control = "Semprot dengan fungisida berbahan aktif Mancozeb. Jaga jarak tanam yang cukup untuk ventilasi yang baik."
                )
            )
            plantDao.insertDiseases(delimaDiseases)

            val delimaWeeds = Weeds(
                plantsId = 10,
                manualWeeding = "Jangka gulma secara rutin dengan cara mencabut atau membersihkan area sekitar tanaman.",
                herbicide = "Gunakan herbisida berbahan aktif Glyphosate sebelum tanaman delima tumbuh atau jika sudah ada gulma, gunakan herbisida selektif."
            )
            plantDao.insertWeeds(delimaWeeds)

            val delimaPlantings = listOf(
                Plantings(
                    plantsId = 10,
                    preparing = "Gali lubang tanam dengan ukuran 30x30x30 cm. Campurkan tanah dengan kompos sebelum menanam.",
                    planting = "Tanam bibit delima pada kedalaman yang sama dengan tinggi bibit di dalam pot atau media semai. Siram hingga tanah lembab dan padatkan tanah di sekitar bibit."
                )
            )
            plantDao.insertPlantings(delimaPlantings)

            val bananaLandPreparations = LandPreparations(
                plantsId = 11,
                processing = "Lahan dibersihkan dari gulma dan sisa-sisa tanaman sebelumnya. Lakukan pengolahan tanah dengan membajak atau mencangkul hingga gembur. Buat lubang tanam dengan ukuran 50x50x50 cm dengan jarak tanam 3x3 meter.",
                irrigation = "Lakukan penyiraman awal setelah penanaman untuk menjaga kelembaban tanah."
            )
            plantDao.insertLandPreparations(bananaLandPreparations)

            val bananaNurseries = Nurseries(
                plantsId = 11,
                seedSelection = "Pilih anakan pisang yang sehat dengan tinggi sekitar 1-1,5 meter dan diameter batang sekitar 10-15 cm.",
                soaking = "Tidak perlu.",
                seeding = "Tidak perlu."
            )
            plantDao.insertNurseries(bananaNurseries)

            val bananaPlantings = listOf(
                Plantings(
                    plantsId = 11,
                    preparing = "Buat lubang tanam dengan ukuran yang telah ditentukan, berikan pupuk dasar sebelum penanaman.",
                    planting = "Tanam anakan pisang di lubang tanam, timbun dengan tanah hingga pangkal batang tertutup, padatkan tanah di sekitar tanaman, dan siram hingga basah."
                )
            )

            plantDao.insertPlantings(bananaPlantings)

            val bananaFertilization = listOf(
                Fertilization(
                    plantsId = 11,
                    type = "Dasar",
                    agePlant = "0 Hari",
                    urea = "0,2 kg/lubang",
                    tsp = "0,3 kg/lubang",
                    kcl = "0,2 kg/lubang",
                    organicFertilizer = "Pemberian pupuk kandang sebanyak 10-20 kg/lubang tanam."
                ),
                Fertilization(
                    plantsId = 11,
                    type = "Susulan",
                    agePlant = "90 Hari",
                    urea = "0,1 kg/tanaman",
                    tsp = "0,1 kg/tanaman",
                    kcl = "0,1 kg/tanaman",
                    organicFertilizer = null
                ),
                Fertilization(
                    plantsId = 11,
                    type = "Susulan",
                    agePlant = "180 Hari",
                    urea = "0,1 kg/tanaman",
                    tsp = "0,1 kg/tanaman",
                    kcl = "0,1 kg/tanaman",
                    organicFertilizer = null
                )
            )
            plantDao.insertFertilization(bananaFertilization)

            val bananaVarieties = listOf(
                Varieties(
                    plantsId = 11,
                    name = "Varietas Pisang Cavendish",
                    superiority = "Buah besar, tahan terhadap penyakit, dan memiliki daya simpan yang baik.",
                    weakness = "Rentan terhadap penyakit layu fusarium."
                ),
                Varieties(
                    plantsId = 11,
                    name = "Varietas Pisang Raja Bulu",
                    superiority = "Rasa buah yang manis, adaptasi baik di daerah tropis.",
                    weakness = "Masa panen lebih lama."
                ),
                Varieties(
                    plantsId = 11,
                    name = "Varietas Pisang Ambon Kuning",
                    superiority = "Rasa manis dan lezat, ukuran buah besar.",
                    weakness = "Membutuhkan perawatan intensif."
                )
            )
            plantDao.insertVarieties(bananaVarieties)

            val bananaPests = listOf(
                Pests(
                    plantsId = 11,
                    name = "Ulat Penggulung Daun",
                    chemicalControl = "Insektisida dengan kandungan sipermetrin.",
                    biologicalControl = "Menggunakan larutan neem oil karena efektif sebagai insektisida alami."
                ),
                Pests(
                    plantsId = 11,
                    name = "Nematoda",
                    chemicalControl = "Nematisida berbahan aktif karbofuran.",
                    biologicalControl = "Penggunaan pupuk organik berbasis jamur nematofag."
                ),
                Pests(
                    plantsId = 11,
                    name = "Kutu Daun",
                    chemicalControl = "Insektisida dengan kandungan imidakloprid.",
                    biologicalControl = "Larutan air sabun dan minyak neem untuk mengganggu pernapasan kutu daun."
                )
            )
            plantDao.insertPests(bananaPests)

            val bananaDiseases = listOf(
                Diseases(
                    plantsId = 11,
                    name = "Penyakit Layu Fusarium",
                    control = "Penggunaan varietas tahan penyakit, rotasi tanaman, dan aplikasi fungisida berbahan aktif benomil."
                ),
                Diseases(
                    plantsId = 11,
                    name = "Busuk Akar",
                    control = "Lakukan sanitasi lahan dan penggunaan varietas yang tahan terhadap penyakit ini."
                )
            )

            plantDao.insertDiseases(bananaDiseases)

            val bananaWeeds = Weeds(
                plantsId = 11,
                manualWeeding = "Penyiangan secara manual dengan mencabut gulma di sekitar tanaman.",
                herbicide = "Aplikasi herbisida kontak seperti paraquat."
            )
            plantDao.insertWeeds(bananaWeeds)

            val mangoLandPreparations = LandPreparations(
                plantsId = 12,
                processing = "Lahan dibersihkan dari gulma dan sisa-sisa tanaman sebelumnya. Lakukan pengolahan tanah dengan membajak atau mencangkul hingga gembur. Buat lubang tanam dengan ukuran 60x60x60 cm dengan jarak tanam 8x8 meter.",
                irrigation = "Lakukan penyiraman awal setelah penanaman untuk menjaga kelembaban tanah."
            )
            plantDao.insertLandPreparations(mangoLandPreparations)

            val mangoNurseries = Nurseries(
                plantsId = 12,
                seedSelection = "Pilih bibit mangga yang sehat, bebas penyakit, dan memiliki tinggi sekitar 75-100 cm.",
                soaking = "Tidak perlu.",
                seeding = "Tidak perlu."
            )
            plantDao.insertNurseries(mangoNurseries)

            val mangoPlantings = listOf(
                Plantings(
                    plantsId = 12,
                    preparing = "Buat lubang tanam dengan ukuran yang telah ditentukan, berikan pupuk dasar sebelum penanaman.",
                    planting = "Tanam bibit mangga di lubang tanam, timbun dengan tanah hingga pangkal batang tertutup, padatkan tanah di sekitar tanaman, dan siram hingga basah."
                )
            )

            plantDao.insertPlantings(mangoPlantings)

            val mangoFertilization = listOf(
                Fertilization(
                    plantsId = 12,
                    type = "Dasar",
                    agePlant = "0 Hari",
                    urea = "0,2 kg/lubang",
                    tsp = "0,3 kg/lubang",
                    kcl = "0,2 kg/lubang",
                    organicFertilizer = "Pemberian pupuk kandang sebanyak 10-20 kg/lubang tanam."
                ),
                Fertilization(
                    plantsId = 12,
                    type = "Susulan",
                    agePlant = "6 Bulan",
                    urea = "0,1 kg/tanaman",
                    tsp = "0,1 kg/tanaman",
                    kcl = "0,1 kg/tanaman",
                    organicFertilizer = null
                ),
                Fertilization(
                    plantsId = 12,
                    type = "Susulan",
                    agePlant = "12 Bulan",
                    urea = "0,1 kg/tanaman",
                    tsp = "0,1 kg/tanaman",
                    kcl = "0,1 kg/tanaman",
                    organicFertilizer = null
                )
            )
            plantDao.insertFertilization(mangoFertilization)

            val mangoVarieties = listOf(
                Varieties(
                    plantsId = 12,
                    name = "Varietas Arumanis",
                    superiority = "Rasa manis dan harum, daging buah tebal.",
                    weakness = "Rentan terhadap penyakit antraknosa."
                ),
                Varieties(
                    plantsId = 12,
                    name = "Varietas Gedong Gincu",
                    superiority = "Rasa manis, warna kulit menarik, cocok untuk pasar ekspor.",
                    weakness = "Pertumbuhan pohon relatif lambat."
                ),
                Varieties(
                    plantsId = 12,
                    name = "Varietas Manalagi",
                    superiority = "Rasa manis, tahan terhadap serangan hama.",
                    weakness = "Ukuran buah relatif kecil."
                )
            )
            plantDao.insertVarieties(mangoVarieties)

            val mangoPests = listOf(
                Pests(
                    plantsId = 12,
                    name = "Penggerek Batang",
                    chemicalControl = "Insektisida berbahan aktif karbofuran.",
                    biologicalControl = "Larutan neem oil karena efektif sebagai insektisida alami."
                ),
                Pests(
                    plantsId = 12,
                    name = "Lalat Buah",
                    chemicalControl = "Insektisida dengan kandungan dimetoat.",
                    biologicalControl = "Perangkap feromon untuk menangkap lalat buah jantan."
                ),
                Pests(
                    plantsId = 12,
                    name = "Tungau",
                    chemicalControl = "Akarisida berbahan aktif abamektin.",
                    biologicalControl = "Minyak neem untuk mengganggu sistem reproduksi tungau."
                )
            )
            plantDao.insertPests(mangoPests)

            val mangoDiseases = listOf(
                Diseases(
                    plantsId = 12,
                    name = "Antraknosa",
                    control = "Pemangkasan bagian tanaman yang terinfeksi, aplikasi fungisida berbahan aktif mancozeb atau benomil."
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
                manualWeeding = "Penyiangan secara manual dengan mencabut gulma di sekitar tanaman.",
                herbicide = "Aplikasi herbisida kontak seperti paraquat."
            )
            plantDao.insertWeeds(mangoWeeds)

            val grapeLandPreparations = LandPreparations(
                plantsId = 13,
                processing = "Tanah digemburkan dan dicampur dengan pupuk kandang, kemudian dibuat bedengan dengan lebar 1-1,5 meter dan tinggi 25-30 cm.",
                irrigation = "Sistem irigasi tetes atau drip irrigation."
            )
            plantDao.insertLandPreparations(grapeLandPreparations)

            val grapeNurseries = Nurseries(
                plantsId = 13,
                seedSelection = "Pilih bibit dari stek yang berasal dari tanaman induk yang sehat dan produktif.",
                soaking = "Tidak Perlu",
                seeding = "Stek ditanam di media tanam yang subur dan lembab sampai akar tumbuh."
            )
            plantDao.insertNurseries(grapeNurseries)

            val grapePlantings = listOf(
                Plantings(
                    plantsId = 13,
                    preparing = "Buat lubang tanam dengan kedalaman 30-50 cm dan jarak tanam 2-3 meter.",
                    planting = "Tanam stek anggur dengan kemiringan 45 derajat dan tutup dengan tanah, lalu siram secukupnya."
                )
            )
            plantDao.insertPlantings(grapePlantings)

            val grapeFertilization = listOf(
                Fertilization(
                    plantsId = 13,
                    type = "Dasar",
                    agePlant = "Pra Tanam",
                    urea = "100 kg/ha",
                    tsp = "200 kg/ha",
                    kcl = "100 kg/ha",
                    organicFertilizer = "Gunakan pupuk kandang 20-30 ton/ha pupuk kandang."
                ),
                Fertilization(
                    plantsId = 13,
                    type = "Susulan",
                    agePlant = "Setiap 30 hari",
                    urea = "50 kg/ha",
                    tsp = "100 kg/ha",
                    kcl = "50 kg/ha",
                    organicFertilizer = null
                )
            )
            plantDao.insertFertilization(grapeFertilization)

            val grapeVarieties = listOf(
                Varieties(
                    plantsId = 13,
                    name = "Vitis vinifera",
                    superiority = "Produktivitas tinggi, rasa manis.",
                    weakness = "Rentan terhadap penyakit jamur."
                ),
                Varieties(
                    plantsId = 13,
                    name = "Concord",
                    superiority = "Tahan terhadap penyakit, cocok untuk jus.",
                    weakness = " Rasa sedikit asam."
                ),
                Varieties(
                    plantsId = 13,
                    name = "Crimson Seedless",
                    superiority = "Tanpa biji, buah lebih tahan lama.",
                    weakness = "Perawatan intensif."
                )
            )
            plantDao.insertVarieties(grapeVarieties)

            val grapePests = listOf(
                Pests(
                    plantsId = 13,
                    name = "Kutu daun (aphid)",
                    chemicalControl = "Gunakan zat aktif Imidakloprid",
                    biologicalControl = "Minyak neem (menyebabkan gangguan pada sistem reproduksi kutu daun)."
                ),
                Pests(
                    plantsId = 13,
                    name = "Thrips",
                    chemicalControl = "Gunakan zat aktif Spinosad",
                    biologicalControl = "Larutan sabun (memecahkan sel kutikula hama)."
                ),
                Pests(
                    plantsId = 13,
                    name = "Ulat grayak",
                    chemicalControl = "Bacillus thuringiensis",
                    biologicalControl = "Ekstrak daun nimba (mengandung azadirachtin yang menghambat makan ulat)."
                )
            )
            plantDao.insertPests(grapePests)

            val grapeDiseases = listOf(
                Diseases(
                    plantsId = 13,
                    name = "Embun tepung (powdery mildew)",
                    control = "Semprot dengan fungisida berbahan aktif sulfur atau gunakan susu sapi encer sebagai fungisida alami."
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
                manualWeeding = "Penyiangan secara manual dengan tangan atau cangkul.",
                herbicide = "Glyphosate (aplikasi dengan hati-hati agar tidak mengenai tanaman utama)."
            )
            plantDao.insertWeeds(grapeWeeds)

            val watermelonLandPreparations = LandPreparations(
                plantsId = 14,
                processing = "Lahan dibajak sedalam 30-40 cm dan dibiarkan selama 1-2 minggu untuk mengurangi hama dan penyakit. Tanah dicampur dengan pupuk kandang atau kompos.",
                irrigation = "Lahan harus memiliki sistem irigasi yang baik untuk memastikan tanah tetap lembap namun tidak tergenang."
            )

            val watermelonNurseries = Nurseries(
                plantsId = 14,
                seedSelection = "Pilih benih yang berkualitas, bebas dari penyakit, dan memiliki daya kecambah tinggi. Benih hibrida sering disarankan untuk hasil yang lebih baik.",
                soaking = "Rendam benih dalam air hangat (sekitar 30°C) selama 6-8 jam.",
                seeding = "Benih disemaikan dalam polybag atau tray semai dengan media tanam yang subur dan gembur. Biarkan di tempat teduh dan sirami secara teratur sampai tumbuh 2-3 daun sejati sebelum dipindah ke lahan."
            )

            val watermelonPlantings = listOf(
                Plantings(
                    plantsId = 14,
                    preparing = "Buat bedengan dengan lebar 1 meter dan tinggi 20-30 cm. Jarak antar bedengan sekitar 3 meter.",
                    planting = "Pindahkan bibit ke lahan dengan jarak tanam sekitar 70-100 cm dalam barisan dan jarak antar barisan 3-4 meter. Tanam bibit sedalam 3-5 cm dan tutup dengan tanah halus."
                )
            )

            val watermelonFertilization = listOf(
                Fertilization(
                    plantsId = 14,
                    type = "Dasar",
                    agePlant = "0 Hari",
                    urea = "100 kg/ha",
                    tsp = "150 kg/ha",
                    kcl = "100 kg/ha",
                    organicFertilizer = "Berikan pupuk kandang atau kompos 10 ton/ha."
                ),
                Fertilization(
                    plantsId = 14,
                    type = "Susulan",
                    agePlant = "14 hari",
                    urea = "50 kg/ha",
                    tsp = "0",
                    kcl = "50 kg/ha",
                    organicFertilizer = null
                ),
                Fertilization(
                    plantsId = 14,
                    type = "Susulan",
                    agePlant = "30 Hari",
                    urea = "50 kg/ha",
                    tsp = "0",
                    kcl = "50 kg/ha",
                    organicFertilizer = null
                )
            )

            val watermelonVarieties = listOf(
                Varieties(
                    plantsId = 14,
                    name = "New Dragon",
                    superiority = "Tahan terhadap penyakit layu fusarium, buah besar.",
                    weakness = "Memerlukan perawatan intensif."
                ),
                Varieties(
                    plantsId = 14,
                    name = "Crimson Sweet",
                    superiority = "Buah besar dengan daging merah, tahan terhadap beberapa penyakit, dan memiliki umur simpan yang baik.",
                    weakness = "Memerlukan pemupukan dan penyiraman yang konsisten untuk hasil terbaik."
                ),
                Varieties(
                    plantsId = 14,
                    name = "Sugar Baby",
                    superiority = "Buah kecil dengan daging merah gelap dan rasa yang manis, cocok untuk ukuran semangka mini.",
                    weakness = "Rentan terhadap serangan hama dan penyakit jika tidak dikelola dengan baik."
                )
            )

            val watermelonPests = listOf(
                Pests(
                    plantsId = 14,
                    name = "Kutu Daun (Aphids)",
                    chemicalControl = "Gunakan insektisida dengna zat aktif Imidakloprid",
                    biologicalControl = "Minyak neem, efektif karena mengandung azadirachtin yang mengganggu siklus hidup kutu daun"
                ),
                Pests(
                    plantsId = 14,
                    name = "Lalat Buah (Bactrocera cucurbitae)",
                    chemicalControl = "Gunakan zat aktif Bacillus thuringiensis",
                    biologicalControl = "Larutan bawang putih, mengandung sulfur yang mengganggu metabolisme ulat"
                ),
                Pests(
                    plantsId = 14,
                    name = "Ulat Grayak (Spodoptera litura)",
                    chemicalControl = "Gunakan zat aktif Malathion.",
                    biologicalControl = "Perangkap feromon; menarik lalat buah jantan dan mengurangi populasi."
                )
            )

            val watermelonDiseases = listOf(
                Diseases(
                    plantsId = 14,
                    name = "Layu Fusarium",
                    control = "Rotasi tanaman, penggunaan fungisida berbasis Trichoderma, dan pemilihan varietas yang tahan penyakit"
                ),
                Diseases(
                    plantsId = 14,
                    name = "Antraknosa",
                    control = "Aplikasikan fungisida sesuai kebutuhan untuk mengurangi dampak penyakit pada tanaman."
                )
            )

            val watermelonWeeds = Weeds(
                plantsId = 14,
                manualWeeding = "Penyiangan dengan tangan atau alat kecil setiap 2 minggu",
                herbicide = "Glifosat, diterapkan dengan hati-hati untuk menghindari kontak dengan tanaman semangka"
            )

            plantDao.insertLandPreparations(watermelonLandPreparations)
            plantDao.insertNurseries(watermelonNurseries)
            plantDao.insertPlantings(watermelonPlantings)
            plantDao.insertFertilization(watermelonFertilization)
            plantDao.insertVarieties(watermelonVarieties)
            plantDao.insertPests(watermelonPests)
            plantDao.insertDiseases(watermelonDiseases)
            plantDao.insertWeeds(watermelonWeeds)

            val melonLandPreparations = LandPreparations(
                plantsId = 15,
                processing = "Lakukan pengolahan tanah dengan membajak sedalam 30-40 cm untuk memperbaiki struktur tanah dan menghilangkan gulma. Tambahkan pupuk kandang atau kompos sebanyak 20-30 ton/ha untuk meningkatkan kesuburan tanah.",
                irrigation = "Pastikan sistem irigasi tersedia, bisa menggunakan irigasi tetes atau sprinkle untuk memastikan tanaman mendapatkan air yang cukup terutama pada masa awal pertumbuhan dan pembentukan buah."
            )

            val melonNurseries = Nurseries(
                plantsId = 15,
                seedSelection = "Pilih benih yang berkualitas dan bersertifikat, tahan terhadap penyakit, dan memiliki daya tumbuh tinggi.",
                soaking = "Rendam benih dalam air hangat (40-50°C) selama 6-8 jam untuk mempercepat perkecambahan.",
                seeding = "Semai benih di bedengan atau tray semai dengan media campuran tanah, pupuk kandang, dan pasir (1:1:1). Pindahkan bibit setelah berumur 7-10 hari atau memiliki 2-3 daun sejati."
            )

            val melonPlantings = listOf(
                Plantings(
                    plantsId = 15,
                    preparing = "Buat lubang tanam dengan jarak tanam 60 x 70 cm. Lubang tanam diberi pupuk dasar berupa kompos atau pupuk kandang matang sebanyak 0,5 kg/lubang.",
                    planting = "Pindahkan bibit melon ke lubang tanam pada sore hari untuk mengurangi stres tanaman. Tanam dengan kedalaman yang sama dengan media semai."
                )
            )

            val melonFertilization = listOf(
                Fertilization(
                    plantsId = 15,
                    type = "Dasar",
                    agePlant = "0 Hari",
                    urea = "50 kg/ha",
                    tsp = "100 kg/ha",
                    kcl = "50 kg/ha",
                    organicFertilizer = "20-30 ton/ha kompos atau pupuk kandang"
                ),
                Fertilization(
                    plantsId = 15,
                    type = "Susulan",
                    agePlant = "15 hari",
                    urea = "50 kg/ha",
                    tsp = "50 kg/ha",
                    kcl = "50 kg/ha",
                    organicFertilizer = null
                ),
                Fertilization(
                    plantsId = 15,
                    type = "Susulan",
                    agePlant = "30 hari",
                    urea = "50 kg/ha",
                    tsp = "50 kg/ha",
                    kcl = "50 kg/ha",
                    organicFertilizer = null
                ),
                Fertilization(
                    plantsId = 15,
                    type = "Susulan",
                    agePlant = "45 hari",
                    urea = "50 kg/ha",
                    tsp = "50 kg/ha",
                    kcl = "50 kg/ha",
                    organicFertilizer = null
                )
            )

            val melonVarieties = listOf(
                Varieties(
                    plantsId = 15,
                    name = "Melon Hikapel",
                    superiority = "Buah manis, tahan penyakit, hasil tinggi.",
                    weakness = "Membutuhkan perawatan intensif."
                ),
                Varieties(
                    plantsId = 15,
                    name = "Melon Sky Rocket",
                    superiority = "Ukuran besar, rasa manis, tahan penyimpanan lama.",
                    weakness = "Rentan terhadap serangan hama."
                ),
                Varieties(
                    plantsId = 15,
                    name = "Melon Glamour",
                    superiority = "Daging tebal, aroma harum, tahan angkut.",
                    weakness = "Membutuhkan teknik budidaya khusus."
                )
            )

            val melonPests = listOf(
                Pests(
                    plantsId = 15,
                    name = "Kutu Daun (Aphis gossypii)",
                    chemicalControl = "Imidacloprid",
                    biologicalControl = "Ekstrak daun nimba (Azadirachta indica) karena mengandung azadirachtin yang menghambat pertumbuhan dan perkembangan kutu."
                ),
                Pests(
                    plantsId = 15,
                    name = "Ulat Grayak (Spodoptera litura)",
                    chemicalControl = "Emamectin benzoate",
                    biologicalControl = "Bacillus thuringiensis (Bt) karena bakteri ini menghasilkan protein kristal yang merusak usus ulat."
                ),
                Pests(
                    plantsId = 15,
                    name = "Lalat Buah (Bactrocera spp.)",
                    chemicalControl = "Spinosad",
                    biologicalControl = "Perangkap feromon, efektif dalam menarik dan menangkap lalat buah jantan sehingga mengurangi populasi."
                )
            )

            val melonDiseases = listOf(
                Diseases(
                    plantsId = 15,
                    name = "Embun Tepung (Powdery Mildew)",
                    control = "Semprotkan fungisida berbahan aktif sulfur atau menggunakan campuran susu dan air (1:10) sebagai fungisida alami."
                ),
                Diseases(
                    plantsId = 15,
                    name = "Layu Fusarium (Fusarium oxysporum)",
                    control = "Rotasi tanaman, penggunaan varietas tahan, dan aplikasi fungisida berbahan aktif carbendazim."
                )
            )

            val melonWeeds = Weeds(
                plantsId = 15,
                manualWeeding = "Penyiangan dengan tangan atau alat secara rutin, terutama pada awal pertumbuhan tanaman.",
                herbicide = "Aplikasi herbisida pra-tumbuh seperti pendimethalin untuk mencegah pertumbuhan gulma."
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
                processing = "Pengolahan tanah dilakukan dengan cara mencangkul atau membajak sedalam 30-40 cm. Tambahkan pupuk kandang atau kompos sebanyak 20-30 ton/ha untuk meningkatkan kesuburan tanah. Biarkan tanah selama 1-2 minggu sebelum penanaman.",
                irrigation = "Sistem irigasi tetes sangat disarankan untuk tanaman apel karena dapat memberikan kelembapan yang cukup dan efisien. Pastikan drainase tanah baik untuk menghindari genangan air."
            )

            val appleNurseries = Nurseries(
                plantsId = 16,
                seedSelection = "Pilih benih dari varietas unggul yang sesuai dengan kondisi iklim dan tanah setempat, serta memiliki ketahanan terhadap penyakit.",
                soaking = "Tidak Perlu",
                seeding = "Penyemaian dilakukan di bedengan atau polybag dengan media campuran tanah, pupuk kandang, dan pasir (1:1:1). Bibit dapat dipindahkan setelah berumur 1 tahun atau memiliki tinggi sekitar 30-50 cm."
            )

            val applePlantings = listOf(
                Plantings(
                    plantsId = 16,
                    preparing = "Buat lubang tanam dengan ukuran 60 x 60 x 60 cm dengan jarak tanam sekitar 4-5 meter antar tanaman. Lubang tanam diberi pupuk dasar berupa kompos atau pupuk kandang matang sebanyak 10-20 kg/lubang.",
                    planting = "Tanam bibit apel pada lubang tanam dan timbun dengan tanah hingga pangkal batang tertutup. Padatkan tanah di sekitar pangkal batang dan siram dengan air."
                )
            )

            val appleFertilization = listOf(
                Fertilization(
                    plantsId = 16,
                    type = "Dasar",
                    agePlant = "0 hari (sebelum tanam)",
                    urea = "100 kg/ha",
                    tsp = "200 kg/ha",
                    kcl = "150 kg/ha",
                    organicFertilizer = "Berikan pupuk kandang atau kompos sebelum penanaman untuk memperbaiki kesuburan tanah, 20-30 ton/ha kompos atau pupuk kandang."
                ),
                Fertilization(
                    plantsId = 16,
                    type = "Susulan",
                    agePlant = "3 bulan",
                    urea = "50 kg/ha",
                    tsp = "100 kg/ha",
                    kcl = "75 kg/ha",
                    organicFertilizer = null
                ),
                Fertilization(
                    plantsId = 16,
                    type = "Susulan",
                    agePlant = "6 bulan",
                    urea = "50 kg/ha",
                    tsp = "100 kg/ha",
                    kcl = "75 kg/ha",
                    organicFertilizer = null
                ),
                Fertilization(
                    plantsId = 16,
                    type = "Susulan",
                    agePlant = "12 bulan",
                    urea = "50 kg/ha",
                    tsp = "100 kg/ha",
                    kcl = "75 kg/ha",
                    organicFertilizer = null
                )
            )

            val appleVarieties = listOf(
                Varieties(
                    plantsId = 16,
                    name = "Rome Beauty",
                    superiority = "Produktivitas tinggi, buah besar, tahan penyakit.",
                    weakness = "Rasa kurang manis jika ditanam di dataran rendah."
                ),
                Varieties(
                    plantsId = 16,
                    name = "Anna",
                    superiority = "Buah manis, cepat berbuah, cocok untuk daerah tropis.",
                    weakness = "Rentan terhadap penyakit busuk buah."
                ),
                Varieties(
                    plantsId = 16,
                    name = "Granny Smith",
                    superiority = "Buah hijau dengan rasa asam yang segar, tahan terhadap penyakit dan hama.",
                    weakness = "Memerlukan cuaca yang lebih dingin untuk menghasilkan buah dengan warna yang terbaik."
                )
            )

            val applePests = listOf(
                Pests(
                    plantsId = 16,
                    name = "Kutu Daun (Aphis pomi)",
                    chemicalControl = "Gunakan insektisida yang mengandung Imidacloprid",
                    biologicalControl = "Ekstrak daun nimba (Azadirachta indica) karena mengandung azadirachtin yang menghambat pertumbuhan dan perkembangan kutu."
                ),
                Pests(
                    plantsId = 16,
                    name = "Penggerek Buah (Cydia pomonella)",
                    chemicalControl = "Gunakan insektisida yang mengandung Chlorantraniliprole",
                    biologicalControl = "Bacillus thuringiensis (Bt) karena bakteri ini menghasilkan protein kristal yang merusak usus ulat."
                ),
                Pests(
                    plantsId = 16,
                    name = "Tungau Merah (Panonychus ulmi)",
                    chemicalControl = "Gunakan insektisida yang mengandung Abamectin",
                    biologicalControl = "Minyak neem karena dapat menghambat pertumbuhan dan reproduksi tungau."
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
                    name = "Busuk Buah (Monilinia fructigena)",
                    control = "Pemangkasan cabang yang terinfeksi, aplikasi fungisida berbahan aktif captan, serta menjaga kebersihan kebun."
                ),
                Diseases(
                    plantsId = 16,
                    name = "Kanker Batang (Nectria galligena)",
                    control = "Potong dan bakar bagian tanaman yang terinfeksi, aplikasikan fungisida berbahan aktif thiophanate-methyl pada luka potongan."
                )
            )

            val appleWeeds = Weeds(
                plantsId = 16,
                manualWeeding = "Penyiangan dengan tangan atau alat secara rutin, terutama pada awal pertumbuhan tanaman dan setelah panen.",
                herbicide = "Aplikasi herbisida pra-tumbuh seperti glyphosate untuk mencegah pertumbuhan gulma."
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
                processing = "Lakukan pengolahan tanah dengan cara mencangkul atau membajak sedalam 30-40 cm. Tambahkan pupuk kandang atau kompos sebanyak 20-30 ton/ha untuk meningkatkan kesuburan tanah. Biarkan tanah selama 1-2 minggu sebelum penanaman.",
                irrigation = "Sistem irigasi tetes sangat disarankan untuk tanaman jeruk karena dapat memberikan kelembapan yang cukup dan efisien. Pastikan drainase tanah baik untuk menghindari genangan air."
            )

            val orangeNurseries = Nurseries(
                plantsId = 17,
                seedSelection = "Pilih benih dari varietas unggul yang sesuai dengan kondisi iklim dan tanah setempat, serta memiliki ketahanan terhadap penyakit.",
                soaking = "Tidak Perlu",
                seeding = "Penyemaian dilakukan di bedengan atau polybag dengan media campuran tanah, pupuk kandang, dan pasir (1:1:1). Bibit dapat dipindahkan setelah berumur 1 tahun atau memiliki tinggi sekitar 30-50 cm."
            )

            val orangePlantings = listOf(
                Plantings(
                    plantsId = 17,
                    preparing = "Buat lubang tanam dengan ukuran 50 x 50 x 50 cm dengan jarak tanam sekitar 4-5 meter antar tanaman. Lubang tanam diberi pupuk dasar berupa kompos atau pupuk kandang matang sebanyak 10-20 kg/lubang.",
                    planting = "Tanam bibit jeruk pada lubang tanam dan timbun dengan tanah hingga pangkal batang tertutup. Padatkan tanah di sekitar pangkal batang dan siram dengan air."
                )
            )

            val orangeFertilization = listOf(
                Fertilization(
                    plantsId = 17,
                    type = "Dasar",
                    agePlant = "0 hari (sebelum tanam)",
                    urea = "100 kg/ha",
                    tsp = "200 kg/ha",
                    kcl = "150 kg/ha",
                    organicFertilizer = "20-30 ton/ha kompos atau pupuk kandang"
                ),
                Fertilization(
                    plantsId = 17,
                    type = "Susulan",
                    agePlant = "3 bulan",
                    urea = "50 kg/ha",
                    tsp = "100 kg/ha",
                    kcl = "75 kg/ha",
                    organicFertilizer = null
                ),
                Fertilization(
                    plantsId = 17,
                    type = "Susulan",
                    agePlant = "6 bulan",
                    urea = "50 kg/ha",
                    tsp = "100 kg/ha",
                    kcl = "75 kg/ha",
                    organicFertilizer = null
                ),
                Fertilization(
                    plantsId = 17,
                    type = "Susulan",
                    agePlant = "12 bulan",
                    urea = "50 kg/ha",
                    tsp = "100 kg/ha",
                    kcl = "75 kg/ha",
                    organicFertilizer = null
                )
            )

            val orangeVarieties = listOf(
                Varieties(
                    plantsId = 17,
                    name = "Jeruk Siam",
                    superiority = "Buah manis, produktivitas tinggi, tahan penyakit.",
                    weakness = "Rentan terhadap perubahan cuaca ekstrim."
                ),
                Varieties(
                    plantsId = 17,
                    name = "Jeruk Keprok",
                    superiority = "Buah berukuran besar, rasa manis, kulit mudah dikupas.",
                    weakness = "Membutuhkan perawatan intensif."
                ),
                Varieties(
                    plantsId = 17,
                    name = "Jeruk Bali",
                    superiority = "Ukuran buah besar, tahan penyimpanan lama, daging buah tebal.",
                    weakness = "Periode panen yang lebih lama."
                )
            )

            val orangePests = listOf(
                Pests(
                    plantsId = 17,
                    name = "Kutu Daun (Aphis sp.)",
                    chemicalControl = "Gunakan insektisida yang mengandung Imidacloprid",
                    biologicalControl = "Ekstrak daun nimba (Azadirachta indica) karena mengandung azadirachtin yang menghambat pertumbuhan dan perkembangan kutu."
                ),
                Pests(
                    plantsId = 17,
                    name = "Penggerek Buah (Bactrocera dorsalis)",
                    chemicalControl = "Gunakan insektisida yang mengandung Spinosad",
                    biologicalControl = "Perangkap feromon, efektif dalam menarik dan menangkap lalat buah jantan sehingga mengurangi populasi."
                ),
                Pests(
                    plantsId = 17,
                    name = "Tungau Merah (Panonychus citri)",
                    chemicalControl = "Gunakan insektisida yang mengandung Abamectin",
                    biologicalControl = "Minyak neem karena dapat menghambat pertumbuhan dan reproduksi tungau."
                )
            )

            val orangeDiseases = listOf(
                Diseases(
                    plantsId = 17,
                    name = "Penyakit Greening (Citrus Greening Disease)",
                    control = "Gunakan bibit bebas penyakit, pemangkasan dan pembakaran tanaman terinfeksi, aplikasi insektisida untuk mengendalikan vektor penyakit (Diaphorina citri)."
                ),
                Diseases(
                    plantsId = 17,
                    name = "Busuk Akar (Phytophthora sp.)",
                    control = "Peningkatan drainase tanah, aplikasi fungisida berbahan aktif metalaksil, dan rotasi tanaman."
                )
            )

            val orangeWeeds = Weeds(
                plantsId = 17,
                manualWeeding = "Penyiangan dengan tangan atau alat secara rutin, terutama pada awal pertumbuhan tanaman dan setelah panen.",
                herbicide = "Aplikasi herbisida pra-tumbuh seperti glyphosate untuk mencegah pertumbuhan gulma."
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
                processing = "Lakukan pengolahan tanah dengan cara mencangkul atau membajak sedalam 20-30 cm. Tambahkan pupuk kandang atau kompos sebanyak 10-15 ton/ha untuk meningkatkan kesuburan tanah. Biarkan tanah selama 1-2 minggu sebelum penanaman.",
                irrigation = "Gunakan sistem irigasi tetes atau sprinkle. Pastikan kelembapan tanah terjaga terutama pada masa awal pertumbuhan dan pembentukan buah."
            )

            val papayaNurseries = Nurseries(
                plantsId = 18,
                seedSelection = "Pilih benih pepaya dari varietas unggul yang bebas penyakit, memiliki daya tumbuh tinggi, dan sesuai dengan kondisi iklim setempat.",
                soaking = "Tidak Perlu",
                seeding = "Semai benih di bedengan atau polybag dengan media campuran tanah, pupuk kandang, dan pasir (1:1:1). Pindahkan bibit setelah berumur 2-3 bulan atau setelah memiliki 4-6 daun sejati."
            )

            val papayaPlantings = listOf(
                Plantings(
                    plantsId = 18,
                    preparing = "Buat lubang tanam dengan ukuran 40 x 40 x 40 cm dengan jarak tanam sekitar 2-3 meter antar tanaman. Lubang tanam diberi pupuk dasar berupa kompos atau pupuk kandang matang sebanyak 5-10 kg/lubang.",
                    planting = "Tanam bibit pepaya pada lubang tanam dan timbun dengan tanah hingga pangkal batang tertutup. Padatkan tanah di sekitar pangkal batang dan siram dengan air."
                )
            )

            val papayaFertilization = listOf(
                Fertilization(
                    plantsId = 18,
                    type = "Dasar",
                    agePlant = "0 hari (sebelum tanam)",
                    urea = "100 kg/ha",
                    tsp = "150 kg/ha",
                    kcl = "100 kg/ha",
                    organicFertilizer = "10-15 ton/ha kompos atau pupuk kandang"
                ),
                Fertilization(
                    plantsId = 18,
                    type = "Susulan",
                    agePlant = "1 bulan",
                    urea = "50 kg/ha",
                    tsp = "50 kg/ha",
                    kcl = "50 kg/ha",
                    organicFertilizer = null
                ),
                Fertilization(
                    plantsId = 18,
                    type = "Susulan",
                    agePlant = "2 bulan",
                    urea = "50 kg/ha",
                    tsp = "50 kg/ha",
                    kcl = "50 kg/ha",
                    organicFertilizer = null
                ),
                Fertilization(
                    plantsId = 18,
                    type = "Susulan",
                    agePlant = "3 bulan",
                    urea = "50 kg/ha",
                    tsp = "50 kg/ha",
                    kcl = "50 kg/ha",
                    organicFertilizer = null
                ),
                Fertilization(
                    plantsId = 18,
                    type = "Susulan",
                    agePlant = "6 bulan",
                    urea = "50 kg/ha",
                    tsp = "50 kg/ha",
                    kcl = "50 kg/ha",
                    organicFertilizer = null
                )
            )

            val papayaVarieties = listOf(
                Varieties(
                    plantsId = 18,
                    name = "Pepaya California",
                    superiority = "Buah besar, daging buah manis, tahan penyakit.",
                    weakness = "Perlu perlakuan khusus untuk menjaga kualitas buah."
                ),
                Varieties(
                    plantsId = 18,
                    name = "Pepaya Red Lady",
                    superiority = "Buah merah, manis, produksi tinggi.",
                    weakness = "Rentan terhadap hama seperti kutu daun."
                ),
                Varieties(
                    plantsId = 18,
                    name = "Pepaya Ambon",
                    superiority = "Buah manis, rasa segar, tahan transportasi.",
                    weakness = "Masa panen lebih lama dibandingkan varietas lain."
                )
            )

            val papayaPests = listOf(
                Pests(
                    plantsId = 18,
                    name = "Kutu Daun (Aphis gossypii)",
                    chemicalControl = "Imidacloprid",
                    biologicalControl = "Ekstrak daun nimba (Azadirachta indica) karena mengandung azadirachtin yang menghambat pertumbuhan dan perkembangan kutu."
                ),
                Pests(
                    plantsId = 18,
                    name = "Ulat Grayak (Spodoptera litura)",
                    chemicalControl = "Emamectin benzoate",
                    biologicalControl = "Bacillus thuringiensis (Bt) karena bakteri ini menghasilkan protein kristal yang merusak usus ulat."
                ),
                Pests(
                    plantsId = 18,
                    name = "Lalat Buah (Bactrocera spp.)",
                    chemicalControl = "Spinosad",
                    biologicalControl = "Perangkap feromon, efektif dalam menarik dan menangkap lalat buah jantan sehingga mengurangi populasi."
                )
            )

            val papayaDiseases = listOf(
                Diseases(
                    plantsId = 18,
                    name = "Busuk Akar (Phytophthora palmivora)",
                    control = "Peningkatan drainase tanah, aplikasi fungisida berbahan aktif metalaksil, serta pemangkasan bagian tanaman yang terinfeksi."
                ),
                Diseases(
                    plantsId = 18,
                    name = "Penyakit Layu (Fusarium oxysporum)",
                    control = "Rotasi tanaman, penggunaan varietas tahan, aplikasi fungisida berbahan aktif thiophanate-methyl."
                )
            )

            val papayaWeeds = Weeds(
                plantsId = 18,
                manualWeeding = "Penyiangan dengan tangan atau alat secara rutin, terutama pada awal pertumbuhan tanaman dan setelah panen.",
                herbicide = "Aplikasi herbisida pra-tumbuh seperti glyphosate untuk mencegah pertumbuhan gulma."
            )

            plantDao.insertLandPreparations(papayaLandPreparations)
            plantDao.insertNurseries(papayaNurseries)
            plantDao.insertPlantings(papayaPlantings)
            plantDao.insertFertilization(papayaFertilization)
            plantDao.insertVarieties(papayaVarieties)
            plantDao.insertPests(papayaPests)
            plantDao.insertDiseases(papayaDiseases)
            plantDao.insertWeeds(papayaWeeds)
            val coconutLandPreparations = LandPreparations(
                plantsId = 19,
                processing = "Pilih lokasi dengan sinar matahari penuh, tanah berdrainase baik, dan pH tanah 5,0-8,0. Pengolahan Tanah**: Cangkul atau bajak tanah hingga gembur, bersihkan dari gulma dan batu. Buat bedengan dengan jarak 9-10 m antar pohon.",
                irrigation = "Pastikan sistem irigasi mampu menyediakan air secara teratur. Gunakan sistem irigasi tetes atau siram manual. Irigasi dilakukan 2-3 kali seminggu selama musim kemarau. Kurangi frekuensi saat musim hujan."
            )

            val coconutNurseries = Nurseries(
                plantsId = 19,
                seedSelection = "Pilih benih dari buah kelapa segar dengan cangkang keras dan tidak rusak. Pilih varietas unggul seperti Kelapa Hijau, Kelapa Pandan Wangi, atau Kelapa Genjah.",
                soaking = "Rendam benih kelapa dalam air selama 2-3 hari untuk meningkatkan daya berkecambah.",
                seeding = "Gunakan media semai berupa campuran tanah, kompos, dan pasir dengan perbandingan 2:1:1. Semai benih di dalam pot atau bedengan yang terlindung dari sinar matahari langsung."
            )

            val coconutPlantings = listOf(
                Plantings(
                    plantsId = 19,
                    preparing = "1. **Lubang Tanam**: Gali lubang tanam dengan kedalaman 30-40 cm dan lebar 30 cm. 2. **Pupuk Dasar**: Tambahkan pupuk organik ke dalam lubang sebelum penanaman.",
                    planting = "Tanam kelapa dengan posisi tegak dan pastikan tunas berada di atas tanah. Tutup dengan tanah dan padatkan sedikit."
                )
            )

            val coconutFertilization = listOf(
                Fertilization(
                    plantsId = 19,
                    type = "Dasar",
                    agePlant = "0-6 Bulan",
                    urea = "50 g",
                    tsp = "100 g",
                    kcl = "50 g",
                    organicFertilizer = "Kompos atau pupuk kandang, 1-2 kg."
                ),
                Fertilization(
                    plantsId = 19,
                    type = "Susulan",
                    agePlant = "6-12 Bulan",
                    urea = "100 g",
                    tsp = "150 g",
                    kcl = "75 g",
                    organicFertilizer = "Kompos, 2-3 kg."
                ),
                Fertilization(
                    plantsId = 19,
                    type = "Susulan",
                    agePlant = "12-24 Bulan",
                    urea = "150 g",
                    tsp = "200 g",
                    kcl = "100 g",
                    organicFertilizer = "Kompos, 3-4 kg."
                ),
                Fertilization(
                    plantsId = 19,
                    type = "Susulan",
                    agePlant = "24 Bulan ke Atas",
                    urea = "200 g",
                    tsp = "250 g",
                    kcl = "150 g",
                    organicFertilizer = "Kompos, 5 kg."
                )
            )

            val coconutVarieties = listOf(
                Varieties(
                    plantsId = 19,
                    name = "Kelapa Hijau",
                    superiority = "Buah cepat matang, rasa air kelapa segar.",
                    weakness = "Rentan terhadap serangan hama."
                ),
                Varieties(
                    plantsId = 19,
                    name = "Kelapa Pandan Wangi",
                    superiority = "Memiliki aroma wangi, daging kelapa lebih lezat.",
                    weakness = "Produktivitas buah lebih rendah dibandingkan varietas lain."
                ),
                Varieties(
                    plantsId = 19,
                    name = "Kelapa Genjah",
                    superiority = "Cepat berbuah, perawatan lebih mudah.",
                    weakness = "Rentan terhadap penyakit corynespora."
                )
            )

            val coconutPests = listOf(
                Pests(
                    plantsId = 19,
                    name = "Penggerek Buah Kelapa",
                    chemicalControl = "Insektisida berbahan aktif Chlorpyrifos atau Cypermethrin.",
                    biologicalControl = "Larutan bawang putih dan cabe, bisa mengusir hama secara alami."
                ),
                Pests(
                    plantsId = 19,
                    name = "Kumbang Penggerek Batang",
                    chemicalControl = "Insektisida dengan bahan aktif Imidacloprid.",
                    biologicalControl = "Campuran air dan sabun cair, semprotkan pada area yang terkena."
                ),
                Pests(
                    plantsId = 19,
                    name = "Ulat Tanah",
                    chemicalControl = "Piretrin.",
                    biologicalControl = "Campuran minyak neem dan air, semprotkan pada tanah sekitar pohon."
                )
            )

            val coconutDiseases = listOf(
                Diseases(
                    plantsId = 19,
                    name = "Busuk Batang",
                    control = "Gunakan fungisida berbahan aktif Copper Oxychloride. Lindungi area yang sakit dengan melapisi dengan fungisida."
                ),
                Diseases(
                    plantsId = 19,
                    name = "Penyakit Daun Kuning",
                    control = "Perbaiki pola irigasi, gunakan pupuk yang mengandung Magnesium atau Mangan."
                ),
                Diseases(
                    plantsId = 19,
                    name = "Penyakit Hawar Daun",
                    control = "Gunakan fungisida berbahan aktif Mancozeb, serta potong daun yang terinfeksi."
                )
            )

            val coconutWeeds = Weeds(
                plantsId = 19,
                manualWeeding = "Cabut gulma secara manual dan rutin untuk mencegah persaingan nutrisi.",
                herbicide = "Glyphosate untuk mengendalikan gulma, aplikasikan dengan hati-hati agar tidak mengenai tanaman kelapa."
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
                processing = "Lahan harus dibersihkan dari gulma dan sisa tanaman sebelumnya. Lakukan pembajakan dan penggemburan tanah hingga kedalaman 20-30 cm.",
                irrigation = "Pastikan sistem irigasi tersedia dan memadai untuk menjaga kelembaban tanah yang diperlukan oleh tanaman kapas."
            )

            val cottonNurseries = Nurseries(
                plantsId = 20,
                seedSelection = "Pilih benih kapas yang sehat, bersertifikat, dan memiliki daya kecambah tinggi.",
                soaking = "Tidak diperlukan.",
                seeding = "Tidak diperlukan, benih kapas langsung ditanam di lahan."
            )

            val cottonPlantings = listOf(
                Plantings(
                    plantsId = 20,
                    preparing = "Buat lubang tanam dengan jarak 75-90 cm antara baris dan 30-45 cm antar tanaman dalam baris.",
                    planting = "Tanam benih kapas dengan kedalaman sekitar 2-3 cm di lubang tanam yang telah disiapkan."
                )
            )

            val cottonFertilization = listOf(
                Fertilization(
                    plantsId = 20,
                    type = "Dasar",
                    agePlant = "0 hari (sebelum tanam)",
                    urea = "50 kg/ha",
                    tsp = "100 kg/ha",
                    kcl = "50 kg/ha",
                    organicFertilizer = "Gunakan pupuk kandang atau kompos sebanyak 10 ton/ha untuk meningkatkan kesuburan tanah."
                ),
                Fertilization(
                    plantsId = 20,
                    type = "Susulan",
                    agePlant = "30 hari",
                    urea = "50 kg/ha",
                    tsp = "-",
                    kcl = "50 kg/ha",
                    organicFertilizer = null
                ),
                Fertilization(
                    plantsId = 20,
                    type = "Susulan",
                    agePlant = "60 hari",
                    urea = "50 kg/ha",
                    tsp = "-",
                    kcl = "50 kg/ha",
                    organicFertilizer = null
                )
            )

            val cottonVarieties = listOf(
                Varieties(
                    plantsId = 20,
                    name = "Delta Pine 16",
                    superiority = "Tahan terhadap serangan hama bollworm, produktivitas tinggi.",
                    weakness = "Membutuhkan pemupukan yang intensif."
                ),
                Varieties(
                    plantsId = 20,
                    name = "Coker 312",
                    superiority = "Serat panjang, cocok untuk industri tekstil.",
                    weakness = "Rentan terhadap penyakit bakteri."
                ),
                Varieties(
                    plantsId = 20,
                    name = "Tainan 9",
                    superiority = "Tahan terhadap kondisi kering, umur panen lebih cepat.",
                    weakness = "Serat agak kasar."
                )
            )

            val cottonPests = listOf(
                Pests(
                    plantsId = 20,
                    name = "Kutu Daun (Aphid)",
                    chemicalControl = "Gunakan insektisida yang mengandung Imidacloprid",
                    biologicalControl = "Larutan sabun insektisida alami dapat membasmi kutu daun karena mengganggu membran sel kutu."
                ),
                Pests(
                    plantsId = 20,
                    name = "Ulat Grayak (Spodoptera litura)",
                    chemicalControl = "Gunakan insektisida yang mengandung Chlorpyrifos",
                    biologicalControl = "Ekstrak daun neem (mimba) efektif karena mengandung azadirachtin yang bersifat insektisida."
                ),
                Pests(
                    plantsId = 20,
                    name = "Kumbang Tanah (Pachymelus sp.)",
                    chemicalControl = "Gunakan insektisida yang mengandung Carbaryl",
                    biologicalControl = "Penggunaan jamur entomopatogen seperti Beauveria bassiana dapat menginfeksi dan membunuh kumbang."
                )
            )

            val cottonDiseases = listOf(
                Diseases(
                    plantsId = 20,
                    name = "Busuk Akar (Fusarium oxysporum)",
                    control = "Lakukan rotasi tanaman, gunakan fungisida berbahan aktif seperti metil tiofanat, dan perbaiki drainase lahan untuk mencegah kelembaban berlebih."
                ),
                Diseases(
                    plantsId = 20,
                    name = "Karat Daun (Puccinia spp.)",
                    control = "Gunakan fungisida berbahan aktif mancozeb atau propikonazol, serta potong dan musnahkan daun yang terinfeksi."
                )
            )

            val cottonWeeds = Weeds(
                plantsId = 20,
                manualWeeding = "Penyiangan manual menggunakan cangkul atau alat penyiang lain setiap 2-3 minggu sekali.",
                herbicide = "Gunakan herbisida pra-tumbuh seperti pendimethalin atau herbisida pasca-tumbuh seperti glifosat untuk mengendalikan gulma."
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
                processing = "Lahan harus dibersihkan dari gulma dan sisa tanaman sebelumnya. Lakukan pembajakan dan penggemburan tanah hingga kedalaman 20-30 cm.",
                irrigation = "Pastikan sistem irigasi tersedia dan memadai untuk menjaga kelembaban tanah yang diperlukan oleh tanaman rami. Tanah yang ideal untuk rami adalah tanah dengan drainase baik namun cukup kelembaban."
            )

            val ramieNurseries = Nurseries(
                plantsId = 21,
                seedSelection = "Pilih benih rami yang sehat, bersertifikat, dan memiliki daya kecambah tinggi.",
                soaking = "Tidak diperlukan.",
                seeding = "Tidak diperlukan, benih rami langsung ditanam di lahan."
            )

            val ramiePlantings = listOf(
                Plantings(
                    plantsId = 21,
                    preparing = "Buat lubang tanam dengan jarak 30-50 cm antara baris dan 10-20 cm antar tanaman dalam baris.",
                    planting = "Tanam benih rami dengan kedalaman sekitar 2-3 cm di lubang tanam yang telah disiapkan."
                )
            )

            val ramieFertilization = listOf(
                Fertilization(
                    plantsId = 21,
                    type = "Dasar",
                    agePlant = "0 hari (sebelum tanam)",
                    urea = "50 kg/ha",
                    tsp = "100 kg/ha",
                    kcl = "50 kg/ha",
                    organicFertilizer = "Gunakan pupuk kandang atau kompos sebanyak 10 ton/ha untuk meningkatkan kesuburan tanah."
                ),
                Fertilization(
                    plantsId = 21,
                    type = "Susulan",
                    agePlant = "30 hari",
                    urea = "50 kg/ha",
                    tsp = "-",
                    kcl = "50 kg/ha",
                    organicFertilizer = null
                ),
                Fertilization(
                    plantsId = 21,
                    type = "Susulan",
                    agePlant = "60 hari",
                    urea = "50 kg/ha",
                    tsp = "-",
                    kcl = "50 kg/ha",
                    organicFertilizer = null
                )
            )

            val ramieVarieties = listOf(
                Varieties(
                    plantsId = 21,
                    name = "Rami Putih",
                    superiority = "Serat berkualitas tinggi, tahan terhadap serangan hama dan penyakit.",
                    weakness = "Membutuhkan pemeliharaan intensif."
                ),
                Varieties(
                    plantsId = 21,
                    name = "Rami Hijau",
                    superiority = "Pertumbuhan cepat, serat kuat.",
                    weakness = "Rentan terhadap kondisi kering."
                ),
                Varieties(
                    plantsId = 21,
                    name = "Rami Golden",
                    superiority = "Serat halus dan elastis, cocok untuk industri tekstil.",
                    weakness = "Membutuhkan tanah yang subur dan drainase baik."
                )
            )

            val ramiePests = listOf(
                Pests(
                    plantsId = 21,
                    name = "Ulat Grayak (Spodoptera litura)",
                    chemicalControl = "Gunakan insektisida yang mengandung Chlorpyrifos",
                    biologicalControl = "Ekstrak daun neem (mimba) efektif karena mengandung azadirachtin yang bersifat insektisida."
                ),
                Pests(
                    plantsId = 21,
                    name = "Kutu Daun (Aphid)",
                    chemicalControl = "Gunakan insektisida yang mengandung Imidacloprid",
                    biologicalControl = "Larutan sabun insektisida alami dapat membasmi kutu daun karena mengganggu membran sel kutu."
                ),
                Pests(
                    plantsId = 21,
                    name = "Penggerek Batang (Pectinophora gossypiella)",
                    chemicalControl = "Gunakan insektisida yang mengandung Carbaryl",
                    biologicalControl = "Penggunaan jamur entomopatogen seperti Beauveria bassiana dapat menginfeksi dan membunuh penggerek batang."
                )
            )

            val ramieDiseases = listOf(
                Diseases(
                    plantsId = 21,
                    name = "Busuk Akar (Fusarium oxysporum)",
                    control = "Lakukan rotasi tanaman, gunakan fungisida berbahan aktif seperti metil tiofanat, dan perbaiki drainase lahan untuk mencegah kelembaban berlebih."
                ),
                Diseases(
                    plantsId = 21,
                    name = "Embun Tepung (Erysiphe cichoracearum)",
                    control = "Gunakan fungisida berbahan aktif sulfur atau myclobutanil, serta potong dan musnahkan daun yang terinfeksi."
                )
            )

            val ramieWeeds = Weeds(
                plantsId = 21,
                manualWeeding = "Penyiangan manual menggunakan cangkul atau alat penyiang lain setiap 2-3 minggu sekali.",
                herbicide = "Gunakan herbisida pra-tumbuh seperti pendimethalin atau herbisida pasca-tumbuh seperti glifosat untuk mengendalikan gulma."
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
                processing = "Lahan harus dibersihkan dari gulma dan sisa tanaman sebelumnya. Lakukan pembajakan dan penggemburan tanah hingga kedalaman 30-40 cm. Pastikan tanah memiliki pH antara 5.5-6.5.",
                irrigation = "Pastikan sistem irigasi tersedia dan memadai untuk menjaga kelembaban tanah, terutama pada musim kemarau. Sistem irigasi tetes atau sprinkler bisa digunakan."
            )

            val coffeeNurseries = Nurseries(
                plantsId = 22,
                seedSelection = "Pilih benih kopi yang sehat, bersertifikat, dan memiliki daya kecambah tinggi. Varietas unggul seperti Arabika dan Robusta biasanya digunakan.",
                soaking = "Tidak diperlukan.",
                seeding = "Benih kopi disemai terlebih dahulu di bedengan atau polybag selama 3-4 bulan sebelum dipindahkan ke lahan utama."
            )

            val coffeePlantings = listOf(
                Plantings(
                    plantsId = 22,
                    preparing = "Buat lubang tanam dengan ukuran 60x60x60 cm dengan jarak tanam 2.5x2.5 meter untuk kopi Arabika dan 3x3 meter untuk kopi Robusta.",
                    planting = "Pindahkan bibit kopi dari bedengan atau polybag ke lubang tanam yang telah disiapkan. Tanam bibit dengan posisi tegak dan padatkan tanah di sekelilingnya."
                )
            )

            val coffeeFertilization = listOf(
                Fertilization(
                    plantsId = 22,
                    type = "Dasar",
                    agePlant = "0 hari (sebelum tanam)",
                    urea = "100 kg/ha",
                    tsp = "200 kg/ha",
                    kcl = "100 kg/ha",
                    organicFertilizer = "Gunakan pupuk kandang atau kompos sebanyak 20 ton/ha untuk meningkatkan kesuburan tanah."
                ),
                Fertilization(
                    plantsId = 22,
                    type = "Susulan",
                    agePlant = "6 bulan",
                    urea = "50 kg/ha",
                    tsp = "100 kg/ha",
                    kcl = "50 kg/ha",
                    organicFertilizer = null
                ),
                Fertilization(
                    plantsId = 22,
                    type = "Susulan",
                    agePlant = "1 tahun",
                    urea = "50 kg/ha",
                    tsp = "100 kg/ha",
                    kcl = "50 kg/ha",
                    organicFertilizer = null
                )
            )

            val coffeeVarieties = listOf(
                Varieties(
                    plantsId = 22,
                    name = "Arabika Typica",
                    superiority = "Kualitas rasa tinggi, cocok untuk daerah dataran tinggi.",
                    weakness = "Rentan terhadap penyakit karat daun."
                ),
                Varieties(
                    plantsId = 22,
                    name = "Robusta BP 308",
                    superiority = "Tahan terhadap penyakit karat daun, produktivitas tinggi.",
                    weakness = "Rasa kurang kompleks dibandingkan Arabika."
                ),
                Varieties(
                    plantsId = 22,
                    name = "Arabika Bourbon",
                    superiority = "Kualitas rasa sangat tinggi, aroma khas.",
                    weakness = "Membutuhkan perawatan intensif."
                )
            )

            val coffeePests = listOf(
                Pests(
                    plantsId = 22,
                    name = "Penggerek Buah Kopi (Hypothenemus hampei)",
                    chemicalControl = "Gunakan insektisida yang mengandung Deltamethrin",
                    biologicalControl = "Penggunaan jamur Beauveria bassiana dapat menginfeksi dan membunuh penggerek buah kopi."
                ),
                Pests(
                    plantsId = 22,
                    name = "Nematoda Akar (Meloidogyne spp.)",
                    chemicalControl = "Gunakan nematicides berbahan aktif seperti oxamyl",
                    biologicalControl = "Penggunaan ekstrak daun neem (mimba) efektif karena mengandung azadirachtin yang bersifat nematisida."
                ),
                Pests(
                    plantsId = 22,
                    name = "Ulat Daun (Lepidoptera)",
                    chemicalControl = "Gunakan insektisida yang mengandung Bacillus thuringiensis",
                    biologicalControl = "Penggunaan larutan cabai dan bawang putih dapat mengusir ulat daun karena kandungan capsaicin dan allicin."
                )
            )

            val coffeeDiseases = listOf(
                Diseases(
                    plantsId = 22,
                    name = "Karat Daun (Hemileia vastatrix)",
                    control = "Gunakan fungisida berbahan aktif seperti copper oxychloride atau propikonazol. Perbaiki sirkulasi udara di sekitar tanaman dengan memangkas cabang yang terlalu rimbun."
                ),
                Diseases(
                    plantsId = 22,
                    name = "Busuk Akar (Fusarium oxysporum)",
                    control = "Lakukan rotasi tanaman, gunakan fungisida berbahan aktif seperti metil tiofanat, dan perbaiki drainase lahan untuk mencegah kelembaban berlebih."
                )
            )

            val coffeeWeeds = Weeds(
                plantsId = 22,
                manualWeeding = "Penyiangan manual menggunakan cangkul atau alat penyiang lain setiap 2-3 minggu sekali.",
                herbicide = "Gunakan herbisida pra-tumbuh seperti pendimethalin atau herbisida pasca-tumbuh seperti glifosat untuk mengendalikan gulma."
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