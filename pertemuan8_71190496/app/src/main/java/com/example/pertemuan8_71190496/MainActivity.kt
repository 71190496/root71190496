package com.example.pertemuan8_71190496

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.toolbar_default))
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_default, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.like -> {
                val i = Intent(this, HomeActivity::class.java)
                startActivity(i)
            }
            R.id.message -> {
                val i = Intent(this, SecondActivity::class.java)
                startActivity(i)
            }
            R.id.notif -> {
                val i = Intent(this, ThirdActivity::class.java)
                startActivity(i)
            }
            else ->{
                super.onOptionsItemSelected(item)
            }
        }
        return true
    }


}