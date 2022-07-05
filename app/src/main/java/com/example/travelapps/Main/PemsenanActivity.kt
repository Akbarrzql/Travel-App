package com.example.travelapps.Main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.travelapps.Data.DB.User
import com.example.travelapps.Data.DB.UserViewModel
import com.example.travelapps.R
import com.example.travelapps.databinding.ActivityPemsenanBinding
import kotlinx.android.synthetic.main.activity_pemsenan.*
import kotlinx.android.synthetic.main.activity_pemsenan.view.*
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputLayout

class PemsenanActivity : AppCompatActivity() {

    private lateinit var mUserViewModel: UserViewModel
    private lateinit var binding: ActivityPemsenanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pemsenan)
        binding = ActivityPemsenanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        btnCheckout.setOnClickListener {
            insertDataToDatabase()
        }

        val items = listOf("Jakarta", "Bandung", "Semarang", "Surabaya", "Kudus", "Malang")
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, items)
        binding.Keberangkatan.setAdapter(adapter)
        binding.Tujuan.setAdapter(adapter)

        val tipe = listOf("Ekonomi", "Bisnis", "Executive", "Luxury")
        val adapterTipe = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, tipe)
        binding.TipeKelas.setAdapter(adapterTipe)





    }

    private fun insertDataToDatabase() {
        val firstLastName = fistLastName.text.toString()
        val keberangkatan = Keberangkatan.text.toString()
        val tujuan = Tujuan.text.toString()
        val tipeKelas = TipeKelas.text.toString()
        val tanggal = inputTanggal.text.toString()
        val telpHp = inputTelepon.text.toString()

        if (inputCheck(firstLastName, keberangkatan, tujuan, tipeKelas, tanggal, telpHp)) {
            val user = User(0, firstLastName, keberangkatan, tujuan, tipeKelas, tanggal, telpHp)
            mUserViewModel.addUser(user)
            Toast.makeText(this, "Data berhasil ditambahkan", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
        }else{
            Toast.makeText(this, "Data gagal ditambahkan", Toast.LENGTH_SHORT).show()
        }

    }


    private fun inputCheck(firstLastName: String, Keberangkatan: String, tujuan: String, tipeKelas: String, tanggal:String, telpHp:String): Boolean {
        return !(TextUtils.isEmpty(firstLastName)&&TextUtils.isEmpty(Keberangkatan)&&TextUtils.isEmpty(tujuan)&&TextUtils.isEmpty(tipeKelas)&&TextUtils.isEmpty(tanggal)&&telpHp.equals(0))
    }
}