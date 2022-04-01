package com.example.travelapps.Detail

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.travelapps.R

class DetailNews: AppCompatActivity() {
    companion object{
        const val photo = "photo"
        const val nama = "name"
        const val newsdetail = "news"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_list)

        val imgPhoto: ImageView = findViewById(R.id.img_item_photo1)
        val tvNama: TextView = findViewById(R.id.tv_item_name1)
        val tvnewsdetail: TextView = findViewById(R.id.tv_item_detail1)

        val name = intent.getStringExtra(nama)
        val news = intent.getStringExtra(newsdetail)
        val photo = intent.getIntExtra(photo,0)

        imgPhoto.setImageResource(photo)
        tvNama.text = name
        tvnewsdetail.text = news
    }

}