package com.example.a71190496_final

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class TambahActivity :AppCompatActivity() {

    var firestore : FirebaseFirestore? = null
    private lateinit var  auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah)

        firestore = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()



        val edtJudul = findViewById<EditText>(R.id.edtJudul)
        val edtPenulis = findViewById<EditText>(R.id.edtPenulis)
        val edtPenerbit = findViewById<EditText>(R.id.edtPenerbit)
        val edtTahun = findViewById<EditText>(R.id.edtTahun)
        val edtHalaman = findViewById<EditText>(R.id.edtHalaman)

        val btnBack = findViewById<Button>(R.id.btnBack)
        val btnTambah = findViewById<Button>(R.id.btnTambah)

        btnTambah.setOnClickListener {
            val buku = Buku(edtJudul.text.toString(), edtPenulis.text.toString(),edtPenerbit.text.toString(), edtTahun.text.toString(),edtHalaman.text.toString())
            edtJudul.setText("")
            edtPenulis.setText("")
            edtPenerbit.setText("")
            edtTahun.setText("")
            edtHalaman.setText("")
            firestore?.collection("buku")?.add(buku)?.addOnSuccessListener {
                Toast.makeText(this, "Tambah Data Berhasil", Toast.LENGTH_SHORT).show()
            }

        }
        btnBack.setOnClickListener {
            val backIntent = Intent(this, BukuActivity::class.java)
            startActivity(backIntent)
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.sidebar, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem) : Boolean {
        when(item.itemId){
            R.id.logout -> {
                auth.signOut()
                val intentKeluar = Intent(this, LoginActivity::class.java)
                startActivity(intentKeluar)
                this.finish()
            }
            R.id.upload -> {
                val intentTambah = Intent(this, TambahActivity::class.java)
                startActivity(intentTambah)
            }
            R.id.home -> {
                val intentHome = Intent(this, BukuActivity::class.java)
                startActivity(intentHome)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}



