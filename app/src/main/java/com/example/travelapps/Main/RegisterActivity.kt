package com.example.travelapps.Main

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.travelapps.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.fragment_fourth.*

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var progressBar: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        supportActionBar?.hide()

        auth = FirebaseAuth.getInstance()
        progressBar = findViewById(R.id.progress_bar)
        progressBar.visibility = ProgressBar.GONE


        btn_daftar.setOnClickListener {
            val nama = et_nama.text.toString()
            val email = et_email_daftar.text.toString()
            val password = et_password_daftar.text.toString()

            if (email.isEmpty() || password.isEmpty() || nama.isEmpty()) {
                et_nama.error = "nama tidak boleh kosong"
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
            //get email from edit text

            registerUser(email, password, nama,)
        }
    }

    private fun registerUser(email: String, password: String, nama: String,) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    user?.sendEmailVerification()
                        ?.addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Intent(this, MainActivity::class.java).also {
                                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//                                    //bundel untuk mengirim data ke activity lain
//                                    val fragment = Fragment()
//                                    val bundle = Bundle()
//                                    bundle.putString("nama", nama)
//                                    fragment.arguments = bundle
//                                    it.putExtras(bundle)
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