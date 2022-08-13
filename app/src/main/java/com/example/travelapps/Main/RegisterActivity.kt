package com.example.travelapps.Main

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.travelapps.Fragment.FourthFragment
import com.example.travelapps.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        supportActionBar?.hide()

        auth = FirebaseAuth.getInstance()
        progressBar = findViewById(R.id.progress_bar)
        progressBar.visibility = ProgressBar.GONE


        btn_daftar.setOnClickListener {
            val namaDepan = et_name_daftar.text.toString()
            val namaBelakang = et_name_belakang_daftar.text.toString()
            val email = et_email_daftar.text.toString()
            val password = et_password_daftar.text.toString()

            if (email.isEmpty() || password.isEmpty() || namaDepan.isEmpty() || namaBelakang.isEmpty()) {
                et_name_daftar.error = "nama tidak boleh kosong"
                et_name_belakang_daftar.error = "nama tidak boleh kosong"
                et_email_daftar.error = "Email tidak boleh kosong"
                et_password_daftar.error = "Password tidak boleh kosong"

                et_email_daftar.requestFocus()
                return@setOnClickListener
            }

            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                et_email_daftar.error = "Email tidak valid"
                et_email_daftar.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty() || password.length < 8) {
                et_password_daftar.error = "Password harus lebih dari 8 karakter"
                et_password_daftar.requestFocus()
                return@setOnClickListener
            }
            progressBar.visibility = ProgressBar.VISIBLE
            registerUser(email, password, namaDepan, namaBelakang)
        }
    }

    private fun registerUser(email: String, password: String, namaDepan: String, namaBelakang: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    user?.sendEmailVerification()
                        ?.addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Intent(this, MainActivity::class.java).also {
                                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                    startActivity(it)
                                }
                            }
                        }
                } else {
                    Toast.makeText(
                        baseContext, "Autenfikaasi Gagal",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    fun onClicklupaPassword(view: View) {}
    fun onClickMasuk(view: View) {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}