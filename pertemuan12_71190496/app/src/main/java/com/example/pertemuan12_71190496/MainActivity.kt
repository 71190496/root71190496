package com.example.pertemuan12_71190496

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val edtKota = findViewById<EditText>(R.id.edtKota)
        val btnCek = findViewById<Button>(R.id.btnCek)

        btnCek.setOnClickListener {
            cekCuaca(edtKota.text.toString())
        }
    }
    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    fun cekCuaca(kota: String){
        val url = "https://api.openweathermap.org/data/2.5/forecast?q=${kota}&appid=e282afe349dccf2a6da2b29c8f2dd46d&units=metric"
        val queue = Volley.newRequestQueue(this)
        val tvHasil = findViewById<TextView>(R.id.tvHasil)
        val request = StringRequest(
            Request.Method.GET,
            url,
            { response ->
                val weatherjson = JSONObject(response).getJSONArray("list")
                var str = ""
                val cuacasekarang = weatherjson.getJSONObject(1).getJSONArray("weather").getJSONObject(0).getString("description")
                val cuacabesok = weatherjson.getJSONObject(9).getJSONArray("weather").getJSONObject(0).getString("description")
                val cuacalusa = weatherjson.getJSONObject(17).getJSONArray("weather").getJSONObject(0).getString("description")
                str += "Cuaca di $kota Sekarang : $cuacasekarang\nCuaca di $kota Besok : $cuacabesok\nCuaca di $kota Lusa : $cuacalusa"
                tvHasil.text = str
            },
            {
                tvHasil.text = "Tidak Ada Nama Kota $kota"
            }
        )
        queue.add(request)
    }
}