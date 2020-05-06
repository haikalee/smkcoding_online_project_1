package com.haikal.myapplication

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var inputNama: String = ""
    private var inputUmur: String = ""
    private var inputEmail: String = ""
    private var inputTelp: String = ""
    private var inputAlamat: String = ""
    private var inputJenisKelamin: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSpinner()

        btn_simpan.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(this, R.anim.fade_in)
            btn_simpan.startAnimation(animation)
            ambilInput()
        }
        setActionBarTitle("Biodata")
    }

    fun setActionBarTitle(title: String) {
        if(supportActionBar != null) {
            (supportActionBar as ActionBar).title = title
        }
    }

    private fun ambilInput() {
        inputNama = edt_nama.text.toString()
        inputAlamat = edt_alamat.text.toString()
        inputEmail = edt_email.text.toString()
        inputTelp = edt_telp.text.toString()
        inputUmur = edt_umur.text.toString()
        inputJenisKelamin = spinner_jenis_kelamin.selectedItem.toString()

        when {
            inputNama.isEmpty() -> edt_nama.error = "Tidak boleh kosong"
            inputAlamat.isEmpty() -> edt_alamat.error = "Tidak boleh kosong"
            inputEmail.isEmpty() -> edt_email.error = "Tidak boleh kosong"
            inputTelp.isEmpty() -> edt_telp.error = "Tidak boleh kosong"
            inputUmur.isEmpty() -> edt_umur.error = "Tidak boleh kosong"
            inputJenisKelamin.equals("Pilih Jenis Kelamin", ignoreCase = true) -> Toast.makeText(this, "Jenis kelamin harus dipilih", Toast.LENGTH_SHORT).show()
            else -> {
                profileActivity()
                Toast.makeText(this, "Pindah Ke Profile", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setSpinner() {
        val adapter = ArrayAdapter.createFromResource(this, R.array.jenis_kelamin, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        spinner_jenis_kelamin.adapter = adapter
    }

    private fun profileActivity() {
        val move = Intent(this, Profile::class.java)
        val bundle = Bundle()
        bundle.putString("nama", inputNama)
        bundle.putString("alamat", inputAlamat)
        bundle.putString("email", inputEmail)
        bundle.putString("telp", inputTelp)
        bundle.putString("umur", inputUmur)
        bundle.putString("gender", inputJenisKelamin)
        move.putExtras(bundle)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startActivity(move, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
        }
    }
}
