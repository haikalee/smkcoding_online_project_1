package com.haikal.myapplication

import android.annotation.SuppressLint
import android.app.ActionBar
import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_profile.*

class Profile : AppCompatActivity() {

    companion object{
        const val REQUEST_CODE = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        showProfile()

        if(supportActionBar != null) {
            (supportActionBar as ActionBar).title = "Profile"
        }
        btn_dial.setOnClickListener {
            val no = "08816253226"
            val moveDial = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$no"))
            startActivity(moveDial)
        }

        btn_about.setOnClickListener {
            val moveAbout = Intent(this@Profile, About::class.java)
            startActivity(moveAbout)
        }

        btn_edt_nama.setOnClickListener {
            val moveEdit = Intent(this@Profile, EditProfile::class.java)
            val nama = tv_profile_nama.text.toString()
            val gender = tv_profile_gender.text.toString()
            val email = tv_profile_email.text.toString()
            val telp = tv_profile_telp.text.toString()
            val umur = tv_profile_umur.text.toString()
            val alamat = tv_profile_alamat.text.toString()
            moveEdit.putExtra("nama", nama)
            moveEdit.putExtra("gender", gender)
            moveEdit.putExtra("email", email)
            moveEdit.putExtra("telp", telp)
            moveEdit.putExtra("umur", umur)
            moveEdit.putExtra("alamat", alamat)
            startActivityForResult(moveEdit, REQUEST_CODE)
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_CODE){
            if(resultCode == Activity.RESULT_OK) {
                val resultNama = data?.getStringExtra("nama")
                val resultGender = data?.getStringExtra("gender")
                val resultAlamat = data?.getStringExtra("alamat")
                val resultEmail = data?.getStringExtra("email")
                val resultTelp = data?.getStringExtra("telp")
                val resultUmur = data?.getStringExtra("umur")
                tv_profile_nama.text = resultNama
                tv_profile_gender.text = resultGender
                tv_profile_alamat.text = resultAlamat
                tv_profile_email.text = resultEmail
                tv_profile_telp.text = resultTelp
                tv_profile_umur.text = resultUmur
            } else {
                Toast.makeText(this@Profile, "Gagal Edit", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
