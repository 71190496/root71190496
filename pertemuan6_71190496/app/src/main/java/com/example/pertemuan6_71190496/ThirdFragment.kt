package com.example.pertemuan6_71190496

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment

class ThirdFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_third, container, false)
        val btnThird = v.findViewById<Button>(R.id.btnThird)
        btnThird.setOnClickListener {
            val i = Intent(context, MainActivity::class.java)
            context?.startActivity(i)
        }
        return v
    }
}