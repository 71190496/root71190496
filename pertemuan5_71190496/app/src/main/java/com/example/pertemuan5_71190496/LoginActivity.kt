package com.example.pertemuan5_71190496

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val txUsername : EditText = findViewById(R.id.txUsername)
        val txPassword = findViewById<EditText>(R.id.txPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        btnLogin.setOnClickListener {
            login(txUsername.text.toString(), txPassword.text.toString())
        }
    }
    fun login(username: String, password: String){
        if (password.equals("1234")){
            val i: Intent = Intent(this,MainActivity::class.java)
            i.putExtra("user", username)
            startActivity(i)
        }else{
            toast("Password anda salah")
        }
    }
    fun toast(message: String){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}