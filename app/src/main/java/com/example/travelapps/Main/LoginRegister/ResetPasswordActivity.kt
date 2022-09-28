package com.example.travelapps.Main.LoginRegister

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.example.travelapps.R
import com.google.firebase.auth.FirebaseAuth

class ResetPasswordActivity : AppCompatActivity() {

    private lateinit var etChangePassword : EditText
    private lateinit var btnChangePassword : Button
    private lateinit var progressBarReset: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)

        etChangePassword = findViewById(R.id.etChangePw)
        btnChangePassword = findViewById(R.id.btn_reset)
        progressBarReset = findViewById(R.id.progress_bar_reset)

        progressBarReset.visibility = ProgressBar.GONE

        btnChangePassword.setOnClickListener {
            val email = etChangePassword.text.toString().trim()

            if (email.isEmpty()) {
                etChangePassword.error = "Email Harus Diisi"
                etChangePassword.requestFocus()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                etChangePassword.error = "Email Tidak Valid"
                etChangePassword.requestFocus()
                return@setOnClickListener
            }

            FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener {
                if (it.isSuccessful){
                    Toast.makeText(this, "Silahkan Cek Email Anda", Toast.LENGTH_SHORT).show()
                    progressBarReset.visibility = ProgressBar.GONE
                    Intent(this, LoginActivity::class.java).also {
                        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(it)
                    }
                }else{
                    progressBarReset.visibility = ProgressBar.GONE
                    Toast.makeText(this, "Gagal Mengirim Email", Toast.LENGTH_SHORT).show()
                }
            }
            progressBarReset.visibility = ProgressBar.VISIBLE
        }
    }
}