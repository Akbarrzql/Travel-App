package com.example.travelapps.Main

import android.graphics.Color.TRANSPARENT
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.travelapps.R

class InboxActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inbox)

        getSupportActionBar()?.setBackgroundDrawable(ColorDrawable(TRANSPARENT));
        getSupportActionBar()?.setTitle("Inbox");
    }
}