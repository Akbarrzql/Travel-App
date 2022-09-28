package com.example.travelapps.Fragment

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.travelapps.Main.LoginRegister.LoginActivity
import com.example.travelapps.R
import com.example.travelapps.databinding.FragmentFourthBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_fourth.*
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.*


class FourthFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var storage: FirebaseStorage
    private lateinit var btn_logout: Button
    private lateinit var btn_update: Button
    private lateinit var tvemail: TextView
    private lateinit var etnamePerson: EditText
    private lateinit var etphonePerson: EditText
    private lateinit var etemailPerson: EditText
    private lateinit var imageUri: Uri
    private lateinit var imageProfile: ImageView
    private lateinit var verified: ImageView
    private lateinit var unverified: ImageView
    private lateinit var changePassword: TextView
    private lateinit var binding: FragmentFourthBinding

    companion object{
        const val REQUEST_CAMERA = 100
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_fourth, container, false)
        binding = FragmentFourthBinding.inflate(layoutInflater)


        auth = FirebaseAuth.getInstance()
        storage = FirebaseStorage.getInstance()
        btn_logout = view.findViewById(R.id.btn_keluar)
        btn_update = view.findViewById(R.id.btnUpdate)
        tvemail = view.findViewById(R.id.emailtv)
        etnamePerson = view.findViewById(R.id.etName)
        etphonePerson = view.findViewById(R.id.etPhone)
        etemailPerson = view.findViewById(R.id.etEmailProfile)
        imageProfile = view.findViewById(R.id.ivProfile)
        verified = view.findViewById(R.id.icVerified)
        unverified = view.findViewById(R.id.icUnverified)
        tvemail.text = auth.currentUser?.email.toString()
        changePassword = view.findViewById(R.id.ChangePassword)

        val user = auth.currentUser

        if (user != null){
            if(user.photoUrl != null){
                Picasso.get().load(user.photoUrl).into(imageProfile)
//                imageProfile.setImageURI(user.photoUrl)
            }else{
                Picasso.get().load(R.drawable.person1).into(imageProfile)
            }

            etnamePerson.setText(user.displayName)
            etemailPerson.setText(user.email)

            if (user.isEmailVerified){
                verified.visibility = View.VISIBLE
            }else{
                unverified.visibility = View.VISIBLE
            }

            if (user.phoneNumber.isNullOrEmpty()){
                etphonePerson.setText("Masukan Nomor Handphone")
            }else{
                etphonePerson.setText(user.phoneNumber)
            }

        }

        btn_update.setOnClickListener {
            val image = when{
                ::imageUri.isInitialized -> imageUri
                user?.photoUrl == null -> Uri.parse(R.drawable.person1.toString())
                else -> user.photoUrl

            }

            val name = etnamePerson.text.toString().trim()

            if (name.isEmpty()){
                etnamePerson.error = "Nama tidak boleh kosong"
                etnamePerson.requestFocus()
                return@setOnClickListener
            }

            UserProfileChangeRequest.Builder()
                .setDisplayName(name)
                .setPhotoUri(image)
                .build().also {
                    user?.updateProfile(it)?.addOnCompleteListener {
                        if(it.isSuccessful){
                            Toast.makeText(activity, "Update Berhasil", Toast.LENGTH_SHORT).show()
                        }else{
                            Toast.makeText(activity, "Update Gagal", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
        }
        unverified.setOnClickListener {
            user?.sendEmailVerification()?.addOnCompleteListener {
                if (it.isSuccessful){
                    Toast.makeText(activity, "Email verifikasi telah dikirim", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(activity, "Gagal mengirimkan email verifikasi", Toast.LENGTH_SHORT).show()
                }
            }
        }
        imageProfile.setOnClickListener {
            selectImage()
        }
        btn_logout.setOnClickListener {
            auth.signOut()
            Intent(activity, LoginActivity::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(it)
            }
        }

        etemailPerson.setOnClickListener {
            val actionUpdateEmail = FourthFragmentDirections.actionUpdateEmail()
            Navigation.findNavController(it).navigate(actionUpdateEmail)
        }

        changePassword.setOnClickListener {
            val  actionChangePassword = FourthFragmentDirections.actionChangePassword()
            Navigation.findNavController(it).navigate(actionChangePassword)
        }

        return view
    }

    private fun selectImage() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, REQUEST_CAMERA)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CAMERA && resultCode == RESULT_OK) {
            imageUri = data?.data!!
            imageProfile.setImageURI(imageUri)
        }
    }



    private fun uploadImage(imageBitmap: Bitmap) {
        val baos = ByteArrayOutputStream()
        val formater = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault())
        val now =Date()
        val fileName = formater.format(now)
        val ref = FirebaseStorage.getInstance().reference.child("img/${fileName}")

        imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val image = baos.toByteArray()

        ref.putBytes(image).addOnCompleteListener {
            if (it.isSuccessful){
                ref.downloadUrl.addOnCompleteListener {
                    it.result?.let {
                        imageUri = it
                        imageProfile.setImageBitmap(imageBitmap)
                    }
                }
            }
        }
    }


}