package com.example.travelapps.Main

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.travelapps.R

class NotificationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        getSupportActionBar()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        getSupportActionBar()?.setTitle("Notifikasi");
    }
}