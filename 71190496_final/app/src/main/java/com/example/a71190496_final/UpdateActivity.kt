package com.example.a71190496_final

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.FirebaseFirestoreKtxRegistrar

class UpdateActivity : AppCompatActivity() {
    var firestore: FirebaseFirestore? = null
    private lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        firestore = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()


        val getJudul = intent.getStringExtra("judul").toString()
        val getPenulis = intent.getStringExtra("penulis").toString()
        val getPenerbit = intent.getStringExtra("penerbit").toString()
        val getTahun = intent.getStringExtra("tahun").toString()
        val getHalaman = intent.getStringExtra("halaman").toString()

        val edtJudul = findViewById<EditText>(R.id.updateJudul)
        val edtPenulis = findViewById<EditText>(R.id.updatePenulis)
        val edtPenerbit = findViewById<EditText>(R.id.updatePenerbit)
        val edtTahun = findViewById<EditText>(R.id.updateTahun)
        val edtHalaman = findViewById<EditText>(R.id.updateHalaman)

        val btnSimpan = findViewById<Button>(R.id.btnSimpan)
        val btnKembali = findViewById<Button>(R.id.btnKembali)

        edtJudul.setText(getJudul)
        edtPenulis.setText(getPenulis)
        edtPenerbit.setText(getPenerbit)
        edtTahun.setText(getTahun)
        edtHalaman.setText(getHalaman)

        btnSimpan.setOnClickListener {
            val updateBuku = Buku(edtJudul.text.toString(),edtPenulis.text.toString(),edtPenerbit.text.toString(),edtTahun.text.toString(),edtHalaman.text.toString())
            firestore?.collection("buku")?.whereEqualTo("judul",getJudul)?.get()!!.addOnSuccessListener {
                for (update in it){
                    firestore?.collection("buku")?.document(update.id)?.set(updateBuku)?.addOnCompleteListener{
                        if(it.isSuccessful){
                            Toast.makeText(this, "Edit Berhasil", Toast.LENGTH_SHORT).show()
                            val i = Intent(this, BukuActivity::class.java)
                            startActivity(i)
                        }
                    }
                }
            }

        }
        btnKembali.setOnClickListener {
            val intentKembali = Intent(this, BukuActivity::class.java)
            startActivity(intentKembali)
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
