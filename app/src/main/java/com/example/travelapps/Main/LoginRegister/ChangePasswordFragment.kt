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
import kotlinx.android.synthetic.main.fragment_change_password.*
import kotlinx.android.synthetic.main.fragment_update_email.*

class ChangePasswordFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var btnAuth: Button
    private lateinit var btnUpdate: Button
    private lateinit var etPassword : EditText
    private lateinit var etNewPasswordChange : EditText
    private lateinit var etConfirmPasswordChange : EditText
    private lateinit var layoutPassword: LinearLayout
    private lateinit var layoutNewPassword: LinearLayout
    private lateinit var progressBarNewPassword: View
    private lateinit var progressBarPassword : View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_change_password, container, false)
        auth = FirebaseAuth.getInstance()

        btnAuth = view.findViewById(R.id.btnAuth)
        btnUpdate = view.findViewById(R.id.btnUpdate)
        etPassword = view.findViewById(R.id.etPassword)
        etNewPasswordChange = view.findViewById(R.id.etNewPassword)
        etConfirmPasswordChange = view.findViewById(R.id.etNewPasswordConfirm)
        layoutPassword = view.findViewById(R.id.layoutPassword)
        layoutNewPassword = view.findViewById(R.id.layoutChangePassword)
        progressBarNewPassword = view.findViewById(R.id.progress_bar_change_password)
        progressBarPassword = view.findViewById(R.id.progress_bar_password)

        val  user = auth.currentUser
        layoutPassword.visibility = View.VISIBLE
        layoutNewPassword.visibility = View.GONE

        btnAuth.setOnClickListener {
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
                        layoutNewPassword.visibility = View.VISIBLE
                        progressBarPassword.visibility = ProgressBar.GONE
                    } else if (it.exception is FirebaseAuthInvalidCredentialsException) {
                        progressBarPassword.visibility = ProgressBar.GONE
                        etPassword.error = "Password Salah"
                        etPassword.requestFocus()
                    }else{
                        progress_bar_change_password.visibility = ProgressBar.GONE
                        Toast.makeText(context, "Gagal", Toast.LENGTH_SHORT).show()
                    }
                }
                progressBarPassword.visibility = ProgressBar.VISIBLE
            }
        }

        btnUpdate.setOnClickListener { view ->
            val password = etNewPasswordChange.text.toString().trim()
            val confirmPassword = etConfirmPasswordChange.text.toString().trim()

            if (password.isEmpty() || password.length < 8){
                etNewPasswordChange.error = "Password Harus Diisi dan Minimal 8 Karakter"
                etNewPasswordChange.requestFocus()
                return@setOnClickListener
            }

            if (password != confirmPassword){
                etConfirmPasswordChange.error = "Password Tidak Sama"
                etConfirmPasswordChange.requestFocus()
                return@setOnClickListener
            }

            user?.let {
                user.updatePassword(password).addOnCompleteListener {
                    if (it.isSuccessful){
                        val actionPasswordUpdated = ChangePasswordFragmentDirections.actionChangedPassword()
                        view.findNavController().navigate(actionPasswordUpdated)
                        Toast.makeText(context, "Password berhasil diubah", Toast.LENGTH_SHORT).show()
                        activity?.finish()
                    }else{
                        progressBarNewPassword.visibility = ProgressBar.GONE
                        Toast.makeText(context, "Password gagal diubah", Toast.LENGTH_SHORT).show()
                    }
                }
                progressBarNewPassword.visibility = ProgressBar.VISIBLE
            }
        }


        return view
    }

}