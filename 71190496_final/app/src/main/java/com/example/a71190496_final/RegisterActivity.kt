package com.example.a71190496_final

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.a71190496_final.databinding.ActivityRegisterBinding

import com.google.firebase.auth.FirebaseAuth

class RegisterActivity:AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding
    lateinit var auth: FirebaseAuth;


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.btnSave.setOnClickListener {
            val email = binding.edtRegEmail.text.toString()
            val password = binding.edtRegPassword.text.toString()

            if (email.isEmpty()) {
                binding.edtRegEmail.error = "Email Harus Diisi"
                binding.edtRegEmail.requestFocus()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.edtRegEmail.error = "Email Tidak Valid"
                binding.edtRegEmail.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                binding.edtRegPassword.error = "Password Harus Diisi"
                binding.edtRegPassword.requestFocus()
                return@setOnClickListener
            }

            if (password.length < 6){
                binding.edtRegPassword.error = "Password Minimal 6 Karakter"
                binding.edtRegPassword.requestFocus()
                return@setOnClickListener
            }

            RegisterFirebase(email, password)


        }

        binding.tvBack.setOnClickListener {
            val i = Intent(this, LoginActivity::class.java)
            startActivity(i)
        }
    }

    private fun RegisterFirebase(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){
                if (it.isSuccessful){
                    Toast.makeText(this, "Register Berhasil", Toast.LENGTH_SHORT).show()
                    val i = Intent(this, LoginActivity::class.java)
                    startActivity(i)
                }else{
                    Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }

    }
}


