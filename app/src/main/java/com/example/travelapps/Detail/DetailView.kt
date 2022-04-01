package com.example.travelapps.Detail

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.travelapps.R

class DetailView : AppCompatActivity() {
    companion object{
        const val photo = "photo"
        const val nama = "name"
        const val detinasidetail = "materi"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_view)

        var imgPhoto: ImageView = findViewById(R.id.img_item_photo)
        var tvNama: TextView = findViewById(R.id.tv_item_name)
        var tvmateridetail: TextView = findViewById(R.id.tv_item_detail)

        val name = intent.getStringExtra(nama)
        val materi = intent.getStringExtra(detinasidetail)
        val photo = intent.getIntExtra(photo,0)

        imgPhoto.setImageResource(photo)
        tvNama.text = name
        tvmateridetail.text = materi
    }

}