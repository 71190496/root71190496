package com.example.pertemuan7_71190496

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listContact = arrayListOf<Kontak>()
        listContact.add(Kontak("John", R.mipmap.ic_launcher, "08213948394", "john.ganteng@gmail.com"))
        listContact.add(Kontak("Julius",R.mipmap.ic_launcher, "0813478374", "julius.keren@gmail.com"))
        listContact.add(Kontak("Thomas",R.mipmap.ic_launcher,  "0856323242", "thomas.shelby3@gmail.com"))


        val rvContact = findViewById<RecyclerView>(R.id.rvKontak)
        rvContact.layoutManager = LinearLayoutManager(this)
        val contactAdapter = KontakAdapter(listContact)
        rvContact.adapter = contactAdapter
    }
}
