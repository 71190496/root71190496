package com.example.a71190496_final

import android.app.ProgressDialog
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a71190496_final.databinding.ActivityBukuBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore

class BukuActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var recyclerView: RecyclerView
    lateinit var binding: ActivityBukuBinding
    lateinit var toggle: ActionBarDrawerToggle


    var firestore: FirebaseFirestore? = null
    var listBuku = arrayListOf<Buku>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buku)
        binding = ActivityBukuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            toggle = ActionBarDrawerToggle(
                this@BukuActivity,
                drawerLayout,
                R.string.open,
                R.string.close
            )
            drawerLayout.addDrawerListener(toggle)
            toggle.syncState()

            supportActionBar?.setDisplayHomeAsUpEnabled(true)



            firestore = FirebaseFirestore.getInstance()

            auth = FirebaseAuth.getInstance()
            val currentUser = auth.currentUser

            val btnCari = findViewById<ImageButton>(R.id.btnCari)
            val edtCari = findViewById<EditText>(R.id.edtCari)
            val rcyBuku = findViewById<RecyclerView>(R.id.rcyBuku)
            val loading = ProgressDialog(this@BukuActivity)
            loading.setMessage("Loading Data...")
            loading.show()
            firestore?.collection("buku")?.get()?.addOnSuccessListener { docs ->
                var hasil = ""
                for (doc in docs) {
                    hasil += "${doc["judul"]}"
                    val bukuAdd = Buku(
                        "${doc["judul"]}",
                        "${doc["penulis"]}",
                        "${doc["penerbit"]}",
                        "${doc["tahun"]}",
                        "${doc["halaman"]}"
                    )
                    listBuku.add(bukuAdd)
                }
                loading.dismiss()
            }
            Handler().postDelayed({
                rcyBuku.layoutManager = LinearLayoutManager(this@BukuActivity)
                val adapter = BukuAdapter(listBuku, this@BukuActivity)
                rcyBuku.adapter = adapter
            }, 1000)

            btnCari.setOnClickListener {
                var pencarian = edtCari.text.toString()
                if (pencarian.isEmpty()) {
                    Toast.makeText(this@BukuActivity, "Pencarian Kosong", Toast.LENGTH_SHORT).show()
                    Handler().postDelayed({
                        rcyBuku.layoutManager = LinearLayoutManager(this@BukuActivity)
                        val adapter = BukuAdapter(listBuku, this@BukuActivity)
                        rcyBuku.adapter = adapter
                    }, 1000)
                } else if (!pencarian.isEmpty()) {
                    loading.setMessage("Mencari...")
                    loading.show()
                    listBuku.clear()
                    firestore?.collection("buku")?.get()?.addOnSuccessListener { docs ->
                        for (cari in docs) {
                            var bool = true
                            val bukuCari = Buku(
                                "${cari["judul"]}",
                                "${cari["penulis"]}",
                                "${cari["penerbit"]}",
                                "${cari["tahun"]}",
                                "${cari["halaman"]}"
                            )
                            if (pencarian.equals("${cari["judul"]}") && bool) {
                                Toast.makeText(this@BukuActivity, "Pencarian Ditemukan", Toast.LENGTH_SHORT)
                                    .show()
                                bool = false
                                listBuku.add(bukuCari)
                            }
                            if (pencarian.equals("${cari["penulis"]}") && bool) {
                                Toast.makeText(this@BukuActivity, "Pencarian Ditemukan", Toast.LENGTH_SHORT)
                                    .show()
                                bool = false
                                listBuku.add(bukuCari)
                            }
                            if (pencarian.equals("${cari["penerbit"]}") && bool) {
                                Toast.makeText(this@BukuActivity, "Pencarian Ditemukan", Toast.LENGTH_SHORT)
                                    .show()
                                bool = false
                                listBuku.add(bukuCari)
                            }
                            if (pencarian.equals("${cari["tahun"]}") && bool) {
                                Toast.makeText(this@BukuActivity, "Pencarian Ditemukan", Toast.LENGTH_SHORT)
                                    .show()
                                bool = false
                                listBuku.add(bukuCari)
                            }
                            if (pencarian.equals("${cari["halaman"]}") && bool) {
                                Toast.makeText(this@BukuActivity, "Pencarian Ditemukan", Toast.LENGTH_SHORT)
                                    .show()
                                bool = false
                                listBuku.add(bukuCari)
                            }
                        }
                    }
                    loading.dismiss()



                    Handler().postDelayed({
                        rcyBuku.layoutManager = LinearLayoutManager(this@BukuActivity)
                        val adapter = BukuAdapter(listBuku, this@BukuActivity)
                        rcyBuku.adapter = adapter
                    }, 1000)
                }

            }
            navView.setNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.home -> {
                        val intentHome = Intent(this@BukuActivity, BukuActivity::class.java)
                        startActivity(intentHome)
                        Toast.makeText(this@BukuActivity, "Home", Toast.LENGTH_SHORT).show()
                    }
                    R.id.upload -> {
                        val intentTambah = Intent(this@BukuActivity, TambahActivity::class.java)
                        startActivity(intentTambah)
                        Toast.makeText(this@BukuActivity, "Tambah Data", Toast.LENGTH_SHORT).show()
                    }
                    R.id.logout -> {
                        val intentKeluar = Intent(this@BukuActivity, LoginActivity::class.java)
                        startActivity(intentKeluar)
                        Toast.makeText(this@BukuActivity, "Sampai Jumpa", Toast.LENGTH_SHORT).show()
                        auth.signOut()

                    }
                }
                true
            }

        }


    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            true
        }
        return super.onOptionsItemSelected(item)
    }

}


