package com.example.travelapps.Data.DB

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val firtsLastName: String,
    val keberangakatan: String,
    val tujuan: String,
    val tipeKelas: String,
    val tanggal: String,
    val telpHp: String


)