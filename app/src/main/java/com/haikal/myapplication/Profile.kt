package com.haikal.myapplication

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_profile.*

class Profile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        showProfile()

        btn_dial.setOnClickListener {
            val no = "08816253226"
            val moveDial = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$no"))
            startActivity(moveDial)
        }
    }

    private fun showProfile() {
        val bundle = intent.extras
        val nama = bundle?.getString("nama")
        val gender = bundle?.getString("gender")
        val alamat = bundle?.getString("alamat")
        val email = bundle?.getString("email")
        val telp = bundle?.getString("telp")
        val umur = bundle?.getString("umur")

        tv_profile_nama.text = nama
        tv_profile_alamat.text = alamat
        tv_profile_gender.text = gender
        tv_profile_email.text = email
        tv_profile_telp.text = telp
        tv_profile_umur.text = umur
    }
}
