package com.example.travelapps.Main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.example.travelapps.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //hide actionbar
        supportActionBar?.hide()

        auth = FirebaseAuth.getInstance()
        progressBar = findViewById(R.id.progress_bar_login)
        progressBar.visibility = ProgressBar.GONE

        btn_masuk.setOnClickListener {
            val emailMasuk = et_email.text.toString()
            val passwordMasuk = et_password.text.toString()

            if (emailMasuk.isEmpty()){
                et_email.error = "Email tidak boleh kosong"
                et_email.requestFocus()
                return@setOnClickListener
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(emailMasuk).matches()){
                et_email.error = "Masukkan email yang valid"
                et_email.requestFocus()
                return@setOnClickListener
            }
            if (passwordMasuk.isEmpty() || passwordMasuk.length < 8){
                et_password.error = "Password tidak boleh kosong dan minimal 8 karakter"
                et_password.requestFocus()
                return@setOnClickListener
            }

            loginUser(emailMasuk, passwordMasuk)
            progressBar.visibility = ProgressBar.VISIBLE
        }
    }

    private fun loginUser(emailMasuk: String, passwordMasuk: String) {
        auth.signInWithEmailAndPassword(emailMasuk, passwordMasuk)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Intent(this, MainActivity::class.java).also {
                        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(it)
                    }
                } else {
                    Toast.makeText(this, "${task.exception}", Toast.LENGTH_SHORT).show()
                }
            }
    }

    override fun onStart() {
        super.onStart()
        if (auth.currentUser != null){
            Intent(this, MainActivity::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(it)
            }
        }
    }

    fun onClicklupaPassword(view: View) {
    }

    fun onClikDaftar(view: View) {
        startActivity(Intent(this, RegisterActivity::class.java))
        finish()
    }
}