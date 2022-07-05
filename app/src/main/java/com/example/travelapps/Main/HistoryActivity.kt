package com.example.travelapps.Main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.travelapps.Adapter.ListAdapter
import com.example.travelapps.Data.DB.UserViewModel
import com.example.travelapps.R

class HistoryActivity : AppCompatActivity() {

    private lateinit var mUserViewModel: UserViewModel
    private lateinit var recyclerView: RecyclerView
    val adapter = ListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        //RecyclerView
        recyclerView = findViewById(R.id.rvHistory)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        //UserViewModel
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        mUserViewModel.readAllData.observe(this, Observer { user ->
            adapter.setData(user)
        })
    }
}