package com.haikal.myapplication

import android.app.ActionBar
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_edit_profile.*

class EditProfile : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        setSpinner()

        val bundle = intent.extras
        val nama = bundle?.getString("nama")
        val gender = bundle?.getString("gender")
        val email = bundle?.getString("email")
        val telp = bundle?.getString("telp")
        val umur = bundle?.getString("umur")
        val alamat = bundle?.getString("alamat")
        edt_edit_nama.setText(nama)
        edt_edit_email.setText(email)
        edt_edit_telp.setText(telp)
        edt_edit_umur.setText(umur)
        edt_edit_alamat.setText(alamat)
        btn_edit_profile.setOnClickListener { saveData() }
    }

    private fun saveData() {
        val namaEdit = edt_edit_nama.text.toString()
        val genderEdit = spinner_edit_gender.selectedItem.toString()
        val emailEdit = edt_edit_email.text.toString()
        val telpEdit = edt_edit_telp.text.toString()
        val umurEdit = edt_edit_umur.text.toString()
        val alamatEdit = edt_edit_alamat.text.toString()
        if(!namaEdit.isEmpty() && !genderEdit.equals("Pilih jenis kelamin") && !emailEdit.isEmpty() && !telpEdit.isEmpty() && !umurEdit.isEmpty() && !alamatEdit.isEmpty()) {
            val result = Intent()
            result.putExtra("nama", namaEdit)
            result.putExtra("email", emailEdit)
            result.putExtra("gender", genderEdit)
            result.putExtra("telp", telpEdit)
            result.putExtra("alamat", alamatEdit)
            result.putExtra("umur", umurEdit)
            setResult(Activity.RESULT_OK, result)
        } else {
            setResult(Activity.RESULT_CANCELED)
        }
        finish()
    }

    private fun setSpinner() {
        val adapter = ArrayAdapter.createFromResource(this, R.array.jenis_kelamin, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        spinner_edit_gender.adapter = adapter
    }


}
