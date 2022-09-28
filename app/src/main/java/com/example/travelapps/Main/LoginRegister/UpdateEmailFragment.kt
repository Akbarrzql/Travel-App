package com.example.travelapps.Main.LoginRegister

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.findNavController
import com.example.travelapps.R
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_update_email.*

class   UpdateEmailFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var layoutPassword : LinearLayout
    private lateinit var layoutEmail : LinearLayout
    private lateinit var buttonAuth : Button
    private lateinit var buttonUpdate : Button
    private lateinit var progressBar: View
    private lateinit var progressBarEmail : View
    private lateinit var etEmail : EditText


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_update_email, container, false)
        auth = FirebaseAuth.getInstance()

        layoutPassword = view.findViewById(R.id.layoutPassword)
        layoutEmail = view.findViewById(R.id.layoutEmail)
        buttonAuth = view.findViewById(R.id.btnAuth)
        buttonUpdate = view.findViewById(R.id.btnUpdate)
        progressBar = view.findViewById(R.id.progress_bar_update)
        progressBarEmail = view.findViewById(R.id.progress_bar_email)
        etEmail = view.findViewById(R.id.etEmail)
        progressBar.visibility = ProgressBar.GONE

        val user = auth.currentUser
        layoutPassword.visibility = View.VISIBLE
        layoutEmail.visibility = View.GONE

        buttonAuth.setOnClickListener {
            val password = etPassword.text.toString().trim()

            if (password.isEmpty()){
                etPassword.error = "Password Harus Diisi"
                etPassword.requestFocus()
                return@setOnClickListener
            }

            user?.let {
                val userCredential = EmailAuthProvider.getCredential(it.email!!, password)
                it.reauthenticate(userCredential).addOnCompleteListener {
                        if (it.isSuccessful){
                            layoutPassword.visibility = View.GONE
                            layoutEmail.visibility = View.VISIBLE
                            progressBarEmail.visibility = ProgressBar.GONE
                        } else if (it.exception is FirebaseAuthInvalidCredentialsException) {
                            progressBarEmail.visibility = ProgressBar.GONE
                            etPassword.error = "Password Salah"
                            etPassword.requestFocus()
                        }else{
                            progressBarEmail.visibility = ProgressBar.GONE
                            Toast.makeText(context, "Gagal", Toast.LENGTH_SHORT).show()
                        }
                    }
                progressBarEmail.visibility = ProgressBar.VISIBLE
            }
        }

        buttonUpdate.setOnClickListener { view ->
            val email = etEmail.text.toString().trim()

            if (email.isEmpty()){
                etEmail.error = "Email Harus Diisi"
                etEmail.requestFocus()
                return@setOnClickListener
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                etEmail.error = "Masukkan email yang valid"
                etEmail.requestFocus()
                return@setOnClickListener
            }

            user?.let {
                user.updateEmail(email).addOnCompleteListener {
                    if (it.isSuccessful){
                        val actionEmailUpdated = UpdateEmailFragmentDirections.actionEmailUpdated()
                        view.findNavController().navigate(actionEmailUpdated)
                        Toast.makeText(context, "Email berhasil diubah", Toast.LENGTH_SHORT).show()
                        activity?.finish()
                    }else{
                        progressBar.visibility = ProgressBar.GONE
                        Toast.makeText(context, "Email gagal diubah", Toast.LENGTH_SHORT).show()
                    }
                }
                progressBar.visibility = ProgressBar.VISIBLE
            }
        }

        return view
    }

}