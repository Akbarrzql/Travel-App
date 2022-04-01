package com.example.travelapps.Data

import com.example.travelapps.R

object DestinasiData {
    private val destinasiName = arrayOf("DKI Jakarta",
    "Bandung",
    "Semarang",
    "Surabaya",
    "Yogyakarta",
    "Medan",
    "Padang",
    "Pontianak",
    "Lombok",
    "Bali")

    private val destinasiDetail = arrayOf("Jakarta, atau secara resmi bernama Daerah Khusus Ibukota Jakarta atau Jakarta Raya adalah ibu kota negara dan kota terbesar di Indonesia. Jakarta merupakan satu-satunya kota di Indonesia yang memiliki status setingkat provinsi. Jakarta terletak di pesisir bagian barat laut Pulau Jawa.",
    "Bandung adalah ibu kota provinsi Jawa Barat, Indonesia serta menjadi kota terbesar kelima di Indonesia setelah Jakarta, Surabaya, Medan, dan Makassar. Secara kepadatan kota ini merupakan kota terpadat ke-5 di Indonesia setelah Jakarta, Bekasi, Surabaya, dan Medan dengan kepadatan penduduk mencapai 15.051/km2.",
    "Kota Semarang adalah ibu kota Provinsi Jawa Tengah, Indonesia sekaligus kota metropolitan terbesar kelima di Indonesia setelah Jakarta, Surabaya, Medan, dan Bandung. Sebagai salah satu kota paling berkembang di Pulau Jawa, Kota Semarang mempunyai jumlah penduduk sekitar 1,7 juta jiwa.",
    "Kota Surabaya adalah ibu kota Provinsi Jawa Timur, Indonesia, sekaligus kota metropolitan terbesar di provinsi tersebut. Surabaya merupakan kota terbesar kedua di Indonesia setelah Jakarta. Kota ini terletak 800 km sebelah timur Jakarta, atau 435 km sebelah barat laut Denpasar, Bali.",
    "Kota Yogyakarta adalah ibu kota dan pusat pemerintahan Daerah Istimewa Yogyakarta, Indonesia. Kota ini adalah kota besar yang mempertahankan konsep tradisional dan budaya jawa. Kota Yogyakarta adalah kediaman bagi Sultan Hamengkubuwana dan Adipati Paku Alam.",
    "Medan adalah ibu kota provinsi Sumatra Utara, Indonesia. Kota ini merupakan kota terbesar ketiga di Indonesia setelah DKI Jakarta dan Surabaya serta kota terbesar di luar pulau Jawa.",
    "Kota Padang adalah kota terbesar di pantai barat Pulau Sumatra dan ibu kota provinsi Sumatra Barat, Indonesia. Kota ini merupakan pintu gerbang barat Indonesia dari Samudra Hindia.",
    "Pontianak adalah ibu kota provinsi Kalimantan Barat, Indonesia. Kota ini dikenal sebagai Kota Khatulistiwa karena dilalui garis khatulistiwa. Di utara Kota Pontianak, tepatnya Siantan, terdapat Tugu Khatulistiwa yang dibangun pada tempat yang dilalui garis khatulistiwa.",
    "Pulau Lombok adalah sebuah pulau di kepulauan Sunda Kecil atau Nusa Tenggara yang terpisahkan oleh Selat Lombok dari Bali di sebelah barat dan Selat Alas di sebelah timur dari Sumbawa. Pulau ini kurang lebih berbentuk bulat dengan semacam \"ekor\" di sisi barat daya yang panjangnya kurang lebih 70 km.",
    "Bali adalah sebuah provinsi di Indonesia yang ibu kotanya bernama Denpasar. Bali juga merupakan salah satu pulau di Kepulauan Nusa Tenggara.")

    private val iconDestinasi = intArrayOf(R.drawable.jakarta1,
    R.drawable.bandung2,
    R.drawable.semarang,
    R.drawable.surabaya,
    R.drawable.yogyakarta,
    R.drawable.medan2,
    R.drawable.padang,
    R.drawable.pontianak,
    R.drawable.lombok,
    R.drawable.bali,
    )

val listdestinasi:ArrayList<Destinasi>
get() {
    val list = arrayListOf<Destinasi>()
for (position in destinasiName.indices){
    val  destinasi = Destinasi()
    destinasi.name = destinasiName[position]
    destinasi.detail = destinasiDetail[position]
    destinasi.photo = iconDestinasi[position]
    list.add(destinasi)
}
    return list
}
}